package game;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel implements Runnable{

    // Screen Settings //

    final int originalTileSize = 64;
    final int scale = 2;
    final int maxScreenColumns = 10;
    final int getMaxScreenRows = 6;

    public final int tileSize = originalTileSize * scale;
    final int screenWidth = tileSize * maxScreenColumns;
    final int screenHeight = tileSize * getMaxScreenRows;

    // Game tools //
    Thread gameThread;
    KeyPressHandler keyPressHandler = new KeyPressHandler();
    public int maxFPS = 60;


    // Player Information //
    Player player = new Player(this, keyPressHandler);



    public GameWindow() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyPressHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }


    @Override
    public void run() {

        // Game is locked to FPS value or less
        double drawInterval = (double) 1000000000 / this.maxFPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }


    }



    public void update() {
        this.player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.player.draw(g2);
        g2.dispose();
    }
}
