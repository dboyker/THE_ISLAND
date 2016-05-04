package model.Chunk;

import javax.swing.*;

/**
 * Created by davidboyker on 17/04/16.
 */
public class Tree extends Chunk {

    private String type;

    public Tree(String type) {
        super(null, new java.awt.Color(0, 155, 114),false);
        this.type = type;
        reset_image();
    }
    @Override
    public void reset_image() {
        if (type.equals("top")) {
            image = new ImageIcon("image/pine_top.png").getImage();
        }
        else if (type.equals("bottom")) {
            image = new ImageIcon("image/pine_bottom.png").getImage();
        }
    }
}