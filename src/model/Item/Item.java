package model.Item;

import model.Person.Person;
import model.Person.Player.Player;

import java.awt.*;

/**
 * Created by davidboyker on 19/04/16.
 */
public class Item {

    protected String name;
    private Color color;

    public String getName() {return this.name;}

    public void use(Person person) {}

}
