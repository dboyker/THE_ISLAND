package model.Chunk;

import javax.swing.*;

/**
 * Created by davidboyker on 17/04/16.
 */
    public class Sand extends Chunk {

        public Sand() {
            super(new ImageIcon("image/sand.png").getImage(), new java.awt.Color(249, 191, 59),true);
        }

    @Override
    public void reset_image() {this.image = new ImageIcon("image/sand.png").getImage();}

}
