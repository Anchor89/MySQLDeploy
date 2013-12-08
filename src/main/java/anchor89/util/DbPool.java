package anchor89.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

import anchor89.entry.Server;

public class DbPool {
  final private static Logger logger = LogManager.getLogger(DbPool.class);
  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      logger.error(e);
    }
  }
  
  public static Connection connection(Server server, String dbName) {
    Connection result = null;
    try {
      result = DriverManager.getConnection(server.getUrl(dbName), server.getUser(), server.getPassword());
      logger.debug(result);
    } catch (SQLException e) {
      logger.error("Error in get connection for Server:" + server.getId() + " Database:" + dbName);
      logger.error(e);
    }
    return result;
  }
  
  /*
  public static Connection getConnection(String id) {
    Connection result = null;
    try {
      if (isExist(id)) {
        result = DriverManager.getConnection("proxool." + id);
      }
    } catch (SQLException ex) {
      logger.error(ex);
    }
    return result;
  }
  
  public static boolean isExist(String id) {
    String[] alias = ProxoolFacade.getAliases();
    for (String a : alias) {
      if (a.compareTo(id) == 0) {
        return true;
      }
    }
    return false;
  }
      
  /**
   * Register server to proxool with given information.
   * 
   * @param type
   *          Type of server. Only support mysql now.
   * @param host
   *          The IP address of the server's location
   * @param alias
   *          The alias regitered in proxool
   * @param user
   *          The username used to login to db
   * @param password
   *          The password used to login to db
   * @param db
   *          The name of the server
   * /
  public static void registerToProxool(Server db) {
    FileInputStream fif = null;
    if (!db.getType().equalsIgnoreCase("mysql")) {
      throw new UnsupportedOperationException(
          "Only support register server of type mysql");
    }
    
    try {
      Properties info = new Properties();
      String path = FileHelper.getRootPath();
      path += "pool.properties";
      fif = new FileInputStream(path);
      info.load(fif);
      info.setProperty("user", db.getUser());
      info.setProperty("password", db.getPassword());
      String dbUrl = db.getUrl("test");
      String driverClass = "com.mysql.jdbc.Driver";
      String url = "proxool." + db.getId() + ":" + driverClass + ":" + dbUrl;
      if (!isExist(db.getId())) {
        ProxoolFacade.registerConnectionPool(url, info);
      } else {
        ProxoolFacade.updateConnectionPool(url, info);
      }
    } catch (FileNotFoundException ex) {
      logger.error(ex);
    } catch (IOException ex) {
      logger.error(ex);
    } catch (ProxoolException ex) {
      logger.error(ex);
    }
  } */
}
