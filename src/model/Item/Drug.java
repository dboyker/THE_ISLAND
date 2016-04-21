package model.Item;

import model.Person.Person;

/**
 * Created by davidboyker on 20/04/16.
 */
public class Drug extends Item {
    private int effect = 25;

    public Drug() {name = "drug";}

    @Override
    public void use(Person person) {
        person.setHealth(25);
    };
}
