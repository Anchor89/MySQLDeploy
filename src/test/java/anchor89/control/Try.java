package anchor89.control;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

public class Try {
  final private static Logger logger = LogManager.getLogger(Try.class);
  
  class Base {
    protected String[] doparse(String str) {
      List<String> list = new LinkedList<String>();
      for (String s : str.split(",")) {
        list.add(s);
      }
      String[] result = new String[list.size()];
      list.toArray(result);
      return result;
    }
  }
  
  class Derived extends Base {
    String[] value = null;
    public void parse(String str) {
      value = doparse(str);
    }
    
    public void show() {
      for (String v : value) {
        logger.debug(v);
      }
    }
  }
  
  @Test
  public void test() {
    Derived d = new Derived();
    d.parse("1,2,3");
    d.show();
  }
}
