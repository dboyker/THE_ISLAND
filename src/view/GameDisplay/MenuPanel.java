package view.GameDisplay;

import controller.ButtonCallback;
import controller.InputListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import model.Game;

/**
 * Created by davidboyker on 16/04/16.
 */
public class MenuPanel extends JPanel {
    private JButton continue_button;
    private JButton quit_button;
    private JButton save_game_button;

    public MenuPanel(Game game) {
        this.setLayout(new GridLayout(3,1));
        this.setBounds(150, 150, 380, 100);
        this.setBackground(Color.DARK_GRAY);
        this.setVisible(false);
        continue_button = new JButton("resume");
        save_game_button = new JButton("save");
        quit_button = new JButton("quit");
        continue_button.setFocusable(false);
        save_game_button.setFocusable(false);
        continue_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game)));
        save_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.save_game(game)));
        quit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        this.add(continue_button);
        this.add(save_game_button);
        this.add(quit_button);
    }
}
