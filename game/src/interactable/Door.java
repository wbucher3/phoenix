package interactable;

import util.UtilityFunctions;

public class Door extends ParentInteractable {

    public Door(String name, int x, int y, boolean collision) {
        super(name, x, y, collision);
        super.setName("Door");
        super.setImage(UtilityFunctions.readImageFile("./assets/items/", "door", ".png"));
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void handleInteraction() {

    }
}
