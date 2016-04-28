package model.Item;

import model.Person.Person;

import java.awt.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class ChestActivator extends Item {

        public ChestActivator(float[] position) {
            super("chest activator", Color.black, null, position);
        }

        @Override
        public void interact(Person person) {
            System.out.println("chest activator");
            // launch the chest interface
        }
}
