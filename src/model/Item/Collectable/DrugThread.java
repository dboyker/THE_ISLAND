package model.Item.Collectable;

import model.Item.Collectable.Drug;
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
        person.setSpeed(10);
        int c = 1;
        while (c <= 10) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            c ++;
        }
        person.setSpeed(30);
        drug.terminate_thread();
    }
}
