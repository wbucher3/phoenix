package tile;

public class TileInformation {

    private String tileMapDirectory;
    private String imageDirectory;
    private String[] names;
    private boolean[] collidable;


    public TileInformation(String tileMapDirectory, String imageDirectory, String[] names, boolean[] collidable) {
        this.tileMapDirectory = tileMapDirectory;
        this.imageDirectory = imageDirectory;
        this.names = names;
        this.collidable = collidable;
    }


    public String getImageDirectory() {
        return imageDirectory;
    }

    public void setImageDirectory(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public boolean[] getCollidable() {
        return collidable;
    }

    public void setCollidable(boolean[] collidable) {
        this.collidable = collidable;
    }

    public String getTileMapDirectory() {
        return tileMapDirectory;
    }

    public void setTileMapDirectory(String tileMapDirectory) {
        this.tileMapDirectory = tileMapDirectory;
    }
}
