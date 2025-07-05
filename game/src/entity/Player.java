package entity;

import game.CollisionHandler;
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
        super.setJumpPower(22);
        this.screenCenterX = screenCenterX - (super.getWidth() / 2);
        this.screenCenterY = screenCenterY - (super.getHeight() / 2);
        super.getSpriteImages("./assets/player_sprites/", ".png");
        super.setHitBox(new Rectangle(20, 20, 30, 30));

    }

    public void drawPlayer(Graphics2D graphics2D) {
        super.draw(graphics2D, screenCenterX, screenCenterY);
    }

    public void update() {
        // TICK SPRITE FRAME
        if (super.getFrameCounter() < super.getSpriteAnimationSpeed()) {
            super.setFrameCounter(super.getFrameCounter() + 1);
        } else {
            super.setFrameCounter(0);
        }
        super.setPreviousState(super.getCurrentState());

        // MOVEMENT
        if (keyPressHandler.isKeyPressed()) {
            if (keyPressHandler.rightPressed) {
                super.setDirection(Direction.RIGHT);
                super.setCurrentState(State.WALK);
            }
            if (keyPressHandler.leftPressed) {
                super.setDirection(Direction.LEFT);
                super.setCurrentState(State.WALK);
            }
            if (keyPressHandler.jumpPressed) {
                super.setCurrentState(State.JUMP);
            }
        } else if (!this.collisionHandler.checkFloorCollision(this)) {
            super.setCurrentState(State.FALLING);
        } else {
            super.setCurrentState(State.IDLE);
        }

        // CHECK COLLISION
        if (keyPressHandler.rightPressed && !this.collisionHandler.checkRightWallCollision(this)) {
            super.setX(super.getX() + super.getSpeed());
        }

        if (keyPressHandler.leftPressed && !this.collisionHandler.checkLeftWallCollision(this)) {
            super.setX(super.getX() - super.getSpeed());
        }

        if (keyPressHandler.jumpPressed && !this.collisionHandler.checkCeilingCollision(this) && super.getJumpFrames() < 10) {
            super.setJumpFrames(super.getJumpFrames() + 1);
            super.setY(super.getY() - super.getJumpPower());
        }

        if (!this.collisionHandler.checkFloorCollision(this)) {
            super.setY(super.getY() + Constants.GRAVITY);
        } else {
            super.setJumpFrames(0);
        }

    }


    // Needed for drawing of tiles
    public int getScreenCenterX() { return screenCenterX; }
    public int getScreenCenterY() { return screenCenterY; }

}
