package model.Person.NPC;

import model.Map;
import model.Person.Person;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 31/03/16.
 */
public class NPC extends Person {
    public NPC(Map map, float[] position, Color color) {
        super(map, position, color);
    }

}
