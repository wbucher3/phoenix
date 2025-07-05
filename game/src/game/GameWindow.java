package game;

import entity.Player;
import tile.Tile;
import tile.TileHandler;
import tile.TileInformation;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel implements Runnable{

    // Screen Settings //

    final int maxScreenColumns = 25;
    final int maxScreenRows = 20;

    public final int tileSize = 64;

    final int screenWidth = 1280;
    final int screenHeight = 800;

    // Game tools //
    Thread gameThread; // separates off the main thread which is running the window
    public int maxFPS = 60;

    // Handlers //
    KeyPressHandler keyPressHandler = new KeyPressHandler();
    CollisionHandler collisionHandler = new CollisionHandler(this);

    // Player Information //
    Player player = new Player(keyPressHandler, collisionHandler, screenWidth / 2, screenHeight / 2);

    // Map //
    TileInformation level1 = new TileInformation("./assets/maps/testmap.csv", "./assets/tiles/", new String[]{"stone-block", "wood-block"}, new boolean[]{false, true});
    TileHandler tileHandler = new TileHandler(maxScreenRows, maxScreenColumns, tileSize, 2, level1);

    public GameWindow() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
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
        this.tileHandler.drawAllTiles(g2, player);
        this.player.drawPlayer(g2);
        g2.dispose();
    }
}
