// Thread pour l'objet de classe Map

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
            // toutes les 5 ms, l'écran de jeu se rafraichit pour mettre à jour, les personnages, les objets, les chunks, ...
            frame.game_panel_1.update();
            try {frame.game_panel_2.update();}
            catch (NullPointerException e) {}
        }
    }
}
