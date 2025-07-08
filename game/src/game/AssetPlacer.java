package game;

import interactable.Chest;
import interactable.Door;
import interactable.Key;

public class AssetPlacer {

    private AbstractFloor window;

    public AssetPlacer(AbstractFloor window) {
        this.window = window;
    }

    public void setObject() {
        window.items[0] = new Key("Key 1",  window.tileSize * 6, window.tileSize * 4, false);
        window.items[1] = new Door("Door 1 Left",  window.tileSize * 6, window.tileSize * 6, false);
        window.items[2] = new Door("Door 1 Right",  window.tileSize * 7, window.tileSize * 6, false);
        window.items[3] = new Chest("Chest 1",  window.tileSize * 14, window.tileSize * 9, false);
        window.items[4] = new Key("Key 2",  window.tileSize * 14, window.tileSize * 12, false);

    }
}
