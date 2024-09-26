package scenario1;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class TaskProducer implements Runnable {
    private BlockingQueue<Task> queue;
    private Integer priorityInteger;
    private static AtomicLong nextId = new AtomicLong(0); // Para garantir IDs únicos

    public TaskProducer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public TaskProducer(BlockingQueue<Task> queue, Integer priorityInteger) {
        this.queue = queue;   
        this.priorityInteger = priorityInteger;     
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            long id = nextId.getAndIncrement(); // Gera um novo ID
            Task task = new Task(id);
            try {
                System.out.println("Producer created");
                
                queue.put(task);
                Thread.sleep(5000); // Intervalo randômico entre tarefas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted");
            }
        }
    }
}