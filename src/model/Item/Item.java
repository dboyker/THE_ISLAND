/**
 * Created by davidboyker on 19/04/16.
 */

package model.Item;

import model.Person.Player.Player;
import model.Map.Map;
import java.awt.*;
import java.io.Serializable;

public class Item implements Serializable{

    private static final long serialVersionUID = 51L;
    protected Map map;
    protected float position[];
    protected String name;
    private Color color;
    protected transient Image image;


    public Item(String name, Color color, Image image, float[] position) {
        this.name = name;
        this.color = color;
        this.image = image;
        this.position = new float[2];
        this.position = position;
    }

    public Image getImage() {return this.image;}
    public Color getColor() {return this.color;}
    public float[] getPosition() {return this.position;}
    public void setMap(Map map) {this.map = map;}
    public Map getMap() {return this.map;}
    public void setPosition(float[] position) {
        this.position = position;
        map.getItems()[(int) position[0]][(int) position[1]] = this;
    }

    public void reset_image() {}

    public void interact(Player player) {}

}
