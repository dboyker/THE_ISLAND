// thread pour les objets de classe Fire

package model.Item.Hazardous;

import model.Item.Hazardous.*;
import model.Person.Person;

/**
 * Created by davidboyker on 28/04/16.
 */
public class FireThread implements Runnable {

    private Fire fire;

    public FireThread(Fire  fire) {
        this.fire = fire;
    }

    public void run() {
        int c = 1;
        while (c <= 100) { // chaque objet fire n'est actif que pendant 100 itérations
            int pos_x = (int) fire.getPosition()[0];
            int pos_y = (int) fire.getPosition()[1];
            Person victim = fire.getMap().getPersons()[pos_x][pos_y];
            if (victim != null) {
                // si une personne se trouve à la même position que l'objet Fire, il se voit affligé des dégâts
                victim.setHealth(fire.getDamage()/10);
            }
            try {
                Thread.sleep(100); // une personne peut etre blessé jusqu'à 10 fois par seconde par le feu
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            c ++;
        }
        fire.terminate_thread();
    }
}
