package view.GameDisplay;

import controller.ButtonCallback;
import controller.InputListener;
import model.Person.Player.Inventory;
import model.Item.*;
import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 16/04/16.
 */
public class InventoryPanel extends JPanel {

    private Game game;
    private Inventory inventory;

    public InventoryPanel(Inventory inventory, Game game) {
        this.game = game;
        this.setLayout(new GridLayout(3,2));
        this.inventory = inventory;
        // inventory basic
        this.setBackground(Color.DARK_GRAY);
    }

    public void display() {
        this.setVisible(true);
        this.removeAll();
        JButton exit_button = new JButton("resume");
        exit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game)));
        exit_button.setFocusable(false);
        Item[] items = inventory.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                Item item = items[i];
                JPanel panel = new JPanel();
                JLabel label = new JLabel(item.getName());
                JButton use_button = new JButton("use");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.use_item(inventory.getPlayer(),item)));
                JButton throw_button = new JButton("throw");
                throw_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.throw_item(inventory.getPlayer(),item)));
                panel.add(label);
                panel.add(use_button);
                panel.add(throw_button);
                this.add(panel);
            }
        }
        this.add(exit_button,BorderLayout.SOUTH);


        this.revalidate();
    }
}
