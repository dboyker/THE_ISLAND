package model.Chunk;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 31/03/16.
 */
public class Building extends Chunk {

        public Building(String type) {
            super(null, Color.lightGray,false);
            if (type == "rtl") {this.image = new ImageIcon("image/building/rooftl.png").getImage();}
            else if (type == "rtr") {this.image = new ImageIcon("image/building/rooftr.png").getImage();}
            else if (type == "rtm") {this.image = new ImageIcon("image/building/rooftm.png").getImage();}
            else if (type == "rbl") {this.image = new ImageIcon("image/building/roofbl.png").getImage();}
            else if (type == "rbr") {this.image = new ImageIcon("image/building/roofbr.png").getImage();}
            else if (type == "rbm") {this.image = new ImageIcon("image/building/roofbm.png").getImage();}
            else if (type == "bbl") {this.image = new ImageIcon("image/building/bbl.png").getImage();}
            else if (type == "bbr") {this.image = new ImageIcon("image/building/bbr.png").getImage();}
            else if (type == "bbm") {this.image = new ImageIcon("image/building/bbm.png").getImage();}
            else if (type == "bbmw") {this.image = new ImageIcon("image/building/bbmw.png").getImage();}
            else if (type == "ml") {this.image = new ImageIcon("image/building/ml.png").getImage();}
            else if (type == "mr") {this.image = new ImageIcon("image/building/mr.png").getImage();}
            else if (type == "mm") {this.image = new ImageIcon("image/building/mm.png").getImage();}
            else {}
        }
}
