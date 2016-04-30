package model.Person.Player;

import model.Item.Collectable.Collectable;
import model.Item.Item;

/**
 * Created by davidboyker on 2/04/16.
 */
public class Inventory {

    private Player player;
    private Collectable[] items;
    private int inventory_size;


    public Inventory(Player player) {
        this.player = player;
        inventory_size = 5;
        items = new Collectable[inventory_size];
    }

    public Player getPlayer() {return this.player;}
    public void setItems(Collectable item) {
        int length = 0;
        for (int i = 0; i < inventory_size; i++) {
            if (items[i] != null) {length += 1;}
        }
        if (length < inventory_size) {  // assez de place pour ajout d'objet
            items[length] = item;
        }
        else {  // pas assez de place pour rajouter objet
        }
    }
    public Collectable[] getItems() {return this.items;}
    public void removeItem(Collectable item) {
        for (int i = 0; i < inventory_size; i++) {
            if (items[i] == item) {
                System.out.println("remove item");
                items[i] = null;}
        }
    }

}
