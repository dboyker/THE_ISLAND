// Classe pour les boules de feu tirés par les joueurs

package model.Item.Hazardous;

import model.Item.Item;
import model.Chunk.Chunk;
import model.Map.Map;
import model.Person.Person;

import javax.swing.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Bullet extends Item implements Hazardous {

    private int damage;
    private transient Thread thread;
    private float[] direction;

    public Bullet(Map map, Person person, float[] position, float[] direction){
        super("bullet",null, new ImageIcon("image/bullet.png").getImage(), position);
        this.damage = person.getShoot_damage();
        this.map = map;
        this.direction = direction;
        this.thread = new Thread(new BulletThread(this));
        (thread).start();
    }

    // déplacement de la boule de feu sur la carte
    public void move() {
        // see if player
        Person person = map.getPersons()[(int) position[0]][(int) position[1]];
        if (person != null) {
            // blesse le joueur
            person.setHealth(this.damage);
            // terminate
            this.terminate_thread();
            return;
        }
        Chunk chunk = map.getChunks()[(int) position[0]][(int) position[1]];
        if (!chunk.getWalkable()) {  // si la boule de feu arrive sur une case qui n'est pas pratiquable (mur, arbre, etc.), elle disparait
            this.terminate_thread();
            return;
        }
        this.map.delete_item(this);  // la boule de feu détruit les objets sur son passage
        this.map.getItems()[(int) (position[0] + direction[0])][(int) (position[1] + direction[1])] = this;
        this.position[0] += this.direction[0];
        this.position[1] += this.direction[1];
        try {
            Thread.sleep(200);  // délai de déplacement
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.position[0] = (int) this.position[0];
        this.position[1] = (int) this.position[1];
    }

    public void terminate_thread() {
        map.delete_item(this);
        this.thread.suspend();
    }

    @Override
    public void reset_image() {
        image = new ImageIcon("image/bullet.png").getImage();
    }

    public int getDamage() {return this.damage;}
}
