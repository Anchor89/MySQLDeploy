package anchor89.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class X {
  final private static Logger logger = LogManager.getLogger(X.class);
  
  /**
   * Try to get the value of the attribute of given element. If there is no
   * such attribute, return defaultRet.
   * @param element The element to fetch attribute.
   * @param key The key of the attribute.
   * @param defaultRet The returned value is given attribute does not exist.
   * @return
   */
  public static String getAttribute(Element element, String key, String defaultRet) {
    return element.hasAttribute(key)? element.getAttribute(key):defaultRet;
  }
  /**
   * Get the text content of the first child under given parent.
   * If there is no such child, return the defaultText.
   * @param parent Parent element.
   * @param child The tag name of the child element.
   * @param defaultText Returned content if the given child dose not 
   * exist. This can be null.
   * @return
   */
  public static String getFirstChildText(Element parent, String child, String defaultText) {
    NodeList children = parent.getElementsByTagName(child);
    return children.getLength() > 0? children.item(0).getTextContent():defaultText;
  }  
}
