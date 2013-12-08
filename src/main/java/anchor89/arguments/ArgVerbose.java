package anchor89.arguments;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ArgVerbose extends Argument {
  final private static Logger logger = LogManager.getLogger(ArgVerbose.class);
  
  private String value = Argument.argFalse;
  
  public ArgVerbose() {
  }
  
  public ArgVerbose(String[] args) {
    parse(args);
  }

  @Override
  public String shortKey() {
    return Arguments.verboseS;
  }

  @Override
  public String fullKey() {
    return Arguments.verbose;
  }

  @Override
  public String defaultValue() {
    return Argument.argFalse;
  }

  @Override
  public String description() {
    return "If this flag is set, then each executiong of sqls will be display.";
  }

  @Override
  public String valueFormat() {
    return "";
  }

  @Override
  public void doParse(String[] args) {
    value = parseAsBooleanString(args);
  }

  @Override
  public int valueCount() {
    return 1;
  }

  @Override
  public String value(int index) {
    return value;
  }

  @Override
  public List<String> values() {
    return Arrays.asList(value);
  }

  @Override
  public boolean isTrue() {
    return value.equals(Argument.argTrue);
  }

  @Override
  public boolean isValid() {
    return true;
  }
}
