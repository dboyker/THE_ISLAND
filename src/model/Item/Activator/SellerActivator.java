package model.Item.Activator;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class SellerActivator extends Item {

    public SellerActivator(float[] position) {
        super("seller activator", Color.black, null, position);
    }

    @Override
    public void interact(Person person) {
        // launch the seller interface
        if (person.getClass() == model.Person.Player.Player.class) {
            person.getMap().game.getController().seller((Player) person);
        }
    }
}
