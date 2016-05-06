// Objets pour l'extérieur des batiments

/**
 * Created by davidboyker on 31/03/16.
 */

package model.Chunk;

import javax.swing.*;
import java.awt.*;

public class Building extends Chunk {

    private String type;

        public Building(String type) {
            super(null, Color.lightGray,false);
            this.type = type;
            reset_image();
        }

    @Override
    public void reset_image() {
        if (type.equals("rtl")) {this.image = new ImageIcon("image/building/rooftl.png").getImage();}
        else if (type.equals("rtr")) {this.image = new ImageIcon("image/building/rooftr.png").getImage();}
        else if (type.equals("rtm")) {this.image = new ImageIcon("image/building/rooftm.png").getImage();}
        else if (type.equals("rbl")) {this.image = new ImageIcon("image/building/roofbl.png").getImage();}
        else if (type.equals("rbr")) {this.image = new ImageIcon("image/building/roofbr.png").getImage();}
        else if (type.equals("rbm")) {this.image = new ImageIcon("image/building/roofbm.png").getImage();}
        else if (type.equals("bbl")) {this.image = new ImageIcon("image/building/bbl.png").getImage();}
        else if (type.equals("bbr")) {this.image = new ImageIcon("image/building/bbr.png").getImage();}
        else if (type.equals("bbm")) {this.image = new ImageIcon("image/building/bbm.png").getImage();}
        else if (type.equals("bbmw")) {this.image = new ImageIcon("image/building/bbmw.png").getImage();}
        else if (type.equals("ml")) {this.image = new ImageIcon("image/building/ml.png").getImage();}
        else if (type.equals("mr")) {this.image = new ImageIcon("image/building/mr.png").getImage();}
        else if (type.equals("mm")) {this.image = new ImageIcon("image/building/mm.png").getImage();}
    }
}
