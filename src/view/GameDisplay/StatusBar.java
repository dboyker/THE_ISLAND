package view.GameDisplay;

import controller.ButtonCallback;
import model.Game;
import controller.InputListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 20/04/16.
 */
public class StatusBar extends JPanel {

    private Game game;
    private JButton inventory_button;
    private JButton menu_button;
    private JLabel health_label;
    private JLabel money_label;
    private JLabel villagers_label;

    public StatusBar(Game game) {
        this.game = game;
        this.setBounds(0,0,680,40);
    }

    public void display() {
        this.removeAll();
        this.setBackground(Color.DARK_GRAY);
        inventory_button = new JButton("inventory");
        inventory_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.show_inventory()));
        menu_button = new JButton("menu");
        menu_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.show_menu(game)));
        menu_button.setFocusable(false);
        inventory_button.setFocusable(false);
        int health = game.getPlayer().getHealth();
        health_label = new JLabel("<3 "+health+"");
        int money = game.getPlayer().getMoney();
        money_label = new JLabel("$ "+money+"");
        int number_of_villagers = game.count_villagers();  // calcul du nombre de villageois
        villagers_label = new JLabel("number of villager: "+number_of_villagers+"");
        this.add(inventory_button);
        this.add(menu_button);
        this.add(health_label);
        this.add(money_label);
        this.add(villagers_label);
    }

    public void update() {
        int health = game.getPlayer().getHealth();
        int money = game.getPlayer().getMoney();
        this.health_label.setText("<3 "+health+"");
        this.money_label.setText("$ "+money);
        int number_of_villagers = game.count_villagers();  // calcul du nombre de villageois
        this.villagers_label.setText("number of villager: "+number_of_villagers+"");
    }

}
