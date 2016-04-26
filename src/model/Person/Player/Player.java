package model.Person.Player;
import controller.PlayerThread;
import model.Item.Collectable.Drug;
import model.Item.Collectable.MediKit;
import model.Item.Weapon.Weapon;
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
        Weapon machette = new Weapon(20,20);  // creation d'une machette
        this.melee_weapon = machette;
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
        this.thread = new Thread(new PlayerThread(this));
    }

    public Inventory getInventory() {
        return this.inventory;
    }

}
