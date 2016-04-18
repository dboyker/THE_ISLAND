package model.Chunk;
import javafx.scene.paint.Color;
import model.*;
import model.Person.Player.Player;

import java.io.Serializable;

/**
 * Created by davidboyker on 30/03/16.
 */
public class Chunk implements Serializable {

    protected Map map;
    private boolean default_walkable;
    private boolean walkable;
    private Player player;
    public java.awt.Color color;

    public Chunk(java.awt.Color color, boolean walkable) {
        this.color = color;
        this.walkable = walkable;
        this.default_walkable = walkable;
    }

    public boolean getWalkable() {return this.walkable;}
    public void setWalkable(boolean walkable) {
        if (walkable == false) {this.walkable = false;}
        else {this.walkable = this.default_walkable;}}

    public Player getPlayer() {return this.player;}

    public void interact() {}

}
