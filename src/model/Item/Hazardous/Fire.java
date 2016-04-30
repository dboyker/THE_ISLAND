package model.Item.Hazardous;

import controller.Thread.FireThread;
import javax.swing.*;
import model.Map.Map;
/**
 * Created by davidboyker on 26/04/16.
 */
public class Fire extends Hazardous {

    private Thread thread;
    private Map map;

    public Fire(Map map, float[] position){
        super("fire", new ImageIcon("image/fire.png").getImage(), position);
        this.damage = -5;
        this.map = map;
        this.thread = new Thread(new FireThread(map, this));
        (thread).start();
    }

    public void terminate_thread() {
        thread.interrupt();
        map.getItems()[(int) position[0]][(int) position[1]] = null;
    }
}
