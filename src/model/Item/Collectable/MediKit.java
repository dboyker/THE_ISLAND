package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 19/04/16.
 */
public class MediKit extends Item implements Collectable {

    public MediKit() {
        super("medi-kit", null, new ImageIcon("image/medikit.png").getImage(), null);
    }

    public void use(Person person) {
        person.setHealth(100);
    }

    @Override
    public void interact(Person person) {
        if (person.getClass() == model.Person.Player.Player.class) {
            Player player = (Player) person;
            Boolean added = player.getInventory().setItems(this);
            if (added) {map.deleteItem(this);}
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void reset_image() {
        image = new ImageIcon("image/medikit.png").getImage();
    }
}
