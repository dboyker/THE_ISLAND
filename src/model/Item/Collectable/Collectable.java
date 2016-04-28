package model.Item.Collectable;

import model.Item.Item;

import java.awt.*;

/**
 * Created by davidboyker on 26/04/16.
 */
public class Collectable extends Item {
    public Collectable(String name, Image image, float[] position) {
        super(name, Color.blue, image, position);
    }
}
