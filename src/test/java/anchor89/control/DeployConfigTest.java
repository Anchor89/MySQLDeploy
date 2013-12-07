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
    String filename = "deploy.xml";
    String path = FileHelper.getRootPath();
    DeployConfig config = new DeployConfig(path + filename);
    Server server = config.getServers().getServerById("metadb");
    logger.debug(server);
    assertNotNull(server);
    assertEquals("10.10.106.11", server.getIp());
    assertEquals("3306", server.getPort());
    assertEquals("root", server.getUser());
    assertEquals("111111", server.getPassword());
    Tasks tasks = config.getTasks();
    assertNotNull(tasks);
    Task task = null;
    task = tasks.getTaskById("createDb");
    logger.debug(task);
    assertNotNull(task);
    
    task = tasks.getTaskById("createTables");
    logger.debug(task);
    assertNotNull(task);
    
    task = tasks.getTaskById("initData");
    logger.debug(task);
    assertNotNull(task);
  }
}
