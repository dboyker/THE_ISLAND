// Classe pour l'eau


/**
 * Created by davidboyker on 31/03/16.
 */

package model.Chunk;

import javax.swing.*;
import java.awt.*;

public class Water extends Chunk {

    public Water() {super(new ImageIcon("image/water.png").getImage(), new Color(35,137,218),false);}

    @Override
    public void reset_image() {this.image = new ImageIcon("image/water.png").getImage();}
}
