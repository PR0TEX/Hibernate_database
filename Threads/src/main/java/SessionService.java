import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SessionService {
    private final int nbOfThreads;
    private final Results results = new Results();
    private Tasks tasks = new Tasks();
    private final List<Thread> threads = new ArrayList<>();
    private int precision,job_id=0;
    private boolean isEnd = false;

    public SessionService(int nbOfThreads){
        this.nbOfThreads = nbOfThreads;
    }
    private Tasks prepareTasks(Tasks tasks){
        tasks.put(new Task(job_id++, 5));
        tasks.put(new Task(job_id++,2));
        tasks.put(new Task(job_id++,4));
        tasks.put(new Task(job_id++,5));
        tasks.put(new Task(job_id++,4));
        return tasks;
    }

    public void startGenericSession() {
        for (int i = 0; i < nbOfThreads; i++) {
            Thread thread = new Thread(new Manager(tasks,results,i));
            threads.add(thread);
            thread.start();
        }
        tasks = prepareTasks(tasks);
        Scanner read = new Scanner(System.in);

        System.out.println("Podaj polecenie: ");
        System.out.println("1.Dalej");
        System.out.println("2.Koniec");

        while(!isEnd && read.hasNext()){
            switch (read.nextInt()){
                case 1:
                    System.out.println("Podaj precyzje");
                    precision = read.nextInt();
                    tasks.put(new Task(job_id++,precision));
                    System.out.println("Podaj polecenie: ");
                    break;
                case 2:
                    isEnd = true;
                    break;
            }
        }
        read.close();
        results.print();
        for (Thread th : threads){
            th.interrupt();
        }

    }
}
