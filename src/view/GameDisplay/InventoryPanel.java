package view.GameDisplay;

import controller.ButtonCallback;
import controller.InputListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 16/04/16.
 */
public class InventoryPanel extends JPanel {
    public InventoryPanel() {
        this.setBackground(Color.BLACK);
        JButton exit_button = new JButton("exit");
        exit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game()));
        exit_button.setFocusable(false);
        this.add(exit_button);
    }
}
