// Thread pour les objets de classe MiniMap

package controller.GameController;
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
            // rafraîchissement de la(les) frame(s) mini map toutes les 10 ms
            frame.game_panel_1.mini_map.repaint();
            try {frame.game_panel_2.mini_map.repaint();}
            catch (NullPointerException e) {}

        }
    }
}
