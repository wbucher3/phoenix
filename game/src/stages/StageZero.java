package stages;

import game.AbstractStage;
import interactable.ParentInteractable;
import tile.TileInformation;

public class StageZero extends AbstractStage {


    public StageZero() {
        super(20, 25, getTileInformation(), getFloorItems());
    }

    private static TileInformation getTileInformation() {
        return new TileInformation("./assets/maps/testmap1.csv", "./assets/tiles/", new String[]{"stone-block", "wood-block"}, new boolean[]{false, true});

    }

    private static ParentInteractable[] getFloorItems() {
        ParentInteractable[] items = new ParentInteractable[10];
//        items[0] = new Key("Key 1",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 4, false);
//        items[1] = new Door("Door 1 Left",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 6, false);
//        items[2] = new Door("Door 1 Right",  Constants.TILE_SIZE * 7, Constants.TILE_SIZE * 6, false);
//        items[3] = new Chest("Chest 1",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 9, false);
//        items[4] = new Key("Key 2",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 12, false);
        return items;
    }
}

