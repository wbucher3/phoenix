package game;

import entity.Entity;
import entity.Player;
import interactable.ParentInteractable;
import util.Constants;
import util.Pair;

import java.awt.*;

public class CollisionHandler {

    private final AbstractStage abstractStage;

    public CollisionHandler(AbstractStage abstractStage) {
        this.abstractStage = abstractStage;
    }

    public boolean checkDownCollision(Entity entity) {

        // Get corners of entity hitbox
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        // gets the tiles we are in (bottom row and the two columns above it)
        int entityLeftColumn = entityX1 / Constants.TILE_SIZE;
        int entityRightColumn = entityX2 / Constants.TILE_SIZE;
        int entityBottomRow = ((entityY2 + entity.getSpeed()) / Constants.TILE_SIZE);

        // get the type of tile
        int leftDownTile = abstractStage.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];
        int rightDownTile = abstractStage.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        // check if the tile type is collidable
        return abstractStage.tileHandler.getTiles()[leftDownTile].isCollidable() || abstractStage.tileHandler.getTiles()[rightDownTile].isCollidable();
    }

    public boolean checkUpCollision(Entity entity) {

        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = entityX1 / Constants.TILE_SIZE;
        int entityRightColumn = entityX2 / Constants.TILE_SIZE;
        int entityTopRow = (entityY1 - entity.getSpeed()) / Constants.TILE_SIZE;

        int leftUpTile = abstractStage.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int rightUpTile = abstractStage.tileHandler.getTileMap()[entityTopRow][entityRightColumn];

        return abstractStage.tileHandler.getTiles()[leftUpTile].isCollidable() || abstractStage.tileHandler.getTiles()[rightUpTile].isCollidable();
    }

    public boolean checkRightWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityRightColumn = (entityX2 + entity.getSpeed())/ Constants.TILE_SIZE;
        int entityTopRow = entityY1 / Constants.TILE_SIZE;
        int entityBottomRow = entityY2 / Constants.TILE_SIZE;

        int topWallTile = abstractStage.tileHandler.getTileMap()[entityTopRow][entityRightColumn];
        int bottomWallTile = abstractStage.tileHandler.getTileMap()[entityBottomRow][entityRightColumn];

        return abstractStage.tileHandler.getTiles()[topWallTile].isCollidable() || abstractStage.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }
    public boolean checkLeftWallCollision(Entity entity) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityX2 = entityX1 + entity.getHitBox().width;
        int entityY1 = entity.getY() + entity.getHitBox().y;
        int entityY2 = entityY1 + entity.getHitBox().height;

        int entityLeftColumn = (entityX1 - entity.getSpeed())/ Constants.TILE_SIZE;
        int entityTopRow = entityY1 / Constants.TILE_SIZE;
        int entityBottomRow = entityY2 / Constants.TILE_SIZE;

        int topWallTile = abstractStage.tileHandler.getTileMap()[entityTopRow][entityLeftColumn];
        int bottomWallTile = abstractStage.tileHandler.getTileMap()[entityBottomRow][entityLeftColumn];

        return abstractStage.tileHandler.getTiles()[topWallTile].isCollidable() || abstractStage.tileHandler.getTiles()[bottomWallTile].isCollidable();
    }


    public boolean checkItemCollision(Rectangle hitbox) {
        int index = -1;
        boolean collision = false;

        for (int i = 0; i < abstractStage.items.length; i++) {
            ParentInteractable currentItem = abstractStage.items[i];
            if (currentItem == null) continue;

            if (hitbox.intersects(currentItem.getHitbox()) && currentItem.isCollision()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUpItemCollision(Entity entity, boolean player) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityY1 = entity.getY() + entity.getHitBox().y - entity.getSpeed();
        return this.checkItemCollision(new Rectangle(entityX1, entityY1, entity.getHitBox().width, entity.getHitBox().height));
    }
    public boolean checkDownItemCollision(Entity entity, boolean player) {
        int entityX1 = entity.getX() + entity.getHitBox().x;
        int entityY1 = entity.getY() + entity.getHitBox().y + entity.getSpeed();
        return this.checkItemCollision(new Rectangle(entityX1, entityY1, entity.getHitBox().width, entity.getHitBox().height));
    }

    public boolean checkRightItemCollision(Entity entity, boolean player) {
        int entityX1 = entity.getX() + entity.getHitBox().x + entity.getSpeed();
        int entityY1 = entity.getY() + entity.getHitBox().y;
        return this.checkItemCollision(new Rectangle(entityX1, entityY1, entity.getHitBox().width, entity.getHitBox().height));
    }
    public boolean checkLeftItemCollision(Entity entity, boolean player) {
        int entityX1 = entity.getX() + entity.getHitBox().x - entity.getSpeed();
        int entityY1 = entity.getY() + entity.getHitBox().y;
        return this.checkItemCollision(new Rectangle(entityX1, entityY1, entity.getHitBox().width, entity.getHitBox().height));
    }

    // used for interaction, not for collider logic. That is what above it for
    public boolean isPlayerOnTop(Player player, ParentInteractable item) {
        int playerX = player.getX() + player.getHitBox().x;
        int playerY = player.getY() + player.getHitBox().y;
        Rectangle playerHitBox = new Rectangle(playerX, playerY, player.getHitBox().width , player.getHitBox().height);
        if (item == null) return false;

        return playerHitBox.intersects(item.getHitbox());
    }
}
