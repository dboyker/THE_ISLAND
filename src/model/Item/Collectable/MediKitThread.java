// Classe pour les threads gérant les objets MediKit

package model.Item.Collectable;

import model.Person.Person;

/**
 * Created by davidboyker on 5/05/16.
 */
public class MediKitThread implements Runnable {

    private Person person;
    private MediKit medikit;

    public MediKitThread(Person person, MediKit  medikit) {
        this.person = person;
        this.medikit = medikit;
    }

    public void run() {
        int c = 1;
        while (c <= 200) {  // environ 20 secondes d'effet: 200 iterations de 100ms.
            person.setHealth(100); // la vie du joueur est constamment remise à 100.
            try {Thread.sleep(100);}
            catch (InterruptedException ex) {Thread.currentThread().interrupt();}
            c ++;
        }
        medikit.terminate_thread();
    }
}
