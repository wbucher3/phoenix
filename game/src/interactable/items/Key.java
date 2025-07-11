package interactable.items;

import interactable.ParentInteractable;
import util.UtilityFunctions;

public class Key extends ParentInteractable {

    public Key(String name, int x, int y, boolean collision) {
        super(name, x, y, collision);
        super.setName("Key");
        super.setImage(UtilityFunctions.readImageFile("./assets/items/", "key", ".png"));
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void handleInteraction() {

    }

}
