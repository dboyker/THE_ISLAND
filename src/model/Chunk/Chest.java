package model.Chunk;

import javax.swing.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Chest extends Chunk{
    public Chest() {
        super(null, java.awt.Color.black, false);
        image = new ImageIcon("image/chest.png").getImage();
    }
}
