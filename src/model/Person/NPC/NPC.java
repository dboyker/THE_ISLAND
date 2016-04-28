package model.Person.NPC;

import controller.OpponentThread;
import model.Map;
import model.Person.Person;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 31/03/16.
 */
public class NPC extends Person {
    public NPC(Map map, float[] position, Color color) {
        super(map, position, color);
        this.health = 60;
        this.money = 30;
    }
}
