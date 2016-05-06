/**
 * Created by davidboyker on 19/04/16.
 */

package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.*;

import javax.swing.*;

public class MediKit extends Item implements Collectable {

    private Thread thread;

    public MediKit() {super("medi-kit", null, new ImageIcon("image/medikit.png").getImage(), null);}

    public void use(Person person) {
        this.thread = new Thread(new MediKitThread(person, this));
        this.thread.start();
    }

    @Override
    public void interact(Player player) {
            Boolean added = player.getInventory().setItems(this);
            if (added) {map.delete_item(this);}

    }

    public String getName() {return this.name;}

    public void terminate_thread() {thread.interrupt();}

    @Override
    public void reset_image() {image = new ImageIcon("image/medikit.png").getImage();}
}
