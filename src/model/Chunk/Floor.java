package model.Chunk;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 18/04/16.
 */
public class Floor extends Chunk {

    public Floor() {
        super(new ImageIcon("image/floor.png").getImage(), Color.GRAY,true);
    }
    public void reset_image() {
        image = new ImageIcon("image/floor.png").getImage();
    }
}
