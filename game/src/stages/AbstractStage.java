package stages;

import interactable.ParentInteractable;
import tile.TileHandler;
import tile.TileInformation;
import util.Constants;

import java.io.File;

public abstract class AbstractStage {

    private int maxMapRows;
    private int maxMapColumns;
    private TileInformation tileInformation;
    private TileHandler tileHandler;
    private ParentInteractable[] floorItems;
    private int playerSpawnX;
    private int playerSpawnY;
    private final File stageMusic;

    public AbstractStage(int maxMapRows, int maxMapColumns, int playerSpawnX, int playerSpawnY, TileInformation tileInformation, File stageMusic) {
        this.maxMapRows = maxMapRows;
        this.maxMapColumns = maxMapColumns;
        this.playerSpawnX = playerSpawnX;
        this.playerSpawnY = playerSpawnY;
        this.tileInformation = tileInformation;
        this.floorItems = this.getFloorItems();
        this.tileHandler = new TileHandler(maxMapRows, maxMapColumns, Constants.TILE_SIZE, 2, tileInformation);
        this.stageMusic = stageMusic;
    }

    public abstract ParentInteractable[] getFloorItems();
    public File getStageMusic() { return this.stageMusic;} ;

    public int getMaxMapRows() {
        return maxMapRows;
    }

    public void setMaxMapRows(int maxMapRows) {
        this.maxMapRows = maxMapRows;
    }

    public int getMaxMapColumns() {
        return maxMapColumns;
    }


    public TileInformation getTileInformation() {
        return tileInformation;
    }


    public TileHandler getTileHandler() {
        return tileHandler;
    }

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawnX(int playerSpawnX) {
        this.playerSpawnX = playerSpawnX;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public void setPlayerSpawnY(int playerSpawnY) {
        this.playerSpawnY = playerSpawnY;
    }
}
