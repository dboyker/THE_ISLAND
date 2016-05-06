// Classe pour les coeurs à ramasser

package model.Item.Instantaneous;

import model.Item.Item;
import model.Map.Map;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/04/16.
 */
public class Heart extends Item {

    public Heart(Map map, float[] position) {
        super("name", Color.yellow, new ImageIcon("image/heart.png").getImage(), position);
        this.map = map;
        map.getItems()[(int) position[0]][(int) position[1]] = this;
    }

    public void interact(Player player) {
            player.setHealth(10);
            map.delete_item(this);
    }
}
