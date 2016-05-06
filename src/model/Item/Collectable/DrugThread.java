// Classe thread pour les objets Drug

package model.Item.Collectable;

import model.Person.Person;

/**
 * Created by davidboyker on 3/05/16.
 */
public class DrugThread implements Runnable {

    private Person person;
    private Drug drug;

    public DrugThread(Person person, Drug  drug) {
        this.person = person;
        this.drug = drug;
    }

    public void run() {
        person.setSpeed(10);  // augmentation du délai de déplacement du joueur, c'est à dire sa vitesse
        int c = 1;

        try {
            Thread.sleep(15000);  // 15 secondes d'effet
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        c ++;

        person.setSpeed(30);  // la vitesse du joueur est remise à zéro
        drug.terminate_thread();  // fin de l'effet
    }
}
