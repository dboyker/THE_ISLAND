package controller.Thread;

import model.Person.Player.Player;
import view.Frame;

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
           try {
                Thread.sleep(5);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            player.move();
            player.attack();
        }
    }
}
