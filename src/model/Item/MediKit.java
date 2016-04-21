package model.Item;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.*;

/**
 * Created by davidboyker on 19/04/16.
 */
public class MediKit extends Item {

    private int effect = 25;

    public MediKit() {name = "medi-kit";}

    @Override
    public void use(Person person) {
        person.setHealth(25);
    };

}
