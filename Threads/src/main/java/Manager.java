public class Manager implements Runnable{
    private final Results results;
    private final Tasks tasks;
    private int threadNb=0;
    public Manager(Tasks tasks, Results results, int threadNb){
        this.tasks = tasks;
        this.results = results;
        this.threadNb = threadNb;
    }

    @Override
    public void run(){
        while (!Thread.interrupted()){
            try {
                Task task = tasks.take();
                task.setThreadNumber(threadNb);
                Thread.sleep(200);
                results.addTask(task.count());
            } catch (InterruptedException exception){
                break;
            }
        }
    }
}


