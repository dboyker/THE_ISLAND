package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;

/**
 * Created by davidboyker on 20/04/16.
 */
public class Drug extends Item implements Collectable {

    private Thread thread;

    public Drug() {
        super("drug", null, new ImageIcon("image/drug.png").getImage(), null);
    }

    public void use(Person person) {
        this.thread = new Thread(new DrugThread(person, this));
        this.thread.start();
    }

    @Override
    public void interact(Person person) {
        if (person.getClass() == model.Person.Player.Player.class) {
            Player player = (Player) person;
            Boolean added = player.getInventory().setItems(this);
            if (added) {map.deleteItem(this);}
        }
    }

    public String getName() {return this.name;}

    public void terminate_thread() {thread.interrupt();}

    @Override
    public void reset_image() {
        image = new ImageIcon("image/drug.png").getImage();
    }
}