package model.Chunk;
import model.*;
import model.Map.BuildingMap;
import model.Map.Map;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 3/04/16.
 */
public class Door extends Chunk {

    protected Map map;
    protected Door leadTo;

    public Door(Map map) {
        super(new ImageIcon("image/door.png").getImage(), Color.BLACK, true);
        this.map = map;
    }

    public void setLeadTo(Door leadTo) {this.leadTo = leadTo;}
    public Door getLeadTo() {return this.leadTo;}
    public Map getMap() {return this.map;}

    public void interact(Player player) {
        //get new map and set player to opposite door
        Map newMap = leadTo.map;
        Game game = map.game;
        for (int i = 0; i < newMap.getHeight(); i++) {
            for (int j = 0; j < newMap.getWidth(); j++) {
                if (newMap.getChunks()[j][i] == leadTo) { //  move player to this position
                    float[] position = new float[2];
                    position[0] = j;
                    if (this.map.getClass() == BuildingMap.class) {position[1] = i+1;}
                    else {position[1] = i-1;}
                    map.getPersons()[(int) player.getPosition()[0]][(int) player.getPosition()[1]] = null;
                    player.setMap(leadTo.map);
                    player.setPosition(position); // change mini map displayed map
                    if (game.getController().getFrame().game_panel_1.getPlayer() == player) {
                        game.getController().getFrame().game_panel_1.mini_map.setMap(leadTo.map);
                        game.getController().getFrame().game_panel_1.game_screen.setMap(newMap);  // make the game screen display the new map
                    }
                    else {
                        game.getController().getFrame().game_panel_2.mini_map.setMap(leadTo.map);
                        game.getController().getFrame().game_panel_2.game_screen.setMap(newMap);  // make the game screen display the new map
                    }
                }
            }
        }
    }
}

