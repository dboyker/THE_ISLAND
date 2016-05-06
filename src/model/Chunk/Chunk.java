// Classe représentant les cases sur lesquels le joueur et les NPC se déplacent

/**
 * Created by davidboyker on 30/03/16.
 */

package model.Chunk;

import model.Map.Map;
import model.Person.Player.Player;
import java.awt.*;
import java.io.Serializable;

public class Chunk implements Serializable {

    protected Map map;
    protected boolean walkable;
    protected  java.awt.Color color;
    protected transient Image image;

    public Chunk(Image image, java.awt.Color color, boolean walkable) {
        this.image = image;
        this.color = color;
        this.walkable = walkable;
    }

    public Color getColor() {return this.color;}
    public boolean getWalkable() {return this.walkable;}
    public Image getImage() {return this.image;}

    public void reset_image() {}
    public void interact(Player player) {}  // fonction lancée lorsque un player se trouve au même emplacement que cette chunk

}
