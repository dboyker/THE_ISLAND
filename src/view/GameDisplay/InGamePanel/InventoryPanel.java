/**
 * Created by davidboyker on 16/04/16.
 */

package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import model.Item.Collectable.Collectable;
import view.GameDisplay.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel implements InGamePanel {

    private GamePanel game_panel;
    private GridBagConstraints c;

    public InventoryPanel(GamePanel game_panel) {
        this.game_panel = game_panel;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        // inventory basic
        this.setBackground(new java.awt.Color(44, 61, 79));;
    }

    public void display() {
        this.setVisible(true);
        this.removeAll();
        JButton exit_button = new JButton("resume");
        exit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game_panel)));
        exit_button.setFocusable(false);
        ArrayList<Collectable> items = game_panel.getPlayer().getInventory().getItems();
        int i;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                Collectable item = items.get(i);
                JLabel label = new JLabel(item.getName());
                JButton use_button = new JButton("use");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.use_item(item, game_panel)));
                JButton throw_button = new JButton("throw");
                throw_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.throw_item(item, game_panel)));
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

    public void update() {
        this.setVisible(false);
        this.display();
    }
}
