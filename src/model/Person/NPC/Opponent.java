package model.Person.NPC;

import controller.Thread.OpponentThread;
import model.Map.Map;
import model.Person.Person;
import model.Person.Player.Player;

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

    public void go_to_player(Player player) {
        if (player.getPosition()[0] != this.position[0]) {  // move in x
            int dx = (int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0]));
            this.setDx(dx);
            this.setDy(0);
        }
        else if (player.getPosition()[1] != this.position[1]){  // move in y
            int dy = (int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1]));
            this.setDx(0);
            this.setDy(dy);
        }
        if (Math.abs(player.getPosition()[0] - this.position[0]) < 2 && Math.abs(player.getPosition()[1] - this.position[1]) < 2) {
            // attack player if on the next case
            this.melee_attack();
        }
    }

    public void escape_player(Player player) {
        int dx = -1 * (int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0]));
        if (!map.getChunks()[(int)this.position[0] + dx][(int)this.position[1]].getWalkable() || dx == 0) {
            // cannot walk in x - > try to walk in y
            int dy = -1 * (int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1]));
            if (!map.getChunks()[(int)this.position[0]][(int)this.position[1] + dy].getWalkable() || dy == 0) {
                dx = -1*dx;
                if (!map.getChunks()[(int)this.position[0] + dx][(int)this.position[1]].getWalkable() || dx == 0) {
                    dy = -1*dy;
                    this.setDy(dy);
                }
                else {this.setDx(dx);}

            }
            else {
                this.setDy(dy);
            }
        }
        else {
            this.setDx(dx);
        }
    }
}
