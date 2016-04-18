package view;

import controller.ButtonCallback;
import controller.InputListener;
import controller.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
/**
 * Created by davidboyker on 29/03/16.
 */

//to be completed

public class LoadGamePanel extends JPanel {

    private JButton back_button;

    public LoadGamePanel() {

        this.setBackground(Color.CYAN);
        back_button = new JButton("back");
        back_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        this.add(back_button);

        //read all files name & display them
        File folder = new File("src/saved_games/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                JPanel play_load = new JPanel();
                JLabel play_label = new JLabel(name);
                JButton play_button = new JButton("play");
                play_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.load_game(name)));
                play_load.add(play_label);
                play_load.add(play_button);
                this.add(play_load);

            }
        }
    }
}
