package controller;
import view.*;
import view.GameDisplay.InventoryPanel;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Main {
    private static Frame frame;
    public static void main(String[] args) {
        //launch window & display main menu
        frame = new Frame();
        frame.run();
        frame.main_menu();
        new InputListener(frame); // add listener for keyboard/mouse events
        new ButtonCallback(frame); // add calback functions to buttons
    }
}

