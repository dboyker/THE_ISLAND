package view;

import controller.ButtonCallback;
import controller.InputListener;
import controller.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by davidboyker on 29/03/16.
 */

//to be completed


public class NewGamePanel extends JPanel {

    private JButton back_button;
    private JButton start_button;

    public NewGamePanel() {
        this.setBackground(Color.ORANGE);
        back_button = new JButton("back");
        back_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        start_button = new JButton("start");
        start_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.start_new_game()));
        this.add(start_button);
        this.add(back_button);
    }
}

