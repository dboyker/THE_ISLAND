package view.GameDisplay.InGamePanel;

import javax.swing.*;
import java.awt.*;
import model.Game;
import controller.EventListener.InputListener;
import controller.EventListener.ButtonCallback;
import view.GameDisplay.GamePanel;

/**
 * Created by davidboyker on 30/04/16.
 */
public class SellerPanel extends JPanel implements InGamePanel {

    private Game game;
    private GamePanel game_panel;

    public SellerPanel(Game game, GamePanel game_panel) {
        this.game = game;
        this.game_panel = game_panel;
        this.setBackground(new java.awt.Color(44, 61, 79));;
    }

    public void display() {
        System.out.println("display seller ui");
        this.removeAll();
        this.setVisible(true);
        JButton button1 = new JButton();
        button1.setText("Upgrade melee attack: 1000$");
        button1.setFocusable(false);
        JButton button2 = new JButton();
        button2.setText("Upgrade fire attack: 1000$");
        button2.setFocusable(false);
        JButton button3 = new JButton();
        button3.setText("Upgrade shoot attack: 1000$");
        button3.setFocusable(false);
        JButton resume_button = new JButton();
        resume_button.setText("resume");
        resume_button.setFocusable(false);
        resume_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game, game_panel)));
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(resume_button);
        this.revalidate();
    }

    public void update() {}
}
