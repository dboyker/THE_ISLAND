// thread pour les objets de classe Bullet

package model.Item.Hazardous;

/**
 * Created by davidboyker on 28/04/16.
 */
public class BulletThread implements Runnable {

    private Bullet bullet;

    public BulletThread(Bullet bullet) {this.bullet = bullet;}

    public void run() {
        try {
            Thread.sleep(200); // délai avant le premier déplacement
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            // déplace le bullet
            bullet.move();
        }
    }
}
