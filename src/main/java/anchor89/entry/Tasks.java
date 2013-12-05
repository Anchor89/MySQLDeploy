package anchor89.entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import anchor89.util.C;

public class Tasks implements GenerateFromElement {
  final private static Logger logger = LogManager.getLogger(Tasks.class);
  
  private Map<String, Task> taskStore = new HashMap<String, Task>();
  public Tasks() {
  }

  /**
   * Each invoke of this method will add the content in given
   * element to tasks. 
   */
  public boolean fromElement(Element element) {
    boolean result = true;
    NodeList taskList = element.getElementsByTagName(C.task);
    for (int i=0; i<taskList.getLength(); i++) {
      Task task = new Task();
      task.fromElement((Element)taskList.item(i));
      taskStore.put(task.getId(), task);
    }
    return result;
  }
  
  public Task getTaskById(String id) {
    return taskStore.get(id);
  }
}
