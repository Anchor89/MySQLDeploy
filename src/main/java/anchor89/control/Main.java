package anchor89.control;

import java.io.File;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.runners.ParentRunner;

import anchor89.arguments.Argument;
import anchor89.arguments.Arguments;
import anchor89.entry.DeployConfig;
import anchor89.util.C;
import anchor89.util.FileHelper;
import anchor89.util.MultiMap;
import anchor89.util.U;

public class Main {
  final private static Logger logger = LogManager.getLogger(Main.class);
  
  public static void showProcess() {
    String info = "\n"
        + "Process info:\n"
        + "Config file:" + Arguments.get(Arguments.file).value(0) + "\n"
        + "Tasks to be run:" + Arguments.get(Arguments.task).values() + "\n";
    U.println(info);
  }
  
  public static void main(String[] args) {
    Arguments.processArguments(args);
    if (!Arguments.argumentsValid()) {
      Arguments.helpInfo();
    }
    logger.info(Arguments.showString());
    
    String dir = FileHelper.getRootPath();
    String filename = Arguments.get(Arguments.file).value(0); 
    String path = filename.contains(File.separator)? filename:dir+filename;
    DeployConfig config = new DeployConfig(path);
    Master master = new Master(config);
    master.run();
  }
}
