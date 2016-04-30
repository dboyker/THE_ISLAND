package model.Person;
import model.Item.Hazardous.Bullet;
import model.Item.Hazardous.Fire;
import model.Map.Map;
import model.Chunk.*;
import model.Item.*;

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
    protected Boolean melee_attack = false;
    protected Boolean fire_attack = false;
    protected Boolean shoot_attack = false;
    protected java.awt.Color color;
    protected Image image;
    protected Image image_up;
    protected Image image_up_1;
    protected Image image_up_2;
    protected Image image_down;
    protected Image image_down_1;
    protected Image image_down_2;
    protected Image image_left;
    protected Image image_left_1;
    protected Image image_left_2;
    protected Image image_right;
    protected Image image_right_1;
    protected Image image_right_2;

    public Person(Map map, float[] position, Color color) {
        this.map = map;
        this.position = new float[2];
        this.position = position;
        this.chunk = this.map.getChunks()[(int)position[0]][(int)position[1]];
        this.map.getPersons()[(int)position[0]][(int)position[1]] = this;
        direction = new int[]{0, 1};
        this.color = color;
    }

    public void startThread() {
        try {this.thread.start();}
        catch (NullPointerException e) {}
    }

    public void suspendThread(){
        try {this.thread.suspend();}
        catch (NullPointerException e) {}
    }

    public void resumeThread(){
        try {this.thread.resume();}
        catch (NullPointerException e) {}
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
    public void setMelee_attack(Boolean b) {this.melee_attack = b;}
    public void setFire_attack(Boolean b) {this.fire_attack = b;}
    public void setShoot_attack(Boolean b) {this.shoot_attack = b;}
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
        if (this.health <= 0) {  // dead
            this.color = Color.black;
            //this.image = null;
            float[] pos = new float[2];
            pos[0] = (int) position[0];
            pos[1] = (int) position[1];
            new Coin(map, pos, this.money);  // drop money
            if (this.getClass() == model.Person.Player.Player.class) {
                map.game.getController().game_over();
            }
            map.deletPerson(this);  // remove the player
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
            if (dx != this.direction[0] || dy != this.direction[1]) { //  changement de direction
                if (dx != 0) {
                    this.direction[0] = (int) dx;
                    this.direction[1] = 0;
                    if (dx > 0) {
                        this.image = this.image_right;
                    } else {
                        this.image = this.image_left;
                    }
                } else if (dy != 0) {
                    this.direction[0] = 0;
                    this.direction[1] = (int) dy;
                    if (dy > 0) {
                        this.image = this.image_down;
                    } else {
                        this.image = this.image_up;
                    }
                }
            }
            Chunk next_chunk = map.getChunks()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            Person next_person = map.getPersons()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            if (!next_chunk.getWalkable() || next_person != null) {
                // impossible to move
                this.dx = 0;
                this.dy = 0;
            }
            else {  // move
                if (dx != 0 && dy != 0) {
                    // empêche les déplacements en diagonale
                    dy = 0;
                }
                map.getPersons()[(int) position[0]][(int) position[1]] = null;  // person leaves old chunk
                if (this.health > 0) {
                    map.getPersons()[(int) position[0] + dx][(int) position[1] + dy] = this;  // person is on the new chunk
                }
                else {return;}
                int c = 0;
                int movex = dx;
                int movey = dy;
                while (c < 10) {
                    if (movey < 0 && c < 5) {
                        this.image = this.image_up_1;
                    }
                    else if (movey < 0 && c > 5) {
                        this.image = this.image_up_2;
                    }
                    else if (movey > 0 && c < 5) {
                        this.image = this.image_down_1;
                    }
                    else if (movey > 0 && c > 5) {
                        this.image = this.image_down_2;
                    }
                    else if (movex < 0 && c < 5) {
                        this.image = this.image_left_1;
                    }
                    else if (movex < 0 && c > 5) {
                        this.image = this.image_left_2;
                    }
                    else if (movex > 0 && c < 5) {
                        this.image = this.image_right_1;
                    }
                    else if (movex > 0 && c > 5) {
                        this.image = this.image_right_2;
                    }
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
                if (direction[1] < 0) {
                    this.image = this.image_up;
                }
                else if (direction[1] > 0) {
                    this.image = this.image_down;
                }
                else if (direction[0] < 0) {
                    this.image = this.image_left;
                }
                else if (direction[0] > 0) {
                    this.image = this.image_right;
                }
                chunk = map.getChunks()[(int) position[0]][(int) position[1]];
                if (this.getClass() == model.Person.Player.Player.class) { chunk.interact(); }
                Item item = map.getItems()[(int) position[0]][(int) position[1]];
                try {item.interact(this);}
                catch (NullPointerException e) {}
            }
        }
    }

    public void attack() {
        Boolean attack = false;
        if (this.melee_attack) {
            melee_attack();
            attack = true;
        }
        else if (this.fire_attack) {
            fire_attack();
            attack = true;
        }
        else if (this.shoot_attack) {
            shoot_attack();
            attack = true;
        }
        if (attack == true) {
            this.shoot_attack = false;
            this.fire_attack = false;
            this.melee_attack = false;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // attaque de contact
    public void melee_attack() {
        // get next chunk position
        int target_pos_x = (int) this.position[0] + this.direction[0];
        int target_pos_y = (int) this.position[1] + this.direction[1];
        float[] position = new float[2];
        position[0] = target_pos_x;
        position[1] = target_pos_y;
        Person opponent;
        opponent = map.getPersons()[(int) target_pos_x][(int) target_pos_y];
        int damage = 10;
        if (opponent != null) {
            opponent.setHealth(-1 * damage);
            if (opponent.getHealth() < 0) {  // opponent is killed
                this.setMoney(opponent.getMoney());
                }
            }
    }

    // mets le feu à un chunk
    public void fire_attack() {
        // get next chunk position
        int target_pos_x = (int) this.position[0] + this.direction[0];
        int target_pos_y = (int) this.position[1] + this.direction[1];
        float[] position = new float[2];
        position[0] = target_pos_x;
        position[1] = target_pos_y;
        if (map.getChunks()[target_pos_x][target_pos_y].getWalkable()) {
            // put fire on it
            this.map.getItems()[target_pos_x][target_pos_y] = new Fire(map, position);
        }
    }

    // attaque à l'arme à feu
    public void shoot_attack() {
        int target_pos_x = (int) this.position[0] + this.direction[0];
        int target_pos_y = (int) this.position[1] + this.direction[1];
        float[] position = new float[2];
        position[0] = target_pos_x;
        position[1] = target_pos_y;
        float[] direction = new float[2];
        direction[0] = this.direction[0];
        direction[1] = this.direction[1];
        this.map.getItems()[target_pos_x][target_pos_y] = new Bullet(map, position, direction);
    }

}
