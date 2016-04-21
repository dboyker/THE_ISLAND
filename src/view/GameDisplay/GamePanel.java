package view.GameDisplay;
import model.*;
import controller.*;
import model.Person.Player.Inventory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JLayeredPane {

    private Game game;
    public GameScreen game_screen;
    public StatusBar status_bar;
    public InventoryPanel inventory_panel;
    public MenuPanel menu_panel;
    private JFrame mini_map_frame;
    public MiniMap mini_map;
    private JButton continue_button;
    private JButton quit_button;
    private JButton save_game_button;

    public GamePanel(Game game) {
        this.game = game;
        //inventory panel
        Inventory inventory = game.getPlayer().getInventory();
        inventory_panel = new InventoryPanel(inventory, game);
        inventory_panel.setBounds(100,100,480,100);
        inventory_panel.setVisible(false);
        this.add(inventory_panel, new Integer(1),0);
        //menu panel
        menu_panel = new MenuPanel(game);
        this.add(menu_panel);
        //status bar
        status_bar = new StatusBar(game);
        this.add(status_bar);
        status_bar.display();
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

    public void update() {
        this.repaint();
        this.status_bar.update();
    }

}

