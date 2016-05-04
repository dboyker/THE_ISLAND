package model.Chunk;

import javax.swing.*;

/**
 * Created by davidboyker on 27/04/16.
 */
public class Dock extends Chunk {

    private String type;

    public Dock(String type) {
        super(null, java.awt.Color.black, true);
        this.type = type;
        reset_image();
    }

    public void reset_image() {
        if (this.type.equals("bottom")) {image = new ImageIcon("image/dockb.png").getImage();}
        else if (this.type.equals("top")) {image = new ImageIcon("image/dockt.png").getImage();}
    }
}
