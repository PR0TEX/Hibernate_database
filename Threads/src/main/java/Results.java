import java.util.ArrayList;
import java.util.List;

public class Results {
    private final List<Task> results = new ArrayList<>();

    public synchronized void addTask(Task task) { this.results.add(task); }

    public void print(){
        for(Task task : results){
            System.out.println(task);
        }
    }
}
