// classe pour les portes des bâtiments

package model.Chunk;

import model.*;
import model.Map.BuildingMap;
import model.Map.Map;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 3/04/16.
 */
public class Door extends Chunk {

    protected Door leadTo;

    public Door(Map map) {
        super(new ImageIcon("image/door.png").getImage(), Color.BLACK, true);
        this.map = map;
    }

    @Override
    public void reset_image() {
        image = new ImageIcon("image/door.png").getImage();
    }

    public void setLeadTo(Door leadTo) {this.leadTo = leadTo;}
    public Map getMap() {return this.map;}

    public void interact(Player player) {
        Map newMap = leadTo.map;
        for (int i = 0; i < newMap.getHeight(); i++) {
            for (int j = 0; j < newMap.getWidth(); j++) {
                if (newMap.getChunks()[j][i] == leadTo) {
                    float[] position = new float[2];
                    position[0] = j;
                    if (this.map.getClass() == BuildingMap.class) {position[1] = i+1;}
                    else {position[1] = i-1;}
                    map.getPersons()[(int) player.getPosition()[0]][(int) player.getPosition()[1]] = null;
                    player.setMap(leadTo.map);
                    player.setPosition(position);
                }
            }
        }
    }

}

