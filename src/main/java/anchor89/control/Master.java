package anchor89.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import anchor89.arguments.Arguments;
import anchor89.entry.DeployConfig;
import anchor89.entry.Server;
import anchor89.entry.Task;
import anchor89.util.C;
import anchor89.util.MultiMap;
import anchor89.util.U;

public class Master {
  final private static Logger logger = LogManager.getLogger(Master.class);
  
  private DeployConfig config = null;
  private List<String> taskIds = null;

  public Master(DeployConfig config) {
    this.config = config;
    taskIds = Arguments.get("task").values();
  }
  
  public boolean run() {
    return workAll(Arguments.get(Arguments.revert).isTrue());
  }
  
  private boolean workAll(boolean undo) {
    boolean result = true;
    for (String taskId : taskIds) {
      result = result && workOne(taskId, undo);
    }
    return result;
  }
  
  private boolean workOne(String taskId, boolean undo) {
    boolean result = true;
    List<TaskWorker> workers = TaskWorker.configRunners(config, taskId, undo);
    List<Future<Integer>> workouts = new ArrayList<Future<Integer>>();
    ExecutorService ex = Executors.newFixedThreadPool(C.currentWorkers);

    for (TaskWorker worker : workers) {
      workouts.add(ex.submit(worker));
    }
    ex.shutdown();
    try {
      ex.awaitTermination(C.timeout, C.timeoutUnit);
    } catch (InterruptedException e) {
      logger.error(String.format("Timeout on task:%s.", taskId));
      logger.error(e);
    }

    int successCount = 0;
    try {
      for (Future<Integer> workout : workouts) {
        successCount += workout.get() > 0? 1:0;
      }
    } catch (InterruptedException e) {
      logger.error(e);
    } catch (ExecutionException e) {
      logger.error(e);
    }
    
    result = successCount == workers.size();
    return result;
  }

}
