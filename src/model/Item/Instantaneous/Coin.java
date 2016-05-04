package model.Item.Instantaneous;

import model.Item.Item;
import model.Person.Person;
import model.Map.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/04/16.
 */
public class Coin extends Item {

    private int value;

    public Coin(Map map, float[] position, int value) {
        super("name", Color.yellow, new ImageIcon("image/coin.png").getImage(), position);
        this.value = value;
        this.map = map;
        map.getItems()[(int) position[0]][(int) position[1]] = this;
    }

    @Override
    public void interact(Person person) {
        person.setMoney(this.value);
        map.deleteItem(this);
    }
}
