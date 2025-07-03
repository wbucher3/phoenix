package entity;

import game.CollisionHandler;
import game.GameWindow;
import game.KeyPressHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

//    GameWindow gameWindow;
    KeyPressHandler keyPressHandler;
    CollisionHandler collisionHandler;

    public Player(KeyPressHandler keyPressHandler, CollisionHandler collisionHandler) {
        super(2, 20);
        this.keyPressHandler = keyPressHandler;
        this.collisionHandler = collisionHandler;
        super.setX(400);
        super.setY(400);
        super.setWidth(64);
        super.setHeight(64);
        super.setSpeed(4);
        super.setJumpPower(15);
        super.getSpriteImages("./assets/player/", ".png");
        super.setHitBox(new Rectangle(20, 20, 20, 20));
    }


    public void update() {

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

            // SPRITE WORK
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

        // CHECK COLLISION

        if (!this.collisionHandler.checkFloorCollision(this)) {
            super.setY(super.getY() + 9);
        }

        if (keyPressHandler.rightPressed && !this.collisionHandler.checkRightWallCollision(this)) {
            super.setX(super.getX() + super.getSpeed());
        }

        if (keyPressHandler.leftPressed && !this.collisionHandler.checkLeftWallCollision(this)) {
            super.setX(super.getX() - super.getSpeed());
        }

        if (keyPressHandler.jumpPressed && !this.collisionHandler.checkCeilingCollision(this)) {
            super.setY(super.getY() - super.getJumpPower());
        }

    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;

        switch (super.getDirection()) {
            case Direction.IDLE -> image = super.getIdleSprite();
            case Direction.LEFT -> image = super.getLeftSprites()[Math.round((float) super.getFrameCounter() / super.getSpriteAnimationSpeed())];
            case Direction.RIGHT -> image = super.getRightSprites()[Math.round((float) super.getFrameCounter() / super.getSpriteAnimationSpeed())];
            case Direction.JUMP -> image = super.getIdleSprite();
        }
        graphics2D.drawImage(image, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
    }
}
