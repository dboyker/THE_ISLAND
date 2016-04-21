package controller;
import view.Frame;

/**
 * Created by davidboyker on 17/04/16.
 */
public class MiniMapThread implements Runnable {

    private Frame frame;

    public MiniMapThread(Frame frame) {
        this.frame = frame;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            frame.game_panel.mini_map.repaint();

        }
    }
}
