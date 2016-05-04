// Classe pour l'inventaire dont dispose chaque joueur

package model.Person.Player;

import model.Item.Collectable.Collectable;
import java.io.Serializable;

/**
 * Created by davidboyker on 2/04/16.
 */
public class Inventory implements Serializable {

    private Player player;
    private Collectable[] items;
    private int inventory_size;


    public Inventory(Player player) {
        this.player = player;
        inventory_size = 2;
        items = new Collectable[inventory_size];
    }

    // GET & SET
    public Player getPlayer() {return this.player;}
    public Boolean setItems(Collectable item) {
        int length = 0;
        for (int i = 0; i < inventory_size; i++) {if (items[i] != null) {length += 1;}}
        if (length < inventory_size) {items[length] = item; return true;}
        else {return false;}  // pas assez de place pour rajouter objet
    }
    public Collectable[] getItems() {return this.items;}


    public void removeItem(Collectable item) {
        for (int i = 0; i < inventory_size; i++) {
            if (items[i] == item) {
                System.out.println("remove item");
                items[i] = null;
            }
        }
    }

}
