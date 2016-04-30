package controller;
import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import view.*;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Main {
    public static Frame frame;
    public static void main(String[] args) {
        //launch window & display main menu
        frame = new Frame();
        frame.run();
        frame.main_menu();
        new InputListener(frame); // add listener for keyboard/mouse events
    }
}

