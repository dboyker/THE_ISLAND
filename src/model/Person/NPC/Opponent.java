package model.Person.NPC;

import controller.Thread.OpponentThread;
import model.Map.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Opponent extends Person implements NPC {
    private Boolean attacker = false;
    private Boolean coward = false;

    public Opponent(Map map, float[] position, Color color) {
        super(map, position, color);
        double random = (Math.random()*1);
        if (random > 0.5) {this.attacker = true; this.coward = false;}
        else {this.attacker = false; this.coward = true;}
        this.health = 30;
        this.money = 25;
        this.image_up = new ImageIcon("image/opponent/playeru.png").getImage();
        this.image_up_1 = new ImageIcon("image/opponent/playeru1.png").getImage();
        this.image_up_2 = new ImageIcon("image/opponent/playeru2.png").getImage();
        this.image_down = new ImageIcon("image/opponent/playerd.png").getImage();
        this.image_down_1 = new ImageIcon("image/opponent/playerd1.png").getImage();
        this.image_down_2 = new ImageIcon("image/opponent/playerd2.png").getImage();
        this.image_left = new ImageIcon("image/opponent/playerl.png").getImage();
        this.image_left_1 = new ImageIcon("image/opponent/playerl1.png").getImage();
        this.image_left_2 = new ImageIcon("image/opponent/playerl2.png").getImage();
        this.image_right = new ImageIcon("image/opponent/playerr.png").getImage();
        this.image_right_1 = new ImageIcon("image/opponent/playerr1.png").getImage();
        this.image_right_2 = new ImageIcon("image/opponent/playerr2.png").getImage();
        this.image = image_down;
        this.thread = new Thread(new OpponentThread(this));
    }

    public Boolean getAttacker() {return this.attacker;}
    public void setAttacker(Boolean attacker) {this.attacker = attacker;}
    public Boolean getCoward() {return this.coward;}
    public void setCoward(Boolean coward) {this.coward = coward;}
}
