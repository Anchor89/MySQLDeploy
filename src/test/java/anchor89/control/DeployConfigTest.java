package anchor89.control;

import static org.junit.Assert.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import anchor89.entry.DeployConfig;
import anchor89.entry.Server;
import anchor89.entry.Task;
import anchor89.entry.Tasks;
import anchor89.util.FileHelper;

public class DeployConfigTest {
  final private static Logger logger = LogManager
      .getLogger(DeployConfigTest.class);
  
  @Test
  public void test() {
    String filename = "sample.xml";
    String path = FileHelper.getRootPath();
    DeployConfig config = new DeployConfig(path + filename);
    Server server = config.getServers().getServerById("Legal_Test");
    logger.debug(server);
    assertNotNull(server);
    assertEquals("10.10.142.2", server.getIp());
    assertEquals("3306", server.getPort());
    assertEquals("law", server.getUser());
    assertEquals("111111", server.getPassword());
    Tasks tasks = config.getTasks();
    assertNotNull(tasks);
    Task task = null;
    task = tasks.getTaskById("Create_Database");
    logger.debug(task);
    assertNotNull(task);
    
    task = tasks.getTaskById("Create_Table");
    logger.debug(task);
    assertNotNull(task);
    
    task = tasks.getTaskById("Insert_Data");
    logger.debug(task);
    assertNotNull(task);
    
    logger.debug(task.getSqls().get(0).getDoSql());
  }
}
