package view.GameDisplay;
import model.*;
import model.Chunk.*;
import controller.*;
import model.Person.NPC.NPC;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JLayeredPane {

    private Game game;
    public GameScreen game_screen;
    private JPanel status_bar;
    public InventoryPanel inventory_panel;
    public JPanel menu_panel;
    private JFrame mini_map_frame;
    public MiniMap mini_map;
    private JButton continue_button;
    private JButton quit_button;
    private JButton save_game_button;
    private JButton inventory_button;
    private JButton menu_button;

    public GamePanel(Game game) {
        this.game = game;
        //inventory panel
        inventory_panel = new InventoryPanel();
        inventory_panel.setBounds(100,100,480,100);
        inventory_panel.setVisible(false);

        this.add(inventory_panel, new Integer(1),0);
        //menu panel
        menu_panel = new JPanel();
        menu_panel.setBounds(100,100,480,100);
        menu_panel.setVisible(false);
        continue_button = new JButton("resume");
        save_game_button = new JButton("save");
        quit_button = new JButton("quit");
        continue_button.setFocusable(false);
        save_game_button.setFocusable(false);
        continue_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.resume_game()));
        save_game_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.save_game(game)));
        quit_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.go_to_main_menu()));
        menu_panel.add(quit_button);
        menu_panel.add(save_game_button);
        menu_panel.add(continue_button);
        this.add(menu_panel);
        //status bar
        inventory_button = new JButton("inventory");
        inventory_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.show_inventory()));
        menu_button = new JButton("menu");
        menu_button.addMouseListener(new InputListener.ButtonListener(new ButtonCallback.show_menu()));
        menu_button.setFocusable(false);
        inventory_button.setFocusable(false);
        status_bar = new JPanel();
        status_bar.setBounds(0,0,680,40);
        status_bar.setBackground(Color.DARK_GRAY);
        status_bar.add(inventory_button);
        status_bar.add(menu_button);
        this.add(status_bar);
        //game screen
        game_screen = new GameScreen(game);
        game_screen.setBounds(0,40,680,440);
        this.add(game_screen);

        //minimap
        mini_map_frame = new JFrame("mini map");
        mini_map_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mini_map = new MiniMap(game);
        mini_map_frame.getContentPane().add(mini_map);
        mini_map_frame.setSize(200,200);
        mini_map_frame.setLocation(900, 200);
        mini_map_frame.setFocusable(true);
        mini_map_frame.setVisible(true);
        mini_map_frame.setResizable(false);

        this.requestFocus();
    }

}

