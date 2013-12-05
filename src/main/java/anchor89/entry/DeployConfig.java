package anchor89.entry;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import anchor89.util.C;

public class DeployConfig {
  final private static Logger logger = LogManager.getLogger(DeployConfig.class);
  
  Servers servers = new Servers();
  Tasks tasks = new Tasks();
  
  public DeployConfig(String filename) {
    parseFile(filename);
  }
  
  public Servers getServers() {
    return servers;
  }

  public Tasks getTasks() {
    return tasks;
  }

  public boolean parseFile(String filename) {
    boolean result = true;
    logger.info("Read config file:"+filename);
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    try {
      db = dbf.newDocumentBuilder();
      Document doc = db.parse(filename);
      result = fromDocument(doc);
    } catch (ParserConfigurationException e) {
      logger.error(e);
      result = false;
    } catch (SAXException e) {
      logger.error(e);
      result = false;
    } catch (IOException e) {
      logger.error(e);
      result = false;
    }    
    return result;
  }
  
  public boolean fromDocument(Document document) {
    boolean result = true;
    NodeList databasesList = document.getElementsByTagName(C.servers);
    NodeList tasksList = document.getElementsByTagName(C.tasks);
    for (int i=0; i<databasesList.getLength(); i++) {
      result = result && servers.fromElement((Element)databasesList.item(i));
    }
    for (int i=0; i<tasksList.getLength(); i++) {
      result = result && tasks.fromElement((Element)tasksList.item(i));
    }
    return result;
  }
}
