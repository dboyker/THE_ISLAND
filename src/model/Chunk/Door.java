package model.Chunk;
import model.*;

import java.awt.*;

/**
 * Created by davidboyker on 3/04/16.
 */
public class Door extends Chunk {

    protected Map map;
    protected Door leadTo;

    public Door(Map map) {
        super(Color.BLACK,true);this.map = map;
    }

    public void setLeadTo(Door leadTo) {this.leadTo = leadTo;}

    public void interact() {
        //get new map and set player to opposite door
        Map newMap = leadTo.map;
        Game game = map.game;
        for (int i = 0; i < newMap.getHeight(); i++) {
            for (int j = 0; j < newMap.getWidth(); j++) {
                if (newMap.getChunks()[j][i] == leadTo) { //  move player to this position
                    float[] position = new float[2];
                    position[0] = j;
                    if (this.map.getClass() == model.BuildingMap.class) {position[1] = i+1;}
                    else {position[1] = i-1;}
                    game.getPlayer().setPosition(position);
                    game.getPlayer().setMap(leadTo.map);
                    game.getFrame().game_panel.mini_map.setMap(leadTo.map); // change mini map displayed map
                }
            }
        }
        game.getFrame().game_panel.game_screen.setMap(newMap);  // make the game screen display the new map

    }
}

