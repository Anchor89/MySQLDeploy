package anchor89.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class C {
  public static final String dbFileName = "servers.xml";
  
  /**
   * Tag names and attribute keys used in configure file.
   */
  public static final String servers = "servers";
  public static final String server = "server";
  public static final String id = "id";
  public static final String type = "type";
  public static final String ip = "ip";
  public static final String port = "port";
  public static final String user = "user";
  public static final String password = "password";
  public static final String tasks = "tasks";
  public static final String task = "task";
  public static final String targetServer = "target-server";
  public static final String targetDatabase = "target-database";
  public static final String sql = "sql";
  public static final String doSql = "do";
  public static final String undoSql = "undo";
  

  
  public static final String separator = ",";
  
  public static final int currentWorkers = 10;
  
  public static final int timeout = 4;
  public static final TimeUnit timeoutUnit = TimeUnit.HOURS;
}
