package entity.enemy;

import entity.Entity;

public class TestEnemy extends Entity {

    public TestEnemy(int totalFrames, int spriteAnimationSpeed) {
        super(totalFrames, spriteAnimationSpeed);
        super.setSpeed(6);

    }



    @Override
    public void update() {

    }
}
