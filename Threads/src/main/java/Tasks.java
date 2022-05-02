import java.util.ArrayList;
import java.util.List;

public class Tasks {
    private List<Task> tasks = new ArrayList<>();

    public synchronized Task take() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        return tasks.remove(0);
    }
    public synchronized void put(Task task){
        this.tasks.add(task);
        notifyAll();
    }
}
