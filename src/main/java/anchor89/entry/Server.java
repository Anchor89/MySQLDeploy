package anchor89.entry;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import anchor89.util.C;
import anchor89.util.U;
import anchor89.util.X;

public class Server implements GenerateFromElement {
  final private static Logger logger = LogManager.getLogger(Server.class);
  
  private String id = null;
  private String type = null;
  private String ip = null;
  private String port = null;
  private String user = null;
  private String password = null;
  
  public Server() {
  }
  
  public Server(String id, String type, String ip, String port,
      String user, String password) {
    this.id = id;
    this.type = type;
    this.ip = ip;
    this.port = port;
    this.user = user;
    this.password = password;
  }
  
  public boolean fromElement(Element ele) {
    boolean result = true;
    id = X.getAttribute(ele, C.id, null);
    type = X.getAttribute(ele, C.type, "mysql");
    ip = X.getFirstChildText(ele, C.ip, null);
    port = X.getFirstChildText(ele, C.port, null);
    user = X.getFirstChildText(ele, C.user, null);
    password = X.getFirstChildText(ele, C.password, null);
    result = !U.hasNull(id, ip, port, user, password);
    if (!result) {
      logger.error("Error in parsing <server>:" + ele.toString());
    }
    return result;
  }
  
  @Override
  public String toString() {
    String result = String.format("ID:%s,type:%s,url:jdbc:mysql://%s:%s/",
        id, type, ip, port);
    return result;
  }
  
  public String getUrl(String dbName) {
    if (type.equalsIgnoreCase("mysql")) {
      return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf8", ip, port, dbName);
    } else {
      return "";
    }    
  }
  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getIp() {
    return ip;
  }

  public String getPort() {
    return port;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }
}
