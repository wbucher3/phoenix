package game;

import util.ControlBindings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressHandler extends KeyAdapter {

    public boolean upPressed, downPressed, leftPressed, rightPressed, jumpPressed = false;

    public boolean isKeyPressed() {
        return this.rightPressed || this.leftPressed || this.upPressed || this.downPressed;
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
        if (ControlBindings.UP == code) {
            this.upPressed = true;
        }
        if (ControlBindings.DOWN == code) {
            this.downPressed = true;
        }
        if (ControlBindings.SPACE == code) {
            this.jumpPressed = true;
        }

        if (KeyEvent.VK_ESCAPE == code) {
            System.exit(42069);
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        int code = keyEvent.getKeyCode();

        if (ControlBindings.LEFT == code) {
            this.leftPressed = false;
        }
        if (ControlBindings.RIGHT == code) {
            this.rightPressed = false;
        }
        if (ControlBindings.UP == code) {
            this.upPressed = false;
        }
        if (ControlBindings.DOWN == code) {
            this.downPressed = false;
        }
        if (KeyEvent.VK_SPACE == code) {
            this.jumpPressed = false;
        }

    }
}
