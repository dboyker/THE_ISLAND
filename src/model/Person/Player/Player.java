// Classe pour les joueurs

/**
 * Created by davidboyker on 28/03/16.
 */

package model.Person.Player;
import model.Item.Collectable.Drug;
import model.Item.Collectable.MediKit;
import model.Map.Map;
import model.Person.Person;

import javax.swing.*;

public class Player extends Person {

    private Inventory inventory;
    private Inventory chest;

    public Player(Map map, float[] position) {
        super(map, position);
        inventory = new Inventory(this, 5);  // creation d'un inventaire pour le joueur. contenance de 5 items max
        chest = new Inventory(this, 100);  // 100 emplacements pour stocker des items
        // objets de base dans l'inventaire
        this.inventory.setItems(new MediKit());
        this.inventory.setItems(new Drug());
        this.speed = 30;  // réglage temps de déplacement. Correspond au temps pour parcourir 1pixel, en ms
        reset_image();
        this.health = 100;
        this.money = 1000;
        this.thread = new Thread(new PlayerThread(this));  // création d'un thread pour chaque joueur
    }

    // GET & SET
    public Inventory getInventory() {return this.inventory;}
    public Inventory getChest() {return this.chest;}

    @Override
    public void reset_image() {
        this.image_up = new ImageIcon("image/player/playeru.png").getImage();
        this.image_up_1 = new ImageIcon("image/player/playeru1.png").getImage();
        this.image_up_2 = new ImageIcon("image/player/playeru2.png").getImage();
        this.image_down = new ImageIcon("image/player/playerd.png").getImage();
        this.image_down_1 = new ImageIcon("image/player/playerd1.png").getImage();
        this.image_down_2 = new ImageIcon("image/player/playerd2.png").getImage();
        this.image_left = new ImageIcon("image/player/playerl.png").getImage();
        this.image_left_1 = new ImageIcon("image/player/playerl1.png").getImage();
        this.image_left_2 = new ImageIcon("image/player/playerl2.png").getImage();
        this.image_right = new ImageIcon("image/player/playerr.png").getImage();
        this.image_right_1 = new ImageIcon("image/player/playerr1.png").getImage();
        this.image_right_2 = new ImageIcon("image/player/playerr2.png").getImage();
        this.image = this.image_down;
    }

    // fonction pour l'amélioration des dégats d'attaque
    public void upgrade_attack(String type) {
        if (money >= 1000) {  // le joueur a assez d'argent pour upgrader ses attaques
            this.setMoney(-1000);
            if (type == "melee") {
                this.setMelee_damage(2);
            } else if (type == "fire") {
                this.setFire_damage(2);
            } else if (type == "shoot") {
                this.setShoot_damage(2);
            }
        }
    }

}
