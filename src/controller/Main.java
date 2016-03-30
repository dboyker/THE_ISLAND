package controller;
import model.*;
import view.*;
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
    }

    static public void start_new_game() {
        (new Thread(new Game(frame))).start();
    }

    static public void go_to_main_menu() {frame.main_menu();}

}

