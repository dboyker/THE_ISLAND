/**
 * Created by davidboyker on 28/03/16.
 */

package model.Person;

import model.Item.Instantaneous.Coin;
import model.Person.NPC.OpponentThread;
import model.Person.Player.PlayerThread;
import model.Item.Hazardous.Bullet;
import model.Item.Hazardous.Fire;
import model.Map.Map;
import model.Chunk.*;
import model.Item.*;
import model.Person.NPC.Opponent;
import model.Person.Player.Player;
import java.awt.*;
import java.io.Serializable;


public class Person implements Serializable {

    protected transient Thread thread;
    protected Map map;
    protected int health;
    protected int money;
    protected int speed = 50;
    protected int melee_damage = -10;
    protected int shoot_damage = -10;
    protected int fire_damage = -10;
    protected float[] position;
    protected int[] direction;
    protected int dx = 0;
    protected int dy = 0;
    // indicateurs d'attaques. Sont modifiés par un threas si PNJ ou par le clavier si joueur. Il dictent le comportement de la fonction attack()
    protected Boolean melee_attack = false;
    protected Boolean fire_attack = false;
    protected Boolean shoot_attack = false;
    // couleur
    protected java.awt.Color color;
    // différentes images pour les animations
    protected transient Image image;
    protected transient Image image_up;
    protected transient Image image_up_1;
    protected transient Image image_up_2;
    protected transient Image image_down;
    protected transient Image image_down_1;
    protected transient Image image_down_2;
    protected transient Image image_left;
    protected transient Image image_left_1;
    protected transient Image image_left_2;
    protected transient Image image_right;
    protected transient Image image_right_1;
    protected transient Image image_right_2;

    public Person(Map map, float[] position) {
        this.map = map;
        this.position = new float[2];
        this.position = position;
        this.map.getPersons()[(int)position[0]][(int)position[1]] = this;
        direction = new int[]{0, 1};
        this.color = Color.red;
    }

    // ---------- SET & GET ----------
    public Image getImage() {return this.image;}
    public void reset_image() {}
    public void setPosition(float[] position) {
        this.position = position;
        this.map.getPersons()[(int) position[0]][(int) position[1]] = this;
    }
    public float[] getPosition() {return this.position;}
    public int[] getDirection() {return this.direction;}
    public void setDx(int dx) {this.dx = dx;}
    public void setDy(int dy) {this.dy = dy;}
    public void setMelee_attack(Boolean b) {this.melee_attack = b;}
    public void setFire_attack(Boolean b) {this.fire_attack = b;}
    public void setShoot_attack(Boolean b) {this.shoot_attack = b;}
    public void setMap(Map map) {this.map = map;}
    public Map getMap() {return this.map;}
    public Color getColor() {return this.color;}
    public void setHealth(int change) {
        this.health += change;
        if (this.health <= 0) {  // la personne n'a plus de vie
            this.color = Color.black;
            float[] pos = new float[2];
            pos[0] = (int) position[0];
            pos[1] = (int) position[1];
            new Coin(map, pos, this.money);  // drop money
            this.image = null;
            if (this.getClass() == model.Person.Player.Player.class) {
                map.getGame().getController().game_over();
            }
            this.thread = null;
            map.delete_person(this);
        }
        if (this.health > 100) {  // la santé a un palier à 100
            this.health = 100;
        }
    }
    public int getHealth() {return this.health;}
    public int getMoney() {return this.money;}
    public void setMoney(int money) {this.money += money;}
    public int getFire_damage() {return this.fire_damage;}
    public int getShoot_damage() {return this.shoot_damage;}
    public void setMelee_damage(int c) {this.melee_damage *= c;}
    public void setFire_damage(int c) {this.fire_damage *= c;}
    public void setShoot_damage(int c) {this.shoot_damage *= c;}
    public void setSpeed(int value) {this.speed = value;System.out.println(this.speed);}

