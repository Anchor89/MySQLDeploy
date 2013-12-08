package anchor89.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import anchor89.util.C;
import anchor89.util.MultiMap;
import anchor89.util.U;

/**
 * Define one arguments. One argument may have one or more values and may be
 * of boolean type. But all the values is represented by String since the user
 * input is String.
 * 
 * One argument has following attributes that must be implemented.
 * shortKey: The single character represent this argument.
 * fullKey: The full name of this argument.
 * defaultValue: The default value for this argument. Null if it's not needed.
 * valueFormat: Used to tell user the format of values e.g. "v1[,v2]".
 * description: The description for this arguments. Used for displaying in help.
 *
 * The derived class also should implements other method:
 * parse: Parse the arguments passed in and mine out the value(s) belong to this key.
 * valueCount: The number of values.
 * value: Get value by index.
 * values: Get the list of values.
 * isTrue: If this argument has type boolean, this should return true if it's set to true.
 * isValid: If the set values are valid.
 * @author Anchor
 *
 */
public abstract class Argument {
  final protected static Logger logger = LogManager.getLogger(Argument.class);
  
  final public static String argTrue = "T";
  final public static String argFalse = "F";
  
  public abstract String shortKey();
  public abstract String fullKey();
  public abstract String defaultValue();
  public abstract String description();
  public abstract String valueFormat();
  
  public abstract void doParse(String[] args);
  
  public abstract int valueCount();
  public abstract String value(int index);
  public abstract List<String> values();
  public abstract boolean isTrue();
  public abstract boolean isValid();
  
  /**
   * Make this argument manageable.
   */
  protected Argument() {
    Arguments.shortToFull.put(shortKey(), fullKey());
    Arguments.arguments.put(fullKey(), this);
  }

  public void parse(String[] args) {
    int from = 0;
    int to = 1;
    while(from < args.length) {
      to = from + 1;
      if (isKey(args[from]) && isThisKey(args[from])) {
        while(to < args.length && !isKey(args[to])) {
          to++;
        }
        doParse(removeKey(Arrays.copyOfRange(args, from, to)));
      }
      from = to;
    }
  }  
  
  protected boolean isKey(String str) {
    return str!=null && (str.startsWith("-") || str.startsWith("--"));
  }
  
  protected boolean isThisKey(String str) {
    boolean result = false;
    if (str != null) {
      int eqIndex = str.indexOf("=");
      result =  (str.startsWith("-") && str.substring(1, 2).equals(shortKey()))
          || (str.startsWith("--") && str.substring(2, eqIndex == -1?str.length():eqIndex).equals(fullKey()));
    }
    return result;
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
   * @return The values after prefix with "-" or "--" removed or null if the
   * argument is of type boolean.
   */
  protected static String[] removeKey(String[] args) {
    String[] result = null;

    if (args != null && args.length > 0) {
      // args is not empty
      // find value.
      if ((args[0].startsWith("-") && args[0].length() == 2)
          || (args[0].startsWith("--") && !args[0].contains("="))) {
        result = 1 < args.length ? Arrays.copyOfRange(args, 1, args.length)
            : null;
      } else {
        result = Arrays.copyOf(args, args.length);
        result[0] = args[0].startsWith("--") ? result[0].substring(args[0]
            .indexOf('=') + 1)
            : result[0].substring(2);
      }
    }
    
    return result;
  }
  
  /**
   * Use the last element to determine return true or false.
   * @param args
   * @return False if the args is null or empty.
   */
  protected static boolean parseAsBoolean(String[] args) {
    return true;
  }
  
  /**
   * 
   * @param args
   * @return
   */
  protected static String parseAsBooleanString(String[] args) {
    return parseAsBoolean(args)? Argument.argTrue:Argument.argFalse;
  }
  /**
   * Return the last element. But return empty string if the args is null or empty. 
   * @param args
   * @return
   */
  protected static String parseAsString(String[] args) {
    return args != null && args.length > 0? args[args.length-1]:"";
  }

  /**
   * Split the args using C.seperator to one array.
   * @param args
   * @return
   */
  protected static String[] parseAsStrings(String[] args) {
    List<String> all = new LinkedList<String>();
    List<String> one = new LinkedList<String>();
    for (String arg : args) {
      one.clear();
      for (String a : arg.split(C.separator)) {
        one.add(a);
      }
      all.addAll(one);
    }
    String[] result = new String[all.size()];
    all.toArray(result);
    return result;
  }
  
  @Override
  public String toString() {
    return String.format("%s=%s", fullKey(), values());
  }
}
