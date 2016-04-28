package model.Person.Player;
import controller.PlayerThread;
import model.Item.Collectable.Drug;
import model.Item.Collectable.MediKit;
import model.Item.Hazardous.Fire;
import model.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Player extends Person {

    private Inventory inventory;

    public Player(Map map, float[] position, Color color) {
        super(map, position, color);
        inventory = new Inventory(this);  // creation d'un inventaire pour le joueur
        //Weapon machette = new Weapon(20,20);  // creation d'une machette
       // this.melee_weapon = machette;
        MediKit medi_kit = new MediKit();
        Drug drug = new Drug();
        this.inventory.setItems(medi_kit);
        this.inventory.setItems(drug);
        this.speed = 20;
        this.image_up = new ImageIcon("image/player/playeru.png").getImage();
        this.image_down = new ImageIcon("image/player/playerd.png").getImage();
        this.image_left = new ImageIcon("image/player/playerl.png").getImage();
        this.image_right = new ImageIcon("image/player/playerr.png").getImage();
        this.image = this.image_down;
        this.health = 100;
        this.thread = new Thread(new PlayerThread(this));
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    // mets le feu à un chunk
    public void fire_attack() {
        // get next chunk position
        int target_pos_x = (int) this.position[0] + this.direction[0];
        int target_pos_y = (int) this.position[1] + this.direction[1];
        float[] position = new float[2];
        position[0] = target_pos_x;
        position[1] = target_pos_y;
        this.map.getItems()[target_pos_x][target_pos_y] = new Fire(position);
        // put fire on it

    }

    // attaque à l'arme à feu
    public void shoot_attack() {}

}
