package model.Chunk;
import model.*;
import model.Person.Person;
import model.Person.Player.Player;
import model.Item.*;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 30/03/16.
 */
public class Chunk implements Serializable {

    protected Map map;
    protected boolean walkable;
    public java.awt.Color color;
    protected Image image;

    public Chunk(Image image, java.awt.Color color, boolean walkable) {
        this.image = image;
        this.color = color;
        this.walkable = walkable;
     //   this.default_walkable = walkable;
    }

    public boolean getWalkable() {return this.walkable;}
    //public void setWalkable(boolean walkable) {}

    public Image getImage() {return this.image;}

    public void interact() {};

}
