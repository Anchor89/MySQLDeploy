package anchor89.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ArgFile extends Argument {
  final private static Logger logger = LogManager.getLogger(ArgFile.class);
  
  private String value = null;
  
  public ArgFile() {
  }
  
  public ArgFile(String[] args) {
    parse(args);
  }
  
  @Override
  public String shortKey() {
    return Arguments.fileS;
  }
  
  @Override
  public String fullKey() {
    return Arguments.file;
  }
  
  @Override
  public String defaultValue() {
    return null;
  }
  
  @Override
  public String description() {
    return "The file used as config";
  }
  
  @Override
  public String valueFormat() {
    return "filename/filepath";
  }
  
  @Override
  public void doParse(String[] args) {
    value = parseAsString(args);
  }
  
  @Override
  public int valueCount() {
    return value == null?0:1;
  }
  
  @Override
  public String value(int index) {
    return value;
  }
  
  @Override
  public List<String> values() {
    List<String> result = new ArrayList<String>();
    if (value != null) result.add(value);
    return result;
  }
  
  @Override
  public boolean isTrue() {
    return value != null;
  }
  
  @Override
  public boolean isValid() {
    return value != null && !value.isEmpty();
  }
}
