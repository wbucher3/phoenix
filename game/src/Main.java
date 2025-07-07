import game.GameWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");


        JFrame window = new JFrame();
        window.setTitle("Game");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        GameWindow gameWindow = new GameWindow();
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.add(gameWindow);
        window.pack();
        window.setVisible(true);

        gameWindow.startGameThread();
    }
}