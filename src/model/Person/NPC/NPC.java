package model.Person.NPC;

import controller.NPCThread;
import model.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 31/03/16.
 */
public class NPC extends Person {
    public NPC(Map map, float[] position, Color color) {
        super(map, position, color);
        this.image_up = new ImageIcon("image/fatman.png").getImage();
        this.image_down = new ImageIcon("image/fatman.png").getImage();
        this.image_left = new ImageIcon("image/fatman.png").getImage();
        this.image_right = new ImageIcon("image/fatman.png").getImage();
        this.thread = new Thread(new NPCThread(this));
    }

}
