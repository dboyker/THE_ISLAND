package model.Item.Hazardous;

import model.Chunk.Chunk;
import model.Map.Map;
import model.Person.Person;

import javax.swing.*;

/**
 * Created by davidboyker on 28/04/16.
 */
public class Bullet extends Hazardous {

    private transient Thread thread;
    private float[] direction;

    public Bullet(Map map, Person person, float[] position, float[] direction){
        super("bullet", new ImageIcon("image/bullet.png").getImage(), position);
        this.damage = person.getShoot_damage();
        this.map = map;
        this.direction = direction;
        this.thread = new Thread(new BulletThread(this));
        (thread).start();
    }

    public void move() {
        // see if player
        Person person = map.getPersons()[(int) position[0]][(int) position[1]];
        if (person != null) {
            // hurt him
            person.setHealth(this.damage);
            // terminate
            this.terminate_thread();
        }
        Chunk chunk = map.getChunks()[(int) position[0]][(int) position[1]];
        if (!chunk.getWalkable()) {
            this.terminate_thread();
        }
        this.map.deleteItem(this);
        this.map.getItems()[(int) (position[0] + direction[0])][(int) (position[1] + direction[1])] = this;
        this.position[0] += this.direction[0];
        this.position[1] += this.direction[1];
        try {
            Thread.sleep(200);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.position[0] = (int) this.position[0];
        this.position[1] = (int) this.position[1];
    }

    public void terminate_thread() {
        map.deleteItem(this);
        this.thread.suspend();
    }

    @Override
    public void reset_image() {
        image = new ImageIcon("image/bullet.png").getImage();
    }
}
