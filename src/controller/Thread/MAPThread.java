package controller.Thread;

import view.Frame;

/**
 * Created by davidboyker on 17/04/16.
 */
public class MAPThread implements Runnable {

    private Frame frame;

    public MAPThread(Frame frame) {this.frame = frame;}

    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            frame.game_panel.update();
        }
    }
}
