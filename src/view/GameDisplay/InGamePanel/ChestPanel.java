package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import model.Item.Collectable.Collectable;
import view.GameDisplay.GamePanel;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by davidboyker on 30/04/16.
 */
public class ChestPanel extends JPanel implements InGamePanel{

    private GamePanel game_panel;

    public ChestPanel(GamePanel game_panel) {
        this.game_panel = game_panel;
        this.setBackground(new java.awt.Color(44, 61, 79));
    }

    public void display() {
        System.out.println("display chest ui");
        this.removeAll();
        this.setVisible(true);
        JButton resume_button = new JButton();
        resume_button.setText("resume");
        resume_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game_panel)));
        resume_button.setFocusable(false);
        this.add(resume_button);
        // affiche les objets de l'inventaire
        ArrayList<Collectable> items = game_panel.getPlayer().getInventory().getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                Collectable item = items.get(i);
                JLabel label = new JLabel("<html><font color='white'>"+item.getName()+"</font></html>");
                JButton use_button = new JButton("store");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.store_item(item, game_panel)));
                this.add(label);
                this.add(use_button);
            }
        }
        // affiche les objets présents dans le chest
        items = game_panel.getPlayer().getChest().getItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                Collectable item = items.get(i);
                JLabel label = new JLabel(item.getName());
                JButton use_button = new JButton("get");
                use_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.get_item(item, game_panel)));
                this.add(label);
                this.add(use_button);
            }
        }
        this.revalidate();
    }
    public void update() {
        this.setVisible(true);
        this.display();
    }
}
