package tile;

import java.awt.*;
import java.io.*;

public class TileHandler {

    private Tile[] tiles;
    private int numberOfTileTypes;

    int[][] tileMap;

    private int columns;
    private int rows;
    private final int tileSize;

    private final TileInformation tileInformation;

    public TileHandler(int rows, int columns, int tileSize, int numberOfTileTypes, TileInformation tileInformation) {

        // tile handler consts
        this.numberOfTileTypes = numberOfTileTypes;
        this.rows = rows;
        this.columns = columns;
        this.tileSize = tileSize;

        // file paths
        this.tileInformation = tileInformation;

        // load the tile sprites
        this.tiles = new Tile[this.numberOfTileTypes];
        this.initializeTiles();

        // read in the tile map
        this.tileMap = new int[this.columns][this.rows];
        this.loadMap();
    }


    private void initializeTiles() {
        for (int i = 0 ; i < this.tileInformation.getNames().length ; i++) {
            this.getTiles()[i] = new Tile(tileInformation.getImageDirectory(), tileInformation.getNames()[i], ".png", tileInformation.getCollidable()[i]);
        }

    }

    private void loadMap() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.tileInformation.getTileMapDirectory()));

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
                this.getTiles()[this.tileMap[x][y]].draw(graphics2D,x * this.tileSize, y * this.tileSize, this.tileSize);
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int getNumberOfTileTypes() {
        return numberOfTileTypes;
    }

    public void setNumberOfTileTypes(int numberOfTileTypes) {
        this.numberOfTileTypes = numberOfTileTypes;
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
