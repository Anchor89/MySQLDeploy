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
    for (Object obj:objs) {
      if (obj == null) {
        result = true;
        break;
      }
    }
    return result;
  }
}
