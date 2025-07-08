import game.AbstractStage;
import stages.StageOne;
import stages.StageZero;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Needed for Linux
        System.setProperty("sun.java2d.opengl", "true");

        JFrame window = new JFrame();
        window.setTitle("Game");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Sets the first stage
//        AbstractStage abstractStage = new StageZero();
        AbstractStage abstractStage = new StageOne();
        window.add(abstractStage);
        window.pack();
        window.setVisible(true);

        // starts the first stage
        abstractStage.startGameThread();
    }
}