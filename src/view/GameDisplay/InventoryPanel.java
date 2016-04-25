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
    private GridBagConstraints c;

    public InventoryPanel(Inventory inventory, Game game) {
        this.game = game;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
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
        int i;
        for (i = 0; i < items.length; i++) {
            if (items[i] != null) {
                Item item = items[i];
                JPanel panel = new JPanel();
                JLabel label = new JLabel(item.getName());
                JButton use_button = new JButton("use");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.use_item(inventory.getPlayer(),item)));
                JButton throw_button = new JButton("throw");
                throw_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.throw_item(inventory.getPlayer(),item)));
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = i;
                this.add(label,c);
                c.gridx = 1;
                c.gridy = i;
                this.add(use_button,c);
                c.gridx = 2;
                c.gridy = i;
                this.add(throw_button,c);
            }
        }
        c.gridx = 0;
        c.gridy = i + 1;
        c.gridwidth = 3;
        this.add(exit_button,c);


        this.revalidate();
    }
}
