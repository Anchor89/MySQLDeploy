package anchor89.arguments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import anchor89.util.C;
import anchor89.util.MultiMap;
import anchor89.util.U;

public class Arguments {
  final private static Logger logger = LogManager.getLogger(Arguments.class);
  
  public final static String dummy = "dummy";
  public final static String dummyS = "d";
  public final static String file = "file";
  public final static String fileS = "f";
  public final static String task = "task";
  public final static String taskS = "t";
  public final static String verbose = "verbose";
  public final static String verboseS = "v";
  public final static String revert = "revert";
  public final static String revertS = "r";
  
  public static Map<String, String> shortToFull = new HashMap<String, String>();
  public static Map<String, Argument> arguments = new HashMap<String, Argument>();
  
  public static String helpInfo() {
    StringBuilder help = new StringBuilder("Arguments:\n");
    String line = null;
    for (Argument arg : arguments.values()) {
      line = arg.description() == null? String.format("\t-%s,--%s: %s\n", arg.shortKey(), arg.fullKey(), arg.description()):
        String.format("\t-%s,--%s=%s: %s\n", arg.shortKey(), arg.fullKey(), arg.valueFormat(), arg.description());
      help.append(line);
    }
    return help.toString();
  }

  public static boolean argumentsValid() {
    boolean result = true;
    for (Argument arg : arguments.values()) {
      result = result && arg.isValid();
    }
    return result;
  }
  
  /**
   * Process arguments passed in.
   * 
   * @param args
   * @return
   */
  public static void processArguments(String[] args) {
    Argument holder = new ArgDummy(args);
    holder = new ArgFile(args);
    holder = new ArgTask(args);
    holder = new ArgVerbose(args);
    holder = new ArgRevert(args);
  }
  
  public static Argument get(String key) {
    return arguments.get(key);
  }
  
  public static String showString() {
    StringBuilder builder = new StringBuilder();
    for (Argument arg : arguments.values()) {
      builder.append(arg.toString());
      builder.append("\n");
    }
    return builder.toString();
  }
}
