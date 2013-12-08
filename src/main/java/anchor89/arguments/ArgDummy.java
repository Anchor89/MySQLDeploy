package anchor89.arguments;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ArgDummy extends Argument {
  final private static Logger logger = LogManager.getLogger(ArgDummy.class);
  
  private String value = Argument.argFalse;
  
  public ArgDummy() {
  }
  
  public ArgDummy(String[] args) {
    parse(args);
  }
  
  @Override
  public String shortKey() {
    return Arguments.dummyS;
  }
  
  @Override
  public String fullKey() {
    return Arguments.dummy;
  }
  
  @Override
  public String defaultValue() {
    return Argument.argFalse;
  }
  
  @Override
  public String description() {
    return "If this is set, then we will only test if the connection can "
        + "be established for given tasks. None of the sqls in tasks "
        + "will be executed.";
  }
  
  @Override
  public String valueFormat() {
    return null;
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
  
  @Override
  public void doParse(String[] args) {
    value = parseAsBooleanString(args);
  }
}
