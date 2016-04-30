package model.Person.NPC;

import model.Map.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Seller extends Person implements NPC {
    public Seller(Map map, float[] position, Color color) {
        super(map, position, color);
        this.image = new ImageIcon("image/seller.png").getImage();
        this.health = 1000;
    }

    public Boolean getAttacker() {return false;}
    public void setAttacker(Boolean attacker) {}
    public Boolean getCoward() {return false;}
    public void setCoward(Boolean coward) {}
}
