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
        this.setBackground(Color.DARK_GRAY);
        back_button = new JButton("back");
        back_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));

        //réglage taille: 100x100 ou 50x50
        JRadioButton size_button_1 = new JRadioButton("Normal: 100x100");
        size_button_1.setActionCommand("100");
        size_button_1.setSelected(true);
        JRadioButton size_button_2 = new JRadioButton("Small: 81x81");
        size_button_2.setActionCommand("81");
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

        //réglage difficulté: normal ou hard
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

        //réglage nombre de joueurs: 1 joueur ou 2 en coopération
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
            }
        }
        players_button_1.addActionListener(new PlayersListener());
        players_button_2.addActionListener(new PlayersListener());
        // start button
        start_button = new JButton("start");
        start_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.start_new_game(this.game)));
        // ajout des éléments au JPanel
        this.add(back_button);
        this.add(new JLabel("size: "));
        this.add(size_button_1);
        this.add(size_button_2);
        this.add(new JLabel("difficulty: "));
        this.add(difficulty_button_1);
        this.add(difficulty_button_2);
        this.add(start_button);
    }
}

