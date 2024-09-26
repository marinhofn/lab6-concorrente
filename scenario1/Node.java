package java.scenario1;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Node implements Runnable {
    private BlockingQueue<Task> queue;
    private ConcurrentHashMap<Long, String> results;

    public Node(BlockingQueue<Task> queue, ConcurrentHashMap<Long, String> results) {
        this.queue = queue;
        this.results = results;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Task task = queue.take(); // Bloqueia até que uma tarefa esteja disponível
                task.execute();
                results.put(task.id, "Completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Mantém o estado interrompido
                System.out.println("Node interrupted");
            }
        }
    }
}