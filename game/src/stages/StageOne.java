package stages;

import game.GameStateManager;
import interactable.Ladder;
import interactable.items.Chest;
import interactable.items.Key;
import interactable.ParentInteractable;
import tile.TileInformation;
import util.Constants;

import java.io.File;


// TODO Replace with real stage name
public class StageOne extends AbstractStage {


    public StageOne() {
        super(20, 25, 3 * Constants.TILE_SIZE, 3 * Constants.TILE_SIZE, fetchTileInformation(), new File("./assets/music/test-song.wav"));

    }

    private static TileInformation fetchTileInformation() {
        return new TileInformation("./assets/maps/testmap2.csv",
                                    "./assets/tiles/",
                                                new String[]{"stone-block", "stone-custom"},
                                                new boolean[]{false, true});
    }

    public ParentInteractable[] getFloorItems() {
        ParentInteractable[] items = new ParentInteractable[10];
        items[0] = new Key("Key 1",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 4, false);
        items[1] = new Ladder("Ladder",  Constants.TILE_SIZE * 9, Constants.TILE_SIZE * 8, false, StageType.WOODS);
        items[2] = new Chest("Chest 1",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 9, true);
        return items;
    }

}
