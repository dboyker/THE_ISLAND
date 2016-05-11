package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import model.Game;
import controller.EventListener.InputListener;
import model.Person.Player.Player;
import view.GameDisplay.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 20/04/16.
 */
public class StatusBar extends JPanel {

    private GamePanel game_panel;
    private JButton inventory_button;
    private JLabel health_label;
    private JLabel money_label;
    private JLabel villagers_label;

    public StatusBar(GamePanel game_panel) {
        this.game_panel = game_panel;
        this.setBounds(0,0,680,40);
        this.setBackground(new java.awt.Color(44, 61, 79));
    }

    public void display() {
        this.removeAll();
        inventory_button = new JButton("inventory");
        inventory_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.show_inventory(game_panel)));
        inventory_button.setFocusable(false);
        int health = game_panel.getPlayer().getHealth();
        health_label = new JLabel("<html><font color='white'>Life: "+health+"</font></html>");
        int money = game_panel.getPlayer().getMoney();
        money_label = new JLabel("Money: "+money+"$");
        int number_of_villagers = game_panel.getGame().count_villagers();  // calcul du nombre de villageois
        villagers_label = new JLabel("Number of villagers: "+number_of_villagers+"");
        this.add(inventory_button);
        this.add(health_label);
        this.add(money_label);
        this.add(villagers_label);
        update();
    }

    public void update() {
        int health = game_panel.getPlayer().getHealth();
        int money = game_panel.getPlayer().getMoney();
        this.health_label.setText("<html><font color='white'>Life: "+health+" | </font></html>");
        this.money_label.setText("<html><font color='white'>Money: "+money+"$ | </font></html>");
        int number_of_villagers = game_panel.getGame().count_villagers();  // calcul du nombre de villageois
        this.villagers_label.setText("<html><font color='white'>Number of villagers: "+number_of_villagers+"</font></html>");
    }

    public void game_over() {
        this.removeAll();
        // pop up de game over
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame , "GAME OVER");
    }

}
