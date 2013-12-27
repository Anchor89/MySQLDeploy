package anchor89.arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ArgTask extends Argument {
  final private static Logger logger = LogManager.getLogger(ArgTask.class);
  
  private String[] value = null;
  
  public ArgTask() {
  }

  public ArgTask(String[] args) {
    parse(args);
  }
  
  @Override
  public String shortKey() {
    return Arguments.taskS;
  }

  @Override
  public String fullKey() {
    return Arguments.task;
  }

  @Override
  public String defaultValue() {
    return "";
  }

  @Override
  public String description() {
    return "The task's id you want to run. Can use multiple tasks' id";
  }

  @Override
  public String valueFormat() {
    return "id1[,id2]...";
  }

  @Override
  public void doParse(String[] args) {
    value = parseAsStrings(args);
    logger.debug(this.hashCode());
    logger.debug(value.length);
  }

  @Override
  public int valueCount() {
    return value == null? 0:value.length;
  }

  @Override
  public String value(int index) {
    return index < value.length? value[index]:null;
  }

  @Override
  public List<String> values() {
    logger.debug(this.hashCode());
    if (value != null) {
      logger.debug(value.length);
      for (String v : value){
        logger.debug(v);
      }
      return Arrays.asList(value);
    } else {
      return new ArrayList<String>();
    }

  }

  @Override
  public boolean isTrue() {
    return value != null;
  }

  @Override
  public boolean isValid() {
    return value != null;
  }
}
