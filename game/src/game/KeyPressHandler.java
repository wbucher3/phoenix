package game;

import util.ControlBindings;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyPressHandler extends KeyAdapter {

    public boolean upPressed, downPressed, leftPressed, rightPressed = false;
    public boolean debug = false;
    public boolean interactPressed = false;

    public boolean isMovementKeyPressed() {
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
        if (ControlBindings.INTERACT == code) {
            this.interactPressed = true;
        }

        if (KeyEvent.VK_T == code) debug = !debug;


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
        if (ControlBindings.INTERACT == code) {
            this.interactPressed = false;
        }

    }
}
