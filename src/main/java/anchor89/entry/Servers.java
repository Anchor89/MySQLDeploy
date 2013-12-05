package anchor89.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import anchor89.util.C;

public class Servers extends ArrayList<Server> 
                       implements GenerateFromElement {
  final private static Logger logger = LogManager.getLogger(Servers.class);
  
  private Map<String, Server> serverStore = new HashMap<String, Server>();
  /**
   * Get servers connection info from file @filename.
   * @filename is a XML doc specified connection info
   * of each servers.
   * @param filename This is a XML doc.
   */
  public Servers() {
  }
  
  /**
   * Each invoke of this method will add the content in 
   * given element to this servers.
   */
  public boolean fromElement(Element element) {
    boolean result = true;
    NodeList serverList = element.getElementsByTagName(C.server);
    for (int i=0; i<serverList.getLength(); i++) {
      Server server = new Server();
      result = result && server.fromElement((Element)serverList.item(i));
      if (!result) {
        break;
      }
      this.serverStore.put(server.getId(), server);
    }
    return result;
  }

  public Server getServerById(String id) {
    return serverStore.get(id);
  }
  
  public List<String> getIdList() {
    List<String> result = new ArrayList<String>();
    result.addAll(serverStore.keySet());
    return result;
  }
  
  @Override
  public String toString() {
    return serverStore.toString();
  }


}
