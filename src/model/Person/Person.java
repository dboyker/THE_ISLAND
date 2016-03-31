package model.Person;
import controller.*;
import model.Map;
import view.*;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Person {

    protected Map map;
    protected int health;
    protected int[] position;
    protected float dx = 0;
    protected float dy = 0;

    public Person(Map map) {
        this.map = map;
    }

    public void setPosition(int[] position) {this.position = position;}
    public int[] getPosition() {return this.position;}

    public void move(Frame frame) {

        if (dx != 0) {  // need to move in the x-axis
            if (map.getChunks()[(int) (position[0] + dx)][position[1]].getWalkable() == false) {
                this.dx = 0;
            }
            else {
                position[0] += dx;
                frame.game_panel.move_player(dx, 0);
                this.dx = 0;
            }
        }
        if (dy != 0) {  // need to move in the y-axis
            if (map.getChunks()[position[0]][(int) (position[1] + dy)].getWalkable() == false) {
                this.dy = 0;
            }
            else {
                position[1] += dy;
                frame.game_panel.move_player(0, dy);
                this.dy = 0;
            }
        }
    }
}
