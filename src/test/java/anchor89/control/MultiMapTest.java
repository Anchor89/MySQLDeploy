package anchor89.control;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import anchor89.util.MultiMap;

public class MultiMapTest {
  final private static Logger logger = LogManager.getLogger(MultiMapTest.class);


  @Test
  public void putOne() {
    MultiMap<String, String> map = new MultiMap<String, String>();
    map.put("key1", "value1");
    map.put("key2", "value1");
    map.put("key1", "value2");
    assertTrue(Arrays.asList("value1", "value2").equals(map.get("key1")));
    assertTrue(Arrays.asList("value1").equals(map.get("key2")));
  }
  
  @Test
  public void putRange() {
    MultiMap<String, String> map = new MultiMap<String, String>();
    List<String> list1= Arrays.asList("v1", "v2", "v3");
    map.put("key1", list1);
    map.put("key2", list1);
    assertTrue(list1.equals(map.get("key1")));
    assertTrue(list1.equals(map.get("key2")));    
  }
  
  @Test
  public void clearAndSize() {
    MultiMap<String, String> map = new MultiMap<String, String>();
    map.put("key1", "value1");
    map.put("key2", "value1");
    map.put("key1", "value2");
    assertEquals(2, map.size());
    map.clear();
    assertEquals(0, map.size());
  }
}
