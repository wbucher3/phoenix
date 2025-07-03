package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    private BufferedImage image;
    private boolean collidable;

    public Tile(String tileDirectoryPath, String tileFileName, String fileExtension) {
        this.collidable = false;
        this.initializeTileImage(tileDirectoryPath, tileFileName, fileExtension);
    }

    private void initializeTileImage(String tileDirectoryPath, String tileFileName, String fileExtension) {
        try {
            this.setImage(ImageIO.read(new File(tileDirectoryPath + tileFileName + fileExtension)));
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Ran into an error fetching tile image. Expected image path: " + tileDirectoryPath);
        }
    }

    public void draw(Graphics2D graphics2D, int x, int y, int tileSize) {
        graphics2D.drawImage(this.getImage(), x, y, tileSize, tileSize, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }


}
