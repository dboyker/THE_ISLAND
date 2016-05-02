package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import model.Item.Collectable.Collectable;
import model.Person.Player.Inventory;
import model.*;
import view.GameDisplay.GamePanel;
import view.GameDisplay.InGamePanel.InGamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 16/04/16.
 */
public class InventoryPanel extends JPanel implements InGamePanel {

    private Game game;
    private GamePanel game_panel;
    private Inventory inventory;
    private GridBagConstraints c;

    public InventoryPanel(Inventory inventory, Game game, GamePanel game_panel) {
        this.game = game;
        this.game_panel = game_panel;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        this.inventory = inventory;
        // inventory basic
        this.setBackground(new java.awt.Color(44, 61, 79));;
    }

    public void display() {
        this.setVisible(true);
        this.removeAll();
        JButton exit_button = new JButton("resume");
        exit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game, game_panel)));
        exit_button.setFocusable(false);
        Collectable[] items = inventory.getItems();
        int i;
        for (i = 0; i < items.length; i++) {
            if (items[i] != null) {
                Collectable item = items[i];
                JLabel label = new JLabel(item.getName());
                JButton use_button = new JButton("use");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.use_item(inventory.getPlayer(), item, game_panel)));
                JButton throw_button = new JButton("throw");
                throw_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.throw_item(inventory.getPlayer(), item, game_panel)));
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

    public void update() {}
}
