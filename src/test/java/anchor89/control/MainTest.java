package anchor89.control;

import static org.junit.Assert.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

public class MainTest {
  final private static Logger logger = LogManager.getLogger(MainTest.class);
  
  @Test
  public void test() {
    String[] args = new String[7];
    args[0] = "-fv1";
    args[1] = "v2";
    args[2] = "v3";
    args[3] = "v4";
    args[4] = "-tv1";
    args[5] = "v2";
    args[6] = "v3";
    tryArguments(args);
    args[0] = "-f";
    tryArguments(args);
    args[0] = "--file";
    tryArguments(args);
    args[0] = "--file=v1";
    tryArguments(args);
  }
  
  private void tryArguments(String[] args) {
    Main.main(args);
  }
}
