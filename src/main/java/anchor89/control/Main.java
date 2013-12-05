package anchor89.control;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.runners.ParentRunner;

import anchor89.entry.DeployConfig;
import anchor89.util.C;
import anchor89.util.MultiMap;
import anchor89.util.U;

public class Main {
  final private static Logger logger = LogManager.getLogger(Main.class);
  
  private static MultiMap<String, String> arguments = new MultiMap<String, String>();
  
  private static String helpInfo() {
    String help = "Arguments:\n"
        + "\t-f,--file=filename: Which config file will you use. Default is servers.xml. Only one file is acceptable.\n"
        + "\t-t,--task=id1[,id2]: Which tasks do you want to run. Required.\n";
    U.println(help);
    return help;
  }
  
  private static void showProcess() {
    String info = "\n"
        + "Process info:\n"
        + "Config file:" + arguments.get(C.fileF) + "\n"
        + "Tasks to be run:" + arguments.get(C.taskF);
    U.println(info);
  }

  private static boolean argumentsValid() {
    boolean result = arguments.get(C.fileF) != null; // Has file
    result = result && arguments.get(C.taskF) != null; // Has task
    result = result && arguments.get(C.fileF).size() == 1; // File have one value
    return result;
  }
  /**
   * Process arguments passed in.
   * @param args
   * @return
   */
  private static boolean processArguments(String[] args) {
    boolean result = true;
    if (args.length == 0) {
      result = false;
    } else {
      int head = 0;
      int tail = 1;
      MultiMap<String, String> thisArg = null;
      while (head < args.length) {
        if (isKey(args[head])) {
          for(tail = head + 1;tail < args.length && !isKey(args[tail]);tail++) {
          }
          thisArg = parseArgument(Arrays.copyOfRange(args, head, tail));
          arguments.merge(thisArg);
          head = tail;
        } else {
          U.println("Unrecognized argument:" + args[head]);
          result = false;
        }
      }
      result = result && arguments.get(C.fileF).size() == 1;
    }
    return result;
  }
  
  private static boolean isKey(String str) {
    return str!=null && (str.startsWith("-") || str.startsWith("--"));
  }
  /**
   * Parse values following one key at a time.
   * 
   * @param args
   *          the args list to be processed.
   *          E.g: All the following format of args will parse to the same
   *          result whose key is "file" and values are "val1","val2","val3".
   *          The short key for "file" is "f".
   *          -fval1,val2,val3
   *          -fval1 val2 val3
   *          -f val1 val2 val3
   *          -f val2,val2,val3
   *          --file=val1,val2,val3
   *          --file=val1 val2 val3
   *          --file val1 val2 val3
   *          --file val1,val2,val3
   *          But the formats can not be mixed. The follow args are invalid:
   *          -fval1,val2 val3
   *          --file=val1 val2,val3
   *          --fileval1,val2,val3
   * 
   *          All the invalid and not supported format will lead to unknow
   *          result.
   * @return
   */
  private static MultiMap<String, String> parseArgument(String[] args) {
    MultiMap<String, String> result = new MultiMap<String, String>();
    String key = "";
    String[] value = null;
    if (args != null && args.length > 0) {
      // args is not empty
      // find the key
      if (args[0].startsWith("--")) {
        key = args[0].contains("=")? args[0].substring(2, args[0].indexOf('=')):args[0].substring(2);
      } else {
        key = C.shortToFull.get(args[0].substring(1,2));
      }
      // find value.
      if ((args[0].startsWith("-") && args[0].length() == 2)
          || (args[0].startsWith("--") && !args[0].contains("="))) {
        value = Arrays.copyOfRange(args, 1, args.length);
      } else {
        value = Arrays.copyOf(args, args.length);
        value[0] = args[0].startsWith("--")? value[0].substring(args[0].indexOf('=')+1)
            :value[0].substring(2);
      }
      parseList(result, key, value);
    }
    
    return result;
  }
  
  private static void parseList(MultiMap<String, String> map, String key,
      String[] args) {
    if (args.length == 1) {
      parseCommaList(map, key, args[0]);
    } else {
      parseSpaceList(map, key, args);
    }
  }
  
  private static void parseCommaList(MultiMap<String, String> map, String key,
      String args) {
    String[] list = args.split(C.separator);
    parseSpaceList(map, key, list);
  }
  
  private static void parseSpaceList(MultiMap<String, String> map, String key,
      String[] args) {
    for (String arg : args) {
      map.put(key, arg);
    }
  }
  
  public static void main(String[] args) {
    if (!processArguments(args) || !argumentsValid()) {
      helpInfo();
    }
    DeployConfig config = new DeployConfig(arguments.get(C.fileF).get(0));
    Master runner = new Master(config, arguments.get(C.taskF));
    runner.run();
  }
}
