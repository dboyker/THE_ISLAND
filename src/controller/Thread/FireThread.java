package controller.Thread;

import model.Item.Hazardous.*;
import model.Map.Map;
import model.Person.Person;

/**
 * Created by davidboyker on 28/04/16.
 */
public class FireThread implements Runnable {

    private Fire fire;
    private Map map;

    public FireThread(Map map, Fire  fire) {
        this.map = map;
        this.fire = fire;
    }

    public void run() {
        int c = 1;
        while (c < 100) {
            int pos_x = (int) fire.getPosition()[0];
            int pos_y = (int) fire.getPosition()[1];
            Person victim = map.getPersons()[pos_x][pos_y];
            if (victim != null) {
                victim.setHealth(fire.getDamage()/5);
            }
            try {
                Thread.sleep(50);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            c ++;
        }
        fire.terminate_thread();
    }
}
