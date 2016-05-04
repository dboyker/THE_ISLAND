package model.Item.Instantaneous;

import model.Item.Item;
import model.Map.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/04/16.
 */
public class Heart extends Item {
    private Map map;

    public Heart(Map map, float[] position) {
        super("name", Color.yellow, new ImageIcon("image/heart.png").getImage(), position);
        map.getItems()[(int) position[0]][(int) position[1]] = this;
    }

    @Override
    public void interact(Person person) {
        if (person.getClass() == model.Person.Player.Player.class) {
            person.setHealth(10);
            map.deleteItem(this);
        }
    }
}
