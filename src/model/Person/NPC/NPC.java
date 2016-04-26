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

    private Boolean attacker = false;
    private Boolean coward = false;

    public NPC(Map map, float[] position, Color color) {
        super(map, position, color);
        double random = (Math.random()*1);
        if (random > 0.5) {this.attacker = true; this.coward = false;}
        else {this.attacker = false; this.coward = true;}
        this.image_up = new ImageIcon("image/fatman.png").getImage();
        this.image_down = new ImageIcon("image/fatman.png").getImage();
        this.image_left = new ImageIcon("image/fatman.png").getImage();
        this.image_right = new ImageIcon("image/fatman.png").getImage();
        this.image = image_down;
        this.thread = new Thread(new NPCThread(this));
    }

    public Boolean getAttacker() {return this.attacker;}
    public void setAttacker(Boolean attacker) {this.attacker = attacker;}
    public Boolean getCoward() {return this.coward;}
    public void setCoward(Boolean coward) {this.coward = coward;}
}
