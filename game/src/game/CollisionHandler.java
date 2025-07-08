package game;

import entity.Entity;

public class CollisionHandler {

    private final AbstractFloor abstractFloor;

    public CollisionHandler(AbstractFloor abstractFloor) {
        this.abstractFloor = abstractFloor;
    }

    public boolean checkDownCollision(Entity entity) {

        // Get corners of entity hitbox
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        // gets the tiles we are in (bottom row and the two columns above it)
        int entityLeftColumn = entityX1 / abstractFloor.tileSize;
        int entityRightColumn = entityX2 / abstractFloor.tileSize;
        int entityBottomRow = ((entityY2 + entity.getSpeed()) / abstractFloor.tileSize);

        // get the type of tile
        int leftDownTile = abstractFloor.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];
        int rightDownTile = abstractFloor.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        // check if the tile type is collidable
        return abstractFloor.tileHandler.getTiles()[leftDownTile].isCollidable() || abstractFloor.tileHandler.getTiles()[rightDownTile].isCollidable();
    }

    public boolean checkUpCollision(Entity entity) {

        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = entityX1 / abstractFloor.tileSize;
        int entityRightColumn = entityX2 / abstractFloor.tileSize;
        int entityTopRow = (entityY1 - entity.getSpeed()) / abstractFloor.tileSize;

        int leftUpTile = abstractFloor.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int rightUpTile = abstractFloor.tileHandler.getTileMap()[entityTopRow][entityRightColumn];

        return abstractFloor.tileHandler.getTiles()[leftUpTile].isCollidable() || abstractFloor.tileHandler.getTiles()[rightUpTile].isCollidable();
    }

    public boolean checkRightWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityRightColumn = (entityX2 + entity.getSpeed())/ abstractFloor.tileSize;
        int entityTopRow = entityY1 / abstractFloor.tileSize;
        int entityBottomRow = entityY2 / abstractFloor.tileSize;

        int topWallTile = abstractFloor.tileHandler.getTileMap()[entityTopRow][entityRightColumn];
        int bottomWallTile = abstractFloor.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        return abstractFloor.tileHandler.getTiles()[topWallTile].isCollidable() || abstractFloor.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }
    public boolean checkLeftWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = (entityX1 - entity.getSpeed())/ abstractFloor.tileSize;
        int entityTopRow = entityY1 / abstractFloor.tileSize;
        int entityBottomRow = entityY2 / abstractFloor.tileSize;

        int topWallTile = abstractFloor.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int bottomWallTile = abstractFloor.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];

        return abstractFloor.tileHandler.getTiles()[topWallTile].isCollidable() || abstractFloor.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }

}
