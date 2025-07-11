package interactable;

import entity.Player;
import util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ParentInteractable {

    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int x;
    private int y;
    private Rectangle hitbox;


    public ParentInteractable(String name, int x, int y, boolean collision) {
        this.x = x;
        this.y = y;
        this.collision = collision;
        this.hitbox = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public abstract void handleCollision();

    public abstract void handleInteraction();


    public void draw(Graphics2D graphics2D, Player player, boolean playerOverlap) {
        int screenX = x - player.getX() + player.getScreenCenterX();
        int screenY = y - player.getY() + player.getScreenCenterY();

        // checks if the tile is on the screen before drawing it (1 tile extra to keep it smooth)
        if (x + Constants.TILE_SIZE > player.getX() - player.getScreenCenterX()
                && x - Constants.TILE_SIZE < player.getX() + player.getScreenCenterX()
                && y + Constants.TILE_SIZE > player.getY() - player.getScreenCenterY()
                && y - Constants.TILE_SIZE < player.getY() + player.getScreenCenterY()) {
            graphics2D.drawImage(image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        }

        if (playerOverlap) {
            graphics2D.drawString("Press 'E' to Pick Up" , screenX - 10, screenY - 10);
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
