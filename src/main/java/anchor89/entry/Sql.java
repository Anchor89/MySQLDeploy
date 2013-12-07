package anchor89.entry;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;

import anchor89.util.C;
import anchor89.util.U;
import anchor89.util.X;

public class Sql implements GenerateFromElement {
  final private static Logger logger = LogManager.getLogger(Sql.class);
  
  private String doSql = null;
  private String undoSql = null;
  public Sql() {
  }
  
  public Sql(String doSql, String undoSql) {
    this.doSql = doSql;
    this.undoSql = undoSql;
  }

  public boolean fromElement(Element element) {
    boolean result = true;
    doSql = X.getFirstChildText(element, C.doSql, null);
    undoSql = X.getFirstChildText(element, C.undoSql, null);
    result = !U.hasNull(doSql, undoSql);
    if (!result) {
      logger.error("Error parsing <sql>:" + element);
    }
    return result;
  }
  
  public String getDoSql() {
    return doSql;
  }
  
  public String getUndoSql() {
    return undoSql;
  }

  @Override
  public String toString() {
    String result = String.format("do:%s;undo:%s", doSql, undoSql);
    return result;
  }
}
