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
        this.setLayout(null);
        JPanel logo_panel = new JPanel();
        logo_panel.setBounds(0,0,680,440);
        JLabel label = new JLabel("<html><font color='white'>THE ISLAND</font></html>");
        label.setFont(new Font("Helvetica", Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(680,440));
        logo_panel.setBounds(0,0,680,440);
        logo_panel.add(label);
        logo_panel.setBackground(new java.awt.Color(52, 73, 94));
        JPanel menu_panel = new JPanel();
        menu_panel.setBackground(new java.awt.Color(44, 61, 79));
        menu_panel.setBounds(0, 440, 680, 40);
        new_game_button = new JButton("New game");
        load_game_button = new JButton("Load game");
        new_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_new_game_menu()));
        load_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_load_menu()));
        menu_panel.add(new_game_button);
        menu_panel.add(load_game_button);
        this.add(logo_panel);
        this.add(menu_panel);
    }
}
