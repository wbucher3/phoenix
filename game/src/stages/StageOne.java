package stages;

import game.AbstractStage;
import interactable.Chest;
import interactable.Door;
import interactable.Key;
import interactable.ParentInteractable;
import tile.TileInformation;
import util.Constants;

public class StageOne extends AbstractStage {


    public StageOne() {
        super(20, 25, getTileInformation(), getFloorItems());


    }

    private static TileInformation getTileInformation() {
        return new TileInformation("./assets/maps/testmap2.csv", "./assets/tiles/", new String[]{"grass-block", "stone-block"}, new boolean[]{false, true});

    }

    private static ParentInteractable[] getFloorItems() {
        ParentInteractable[] items = new ParentInteractable[10];
        items[0] = new Key("Key 1",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 4, true);
        items[1] = new Door("Door 1 Left",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 6, true);
        items[2] = new Door("Door 1 Right",  Constants.TILE_SIZE * 7, Constants.TILE_SIZE * 6, true);
        items[3] = new Chest("Chest 1",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 9, true);
        items[4] = new Key("Key 2",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 12, true);
        return items;
    }
}
