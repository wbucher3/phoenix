package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private boolean leftMouseClicked, rightMouseClicked;
    private int mouseX;
    private int mouseY;


    @Override
    public void mousePressed(MouseEvent e) {
        int buttonClicked = e.getButton();

        if (buttonClicked == MouseEvent.BUTTON1) {
            this.leftMouseClicked = true;
        }
        if (buttonClicked == MouseEvent.BUTTON2) {
            this.rightMouseClicked = true;
        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int buttonClicked = e.getButton();

        if (buttonClicked == MouseEvent.BUTTON1) {
            this.leftMouseClicked = false;
        }
        if (buttonClicked == MouseEvent.BUTTON2) {
            this.rightMouseClicked = false;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    public int getMouseX() {return this.mouseX; }
    public int getMouseY() {return this.mouseY; }
    public boolean isLeftMouseClicked() {return this.leftMouseClicked;}
    public boolean isRightMouseClicked() {return this.rightMouseClicked;}
}
