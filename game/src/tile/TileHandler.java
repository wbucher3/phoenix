package tile;

import game.GameWindow;

import java.awt.*;
import java.io.*;

public class TileHandler {

    private GameWindow gameWindow;
    private Tile[] tiles;
    private int totalNumberTileTypes;

    int[][] tileMap;

    private int columns;
    private int rows;



    public TileHandler(GameWindow gameWindow) {
        this.totalNumberTileTypes = 2;
        this.tiles = new Tile[totalNumberTileTypes];
        this.gameWindow = gameWindow;
        this.initializeTiles();
        this.rows = 12;
        this.columns = 12;
        this.tileMap = new int[this.columns][this.rows];
        this.loadMap();
    }


    private void initializeTiles() {
        this.getTiles()[0] = new Tile("./assets/tiles/", "wood-block", ".png");
        this.getTiles()[1] = new Tile("./assets/tiles/", "stone-block", ".png");
        this.getTiles()[0].setCollidable(true);
    }

    private void loadMap() {
        String mapPath = "./assets/maps/map_0.txt";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapPath));

            for (int x = 0 ; x < this.rows ; x++) {
                String[] line = reader.readLine().split(" ");
                for (int y = 0 ; y < this.columns ; y++) {
                    this.tileMap[x][y] = Integer.parseInt(line[y]);
                }

            }
            reader.close();

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void drawAllTiles(Graphics2D graphics2D) {
        for (int x = 0 ; x < rows ; x++) {
            for (int y = 0 ; y < columns; y++) {
                this.getTiles()[this.tileMap[x][y]].draw(graphics2D,x * gameWindow.tileSize, y * gameWindow.tileSize, gameWindow.tileSize);
            }
        }
    }

    public GameWindow getGameWindow() {
        return gameWindow;
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int getTotalNumberTileTypes() {
        return totalNumberTileTypes;
    }

    public void setTotalNumberTileTypes(int totalNumberTileTypes) {
        this.totalNumberTileTypes = totalNumberTileTypes;
    }

    public int[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(int[][] tileMap) {
        this.tileMap = tileMap;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
