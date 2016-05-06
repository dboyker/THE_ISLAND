// Thread pour l'objet de classe Map

package controller.GameController;

import view.Frame;

/**
 * Created by davidboyker on 17/04/16.
 */
public class MAPThread implements Runnable {

    public MAPThread() {}

    public void run() {
        while (true) {
            // toutes les 5 ms, l'écran de jeu se rafraichit pour mettre à jour, les personnages, les objets, les chunks, ...
            try {
                Thread.sleep(5);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            // mise à jour des écrans de jeu
            Frame.getGame_panel_1().update();
            try {Frame.getGame_panel_2().update();}  // si multijoueur, deuxième écran de jeu
            catch (NullPointerException e) {}
        }
    }
}
