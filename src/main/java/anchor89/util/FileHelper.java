package anchor89.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FileHelper {
  final private static Logger logger = LogManager.getLogger(FileHelper.class);
  
  public static String getRootPath() {
    return FileHelper.class.getResource("/").getPath();
  }
}
