// Classe pour l'inventaire dont dispose chaque joueur

/**
 * Created by davidboyker on 2/04/16.
 */

package model.Person.Player;

import model.Item.Collectable.Collectable;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    private Player player;
    private ArrayList<Collectable> items;
    private int inventory_size;


    public Inventory(Player player, int inventory_size) {
        this.player = player;
        this.inventory_size = inventory_size;
        items = new ArrayList<Collectable>();
    }

    // GET & SET
    public Player getPlayer() {return this.player;}
    public ArrayList<Collectable> getItems() {return this.items;}
    public Boolean setItems(Collectable item) {  // ajout d'un objet à l'inventaire
        int length = 0;
        for (int i = 0; i<items.size(); i++) {
            if (items.get(i)!= null) {
                length += 1;
            }
        }
        if (length < inventory_size) {items.add(length, item); return true;}
        else {return false;}  // pas assez de place pour rajouter objet
    }

    public void remove_item(Collectable item) {  // suppresion d'un objet de l'inventaire
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                System.out.println("remove item from inventory");
                items.set(i,null);
            }
        }
    }

}
