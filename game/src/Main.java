import game.AbstractFloor;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");


        JFrame window = new JFrame();
        window.setTitle("Game");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        AbstractFloor abstractFloor = new AbstractFloor();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.add(abstractFloor);
        window.pack();
        window.setVisible(true);


        // starts level
        abstractFloor.startGameThread();
    }
}