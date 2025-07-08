package interactable;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ParentInteractable {

    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int x;
    private int y;
    private final int tileSize = 64;

    public ParentInteractable(String name, int x, int y, boolean collision) {
        this.x = x;
        this.y = y;
        this.collision = collision;
    }


    public void draw(Graphics2D graphics2D, Player player) {
        int screenX = x - player.getX() + player.getScreenCenterX();
        int screenY = y - player.getY() + player.getScreenCenterY();

        // checks if the tile is on the screen before drawing it (1 tile extra to keep it smooth)
        if (x + tileSize > player.getX() - player.getScreenCenterX()
                && x - tileSize < player.getX() + player.getScreenCenterX()
                && y + tileSize > player.getY() - player.getScreenCenterY()
                && y - tileSize < player.getY() + player.getScreenCenterY()) {
            graphics2D.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
