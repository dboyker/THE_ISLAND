package model.Person;
import controller.*;
import model.Item.Weapon.Weapon;
import model.Map;
import view.*;
import model.Chunk.*;
import view.Frame;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Person implements Serializable {

    protected Map map;
    protected int health;
    protected int[] position;
    protected int[] direction;
    protected float dx = 0;
    protected float dy = 0;
    protected Weapon melee_weapon;
    protected java.awt.Color color;

    public Person(Map map, int x, int y, Color color) {
        this.map = map;
        position = new int[2];
        position[0] = x;
        position[1] = y;
        direction = new int[]{0,-1};
        this.color = color;
        map.getChunks()[x][y].setWalkable(false);
    }

    public void setPosition(int[] position) {this.position = position;}
    public int[] getPosition() {return this.position;}
    public void setHealth(int damage) {
        this.health -= damage;
    }
    public Color getColor() {return this.color;}

    public void move(Frame frame) {
        if (dx != 0 || dy != 0) {
            if (!map.getChunks()[(int) (position[0] + dx)][(int) (position[1] + dy)].getWalkable()) {  // impossible to walk
                this.dx = 0;
                this.dy = 0;
            }
            else {  // move
               // Chunk current_chunk = map.getChunks()[position[0]][position[1]];
                //current_chunk.setWalkable(true);
                position[0] += dx;
                position[1] += dy;
                this.dx = 0;
                this.dy = 0;
                //frame.game_panel.game_screen.move_player(dx,dy);

               // current_chunk = map.getChunks()[position[0]][position[1]];
                //current_chunk.setWalkable(false);
                //current_chunk.interact();
            }

        }
    }

    public void melee_attack() {
        //get the player at the next case
        int x = position[0] + direction[0];
        int y = position[1] + direction[1];
        try {
            Person opponent;
            opponent = map.getChunks()[x][y].getPlayer();
            int damage = this.melee_weapon.getDamage();
            opponent.setHealth(damage);
        }
        catch (NullPointerException e) { // no opponent
             }


    }
}
