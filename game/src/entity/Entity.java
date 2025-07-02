package entity;

import java.awt.image.BufferedImage;

public class Entity {

    private int x;
    private int y;
    private int speed;
    private int totalFrames;
    private int frameCounter = 1;
    private int spriteAnimationSpeed;

    private BufferedImage idleSprite;
    private BufferedImage[] rightSprites;
    private BufferedImage[] leftSprites;
    private Direction direction;
    private Direction previousDirection;

    public Entity(int totalFrames, int spriteAnimationSpeed) {
        this.totalFrames = totalFrames;
        this.spriteAnimationSpeed = spriteAnimationSpeed;
        this.rightSprites = new BufferedImage[totalFrames];
        this.leftSprites = new BufferedImage[totalFrames];
        this.direction = Direction.IDLE;
    }

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
}
