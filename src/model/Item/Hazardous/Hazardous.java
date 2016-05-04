package model.Item.Hazardous;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import java.awt.*;

/**
 * Created by davidboyker on 26/04/16.
 */
public class Hazardous extends Item {

    protected int damage = 0;

    public Hazardous(String name, Image image, float[] position) {
        super(name, Color.red, image, position);
    }

    public int getDamage() {
        return this.damage;
    }

}
