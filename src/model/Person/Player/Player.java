package model.Person.Player;
import model.Map;
import model.Person.Person;
import view.Frame;

import java.awt.*;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Player extends Person {

    private Inventory inventory;

    public Player(Map map, Color color) {
        super(map, 50, 50, color);
    }

    public void setDx(float dx) {this.dx = dx;}
    public void setDy(float dy) {this.dy = dy;}
    public Inventory getInventory() {
        return this.inventory;
    }

}
