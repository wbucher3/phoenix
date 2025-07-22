package menu;

import game.GameState;
import game.GameStateManager;
import game.KeyPressHandler;
import game.MouseHandler;
import util.Constants;

import java.awt.*;

public class StartScreen {

    private final int x = Constants.SCREEN_WIDTH / 4;
    private final int y = Constants.SCREEN_HEIGHT / 4;
    private final int verticalTitleSpacing = 70;
    private final int tabDistance = 10;
    private MenuOption selectedMenuOption = null;
    private MouseHandler mouseHandler;
    private KeyPressHandler keyPressHandler;

    public StartScreen(MouseHandler mouseHandler, KeyPressHandler keyPressHandler) {
        this.mouseHandler = mouseHandler;
        this.keyPressHandler = keyPressHandler;
    }




    public void draw(Graphics2D graphics2d) {
        graphics2d.setColor(Color.white);
        graphics2d.setFont(new Font("Monospaced", Font.PLAIN, 60));
        graphics2d.drawString(Constants.TITLE, x, y);
        graphics2d.setFont(new Font("Monospaced", Font.PLAIN, 30));
        graphics2d.drawString(Constants.PLAY, x + tabDistance, y + verticalTitleSpacing );


//        graphics2d.drawLine(x + tabDistance,
//                            y + verticalTitleSpacing + 20,
//                            x + tabDistance + 120,
//                            y + verticalTitleSpacing + 20);


        graphics2d.drawString(Constants.EXIT, x + tabDistance, y + verticalTitleSpacing * 2);
    }

    public void update() {
        if (mouseHandler.isLeftMouseClicked()) {
            System.out.println("X: " + mouseHandler.getMouseX() + "  Y: " + mouseHandler.getMouseY());

        }
        if (keyPressHandler.interactPressed) {
            GameStateManager.getInstance().alertNewState(GameState.GAMEPLAY);
        }


    }
}
