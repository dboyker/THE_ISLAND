package model.Person.Player;
import controller.Thread.PlayerThread;
import model.Item.Collectable.Drug;
import model.Item.Collectable.MediKit;
import model.Map.Map;
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
        MediKit medi_kit = new MediKit();
        Drug drug = new Drug();
        this.inventory.setItems(medi_kit);
        this.inventory.setItems(drug);
        this.speed = 30;
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
        this.health = 100;
        this.money = 100;
        this.thread = new Thread(new PlayerThread(this));
    }

    public Inventory getInventory() {return this.inventory;}

}
