package entity;

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
    private int jumpPower;
    private int jumpFrames;

    private Direction direction;
    private State currentState;
    private State previousState;


    // collision
    private Rectangle hitBox;

    // Sprite Fields
    private BufferedImage[] idleSprites;
    private BufferedImage[] duckSprites;
    private BufferedImage[] fallingSprites;
    private BufferedImage[] jumpSprites;
    private BufferedImage[] walkSprites;
//    private Direction previousDirection;

    // Sprite math
    private int totalFrames;
    private int frameCounter = 0;
    private int spriteAnimationSpeed;


    public Entity(int totalFrames, int spriteAnimationSpeed) {
        this.totalFrames = totalFrames;
        this.spriteAnimationSpeed = spriteAnimationSpeed;
        this.walkSprites = new BufferedImage[totalFrames];
        this.idleSprites = new BufferedImage[totalFrames];
        this.jumpSprites = new BufferedImage[totalFrames];
        this.fallingSprites = new BufferedImage[totalFrames];
        this.direction = Direction.RIGHT;
    }

    abstract public void update();

    public void getSpriteImages(String spriteDirectoryPath, String fileExtension) {
        try {
            for (int i = 0; i < this.getTotalFrames() ; i++) {
                String walkPath = spriteDirectoryPath + "walk/" + i + fileExtension;
                String idlePath = spriteDirectoryPath + "idle/" + i + fileExtension;
                String jumpPath = spriteDirectoryPath + "jump/" + i + fileExtension;
                String fallingPath = spriteDirectoryPath + "falling/" + i + fileExtension;

                this.getIdleSprites()[i] = ImageIO.read(new File(idlePath));
                this.getWalkSprites()[i] = ImageIO.read(new File(walkPath));
                this.getJumpSprites()[i] = ImageIO.read(new File(jumpPath));
                this.getFallingSprites()[i] = ImageIO.read(new File(fallingPath));
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Ran into an error fetching sprite images. Expected image path: " + spriteDirectoryPath);
        }
    }

    public void draw(Graphics2D graphics2D, int x, int y) {
        BufferedImage image = null;

        if (this.getPreviousState() != this.getCurrentState()) {
            this.setFrameCounter(0);
        }

        switch (this.getCurrentState()) {
            case State.IDLE ->    image = this.getIdleSprites()[getSpriteValue()];
            case State.JUMP ->    image = this.getJumpSprites()[getSpriteValue()];
            case State.WALK ->    image = this.getWalkSprites()[getSpriteValue()];
            case State.FALLING -> image = this.getFallingSprites()[getSpriteValue()];
        }
        if (this.getDirection() == Direction.RIGHT) {
            graphics2D.drawImage(image, x, y, this.getWidth(), this.getHeight(), null);
        } else {
            graphics2D.drawImage(image, x + this.getWidth(), y, -this.getWidth(), this.getHeight(), null);
        }
    }

    // TODO there has to be a better way to do this with math but I am lazy right now
    // There's a good chance this stays forever lol
    private int getSpriteValue() {
        int spriteValue;
        if (this.getFrameCounter() >= 0 && this.getFrameCounter() < 10) spriteValue = 0;
        else if (this.getFrameCounter() >= 10 && this.getFrameCounter() < 20) spriteValue = 1;
        else if (this.getFrameCounter() >= 20 && this.getFrameCounter() < 30) spriteValue = 2;
        else if (this.getFrameCounter() >= 30 && this.getFrameCounter() < 40) spriteValue = 3;
        else if (this.getFrameCounter() >= 40 && this.getFrameCounter() < 50) spriteValue = 4;
        else spriteValue = 5;
        return spriteValue;
    }


    public BufferedImage[] getWalkSprites() {
        return walkSprites;
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


    public BufferedImage[] getDuckSprites() {
        return duckSprites;
    }


    public BufferedImage[] getJumpSprites() {
        return jumpSprites;
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

    public int getJumpPower() { return jumpPower; }

    public void setJumpPower(int jumpPower) { this.jumpPower = jumpPower; }

    public int getJumpFrames() {
        return jumpFrames;
    }

    public void setJumpFrames(int jumpFrames) {
        this.jumpFrames = jumpFrames;
    }

    public BufferedImage[] getFallingSprites() {
        return fallingSprites;
    }

    public void setFallingSprites(BufferedImage[] fallingSprites) {
        this.fallingSprites = fallingSprites;
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
