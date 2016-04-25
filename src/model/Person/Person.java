package model.Person;
import model.Item.Weapon;
import model.Map;
import model.Chunk.*;
import view.Frame;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Person implements Serializable {

    protected Thread thread;
    protected Map map;
    protected int health;
    protected int money;
    protected int speed = 50;
    protected Chunk chunk;
    protected float[] position;
    protected int[] direction;
    protected int dx = 0;
    protected int dy = 0;
    protected Weapon melee_weapon;
    protected java.awt.Color color;
    protected Image image;
    protected Image image_up;
    protected Image image_down;
    protected Image image_left;
    protected Image image_right;

    public Person(Map map, float[] position, Color color) {
        this.map = map;
        this.position = new float[2];
        this.position = position;
        this.chunk = this.map.getChunks()[(int)position[0]][(int)position[1]];
        direction = new int[]{0, -1};
        this.color = color;
        this.health = 60;
        this.money = 30;
    }

    public void startThread() {
        this.thread.start();
    }

    public Image getImage() {return this.image;}
    public void setPosition(float[] position) {
        this.position = position;
        this.map.getPersons()[(int) position[0]][(int) position[1]] = this;
    }
    public float[] getPosition() {
        return this.position;
    }
    public void setDx(int dx) {this.dx = dx;}
    public void setDy(int dy) {this.dy = dy;}
    public void setMap(Map map) {
        this.map = map;
    }
    public Map getMap() {
        return this.map;
    }
    public Color getColor() {
        return this.color;
    }
    public void setHealth(int change) {
        this.health += change;
        if (this.health < 0) {  // dead
            this.color = Color.black;
            map.getPersons()[(int) position[0]][(int) position[1]] = null;  // remove the player
        }
        if (this.health > 100) {  // maximum health is 100
            this.health = 100;
        }
    }
    public int getHealth() {return this.health;}
    public int getMoney() {return this.money;}
    public void setMoney(int money) {
        this.money += money;
    }

    public void move() {
        if (dx != 0 || dy != 0) {
            //  changement de direction
            if (dx != 0) {
                this.direction[0] = (int) dx;
                this.direction[1] = 0;
                if (dx > 0) {this.image = this.image_right;}
                else {this.image = this.image_left;}
            } else if (dy != 0) {
                this.direction[0] = 0;
                this.direction[1] = (int) dy;
                if (dy > 0) {this.image = this.image_down;}
                else {this.image = this.image_up;}
            }
            Chunk next_chunk = map.getChunks()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            Person next_person = map.getPersons()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            if (!next_chunk.getWalkable() || next_person != null) {
                // impossible to move
                this.dx = 0;
                this.dy = 0;
            } else {  // move
                map.getPersons()[(int) position[0]][(int) position[1]] = null;  // person leaves old chunk
                if (this.health > 0) {
                    map.getPersons()[(int) position[0] + dx][(int) position[1] + dy] = this;  // person is on the new chunk
                }
                int c = 0;
                int movex = dx;
                int movey = dy;
                while (c < 10) {
                    position[0] += movex * 0.1;
                    position[1] += movey * 0.1;
                    position[0] = (float) (Math.round(position[0] * 100.0) / 100.0);  // round to 1 digit
                    position[1] = (float) (Math.round(position[1] * 100.0) / 100.0);  // round to 1 digit
                    c++;
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                chunk = map.getChunks()[(int) position[0]][(int) position[1]];
                if (this.getClass() == model.Person.Player.Player.class) { chunk.interact(); }
            }
        }
    }

    public void melee_attack() {
        //get the player at the next case
        float x = position[0] + direction[0];
        float y = position[1] + direction[1];
        try {
            Person opponent;
            opponent = map.getPersons()[(int) x][(int) y];
            int damage = this.melee_weapon.getDamage();
            opponent.setHealth(-1*damage);
            if (opponent.getHealth() < 0) {  // opponent is killed
                this.setMoney(opponent.getMoney());
            }
        } catch (NullPointerException e) { // no opponent}
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
