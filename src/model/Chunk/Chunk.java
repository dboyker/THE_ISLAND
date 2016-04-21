package model.Chunk;
import model.*;
import model.Person.Person;
import model.Person.Player.Player;
import model.Item.*;

import java.io.Serializable;

/**
 * Created by davidboyker on 30/03/16.
 */
public class Chunk implements Serializable {

    protected Map map;
    private boolean default_walkable;
    private boolean walkable;
    private Person person;
    private Item item;
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

    public Person getPerson() {return this.person;}
    public void setPerson(Person person) {this.person = person;}

    public void interact() {};

}
