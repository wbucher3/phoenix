package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilityFunctions {

    public static BufferedImage readImageFile(String directoryPath, String fileName, String fileExtension) {
        try {
            return ImageIO.read(new File(directoryPath + fileName + fileExtension));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Ran into an error fetching image. Expected image path: " + directoryPath);
        }
    }
}
