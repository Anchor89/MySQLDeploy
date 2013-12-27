package anchor89.control;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import anchor89.util.C;

public class MainTest extends Main{
  final private static Logger logger = LogManager.getLogger(MainTest.class);
  
  @Test
  public void test() {
    String[] args = new String[4];
    args[0] = "-fsample.xml";
    args[1] = "-tcreateDb,createTables,initData";
    args[2] = "-v";
    args[3] = "-d";
//    Main.main(args);
  }

}
