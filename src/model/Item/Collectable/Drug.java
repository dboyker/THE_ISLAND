/**
 * Created by davidboyker on 20/04/16.
 */

package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;

public class Drug extends Item implements Collectable {

    private Thread thread;

    public Drug() {super("drug", null, new ImageIcon("image/drug.png").getImage(), null);}

    public String getName() {return this.name;}

    public void use(Person person) {
        this.thread = new Thread(new DrugThread(person, this));
        this.thread.start();
    }

    @Override
    public void interact(Player player) {
            Boolean added = player.getInventory().setItems(this);
            if (added) {map.delete_item(this);}
    }

    public void terminate_thread() {thread.interrupt();}

    @Override
    public void reset_image() {image = new ImageIcon("image/drug.png").getImage();}
}