    // ---------- Threading ----------
    public void startThread() {
        try {this.thread.start();}
        catch (NullPointerException e) {
            if (this.getClass() == model.Person.Player.Player.class) {
                this.thread = new Thread(new PlayerThread((Player) this));
                this.thread.start();
            }
            else if (this.getClass() == model.Person.NPC.Opponent.class) {
                this.thread = new Thread(new OpponentThread((Opponent) this));
                this.thread.start();
            }
        }
    }

    public void suspendThread(){
        try {this.thread.suspend();}
        catch (NullPointerException e) {}
    }

    public void resumeThread(){
        try {this.thread.resume();}
        catch (NullPointerException e) {}
    }

    // ---------- Déplacements et attaques ----------
    // fonction de déplacement de l'objet Person
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
            Chunk next_chunk;
            Boolean walkable;
            try {
                next_chunk = map.getChunks()[(int) (position[0]) + dx][(int) (position[1]) + dy];
                walkable = next_chunk.getWalkable();
            }
            catch (ArrayIndexOutOfBoundsException e) {next_chunk = null;walkable=false;}
            Person next_person;
            try {
                next_person = map.getPersons()[(int) (position[0]) + dx][(int) (position[1]) + dy];
            }
            catch (ArrayIndexOutOfBoundsException e) {next_person = null;}
            if (!walkable || next_person != null) {
                // impossible to move
                this.dx = 0;
                this.dy = 0;
            }
            else {  // move
                if (dx != 0 && dy != 0) {
                    // empêche les déplacements en diagonale
                    dy = 0;
                }
                map.getPersons()[(int) position[0]][(int) position[1]] = null;  // La personne quitte son chunk actuel
                if (this.health > 0) {
                    map.getPersons()[(int) position[0] + dx][(int) position[1] + dy] = this;
                }
                else {return;}
                int c = 0;
                int movex = dx;
                int movey = dy;
                while (c < 10) {
                    // animations de déplacements
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
                        Thread.sleep(this.speed);
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
                Chunk chunk = map.getChunks()[(int) position[0]][(int) position[1]];
                if (this.getClass() == model.Person.Player.Player.class) { chunk.interact((Player) this); }
                Item item = map.getItems()[(int) position[0]][(int) position[1]];
                if (item != null) {if (this.getClass() == model.Person.Player.Player.class) { item.interact((Player) this); }}
            }
        }
    }

    public void attack() {  // fonction pour l'attaque. Elle lis les préférences d'attaque dictées par le thread de la personne (npc) ou le clavier (player) et lance l'attaque adéquate
        Boolean attack = false;
        // une seule attaque est possible en même temps
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
        if (attack) {  // après l'attaque, remise à zéro des indicateurs d'attaque
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
        // animation
        if (this.direction[1] == 1) {this.image = this.image_down_1;}
        else if (this.direction[1] == -1) {this.image = this.image_up_1;}
        else if (this.direction[1] == 0 && this.direction[0] == 1) {this.image = this.image_right_1;}
        else if (this.direction[1] == 0 && this.direction[0] == -1) {this.image = this.image_left_1;}
        // get next chunk position
        int target_pos_x = (int) this.position[0] + this.direction[0];
        int target_pos_y = (int) this.position[1] + this.direction[1];
        Person opponent;
        try {
            opponent = map.getPersons()[target_pos_x][target_pos_y];
        }
        catch (ArrayIndexOutOfBoundsException e) {opponent = null;}
        int damage = this.melee_damage;
        if (opponent != null) {
            opponent.setHealth(damage);
            }
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        // end animation
        if (this.direction[1] == 1) {this.image = this.image_down;}
        else if (this.direction[1] == -1) {this.image = this.image_up;}
        else if (this.direction[1] == 0 && this.direction[0] == 1) {this.image = this.image_right;}
        else if (this.direction[1] == 0 && this.direction[0] == -1) {this.image = this.image_left;}
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
            this.map.getItems()[target_pos_x][target_pos_y] = new Fire(map, this, position);
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
        this.map.getItems()[target_pos_x][target_pos_y] = new Bullet(map, this, position, direction);
    }

}
