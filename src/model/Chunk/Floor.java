// classe pour le sol, à l'intérieur des bâtiments

/**
 * Created by davidboyker on 18/04/16.
 */

package model.Chunk;

import javax.swing.*;
import java.awt.*;

public class Floor extends Chunk {

    public Floor() {
        super(new ImageIcon("image/floor.png").getImage(), Color.GRAY,true);
    }
    @Override
    public void reset_image() {
        image = new ImageIcon("image/floor.png").getImage();
    }
}
