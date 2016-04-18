package model.Chunk;
import model.*;
import controller.*;

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
                    int[] position = {j,i};
                    game.getPlayer().setPosition(position);
                }
            }
        }
        game.getFrame().game_panel.game_screen.setMap(newMap);  // set new map to game panel

    }
}

