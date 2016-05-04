package model.Chunk;

import javafx.scene.paint.Color;

import javax.swing.*;

/**
 * Created by davidboyker on 31/03/16.
 */
public class Grass extends Chunk {

    public Grass() {
        super(null, new java.awt.Color(0, 255, 114),true);
        reset_image();
    }

    @Override
    public void reset_image() {
        double random = (Math.random());
        if (random > 0.9) {image = new ImageIcon("image/flower.png").getImage();}
        else if(random < 0.9 && random > 0.8) {image = new ImageIcon("image/grass2.png").getImage();}
        else {image = new ImageIcon("image/grass.png").getImage();}
    }
}
