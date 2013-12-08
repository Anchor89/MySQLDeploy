package anchor89.control;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import anchor89.arguments.Arguments;
import anchor89.util.C;

public class ArgumentsTest extends Arguments {
  final private static Logger logger = LogManager
      .getLogger(ArgumentsTest.class);

  @Test
  public void tryArguments() {
    String[] args = new String[2];
    String file = "deploy.xml";
    args[0] = "-f"+file;
    args[1] = "--task=createDb,createTables,initData";
//    args[2] = "--dummy";
    Arguments.processArguments(args);
    logger.debug(Arguments.showString());
    assertTrue(Arguments.get(Arguments.file).value(0).equals(file));
    assertTrue(Arguments.get(Arguments.task).values().equals(Arrays.asList("createDb","createTables","initData")));
    assertTrue(!Arguments.get(Arguments.dummy).isTrue());
  }
}
