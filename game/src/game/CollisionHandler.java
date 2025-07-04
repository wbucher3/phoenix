package game;

import entity.Entity;

public class CollisionHandler {

    private final GameWindow gameWindow;

    public CollisionHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public boolean checkFloorCollision(Entity entity) {

        // Get corners of entity hitbox
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        // gets the tiles we are in (bottom row and the two columns above it)
        int entityLeftColumn = entityX1 / gameWindow.tileSize;
        int entityRightColumn = entityX2 / gameWindow.tileSize;
        int entityBottomRow = (entityY2 + entity.getJumpPower()) / gameWindow.tileSize;

        // get the type of tile
        int leftFloorTile = gameWindow.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];
        int rightFloorTile = gameWindow.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        // check if the tile type is collidable
        return gameWindow.tileHandler.getTiles()[leftFloorTile].isCollidable() || gameWindow.tileHandler.getTiles()[rightFloorTile].isCollidable();
    }

    public boolean checkCeilingCollision (Entity entity) {

        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = entityX1 / gameWindow.tileSize;
        int entityRightColumn = entityX2 / gameWindow.tileSize;
        int entityTopRow = (entityY1 - entity.getJumpPower()) / gameWindow.tileSize;

        int leftCeilingTile = gameWindow.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int rightCeilingTile = gameWindow.tileHandler.getTileMap()[entityTopRow][entityRightColumn];

        return gameWindow.tileHandler.getTiles()[leftCeilingTile].isCollidable() || gameWindow.tileHandler.getTiles()[rightCeilingTile].isCollidable();
    }

    public boolean checkRightWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityRightColumn = (entityX2 + entity.getSpeed())/ gameWindow.tileSize;
        int entityTopRow = entityY1 / gameWindow.tileSize;
        int entityBottomRow = entityY2 / gameWindow.tileSize;

        int topWallTile = gameWindow.tileHandler.getTileMap()[entityTopRow][entityRightColumn];
        int bottomWallTile = gameWindow.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        return gameWindow.tileHandler.getTiles()[topWallTile].isCollidable() || gameWindow.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }
    public boolean checkLeftWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = (entityX1 - entity.getSpeed())/ gameWindow.tileSize;
        int entityTopRow = entityY1 / gameWindow.tileSize;
        int entityBottomRow = entityY2 / gameWindow.tileSize;

        int topWallTile = gameWindow.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int bottomWallTile = gameWindow.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];

        return gameWindow.tileHandler.getTiles()[topWallTile].isCollidable() || gameWindow.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }

}
