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

    // collision
    private Rectangle hitBox;

    // Sprite Fields
    private BufferedImage[] idleSprites;
    private BufferedImage[] duckSprites;
    private BufferedImage[] fallingSprites;
    private BufferedImage[] jumpSprites;
    private BufferedImage[] rightSprites;
    private BufferedImage[] leftSprites;
    private Direction direction;
    private Direction previousDirection;

    // Sprite math
    private int totalFrames;
    private int frameCounter = 0;
    private int spriteAnimationSpeed;


    public Entity(int totalFrames, int spriteAnimationSpeed) {
        this.totalFrames = totalFrames;
        this.spriteAnimationSpeed = spriteAnimationSpeed;
        this.rightSprites = new BufferedImage[totalFrames];
        this.leftSprites = new BufferedImage[totalFrames];
        this.idleSprites = new BufferedImage[totalFrames];
        this.jumpSprites = new BufferedImage[totalFrames];
        this.fallingSprites = new BufferedImage[totalFrames];
        this.direction = Direction.IDLE;
    }

    public void getSpriteImages(String spriteDirectoryPath, String fileExtension) {
        try {
            for (int i = 0; i < this.getTotalFrames() ; i++) {
                String walkPath = spriteDirectoryPath + "walk/" + i + fileExtension;
                String idlePath = spriteDirectoryPath + "idle/" + i + fileExtension;
                String jumpPath = spriteDirectoryPath + "jump/" + i + fileExtension;
                String fallingPath = spriteDirectoryPath + "falling/" + i + fileExtension;

                this.getIdleSprites()[i] = ImageIO.read(new File(idlePath));
                this.getLeftSprites()[i] = ImageIO.read(new File(walkPath));
                this.getRightSprites()[i] = ImageIO.read(new File(walkPath));
                this.getJumpSprites()[i] = ImageIO.read(new File(jumpPath));
                this.getFallingSprites()[i] = ImageIO.read(new File(fallingPath));
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            throw new RuntimeException("Ran into an error fetching sprite images. Expected image path: " + spriteDirectoryPath);

        }
    }


    abstract public void update();
    abstract public void draw(Graphics2D graphics2D);

    public BufferedImage[] getRightSprites() {
        return rightSprites;
    }


    public BufferedImage[] getLeftSprites() {
        return leftSprites;
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

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public void setPreviousDirection(Direction previousDirection) {
        this.previousDirection = previousDirection;
    }

    public BufferedImage[] getIdleSprites() {
        return idleSprites;
    }

    public void setIdleSprites(BufferedImage[] idleSprites) {
        this.idleSprites = idleSprites;
    }

    public BufferedImage[] getDuckSprites() {
        return duckSprites;
    }

    public void setDuckSprites(BufferedImage[] duckSprites) {
        this.duckSprites = duckSprites;
    }

    public BufferedImage[] getJumpSprites() {
        return jumpSprites;
    }

    public void setJumpSprites(BufferedImage[] jumpSprites) {
        this.jumpSprites = jumpSprites;
    }

    public void setRightSprites(BufferedImage[] rightSprites) {
        this.rightSprites = rightSprites;
    }

    public void setLeftSprites(BufferedImage[] leftSprites) {
        this.leftSprites = leftSprites;
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
}
