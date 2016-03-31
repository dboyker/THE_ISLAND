package controller;

import model.Player;
import view.Frame;

/**
 * Created by davidboyker on 30/03/16.
 */
public class PlayerThread implements Runnable {

    private Player player;
    private Frame frame;

    public PlayerThread(Player player, Frame frame) {
        this.player = player;
        this.frame = frame;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            player.move(frame);
        }
    }
}
