package view.GameDisplay.InGamePanel;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 30/04/16.
 */
public class ChestPanel extends JPanel implements InGamePanel{

    private Game game;

    public ChestPanel(Game game) {
        this.game = game;
        this.setBackground(Color.DARK_GRAY);
    }

    public void display() {
        System.out.println("display chest ui");
        this.removeAll();
        this.setVisible(true);
        JButton resume_button = new JButton();
        resume_button.setText("resume");
        resume_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game(game)));
        resume_button.setFocusable(false);
        this.add(resume_button);
        this.revalidate();
    }
    public void update() {}
}
