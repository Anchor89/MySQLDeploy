package anchor89.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class MultiMap<K, V> {
  final private static Logger logger = LogManager.getLogger(MultiMap.class);

  private HashMap<K, ArrayList<V>> data = new HashMap<K, ArrayList<V>>();
  public MultiMap() {
  }
  
  public void put(K key, V value) {
    ArrayList<V> list = data.get(key);
    if (list == null) {
      list = new ArrayList<V>();
      data.put(key, list);
    }
    list.add(value);
  }
  
  public void put(K key, List<V> values) {
    ArrayList<V> list = data.get(key);
    if (list == null) {
      list= new ArrayList<V>();
      data.put(key, list);
    }
    list.addAll(values);
  }
  
  public List<V> get(K key) {
    List<V> result = null;
    result = data.get(key);
    return result;
  }
  
  public Set<K> keySet() {
    return data.keySet();
  }
  
  public int size() {
    return data.size();
  }
  
  public void clear() {
    data.clear();
  }
  
  public void remove(K key) {
    data.remove(key);
  }
  
  public void merge(MultiMap<K, V> other) {
    if (other != null) {
      for (Entry<K, ArrayList<V>> entry : other.data.entrySet()) {
        put(entry.getKey(), entry.getValue());
      }
    }
  }
  
  @Override
  public String toString() {
    return data.toString();
  }
}
