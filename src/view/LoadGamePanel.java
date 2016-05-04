// classe pour le menu de chargement d'une partie existante

package view;

import controller.EventListener.ButtonCallback;
import controller.EventListener.InputListener;
import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
 * Created by davidboyker on 29/03/16.
 */


public class LoadGamePanel extends JPanel {

    private JButton back_button;

    public LoadGamePanel() {
        // réglages de base
        this.setLayout(null);
        JPanel menu_panel = new JPanel();
        menu_panel.setBackground(Color.DARK_GRAY);
        menu_panel.setBounds(0, 440, 680, 40);
        back_button = new JButton("back");
        back_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        menu_panel.add(back_button);
        menu_panel.setBackground(new java.awt.Color(44, 61, 79));
        this.add(menu_panel);
        JPanel control_panel = new JPanel();
        control_panel.setBounds(0, 0, 680, 440);
        control_panel.setBackground(new java.awt.Color(52, 73, 94));
        // lectures des fichiers .SER de sauvegarde et affichages de ces derniers
        File folder = new File("src/saved_games/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 1; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                JPanel play_load = new JPanel();
                play_load.setBackground(new java.awt.Color(52, 73, 94));
                JLabel play_label = new JLabel("<html><font color='white'>"+name+"</font></html>");
                JButton play_button = new JButton("play");
                play_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.load_game(name)));
                play_load.add(play_label);
                play_load.add(play_button);
                control_panel.add(play_load);
            }
        }
        this.add(control_panel);
    }
}
