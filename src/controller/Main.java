// Main class

package controller;
import controller.EventListener.InputListener;
import view.*;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Main {
    public static Frame frame;
    public static void main(String[] args) {
        // Lancement de l'application
        frame = new Frame();
        frame.run();
        frame.main_menu();  // affichage du menu principal
    }
}

