package entity;

import game.GameWindow;
import game.KeyPressHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GameWindow gameWindow;
    KeyPressHandler keyPressHandler;

    public Player(GameWindow gameWindow, KeyPressHandler keyPressHandler) {
        super(2, 20);
        this.gameWindow = gameWindow;
        this.keyPressHandler = keyPressHandler;

        super.setX(400);
        super.setY(400);
        super.setSpeed(4);

        this.getSpriteImages();

    }

    public void getSpriteImages() {
        try {
            super.setIdleSprite(ImageIO.read(new File("./assets/player/0.png")));
            for (int i = 0; i < super.getTotalFrames() ; i++) {
                String path = "./assets/player/" + i + ".png";
                super.getLeftSprites()[i] = ImageIO.read(new File(path));
                super.getRightSprites()[i] = ImageIO.read(new File(path));
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }


    public void update() {

        if (keyPressHandler.isKeyPressed()) {
            if (keyPressHandler.rightPressed) {
                super.setDirection(Direction.RIGHT);
                super.setX(super.getX() + super.getSpeed());
            }
            if (keyPressHandler.leftPressed) {
                super.setDirection(Direction.LEFT);
                super.setX(super.getX() - super.getSpeed());
            }
            if (keyPressHandler.jumpPressed) {
                super.setY(super.getY() - super.getSpeed());
            }

            if (super.getDirection() != Direction.IDLE && super.getDirection() == super.getPreviousDirection()) {

                if (super.getFrameCounter() < super.getSpriteAnimationSpeed()) {
                    super.setFrameCounter(super.getFrameCounter() + 1);
                } else {
                    super.setFrameCounter(1);
                }
            }
            super.setPreviousDirection(super.getDirection());

        } else {
            super.setDirection(Direction.IDLE);
            super.setFrameCounter(1);
        }


    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        switch (super.getDirection()) {
            case Direction.IDLE -> image = super.getIdleSprite();
            case Direction.LEFT -> image = super.getLeftSprites()[Math.round((float) super.getFrameCounter() / super.getSpriteAnimationSpeed())];
            case Direction.RIGHT -> image = super.getRightSprites()[Math.round((float) super.getFrameCounter() / super.getSpriteAnimationSpeed())];
        }
        graphics2D.drawImage(image, super.getX(), super.getY(), gameWindow.tileSize, gameWindow.tileSize, null);
    }
}
