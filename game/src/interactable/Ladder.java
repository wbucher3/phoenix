package interactable;

import game.GameStateManager;
import stages.StageType;
import util.UtilityFunctions;

public class Ladder extends ParentInteractable {



    private StageType nextLocation;

    public Ladder(String name, int x, int y, boolean collision, StageType nextLocation) {
        super(name, x, y, collision);
        super.setImage(UtilityFunctions.readImageFile("./assets/items/", "ladder", ".png"));
        this.nextLocation = nextLocation;
//        this.stage = stage;
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void handleInteraction() {
        GameStateManager.getInstance().alertNewStage(this.nextLocation);
//        this.stage.nextStage(); // give a param to tell what stage to go to

    }

    @Override
    public String getInteractMessage() {
        return "Go to Next Stage?";
    }
}
