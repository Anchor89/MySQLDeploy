package anchor89.entry;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import anchor89.util.C;
import anchor89.util.U;
import anchor89.util.X;

public class Task implements GenerateFromElement {
  final private static Logger logger = LogManager.getLogger(Task.class);
  
  private String id = null;
  private String targetServer = null;
  private String targetDatabase = null;
  private List<Sql> sqls = new ArrayList<Sql>();
  public Task() {
    
  }
  
  public boolean fromElement(Element element) {
    boolean result = true;
    this.id = X.getAttribute(element, C.id, null);
    this.targetServer = X.getAttribute(element, C.targetServer, null);
    this.targetDatabase = X.getAttribute(element, C.targetDatabase, null);
    NodeList sqlList = element.getElementsByTagName("sql");
    for (int i=0; i<sqlList.getLength(); i++) {
      Sql sql = new Sql();
      result = result && sql.fromElement((Element)sqlList.item(i));
    }
    result = result && !U.hasNull(id, targetServer, targetDatabase);
    if (U.hasNull(id, targetServer, targetDatabase)) {
      logger.error("Error in missing id or targetServer or targetDatabase:" + element);
    }
    return result;
  }

  public String getId() {
    return id;
  }

  public String getTargetServer() {
    return targetServer;
  }

  public String getTargetDatabase() {
    return targetDatabase;
  }

  public List<Sql> getSqls() {
    return sqls;
  }
}
