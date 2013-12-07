package anchor89.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import anchor89.entry.DeployConfig;
import anchor89.entry.Server;
import anchor89.entry.Sql;
import anchor89.entry.Task;
import anchor89.util.C;
import anchor89.util.DbPool;

/**
 * Execute given list of sqls on one database in one server.
 * @author Anchor
 * 
 */
public class TaskWorker implements Callable<Integer> {
  final private static Logger logger = LogManager.getLogger(TaskWorker.class);
  
  private Server server = null; // Which server will connect to
  private String dbName = null; // Which database will connect to 
  private List<String> sqls = null; // Which sqls will be executed
  private boolean dummy = true; // True:  Try to establish connection only 
                                //        and do not execute sqls
                                // False: Execute sqls
  
  public static List<TaskWorker> configRunners(DeployConfig config, String taskId, boolean undo, boolean dummy) {
    List<TaskWorker> result = new ArrayList<TaskWorker>();
    boolean flag = true;
    
    Task task = config.getTasks().getTaskById(taskId);
  
    List<Server> servers = parseServers(config, task.getTargetServer());
    List<String> dbNames = parseDbNames(task.getTargetDatabase());
    List<String> sqls = parseSqls(task.getSqls(), undo);
    
    for (Server server : servers) {
      for (String dbName : dbNames) {
        result.add(new TaskWorker(server, dbName, sqls, dummy));
      }
    }
    
    return result;
  }

  private static List<Server> parseServers(DeployConfig config, String targetServer) {
    List<Server> result = new ArrayList<Server>();
    boolean flag = true;
    String[] serverList = targetServer.split(C.separator);
    Server server = null;
    for (String target : serverList) {
      server = config.getServers().getServerById(target);
      flag = flag && server != null;
      if (!flag) {
        logger.error("Not found server with id:" + target);
        result.clear();
        break;
      }
      result.add(server);
    }
    return result;
  }
  
  private static List<String> parseDbNames(String targetDatabase) {
    return Arrays.asList(targetDatabase.split(C.separator));
  }
  
  private static List<String> parseSqls(List<Sql> sqls, boolean undo) {
    List<String> result = new ArrayList<String>();
    for (Sql sql : sqls) {
      result.add(undo? sql.getUndoSql():sql.getDoSql());
    }
    return result;
  }
  
  public TaskWorker(Server server, String dbName, List<String> sqls, boolean dummy) {
    this.server = server;
    this.dbName = dbName;
    this.sqls = sqls;
  }
  
  /**
   * The ABS(return value) is the successfully executed sqls.
   * If return value is negative, the executed sqls is not completed.
   */
  public Integer call() {
    Integer result = 0;
    Connection connection = DbPool.connection(server, dbName);
    
    if (dummy) {
      result = connection != null? 1:0;
    } else {
      if (connection == null) {
        logger.error("Can not connect to %s.%s", server.getId(), dbName);
      } else {
        logger.info("Start work on %s.%s with %d sqls", server.getId(), dbName, sqls.size());
        
        try {
          Statement statement = connection.createStatement();
          for (String sql : sqls) {
            statement.executeUpdate(sql);
            result++;
          }
        } catch (SQLException e) {
          logger.error(e);
        } finally {
          try {
            connection.close();
          } catch (SQLException e) {
            logger.error(e);
          }
        }

        if (result < sqls.size()) {
          logger.error("Only complete %d/%d on %s.%s", result, sqls.size(), server.getId(), dbName);
          result = -result;
        }              
      }
    }    
    return result;
  }
  
}
