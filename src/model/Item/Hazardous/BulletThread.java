// thread pour les objets de classe Bullet

package model.Item.Hazardous;

import model.Item.Hazardous.Bullet;

/**
 * Created by davidboyker on 28/04/16.
 */
public class BulletThread implements Runnable {

    private Bullet bullet;

    public BulletThread(Bullet bullet) {this.bullet = bullet;}

    public void run() {
        try {
            Thread.sleep(200);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            // move the bullet
            bullet.move();
        }
    }
}
