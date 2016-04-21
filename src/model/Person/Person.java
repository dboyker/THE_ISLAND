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

    protected Map map;
    protected int health;
    protected int money;
    protected Chunk chunk;
    protected float[] position;
    protected int[] direction;
    protected int dx = 0;
    protected int dy = 0;
    protected Weapon melee_weapon;
    protected java.awt.Color color;
    protected Image image;

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

    public void setPosition(float[] position) {
        this.position = position;
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
        if (this.health < 0) { // dead
            this.color = Color.black;
            map.getChunks()[(int) position[0]][(int) position[1]].setPerson(null);
        }
        if (this.health > 100) {  // maximum health is 100
            this.health = 100;
        }
    }

    public int getHealth() {return this.health;}

    public int getMoney() {return this.money;}

    public void setMoney(int money) {
        System.out.println(money);
        this.money += money;
    }

    public void move(Frame frame) {
        if (dx != 0 || dy != 0) {
            if (dx != 0) {
                this.direction[0] = (int) dx;
                this.direction[1] = 0;
            } else if (dy != 0) {
                this.direction[0] = 0;
                this.direction[1] = (int) dy;
            }
            Chunk next_chunk = map.getChunks()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            if (!next_chunk.getWalkable() || next_chunk.getPerson() != null) {
                // impossible to move
                this.dx = 0;
                this.dy = 0;
            } else {  // move
                chunk.setPerson(null);
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
                        Thread.sleep(15);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                chunk = map.getChunks()[(int) position[0]][(int) position[1]];
                chunk.setPerson(this);
                chunk.interact();
            }
        }
    }

    public void melee_attack() {
        //get the player at the next case
        float x = position[0] + direction[0];
        float y = position[1] + direction[1];
        try {
            Person opponent;
            opponent = map.getChunks()[(int) x][(int) y].getPerson();
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
