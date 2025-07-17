package interactable.weapon;

import entity.Player;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ParentWeapon {

    private BufferedImage image;
    private BufferedImage imageMirror;
    private String name;
    private boolean collision;
    private int width;
    private int height;
    private Rectangle hitbox;

    public ParentWeapon(int width, int height, String name, BufferedImage image, BufferedImage imageMirror) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.image = image;
        this.imageMirror = imageMirror;
        this.collision = false;
        this.hitbox = new Rectangle(0, 0, 10, 64);
    }


    public void drawWeapon(Graphics2D graphics2d, Player player, int mouseX, int mouseY, boolean clicked) {
        int playerHandX = player.getScreenCenterX() + player.getWidth() / 2;
        int playerHandY = player.getScreenCenterY() + player.getHeight() / 2;

        double adjacentSide = mouseX - playerHandX;
        double oppositeSide = mouseY - playerHandY;
        double angle = Math.atan2(oppositeSide, adjacentSide);
        double finalAngle = angle - (Math.PI / 4); // The sprite is drawn on an angle of 45 degress

        AffineTransform oldTransform = graphics2d.getTransform();
        graphics2d.translate(player.getScreenCenterX() + player.getWidth() / 2, player.getScreenCenterY() + player.getHeight() / 2);

        if (mouseX < player.getScreenCenterX() + player.getWidth() / 2) {
            graphics2d.rotate(finalAngle);
            graphics2d.drawImage(imageMirror, 0, 0,  width, height, null);
        } else {
            graphics2d.rotate(finalAngle);
            graphics2d.drawImage(image, 0, 0,  width, height, null);
        }

        if (clicked) graphics2d.drawRect(0 , 0,  width, height);
        graphics2d.setTransform(oldTransform);
    }
}
