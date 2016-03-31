package controller;
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
        new Game(frame);
    }

    static public void load_new_game() {}

    static public void go_to_main_menu() {frame.main_menu();}

    static public void go_to_load_menu() {}

    static public void go_to_new_game_menu() {}

}

