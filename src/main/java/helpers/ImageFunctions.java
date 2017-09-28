package helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Nick on 08.06.2015.
 */
public class ImageFunctions{


    public static void createThumbnail(String imagePath, String uploadPath, String newImageName, int cropWidth, int cropHeigh) throws IOException {

        File thumbDir = new File(uploadPath);

        if(!thumbDir.exists())
        {
            thumbDir.mkdir();
        }

        String imageThumbPath = uploadPath+File.separator+newImageName;

        File input = new File(imagePath);

        BufferedImage src_cropImage = ImageIO.read(input);

        BufferedImage dst_cropImage = src_cropImage.getSubimage(0, 0, cropWidth, cropHeigh);
        Graphics2D g2d = dst_cropImage.createGraphics();
        g2d.drawImage(dst_cropImage,0,0,cropWidth,cropHeigh,null);
        g2d.dispose();

        String formatName = imageThumbPath.substring(imageThumbPath.lastIndexOf(".")+1);
        ImageIO.write(dst_cropImage,formatName,new File(imageThumbPath));
    }

    public static String resize(String imagePath, String uploadPath, String newImageName, int resizeTo) throws IOException {


        String imageOutput = uploadPath+File.separator+newImageName;

        File input = new File(imagePath);
        BufferedImage inputImage = ImageIO.read(input);

        int src_width = inputImage.getWidth();
        int src_height = inputImage.getHeight();
        int resizeWidth = resizeTo;
        int resizeHeight = resizeTo;
        double src_ratio = (double)src_width/(double)src_height;
        if(src_ratio>1)
        {
            resizeWidth = (int)Math.floor(resizeHeight*src_ratio);
        }
        else if(src_ratio<1)
        {
            resizeHeight = (int)Math.floor(resizeWidth/src_ratio);
        }

        BufferedImage out = new BufferedImage(resizeWidth,resizeHeight,inputImage.getType());
        Graphics2D g2d = out.createGraphics();
        g2d.drawImage(inputImage,0,0,resizeWidth,resizeHeight,null);
        g2d.dispose();

        String formatName = imageOutput.substring(imageOutput.lastIndexOf(".")+1);
        ImageIO.write(out,formatName,new File(imageOutput));

        return imageOutput;

    }

    public static void resizeImage(String imagePath,String uploadPath, String newImageName, int newWidth, int newHieght) throws IOException {
        String imageOutput;
        String uploadThumbPath = uploadPath+File.separator+"thumbs";
        File thumbDir = new File(uploadThumbPath);

        if(!thumbDir.exists())
        {
            thumbDir.mkdir();
        }
        imageOutput = uploadThumbPath+File.separator+newImageName;

        File input = new File(imagePath);
        BufferedImage inputImage = ImageIO.read(input);


        BufferedImage out = new BufferedImage(newWidth,newHieght,inputImage.getType());
        Graphics2D g2d = out.createGraphics();
        g2d.drawImage(inputImage,0,0,newWidth,newHieght,null);
        g2d.dispose();

        String formatName = imageOutput.substring(imageOutput.lastIndexOf(".")+1);
        ImageIO.write(out,formatName,new File(imageOutput));
    }
}
