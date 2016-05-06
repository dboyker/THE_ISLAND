// classe pour les coffres de stockage

/**
 * Created by davidboyker on 28/04/16.
 */

package model.Chunk;

import javax.swing.*;

public class Chest extends Chunk {
    public Chest() {
        super(null, java.awt.Color.black, false);
        reset_image();
    }

    @Override
    public void reset_image() {image = new ImageIcon("image/chest.png").getImage();}
}
