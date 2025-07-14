package game;

import entity.Player;
import interactable.ParentInteractable;
import stages.AbstractStage;
import stages.StageOne;
import stages.StageType;
import stages.StageZero;
import tile.TileHandler;
import tile.TileInformation;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GameStateManager extends JPanel implements Runnable{

    /** Game tools **/
    int currentFPS = -1;
    GameState state = GameState.GAMEPLAY;
    Thread gameThread; // separates off the main thread which is running the window

    /** Map **/
    TileInformation tileInformation;
    TileHandler tileHandler;
    HashMap<StageType, AbstractStage> stageHashMap = new HashMap<>();
    StageType currentStage = StageType.CITY;
    StageType nextStage = StageType.WOODS;

    /** Handlers **/
    KeyPressHandler keyPressHandler = new KeyPressHandler();
    CollisionHandler collisionHandler = new CollisionHandler(this);
    MouseHandler mouseHandler = new MouseHandler();
    public SoundHandler soundHandler = new SoundHandler();

    /** Player Information **/
    Player player = new Player(keyPressHandler, collisionHandler, mouseHandler,this,Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);

    /** Items **/
    public ParentInteractable[] items;
    public void setItems(ParentInteractable[] items) { this.items = items;}


    /**
     * Single implementation
     * Allows GameStateManager to be alerted by interactables */
    private static GameStateManager gameStateManager;
    public static GameStateManager getInstance() {
        if (gameStateManager == null) {
            gameStateManager = new GameStateManager();
        }
        return gameStateManager;
    }


    /**
     * Constructor (duh) creates the game manager...
     * */
    private GameStateManager() {
        // JPanel Set Up
        super.setBackground(Color.BLACK);
        super.setDoubleBuffered(true);
        super.addKeyListener(keyPressHandler);
        super.addMouseListener(mouseHandler);
        super.addMouseMotionListener(mouseHandler);
        super.setFocusable(true);

        // Creates all the map information
        this.stageHashMap.put(StageType.CITY, new StageOne());
        this.stageHashMap.put(StageType.WOODS, new StageZero());

        // Set up first stage
        this.loadStage(this.currentStage);
    }


    /**
     * Called by the ladder interactable
     * Informs the GameStateHandler to navigate to a new stage and triggers next frame
     * */
    public void alertNewStage(StageType stageType) {
        this.nextStage = stageType;
        this.state = GameState.SWITCHING_STAGE;
        System.out.println("Going to next stage: " + stageType);
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

        // Used for FPS Calculation
        long timer = 0;
        int drawCount = 0;
        //

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime; // FPS calc
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

                drawCount++; // FPS Calc
            }

            // FPS Calc
            if (timer >= 1000000000) {
                this.currentFPS = drawCount;
//                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
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
     * Decides on what to draw based on this.state
     * */
    public void paint(Graphics graphics) {

        super.paintComponent(graphics); // Calls JPanel to draw black for screen wipe
        Graphics2D graphics2d = (Graphics2D) graphics;

        switch (this.state) {
            case TITLE -> this.paintTitleScreen(graphics2d);
            case GAMEPLAY -> this.paintLevel(graphics2d);
            case SWITCHING_STAGE -> this.paintStageSwitch();
        }

        // Close the graphics object //
        graphics2d.dispose();
    }


    /**
     * On the next available frame, changes all the stage objects to this.nextStage
     * Resumes state to gameplay
     * */
    public void paintStageSwitch() {
        this.loadStage(this.nextStage);
        this.state = GameState.GAMEPLAY;
    }

    /**
     * Initial state
     * Paints the title screen
     * */
    public void paintTitleScreen(Graphics2D graphics2D){

    }

    /**
     * Sets all the current objects to the desired stage
     * Moves player to starting position of stage
     * */
    public void loadStage(StageType stageType) {
        this.tileInformation = this.stageHashMap.get(stageType).getTileInformation();
        this.tileHandler = this.stageHashMap.get(stageType).getTileHandler();
        this.setItems(this.stageHashMap.get(stageType).getFloorItems());
        this.soundHandler.stopSetPlayAndLoop(this.stageHashMap.get(stageType).getStageMusic());
        this.player.setX(this.stageHashMap.get(stageType).getPlayerSpawnX());
        this.player.setY(this.stageHashMap.get(stageType).getPlayerSpawnY());
    }

    /**
     * Main stage painter
     * Updates all gameplay objects during main loop
     * */
    public void paintLevel(Graphics2D graphics2d) {

        long drawingStartTime = 0;
        if (keyPressHandler.debug) {
            drawingStartTime = System.nanoTime();
        }

        if (!soundHandler.isPlaying()) {
            soundHandler.setFile(this.stageHashMap.get(StageType.CITY).getStageMusic());
            soundHandler.play();
        }

        // Configure Font //
        graphics2d.setColor(Color.white);
        graphics2d.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Draw Map //
        this.tileHandler.drawAllTiles(graphics2d, player);

        // Draw Items //
        for (ParentInteractable item : items) {
            if (item != null) item.draw(graphics2d, player, this.collisionHandler.isPlayerOnTop(player, item));
        }


        // Draw Player //
        this.player.drawPlayer(graphics2d);

        if (keyPressHandler.debug) {
            long drawingEndTime = System.nanoTime();
            long timePassed = drawingEndTime - drawingStartTime;
            graphics2d.setColor(Color.white);
            graphics2d.drawString("FPS: " + this.currentFPS , 10, 380);
            graphics2d.drawString("Draw Time: " + TimeUnit.NANOSECONDS.toMillis(timePassed) + " ms" , 10, 400);

        }
    }
}
