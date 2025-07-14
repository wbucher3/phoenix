package tile;

import entity.Player;

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
        this.tileMap = new int[this.rows][this.columns];
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

            for (int y = 0 ; y < this.rows; y++) {
                String[] line = reader.readLine().split(",");
                for (int x = 0 ; x < this.columns; x++) {
                    this.tileMap[y][x] = Integer.parseInt(line[x]);
                }
            }
            reader.close();

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void drawAllTiles(Graphics2D graphics2D, Player player) {
        for (int y = 0 ; y < rows ; y++) {
            for (int x = 0 ; x < columns; x++) {
                // gets where the tile is to relation to rest of tiles
                int worldY = y * this.tileSize;
                int worldX =  x * this.tileSize;
                // puts all the tiles in relation to the player being in the center of screen
                int screenX = worldX - player.getX() + player.getScreenCenterX();
                int screenY = worldY - player.getY() + player.getScreenCenterY();

                // checks if the tile is on the screen before drawing it (1 tile extra to keep it smooth)
                if (worldX + tileSize > player.getX() - player.getScreenCenterX()
                        && worldX - tileSize < player.getX() + player.getScreenCenterX()
                        && worldY + tileSize > player.getY() - player.getScreenCenterY()
                        && worldY - tileSize < player.getY() + player.getScreenCenterY()) {

                    this.getTiles()[this.tileMap[y][x]].draw(graphics2D, screenX, screenY, this.tileSize);
                }


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


    public int[][] getTileMap() {
        return tileMap;
    }

}
