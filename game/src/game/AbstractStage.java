package game;

import entity.Player;
import interactable.ParentInteractable;
import tile.TileHandler;
import tile.TileInformation;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public abstract class AbstractStage extends JPanel implements Runnable{

    // Game tools //
    Thread gameThread; // separates off the main thread which is running the window

    // Map //
    TileInformation tileInformation;
    TileHandler tileHandler;

    // Handlers //
    KeyPressHandler keyPressHandler = new KeyPressHandler();
    CollisionHandler collisionHandler = new CollisionHandler(this);
    MouseHandler mouseHandler = new MouseHandler();
    public SoundHandler soundHandler = new SoundHandler();

    // Player Information //
    Player player = new Player(keyPressHandler, collisionHandler, this,Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);

    // Items //
    public ParentInteractable[] items;



    /**
     * Constructor (duh) creates the stage...
     * */
    public AbstractStage(int maxMapRows, int maxMapColumns, TileInformation tileInformation, ParentInteractable[] items) {
        // JPanel Set Up
        super.setBackground(Color.BLACK);
        super.setDoubleBuffered(true);
        super.addKeyListener(keyPressHandler);
        super.addMouseListener(mouseHandler);
        super.setFocusable(true);

        // Stage Set Up
        this.tileInformation = tileInformation;
        this.tileHandler = new TileHandler(maxMapRows, maxMapColumns, Constants.TILE_SIZE, 2, tileInformation);
        this.items = items;
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
        double drawInterval = (double) 1000000000 / Constants.GAME_FPS;
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

        long drawingStartTime = 0;
        if (keyPressHandler.debug) {
            drawingStartTime = System.nanoTime();
        }

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

        if (keyPressHandler.debug) {
            long drawingEndTime = System.nanoTime();
            long timePassed = drawingEndTime - drawingStartTime;
            graphics2d.setColor(Color.white);
            graphics2d.drawString("Draw Time: " + TimeUnit.NANOSECONDS.toMillis(timePassed) + " ms" , 10, 400);
        }

        // Close the graphics object //
        graphics2d.dispose();
    }
}
