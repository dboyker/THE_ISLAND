package model.Chunk;

/**
 * Created by davidboyker on 30/03/16.
 */
public class Chunk {
    private boolean walkable;
    public int color;

    public Chunk(int color, boolean walkable) {
        this.color = color;
        this.walkable = walkable;

    }

    public boolean getWalkable() {
        return this.walkable;
    }

}
