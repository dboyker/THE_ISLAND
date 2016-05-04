/**
 * Created by davidboyker on 28/04/16.
 */

// Classe pour les PNJ adversaires

package model.Person.NPC;

import model.Map.Map;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;

public class Opponent extends Person implements NPC {
    private Boolean attacker = false;
    private Boolean coward = false;

    public Opponent(Map map, float[] position) {
        super(map, position);
        double random = (Math.random()*1);
        if (random > 0.5) {this.attacker = true; this.coward = false;}
        else {this.attacker = false; this.coward = true;}
        this.health = 30;
        this.money = 25;
        reset_image();
        this.thread = new Thread(new OpponentThread(this));
    }

    // GET & SET
    public Boolean getAttacker() {return this.attacker;}
    public void setAttacker(Boolean attacker) {this.attacker = attacker;}
    public Boolean getCoward() {return this.coward;}
    public void setCoward(Boolean coward) {this.coward = coward;}


    public void go_to_player(Player player) {  // fonction qui dicte les déplacements du PNJ si celui-ci doit aller vers un joueur et l'attaque
        if (player.getPosition()[0] != this.position[0]) {  // déplacement en X
            int dx = (int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0]));
            this.setDx(dx);
            this.setDy(0);
        }
        else if (player.getPosition()[1] != this.position[1]){  // déplacement en Y
            int dy = (int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1]));
            this.setDx(0);
            this.setDy(dy);
        }
        if (Math.abs(player.getPosition()[0] - this.position[0]) < 2 && Math.abs(player.getPosition()[1] - this.position[1]) < 2) {
            this.melee_attack();  // si le joueur est tout près, attaque
        }
    }


    public void escape_player(Player player) {  // fonction qui dicte les déplacements du PNJ si celui-ci doit fuire un joueur
        int dx = -1 * (int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0]));
        if (!map.getChunks()[(int)this.position[0] + dx][(int)this.position[1]].getWalkable() || dx == 0) {
            int dy = -1 * (int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1]));
            if (!map.getChunks()[(int)this.position[0]][(int)this.position[1] + dy].getWalkable() || dy == 0) {
                dx = -1*dx;
                if (!map.getChunks()[(int)this.position[0] + dx][(int)this.position[1]].getWalkable() || dx == 0) {
                    dy = -1*dy;
                    this.setDy(dy);
                }
                else {this.setDx(dx);}
            }
            else {this.setDy(dy);}
        }
        else {this.setDx(dx);}
    }

    @Override
    public void reset_image() {
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
    }
}
