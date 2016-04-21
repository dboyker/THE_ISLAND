package model.Person.Player;
import model.Item.*;
import model.Item.Weapon;
import model.Map;
import model.Person.Person;

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

    }

    public Inventory getInventory() {
        return this.inventory;
    }

}
