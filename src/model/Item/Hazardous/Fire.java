package model.Item.Hazardous;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import java.awt.*;

/**
 * Created by davidboyker on 26/04/16.
 */
public class Fire extends Item {

    private int damage = -20;

    public Fire(){
        super("fire", Color.red, null);
    }

    @Override
    public void interact(Person person) {
        person.setHealth(damage);
    }
}
