package stages;

import game.GameStateManager;
import interactable.ParentInteractable;
import tile.TileInformation;
import util.Constants;

import java.io.File;

public class StageZero extends AbstractStage {


    public StageZero() {
        super(20, 25, 3 * Constants.TILE_SIZE, 3 * Constants.TILE_SIZE,fetchTileInformation(), new File("./assets/music/test-song.wav"));
    }

    private static TileInformation fetchTileInformation() {
        return new TileInformation("./assets/maps/testmap1.csv",
                                    "./assets/tiles/",
                                                new String[]{"floor", "crazy"},
                                                new boolean[]{false, true});
    }

    public ParentInteractable[] getFloorItems() {
        ParentInteractable[] items = new ParentInteractable[10];
//        items[0] = new Key("Key 1",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 4, false);
//        items[1] = new Door("Door 1 Left",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 6, false);
//        items[2] = new Door("Door 1 Right",  Constants.TILE_SIZE * 7, Constants.TILE_SIZE * 6, false);
//        items[3] = new Chest("Chest 1",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 9, false);
//        items[4] = new Key("Key 2",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 12, false);
        return items;
    }
}

