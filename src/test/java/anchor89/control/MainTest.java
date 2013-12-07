package anchor89.control;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import anchor89.util.C;

public class MainTest extends Main{
  final private static Logger logger = LogManager.getLogger(MainTest.class);
  
  public void test() {
    String[] args = new String[2];
    args[0] = "-fdeploy.xml";
    args[1] = "--dummy";
    tryMain(args);
  }

  @Test
  public void tryArguments() {
    String[] args = new String[3];
    args[0] = "-fdeploy.xml";
    args[1] = "--dummy";
    args[2] = "-tcreateDb,createTables,initData";
    processArguments(args);
    showArguments();
    assertTrue(Arrays.asList("deploy.xml").equals(arguments.get(C.fileF)));
    assertTrue(Arrays.asList(C.argTrue).equals(arguments.get(C.dummyF)));
    assertTrue(Arrays.asList("createDb","createTables","initData").equals(arguments.get(C.taskF)));
  }
  
  public void tryMain(String[] args) {
    Main.main(args);
  }
}
