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

    // collision
    private Rectangle hitBox;

    // Sprite Fields
    private BufferedImage idleSprite;
    private BufferedImage[] duckSprite;
    private BufferedImage[] jumpSprite;
    private BufferedImage[] rightSprites;
    private BufferedImage[] leftSprites;
    private Direction direction;
    private Direction previousDirection;

    // Sprite math
    private int totalFrames;
    private int frameCounter = 1;
    private int spriteAnimationSpeed;


    public Entity(int totalFrames, int spriteAnimationSpeed) {
        this.totalFrames = totalFrames;
        this.spriteAnimationSpeed = spriteAnimationSpeed;
        this.rightSprites = new BufferedImage[totalFrames];
        this.leftSprites = new BufferedImage[totalFrames];
        this.direction = Direction.IDLE;
    }

    public void getSpriteImages(String spriteDirectoryPath, String fileExtension) {
        try {
            this.setIdleSprite(ImageIO.read(new File(spriteDirectoryPath + "0" + fileExtension)));
            for (int i = 0; i < this.getTotalFrames() ; i++) {
                String path = spriteDirectoryPath + i + fileExtension;
                this.getLeftSprites()[i] = ImageIO.read(new File(path));
                this.getRightSprites()[i] = ImageIO.read(new File(path));
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Ran into an error fetching sprite images. Expected image path: " + spriteDirectoryPath);

        }
    }

    abstract public void update();
    abstract public void draw(Graphics2D graphics2D);

    public BufferedImage[] getRightSprites() {
        return rightSprites;
    }

    public void setRightSprites(BufferedImage[] rightSprites) {
        this.rightSprites = rightSprites;
    }

    public BufferedImage[] getLeftSprites() {
        return leftSprites;
    }

    public void setLeftSprites(BufferedImage[] leftSprites) {
        this.leftSprites = leftSprites;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
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

    public BufferedImage getIdleSprite() {
        return idleSprite;
    }

    public void setIdleSprite(BufferedImage idleSprite) {
        this.idleSprite = idleSprite;
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

    public void setSpriteAnimationSpeed(int spriteAnimationSpeed) {
        this.spriteAnimationSpeed = spriteAnimationSpeed;
    }

    public BufferedImage[] getDuckSprite() {
        return duckSprite;
    }

    public void setDuckSprite(BufferedImage[] duckSprite) {
        this.duckSprite = duckSprite;
    }

    public BufferedImage[] getJumpSprite() {
        return jumpSprite;
    }

    public void setJumpSprite(BufferedImage[] jumpSprite) {
        this.jumpSprite = jumpSprite;
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
}
