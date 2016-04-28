package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.*;

import java.awt.*;

/**
 * Created by davidboyker on 19/04/16.
 */
public class MediKit extends Collectable {

    private int effect = 25;

    public MediKit() {
        super("medi-kit", null, null);
    }

    @Override
    public void use(Person person) {
        person.setHealth(25);
    };

}
