package tile;

import util.UtilityFunctions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {

    private BufferedImage image;
    private boolean collidable;

    public Tile(String tileDirectoryPath, String tileFileName, String fileExtension, boolean collidable) {
        this.collidable = collidable;
        this.image = UtilityFunctions.readImageFile(tileDirectoryPath, tileFileName, fileExtension);
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
