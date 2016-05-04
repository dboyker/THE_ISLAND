package model.Item.Hazardous;

import javax.swing.*;
import model.Map.Map;
import model.Person.Person;
/**
 * Created by davidboyker on 26/04/16.
 */
public class Fire extends Hazardous {

    private transient Thread thread;

    public Fire(Map map, Person person, float[] position){
        super("fire", new ImageIcon("image/fire.png").getImage(), position);
        this.damage = person.getFire_damage();
        this.map = map;
        this.thread = new Thread(new FireThread(this));
        (thread).start();
    }

    public void terminate_thread() {
        thread.interrupt();
        map.getItems()[(int) position[0]][(int) position[1]] = null;
    }

    @Override
    public void reset_image() {
        image = new ImageIcon("image/fire.png").getImage();
    }

}
