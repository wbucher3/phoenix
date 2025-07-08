package entity;

import util.UtilityFunctions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity {

    // Location and movement
    private int x;
    private int y;
    private int height;
    private int width;
    private int speed;

    private Direction direction;
    private State currentState;
    private State previousState;


    // collision
    private Rectangle hitBox;

    // Sprite Fields
    private BufferedImage[] idleSprites;
    private BufferedImage[] walkRightSprites;
    private BufferedImage[] walkLeftSprites;
    private BufferedImage[] walkUpSprites;
    private BufferedImage[] walkDownSprites;

    // Sprite math
    private int frameCounter = 0;
    private final int totalFrames;
    private final int spriteAnimationSpeed;


    public Entity(int totalFrames, int spriteAnimationSpeed) {
        // Basic starting values
        this.totalFrames = totalFrames;
        this.spriteAnimationSpeed = spriteAnimationSpeed;
        this.direction = Direction.DOWN;


        // Initialize all array for sprites
        this.walkRightSprites = new BufferedImage[totalFrames];
        this.walkLeftSprites = new BufferedImage[totalFrames];
        this.walkDownSprites = new BufferedImage[totalFrames];
        this.walkUpSprites = new BufferedImage[totalFrames];
        this.idleSprites = new BufferedImage[totalFrames];
    }

    abstract public void update();

    public void readSpriteImages(String spriteDirectoryPath, String fileExtension) {
        for (int i = 0; i < this.getTotalFrames() ; i++) {
            this.idleSprites[i] = UtilityFunctions.readImageFile(spriteDirectoryPath + "right_walk/", String.valueOf(i), fileExtension);
            this.walkUpSprites[i] = UtilityFunctions.readImageFile(spriteDirectoryPath + "up_walk/", String.valueOf(i), fileExtension);
            this.walkDownSprites[i] = UtilityFunctions.readImageFile(spriteDirectoryPath + "down_walk/", String.valueOf(i), fileExtension);
            this.walkRightSprites[i] = UtilityFunctions.readImageFile(spriteDirectoryPath + "right_walk/", String.valueOf(i), fileExtension);
            this.walkLeftSprites[i] = UtilityFunctions.readImageFile(spriteDirectoryPath + "left_walk/", String.valueOf(i), fileExtension);
        }
    }

    public void draw(Graphics2D graphics2D, int x, int y) {
        BufferedImage image = null;

        if (this.getPreviousState() != this.getCurrentState()) {
            this.setFrameCounter(0);
        }

        if (this.currentState == State.WALK) {
            switch (this.getDirection()) {
                case Direction.UP -> image = this.walkUpSprites[getSpriteValue()];
                case Direction.RIGHT -> image = this.walkRightSprites[getSpriteValue()];
                case Direction.DOWN -> image = this.walkDownSprites[getSpriteValue()];
                case Direction.LEFT -> image = this.walkLeftSprites[getSpriteValue()];
            }
        } else {
            image = this.walkDownSprites[0];
        }
        graphics2D.drawImage(image, x, y, this.getWidth(), this.getHeight(), null);

    }

    // TODO there has to be a better way to do this with math but I am lazy right now
    // There's a good chance this stays forever lol
    private int getSpriteValue() {
        int spriteValue;
        if (this.getFrameCounter() >= 0 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 1) spriteValue = 0;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 1 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 2) spriteValue = 1;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 2 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 3) spriteValue = 2;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 3 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 4) spriteValue = 3;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 4 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 5) spriteValue = 4;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 5 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 6) spriteValue = 5;
        else if (this.getFrameCounter() >= (spriteAnimationSpeed / totalFrames) * 6 && this.getFrameCounter() < (spriteAnimationSpeed / totalFrames) * 7) spriteValue = 6;
        else spriteValue = 7;
        return spriteValue;
    }



    public int getTotalFrames() {
        return totalFrames;
    }


    public int getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public BufferedImage[] getIdleSprites() {
        return idleSprites;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpriteAnimationSpeed() {
        return spriteAnimationSpeed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }
}
