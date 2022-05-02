import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Application {
    public static void main(String[] args){
        Locations l = new Locations();
        l.setSource(args[0]);
        List<Path> files = l.getFiles();
        l.checkAmountOfFiles(files);
        //threads as manmy as cores
        ForkJoinPool pool = ForkJoinPool.commonPool();
//        ForkJoinPool pool = new ForkJoinPool(2);
        l.setDest(args[1]);

        long time = System.currentTimeMillis();
        //multiple threads
        try{
            pool.submit(()->files.parallelStream()
                    .map(ImageTransformation::getPair)
                    .map(ImageTransformation::transform)
                    .forEach(Locations::save)
            ).get();
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        //one thread
        files.stream()
                .map(ImageTransformation::getPair)
                .map(ImageTransformation::transform)
                .forEach(Locations::save);

        System.out.println(System.currentTimeMillis() - time);


    }
}
