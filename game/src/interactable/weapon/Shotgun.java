package interactable.weapon;

import util.UtilityFunctions;

public class Shotgun extends ParentWeapon {

    public Shotgun() {
        super(64, 64, "Sword",
                UtilityFunctions.readImageFile("./assets/weapons/", "gun", ".png"),
                UtilityFunctions.readImageFile("./assets/weapons/", "gun-mirror", ".png"));
    }

}
