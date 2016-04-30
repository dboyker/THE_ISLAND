package controller.Thread;

import model.Item.Hazardous.Bullet;

/**
 * Created by davidboyker on 28/04/16.
 */
public class BulletThread implements Runnable {

    private Bullet bullet;

    public BulletThread(Bullet bullet) {this.bullet = bullet;}

    public void run() {
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
