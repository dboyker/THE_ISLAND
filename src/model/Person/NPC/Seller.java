package model.Person.NPC;

import model.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Seller extends NPC {
    public Seller(Map map, float[] position, Color color) {
        super(map, position, color);
        this.image = new ImageIcon("image/seller.png").getImage();
        this.health = 1000;
    }
}
