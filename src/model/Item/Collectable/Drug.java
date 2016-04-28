package model.Item.Collectable;

import model.Item.Item;
import model.Person.Person;

/**
 * Created by davidboyker on 20/04/16.
 */
public class Drug extends Collectable {
    private int effect = 25;

    public Drug() {
        super("drug", null, null);
    }

    @Override
    public void use(Person person) {
        person.setHealth(25);
    }
}
