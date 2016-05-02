package view.GameDisplay;
import model.*;
import model.Person.Player.Player;
import model.Person.Player.Inventory;
import view.GameDisplay.InGamePanel.*;

import javax.swing.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JLayeredPane {

    private Game game;
    private Player player;
    public GameScreen game_screen;
    public StatusBar status_bar;
    public InventoryPanel inventory_panel;
    public SellerPanel seller_panel;
    public ChestPanel chest_panel;
    public MenuPanel menu_panel;
    public JFrame mini_map_frame;
    public MiniMap mini_map;

    public GamePanel(Game game, Player player) {
        this.game = game;
        this.player = player;
        //inventory panel
        Inventory inventory = player.getInventory();
        inventory_panel = new InventoryPanel(inventory, game, this);
        inventory_panel.setBounds(100,100,480,100);
        inventory_panel.setVisible(false);
        this.add(inventory_panel, new Integer(1),0);
        //menu panel
        menu_panel = new MenuPanel(game);
        this.add(menu_panel);
        //status bar
        status_bar = new StatusBar(game, this);
        this.add(status_bar);
        status_bar.display();
        //game screen
        game_screen = new GameScreen(game, this);
        game_screen.setBounds(0,40,680,440);
        this.add(game_screen);
        // seller panel
        seller_panel = new SellerPanel(game, this);
        seller_panel.setBounds(100,100,480,300);
        seller_panel.setVisible(false);
        this.add(seller_panel, new Integer(1),0);
        // chest panel
        chest_panel = new ChestPanel(game, this);
        chest_panel.setBounds(100,100,480,300);
        chest_panel.setVisible(false);
        this.add(chest_panel, new Integer(1),0);
        //minimap
        mini_map_frame = new JFrame("mini map");
        mini_map_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mini_map = new MiniMap(game, player);
        mini_map_frame.getContentPane().add(mini_map);
        mini_map_frame.setSize(200,200);
        mini_map_frame.setLocation(200, 700);
        mini_map_frame.setFocusable(true);
        mini_map_frame.setVisible(true);
        mini_map_frame.setResizable(false);
        this.requestFocus();
    }

    public Player getPlayer() {return this.player;}

    public void update() {
        this.repaint();
        this.status_bar.update();
    }

}

