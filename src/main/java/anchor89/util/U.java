package anchor89.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class U {
  final private static Logger logger = LogManager.getLogger(U.class);
  
  public static void println(Object line) {
    System.out.println(line);
  }
  
  public static boolean hasNull(Object... objs) {
    boolean result = false;
    for (Object obj : objs) {
      if (obj == null) {
        result = true;
        break;
      }
    }
    return result;
  }
  
  public static String joinStrings(String[] strs) {
    return joinStrings(strs, ",");
  }
  
  public static String joinStrings(String[] strs, String deli) {
    String result = "";
    if (strs != null) {
      for (int i = 0; i < strs.length; i++) {
        if (i > 0) {
          result += deli;
        }
        result += strs[i];
      }
    }
    return result;
  }
}
