package entity;

import game.CollisionHandler;
import game.GameWindow;
import game.KeyPressHandler;
import util.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyPressHandler keyPressHandler;
    CollisionHandler collisionHandler;

    private final int screenCenterX;
    private final int screenCenterY;

    public Player(KeyPressHandler keyPressHandler, CollisionHandler collisionHandler, int screenCenterX, int screenCenterY) {
        super(6, 60);
        this.keyPressHandler = keyPressHandler;
        this.collisionHandler = collisionHandler;

        super.setX(200);
        super.setY(200);
        super.setWidth(64);
        super.setHeight(64);
        super.setSpeed(8);
        super.setJumpFrames(10);
        super.setJumpPower(15);
        this.screenCenterX = screenCenterX - (super.getWidth() / 2);
        this.screenCenterY = screenCenterY - (super.getHeight() / 2);
        super.getSpriteImages("./assets/player_sprites/", ".png");
        super.setHitBox(new Rectangle(20, 20, 30, 30));

    }


    public void update() {

        // TICK SPRITE FRAME
        if (super.getFrameCounter() < super.getSpriteAnimationSpeed()) {
            super.setFrameCounter(super.getFrameCounter() + 1);
        } else {
            super.setFrameCounter(0);
        }
        super.setPreviousDirection(super.getDirection());

        // MOVEMENT
        if (keyPressHandler.isKeyPressed()) {
            if (keyPressHandler.rightPressed) {
                super.setDirection(Direction.RIGHT);
            }
            if (keyPressHandler.leftPressed) {
                super.setDirection(Direction.LEFT);
            }
            if (keyPressHandler.jumpPressed) {
                super.setDirection(Direction.JUMP);
            }
        } else if (!this.collisionHandler.checkFloorCollision(this) ){
            super.setDirection(Direction.FALLING);
        } else {
            super.setDirection(Direction.IDLE);
        }

        // CHECK COLLISION



        if (keyPressHandler.rightPressed && !this.collisionHandler.checkRightWallCollision(this)) {
            super.setX(super.getX() + super.getSpeed());
        }

        if (keyPressHandler.leftPressed && !this.collisionHandler.checkLeftWallCollision(this)) {
            super.setX(super.getX() - super.getSpeed());
        }

        if (keyPressHandler.jumpPressed && !this.collisionHandler.checkCeilingCollision(this) && super.getJumpFrames() < 25) {
            super.setJumpFrames(super.getJumpFrames() + 1);
            super.setY(super.getY() - super.getJumpPower());
        }

        if (!this.collisionHandler.checkFloorCollision(this)) {
            super.setY(super.getY() + Constants.GRAVITY);
        } else {
            super.setJumpFrames(0);
        }

    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        if (super.getPreviousDirection() != super.getDirection()) {
            super.setFrameCounter(0);
        }

        switch (super.getDirection()) {
            case Direction.IDLE ->    image = super.getIdleSprites()[getSpriteValue()];
            case Direction.JUMP ->    image = super.getJumpSprites()[getSpriteValue()];
            case Direction.LEFT ->    image = super.getLeftSprites()[getSpriteValue()];
            case Direction.RIGHT ->   image = super.getRightSprites()[getSpriteValue()];
            case Direction.FALLING -> image = super.getFallingSprites()[getSpriteValue()];
        }
        graphics2D.drawImage(image, screenCenterX, screenCenterY, super.getWidth(), super.getHeight(), null);
    }

    // TODO there has to be a better way to do this with math but I am lazy right now
    // There's a good chance this stays forever lol
    private int getSpriteValue() {
        int spriteValue;
        if (super.getFrameCounter() >= 0 && super.getFrameCounter() < 10) spriteValue = 0;
        else if (super.getFrameCounter() >= 10 && super.getFrameCounter() < 20) spriteValue = 1;
        else if (super.getFrameCounter() >= 20 && super.getFrameCounter() < 30) spriteValue = 2;
        else if (super.getFrameCounter() >= 30 && super.getFrameCounter() < 40) spriteValue = 3;
        else if (super.getFrameCounter() >= 40 && super.getFrameCounter() < 50) spriteValue = 4;
        else spriteValue = 5;
        return spriteValue;
    }

    // Needed for drawing of tiles
    public int getScreenCenterX() { return screenCenterX; }
    public int getScreenCenterY() { return screenCenterY; }

}
