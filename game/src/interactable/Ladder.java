package interactable;

import game.AbstractStage;
import util.UtilityFunctions;

public class Ladder extends ParentInteractable {

    AbstractStage stage;


    public Ladder(String name, int x, int y, boolean collision, AbstractStage stage) {
        super(name, x, y, collision);
        super.setImage(UtilityFunctions.readImageFile("./assets/items/", "ladder", ".png"));
        this.stage = stage;
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void handleInteraction() {
        this.stage.nextStage(); // give a param to tell what stage to go to

    }

    @Override
    public String getInteractMessage() {
        return "Go to Next Stage?";
    }
}
