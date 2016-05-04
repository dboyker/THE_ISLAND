// Thread pour les joueurs

package model.Person.Player;

/**
 * Created by davidboyker on 30/03/16.
 */
public class PlayerThread implements Runnable {

    private Player player;

    public PlayerThread(Player player) {
        this.player = player;
    }

    public void run() {
        while (true) {
            // délai de 5ms entre chaque éxécution
            try {
                Thread.sleep(5);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            player.move();  // déplacement
            player.attack();  // attaque
        }
    }
}
