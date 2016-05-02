package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;

import javax.swing.*;
import java.awt.*;
import model.Game;
import view.GameDisplay.InGamePanel.InGamePanel;

/**
 * Created by davidboyker on 16/04/16.
 */
public class MenuPanel extends JPanel implements InGamePanel {
    private JButton quit_button;
    private JButton save_game_button;

    public MenuPanel(Game game) {
        this.setBounds(0, 440, 680, 40);
        this.setBackground(new java.awt.Color(44, 61, 79));;
        save_game_button = new JButton("save");
        quit_button = new JButton("quit");
        save_game_button.setFocusable(false);
        save_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.save_game(game)));
        quit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        this.add(save_game_button);
        this.add(quit_button);
    }

    public void update() {}
    public void display() {}
}
