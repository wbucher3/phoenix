package game;

import util.ControlBindings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressHandler extends KeyAdapter {

    public boolean upPressed, downPressed, leftPressed, rightPressed, jumpPressed = false;

    public boolean isKeyPressed() {
        return this.rightPressed || this.leftPressed || this.jumpPressed;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();

        if (ControlBindings.LEFT == code) {
            this.leftPressed = true;
        }
        if (ControlBindings.RIGHT == code) {
            this.rightPressed = true;
        }
        if (ControlBindings.SPACE == code) {
            this.jumpPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int code = keyEvent.getKeyCode();

        if (KeyEvent.VK_A == code) {
            this.leftPressed = false;
        }
        if (KeyEvent.VK_D == code) {
            this.rightPressed = false;
        }
        if (KeyEvent.VK_SPACE == code) {
            this.jumpPressed = false;
        }

    }
}
