/**
 * Created by davidboyker on 28/04/16.
 */

// Classe pour les PNJ adversaires

package model.Person.NPC;

import model.Chunk.Chunk;
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
        double proba;
        if (map.getGame().getDifficulty().equals("hard")) {  // si diffuculté = hard, la probabilité que les opponents soient de type attacker augmente
            proba = 0.8;  // environ 80% des opponents sont aggressifs vis à vis du joueur
        }
        else {
            proba = 0.5;  // environ 50% des opponents sont aggressifs vis à vis du joueur
        }
        if (random > proba) {this.attacker = true; this.coward = false;}
        else {this.attacker = false; this.coward = true;}
        if (this.map.getGame().getDifficulty().equals("hard")) {
            this.health = 50;  // si la partie est en difficulté hard, le joueur a plus de points de vie
        }
        else {this.health = 30;}
        this.money = 25;
        reset_image();
        this.thread = new Thread(new OpponentThread(this));
    }

    // GET & SET
    public Boolean getAttacker() {return this.attacker;}
    public Boolean getCoward() {return this.coward;}


    // ---------- Fonctions pour l'AI ----------
    // fonce vers le joueur
    public void go_to_player(Player player) {  // fonction qui dicte les déplacements du PNJ si celui-ci doit aller vers un joueur et l'attaque
        int desired_dx = 0;
        int desired_dy = 0;
        if (player.getPosition()[0] != this.position[0]) {  // déplacement désiré en X
            desired_dx = (int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0]));
        }
        else if (player.getPosition()[1] != this.position[1]){  // déplacement désiré en Y
            desired_dy = (int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1]));
        }

        // déplacement en x ou y? test si le npc sait se déplacer en x, sinon déplacement en y
        if (desired_dx !=0) {
            Chunk next_chunk = this.map.getChunks()[(int) this.position[0] + desired_dx][(int) this.position[1]];
            Person next_person = this.map.getPersons()[(int) this.position[0] + desired_dx][(int) this.position[1]];
            try {if (next_person.getClass() == model.Person.Player.Player.class) {next_person = null;}}
            catch (NullPointerException e) {}
            if (next_chunk.getWalkable() && next_person == null) {  // le joueur peut se déplacer en x
                this.dx = desired_dx;
                this.dy = 0;
            }
            else {  // déplacement en y
                next_chunk = this.map.getChunks()[(int) this.position[0]][(int) this.position[1] + 1];
                next_person = this.map.getPersons()[(int) this.position[0]][(int) this.position[1] + 1];
                try {if (next_person.getClass() == model.Person.Player.Player.class) {next_person = null;}}
                catch (NullPointerException e) {}
                if (next_chunk.getWalkable() && next_person == null) {
                    this.dx = 0;
                    this.dy = 1;
                }
                else {
                    this.dx = 0;
                    this.dy = -1;
                }
            }
        }
        else if (desired_dy !=0) {
            Chunk next_chunk = this.map.getChunks()[(int) this.position[0]][(int) this.position[1] + desired_dy];
            Person next_person = this.map.getPersons()[(int) this.position[0]][(int) this.position[1] + desired_dy];
            try {if (next_person.getClass() == model.Person.Player.Player.class) {next_person = null;}}
            catch (NullPointerException e) {}
            if (next_chunk.getWalkable() && next_person == null) {  // le joueur peut se déplacer en y
                this.dx = 0;
                this.dy = desired_dy;
            }
            else {  // déplacement en x
                next_chunk = this.map.getChunks()[(int) this.position[0] + 1][(int) this.position[1]];
                next_person = this.map.getPersons()[(int) this.position[0] + 1][(int) this.position[1]];
                try {if (next_person.getClass() == model.Person.Player.Player.class) {next_person = null;}}
                catch (NullPointerException e) {}
                if (next_chunk.getWalkable() && next_person == null) {
                    this.dx = 1;
                    this.dy = 0;
                }
                else {
                    this.dx = -1;
                    this.dy = 0;
                }
            }
        }

        if (Math.abs(player.getPosition()[0] - this.position[0]) < 2 && Math.abs(player.getPosition()[1] - this.position[1]) < 2) {
            this.melee_attack();  // si le joueur est tout près, attaque
        }
    }

    // fuis le joueur
    public void escape_player(Player player) {  // fonction qui dicte les déplacements du PNJ si celui-ci doit fuire un joueur
        int desired_dx = 0;
        int desired_dy = 0;
        if (player.getPosition()[0] != this.position[0]) {  // déplacement désiré en X
            desired_dx = -1 * ((int) ((player.getPosition()[0] - this.position[0]) / Math.abs(player.getPosition()[0] - this.position[0])));
        }
        else if (player.getPosition()[1] != this.position[1]){  // déplacement désiré en Y
            desired_dy = -1 * ((int) ((player.getPosition()[1] - this.position[1]) / Math.abs(player.getPosition()[1] - this.position[1])));
        }

        // déplacement en x ou y? test si le npc sait se déplacer en x, sinon déplacement en y
        if (desired_dx !=0) {
            Chunk next_chunk = this.map.getChunks()[(int) this.position[0] + desired_dx][(int) this.position[1]];
            Person next_person = this.map.getPersons()[(int) this.position[0] + desired_dx][(int) this.position[1]];
            if (next_chunk.getWalkable() && next_person == null) {  // le joueur peut se déplacer en x
                this.dx = desired_dx;
                this.dy = 0;
            }
            else {  // déplacement en y
                next_chunk = this.map.getChunks()[(int) this.position[0]][(int) this.position[1] + 1];
                next_person = this.map.getPersons()[(int) this.position[0]][(int) this.position[1] + 1];
                if (next_chunk.getWalkable() && next_person == null) {
                    this.dx = 0;
                    this.dy = 1;
                }
                else {
                    this.dx = 0;
                    this.dy = -1;
                }
            }
        }
        else if (desired_dy !=0) {
            Chunk next_chunk = this.map.getChunks()[(int) this.position[0]][(int) this.position[1] + desired_dy];
            Person next_person = this.map.getPersons()[(int) this.position[0]][(int) this.position[1] + desired_dy];
            if (next_chunk.getWalkable() && next_person == null) {  // le joueur peut se déplacer en y
                this.dx = 0;
                this.dy = desired_dy;
            }
            else {  // déplacement en x
                next_chunk = this.map.getChunks()[(int) this.position[0] + 1][(int) this.position[1]];
                next_person = this.map.getPersons()[(int) this.position[0] + 1][(int) this.position[1]];
                if (next_chunk.getWalkable() && next_person == null) {
                    this.dx = 1;
                    this.dy = 0;
                }
                else {
                    this.dx = -1;
                    this.dy = 0;
                }
            }
        }
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
