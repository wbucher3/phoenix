package entity;

import game.CollisionHandler;
import game.KeyPressHandler;

import java.awt.*;

public class Player extends Entity {

    KeyPressHandler keyPressHandler;
    CollisionHandler collisionHandler;

    private final int screenCenterX;
    private final int screenCenterY;

    public Player(KeyPressHandler keyPressHandler, CollisionHandler collisionHandler, int screenCenterX, int screenCenterY) {
        super(8, 32); // animation speed % total frames === 0 for smooth animations
        this.keyPressHandler = keyPressHandler;
        this.collisionHandler = collisionHandler;

        super.setX(3 * 64);
        super.setY(3 * 64);
        super.setWidth(64);
        super.setHeight(64);
        super.setSpeed(8);
        this.screenCenterX = screenCenterX - (super.getWidth() / 2);
        this.screenCenterY = screenCenterY - (super.getHeight() / 2);

        super.readSpriteImages("./assets/cat/", ".png");
        super.setHitBox(new Rectangle(20, 30, 20, 20));

    }

    public void drawPlayer(Graphics2D graphics2D) {
        // player is always in the center of the screen
        super.draw(graphics2D, this.screenCenterX, this.screenCenterY);
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
        if (keyPressHandler.isMovementKeyPressed()) {
            if (keyPressHandler.rightPressed) {
                super.setDirection(Direction.RIGHT);
            }
            if (keyPressHandler.leftPressed) {
                super.setDirection(Direction.LEFT);
            }
            if (keyPressHandler.upPressed) {
                super.setDirection(Direction.UP);
            }
            if (keyPressHandler.downPressed) {
                super.setDirection(Direction.DOWN);
            }
            super.setCurrentState(State.WALK);

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
        if (keyPressHandler.upPressed && !this.collisionHandler.checkUpCollision(this)) {
            super.setY(super.getY() - super.getSpeed());
        }
        if (keyPressHandler.downPressed && !this.collisionHandler.checkDownCollision(this)) {
            super.setY(super.getY() + super.getSpeed());
        }

    }

    // Needed for drawing of tiles
    public int getScreenCenterX() { return screenCenterX; }
    public int getScreenCenterY() { return screenCenterY; }

}
