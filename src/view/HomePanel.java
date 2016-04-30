package view;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 16/04/16.
 */
public class HomePanel extends JPanel {

    private JButton new_game_button;
    private JButton load_game_button;

    public HomePanel() {
        this.setBackground(Color.DARK_GRAY);
        new_game_button = new JButton("New game");
        load_game_button = new JButton("Load game");
        new_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_new_game_menu()));
        load_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_load_menu()));
        new_game_button.setPreferredSize(new Dimension(100,100));
        load_game_button.setPreferredSize(new Dimension(100,100));
        this.add(new_game_button);
        this.add(load_game_button);
    }
}
