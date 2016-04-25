package model.Chunk;

import javax.swing.*;

/**
 * Created by davidboyker on 17/04/16.
 */
public class Tree extends Chunk {

    public Tree(String type) {
        super(null, new java.awt.Color(0, 155, 114),false);
        if (type == "top") {
            image = new ImageIcon("image/pine_top.png").getImage();
        }
        else if (type == "bottom") {
            image = new ImageIcon("image/pine_bottom.png").getImage();
        }
    }
}