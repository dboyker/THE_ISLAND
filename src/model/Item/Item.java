package model.Item;

import model.Person.Person;
import model.Person.Player.Player;

import java.awt.*;

/**
 * Created by davidboyker on 19/04/16.
 */
public class Item {

    protected float position[];
    protected String name;
    private Color color;
    private Image image;


    public Item(String name, Color color, Image image, float[] position) {
        this.name = name;
        this.color = color;
        this.image = image;
        this.position = new float[2];
        this.position = position;
    }

    public String getName() {return this.name;}
    public Image getImage() {return this.image;}
    public Color getColor() {return this.color;}
    public float[] getPosition() {return this.position;}

    public void use(Person person) {}
    public void interact(Person person) {}

}
