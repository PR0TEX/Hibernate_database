import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Locations {
    private static String source;

    private static List<Path> files;
    public List<Path> getFiles(){ return this.files; }

    private static String dest;
    public static String getDest() { return dest; }

    public static void setSource(String src){
        source = src;
        Path path = Path.of(source);
        getFilesFromPath(path);
    }

    public static void setDest(String dst){
        dest = dst;
        File f = new File(dest);
        if(f.mkdir()){ System.out.println("Directory created"); }
        else if(f.isDirectory()){ System.out.println("Directory already exist"); }
        else { System.out.println("ERROR,Directory not created!"); }
    }

    private static void getFilesFromPath(Path p){
        try(Stream<Path> stream = Files.list(p)) {
            files = stream.collect(Collectors.toList());
        }catch (IOException e){
            System.out.println("Problem with reading files");
            e.printStackTrace();
            return;
        }
    }

    public static void checkAmountOfFiles(List<Path> p) {
        int counter = 0;
        for (final Path pathEntry : p) {
            counter++;
        }
        System.out.println(counter + " files in the directory");
    }

    public static void save(Pair<Path, BufferedImage> pathBufferedImagePair){
        try{
            ImageIO.write(pathBufferedImagePair.getRight(),"jpg",pathBufferedImagePair.getLeft().toFile());
        }catch (IOException e){
            System.out.println("ERROR, unable to save");
            e.printStackTrace();
        }

    }


}
