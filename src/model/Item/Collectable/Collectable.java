package model.Item.Collectable;

import model.Person.*;
import model.Item.*;

/**
 * Created by davidboyker on 26/04/16.
 */
public interface Collectable {

    void use(Person person);

    String getName();
}

