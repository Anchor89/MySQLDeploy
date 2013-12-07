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
  
  /**
   * Arguments' short names and full names;
   * Var name format:
   *  for short names: varS;
   *  for full names: varF. 
   * 
   */
  public static final String argTrue = "T";
  public static final String argFalse = "F";
  public static final String fileS = "f";
  public static final String fileF = "file";
  public static final String taskS = "t";
  public static final String taskF = "task";
  public static final String dummyS = "d";
  public static final String dummyF = "dummy";
  public static final Map<String, String> shortToFull = new HashMap<String, String>();
  public static final Set<String> validNames = new HashSet<String>();
  static {
    validNames.add(fileS);
    validNames.add(fileF);
    validNames.add(taskS);
    validNames.add(taskF);
    validNames.add(dummyS);
    validNames.add(dummyF);
    
    shortToFull.put(fileS, fileF);
    shortToFull.put(taskS, taskF);
    shortToFull.put(dummyS, dummyF);    
  }  
  
  public static final String separator = ",";
  
  public static final int currentWorkers = 10;
  
  public static final int timeout = 4;
  public static final TimeUnit timeoutUnit = TimeUnit.HOURS;
}
