package model.Item.Hazardous;

import model.Item.Item;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 26/04/16.
 */
public class Fire extends Hazardous {

    public Fire(float[] position){
        super("fire", new ImageIcon("image/fire.png").getImage(), position);
        this.damage = -20;
    }
}
