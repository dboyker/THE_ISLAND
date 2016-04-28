package model.Person.NPC;

import controller.OpponentThread;
import model.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Opponent extends NPC {
    private Boolean attacker = false;
    private Boolean coward = false;

    public Opponent(Map map, float[] position, Color color) {
        super(map, position, color);
        double random = (Math.random()*1);
        if (random > 0.5) {this.attacker = true; this.coward = false;}
        else {this.attacker = false; this.coward = true;}
        this.image_up = new ImageIcon("image/fatman.png").getImage();
        this.image_down = new ImageIcon("image/fatman.png").getImage();
        this.image_left = new ImageIcon("image/fatman.png").getImage();
        this.image_right = new ImageIcon("image/fatman.png").getImage();
        this.image = image_down;
        this.thread = new Thread(new OpponentThread(this));
    }

    public Boolean getAttacker() {return this.attacker;}
    public void setAttacker(Boolean attacker) {this.attacker = attacker;}
    public Boolean getCoward() {return this.coward;}
    public void setCoward(Boolean coward) {this.coward = coward;}
}
