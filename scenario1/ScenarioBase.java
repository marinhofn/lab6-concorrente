package java.scenario1;
import java.util.concurrent.*;


public class ScenarioBase {
    public static void main(String[] args) {
        int numProducers = 3; // Número de produtores
        int numNodes = 5; // Número de nós de processamento
        BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
        ConcurrentHashMap<Long, String> resultStorage = new ConcurrentHashMap<>();
  
        ExecutorService executor = Executors.newCachedThreadPool();
  
        for (int i = 0; i < numProducers; i++) {
            executor.execute(new TaskProducer(taskQueue, 10));
        }
        for (int i = 0; i < numNodes; i++) {
            executor.execute(new Node(taskQueue, resultStorage));
        }
  
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println("All tasks processed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
  }