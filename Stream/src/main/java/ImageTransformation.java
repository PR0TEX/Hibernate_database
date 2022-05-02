import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageTransformation {
    private static Locations l;
    private static String dst;
    private static void setDst(){
        dst = "copies";







    }
    ImageTransformation(Locations loc){
        l = loc;
    }
    public void setLoc(Locations loc){
        this.l = loc;
    }
    public static Pair<Path, BufferedImage> getPair(Path path){
        try{
            return Pair.of(path.getFileName(), ImageIO.read(path.toFile()));
        }catch (IOException e){
            System.out.println("ERROR, Unable to create pair");
            e.printStackTrace();
        }
        return null;
    }

    public static Pair<Path, BufferedImage> transform(Pair<Path, BufferedImage> pathBufferedImagePair){
        setDst();
        BufferedImage original = pathBufferedImagePair.getRight();
        BufferedImage copy = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                original.getType());
        for(int i=0;i<original.getWidth();i++){
            for (int j=0;j<original.getHeight();j++){
                int rgb = original.getRGB(i,j);
                Color color = new Color(rgb);
                Color outColor = new Color(
                        color.getRed(),
                        color.getBlue(),
                        color.getGreen());
                int outRGB = outColor.getRGB();
                copy.setRGB(i,j,outRGB);
            }
        }

        Path path = Path.of(dst,pathBufferedImagePair.getLeft().getFileName().toString());
        return Pair.of(path,copy);
    }



}
