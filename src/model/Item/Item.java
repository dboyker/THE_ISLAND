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
    private Image image;

    public Item(String name, Color color, Image image) {
        this.name = name;
        this.color = color;
        this.image = image;
    }

    public String getName() {return this.name;}
    public Image getImage() {return this.image;}
    public Color getColor() {return this.color;}

    public void use(Person person) {}
    public void interact() {}

}
