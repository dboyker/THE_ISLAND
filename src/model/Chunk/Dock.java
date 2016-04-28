package model.Chunk;

import javafx.scene.paint.Color;

import javax.swing.*;

/**
 * Created by davidboyker on 27/04/16.
 */
public class Dock extends Chunk {
    public Dock(String type) {
        super(null, java.awt.Color.black, true);
        if (type == "bottom") {image = new ImageIcon("image/dockb.png").getImage();}
        else if (type == "top") {image = new ImageIcon("image/dockt.png").getImage();}
    }
}
