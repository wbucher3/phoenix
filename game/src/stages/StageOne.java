package stages;

import game.AbstractStage;
import interactable.Ladder;
import interactable.items.Chest;
import interactable.Door;
import interactable.items.Key;
import interactable.ParentInteractable;
import tile.TileInformation;
import util.Constants;

public class StageOne extends AbstractStage {


    public StageOne() {
        super(20, 25, getTileInformation());
        super.setItems(this.getFloorItems());
        super.soundHandler.setFile(0);
        super.soundHandler.play();
    }

    private static TileInformation getTileInformation() {
        return new TileInformation("./assets/maps/testmap2.csv", "./assets/tiles/", new String[]{"grass-block", "stone-block"}, new boolean[]{false, true});

    }

    private ParentInteractable[] getFloorItems() {
        ParentInteractable[] items = new ParentInteractable[10];
        items[0] = new Key("Key 1",  Constants.TILE_SIZE * 6, Constants.TILE_SIZE * 4, false);
        items[1] = new Ladder("Ladder",  Constants.TILE_SIZE * 9, Constants.TILE_SIZE * 8, false, this);
        items[2] = new Chest("Chest 1",  Constants.TILE_SIZE * 14, Constants.TILE_SIZE * 9, true);
        return items;
    }
}
