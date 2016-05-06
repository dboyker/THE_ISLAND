// classe pour le menu de création d'une nouvelle partie

package view;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import model.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by davidboyker on 29/03/16.
 */

public class NewGamePanel extends JPanel {
    private JButton back_button;
    private JButton start_button;
    private Game game;

    public NewGamePanel(Game game) {
        this.game = game;
        this.setLayout(null);
        back_button = new JButton("back");
        back_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        // bouttons de réglage taille de la carte
        JRadioButton size_button_1 = new JRadioButton("Normal: 100x100");
        size_button_1.setActionCommand("100");
        size_button_1.setSelected(true);
        JRadioButton size_button_2 = new JRadioButton("Small: 50x50");
        size_button_2.setActionCommand("50");
        ButtonGroup button_size_group = new ButtonGroup();
        button_size_group.add(size_button_1);
        button_size_group.add(size_button_2);
        class SizeListener implements ActionListener {
            public void actionPerformed(ActionEvent ex) {
                String choice = button_size_group.getSelection().getActionCommand();
                game.setMap_size_x(Integer.parseInt(choice));
                game.setMap_size_y(Integer.parseInt(choice));
            }
        }
        size_button_1.addActionListener(new SizeListener());
        size_button_2.addActionListener(new SizeListener());

        // bouttons de réglage difficulté: normal ou hard
        JRadioButton difficulty_button_1 = new JRadioButton("Normal");
        difficulty_button_1.setActionCommand("normal");
        difficulty_button_1.setSelected(true);
        JRadioButton difficulty_button_2 = new JRadioButton("Hard");
        difficulty_button_2.setActionCommand("hard");
        ButtonGroup button_difficulty_group = new ButtonGroup();
        button_difficulty_group.add(difficulty_button_1);
        button_difficulty_group.add(difficulty_button_2);
        class DifficultyListener implements ActionListener {
            public void actionPerformed(ActionEvent ex) {
                String choice = button_difficulty_group.getSelection().getActionCommand();
                game.setDifficulty(choice);
            }
        }
        difficulty_button_1.addActionListener(new DifficultyListener());
        difficulty_button_2.addActionListener(new DifficultyListener());

        // bouttons de réglage du nombre de joueurs: 1 joueur ou 2 en coopération
        JRadioButton players_button_1 = new JRadioButton("Single player");
        players_button_1.setActionCommand("1");
        players_button_1.setSelected(true);
        JRadioButton players_button_2 = new JRadioButton("2 players in coop");
        players_button_2.setActionCommand("2");
        ButtonGroup players_button_group = new ButtonGroup();
        players_button_group.add(players_button_1);
        players_button_group.add(players_button_2);
        class PlayersListener implements ActionListener {
            public void actionPerformed(ActionEvent ex) {
                String choice = players_button_group.getSelection().getActionCommand();
                Boolean multiplayer;
                if (choice == "1") {
                    multiplayer = false;
                    System.out.println("1 player");
                }
                else {
                    multiplayer = true;
                    System.out.println("2 players");
                }
                game.setMultiplayer(multiplayer);
            }
        }
        players_button_1.addActionListener(new PlayersListener());
        players_button_2.addActionListener(new PlayersListener());
        start_button = new JButton("start");
        start_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.start_new_game(this.game)));
        // ajout des différents éléments au JPanel
        JPanel control_panel = new JPanel(null);
        control_panel.setBounds(0, 0, 680, 440);
        JPanel panel1 = new JPanel();
        panel1.setBounds(0, 40, 680, 40);
        panel1.add(new JLabel("size: "));
        panel1.add(size_button_1);
        panel1.add(size_button_2);
        panel1.setBackground(new java.awt.Color(52, 73, 94));
        control_panel.add(panel1);
        JPanel panel2 = new JPanel();
        panel2.setBounds(0, 80, 680, 40);
        panel2.add(new JLabel("difficulty: "));
        panel2.add(difficulty_button_1);
        panel2.add(difficulty_button_2);
        panel2.setBackground(new java.awt.Color(52, 73, 94));
        control_panel.add(panel2);
        JPanel panel3 = new JPanel();
        panel3.setBounds(0, 120, 680, 40);
        panel3.add(new JLabel("type of game: "));
        panel3.add(players_button_1);
        panel3.add(players_button_2);
        panel3.setBackground(new java.awt.Color(52, 73, 94));
        control_panel.add(panel3);
        control_panel.setBackground(new java.awt.Color(52, 73, 94));
        JPanel menu_panel = new JPanel();
        menu_panel.setBackground(new java.awt.Color(44, 61, 79));
        menu_panel.setBounds(0, 440, 680, 40);
        menu_panel.add(start_button);
        menu_panel.add(back_button);
        this.add(control_panel);
        this.add(menu_panel);
    }
}

