package game;

import entity.Player;
import interactable.Chest;
import interactable.Door;
import interactable.Key;
import interactable.ParentInteractable;
import tile.TileHandler;
import tile.TileInformation;

import javax.swing.*;
import java.awt.*;

public class AbstractFloor extends JPanel implements Runnable{

    // Screen Settings //
    final int screenWidth = 1920;
    final int screenHeight = 1080;

    final int maxMapColumns = 25;
    final int maxMapRows = 20;

    public final int tileSize = 64;

    // Game tools //
    Thread gameThread; // separates off the main thread which is running the window
    public int maxFPS = 60;

    // Map //
    TileInformation level1 = new TileInformation("./assets/maps/testmap2.csv", "./assets/tiles/", new String[]{"grass-block", "stone-block"}, new boolean[]{false, true});
    TileHandler tileHandler = new TileHandler(maxMapRows, maxMapColumns, tileSize, 2, level1);

    // Handlers //
    KeyPressHandler keyPressHandler = new KeyPressHandler();
    CollisionHandler collisionHandler = new CollisionHandler(this);
    MouseHandler mouseHandler = new MouseHandler();


    // Player Information //
    Player player = new Player(keyPressHandler, collisionHandler, screenWidth / 2, screenHeight / 2);


    // Items //
    public ParentInteractable[] items = new ParentInteractable[10];


    public AbstractFloor() {
        super.setBackground(Color.BLACK);
        super.setDoubleBuffered(true);
        super.addKeyListener(keyPressHandler);
        super.addMouseListener(mouseHandler);
        super.setFocusable(true);

        // Sets up the item pool
        this.initializeGame();
    }


    /**
     * Creates all the interactable objects for the world
     * */
    public void initializeGame() {
        // Create all the assets
        items[0] = new Key("Key 1",  tileSize * 6, tileSize * 4, false);
        items[1] = new Door("Door 1 Left",  tileSize * 6, tileSize * 6, false);
        items[2] = new Door("Door 1 Right",  tileSize * 7, tileSize * 6, false);
        items[3] = new Chest("Chest 1",  tileSize * 14, tileSize * 9, false);
        items[4] = new Key("Key 2",  tileSize * 14, tileSize * 12, false);
    }

    /**
     * Creates a new thread and starts it
     * Call to begin the level
     * */
    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     * Main game loop
     * Ensure the update() and paintComponent() methods run at the set FPS
     * */
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


    /**
     * Performs actions of the frame
     * */
    public void update() {
        this.player.update();
    }


    /**
     * Draws actions of the frame
     * */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics; // This draws the black background and ensure screen wipe

        // Draw Map //
        this.tileHandler.drawAllTiles(graphics2d, player);

        // Draw Items //
        for (ParentInteractable item : items) {
            if (item != null) item.draw(graphics2d, player);
        }

        // Draw Player //
        this.player.drawPlayer(graphics2d);

        // Close the graphics object //
        graphics2d.dispose();
    }
}
