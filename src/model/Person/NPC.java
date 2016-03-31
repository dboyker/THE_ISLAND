package model.Person;

import model.Map;

/**
 * Created by davidboyker on 31/03/16.
 */
public class NPC extends Player {
    public NPC(Map map) {
        super(map);
        position = new int[2];
        position[0] = 50;
        position[1] = 50;
    }

    public void setDx(float dx) {this.dx = dx;}
    public void setDy(float dy) {this.dy = dy;}
}
