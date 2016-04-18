package model.Person.NPC;

import model.Map;
import model.Person.Person;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 31/03/16.
 */
public class NPC extends Person {
    public NPC(Map map) {
        super(map,50,50, Color.blue);
    }

    public void setDx(float dx) {this.dx = dx;}
    public void setDy(float dy) {this.dy = dy;}
}
