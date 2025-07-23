import game.GameStateManager;
import stages.StageOne;
import util.Constants;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Needed for Linux
        System.setProperty("sun.java2d.opengl", "true");

        JFrame window = new JFrame();
        window.setTitle(Constants.TITLE);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Sets the game manager up
        GameStateManager gameStateManager = GameStateManager.getInstance();
        window.add(gameStateManager);
        window.pack();
        window.setVisible(true);

        // starts the first stage
        gameStateManager.startGameThread();


    }
}