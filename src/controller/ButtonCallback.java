package controller;

import model.Game;
import model.Item.Item;
import model.Person.Player.Inventory;
import model.Person.Player.Player;
import view.Frame;

/**
 * Created by davidboyker on 16/04/16.
 */
public class ButtonCallback {

    private static Frame frame;

    ButtonCallback() {}

    ButtonCallback(Frame frame) {this.frame = frame;}
    public void execute() {}

    public static class go_to_main_menu extends ButtonCallback {
        public void execute() {frame.main_menu();}
    }

    public static class go_to_load_menu extends ButtonCallback {
        public void execute() {frame.load_game_panel();}
    }

    public static class go_to_new_game_menu extends ButtonCallback {
        public void execute() {frame.new_game_panel();}
    }

    public static class start_new_game extends ButtonCallback {
        public void execute() {new Game(frame);}
    }

    public static class load_game extends ButtonCallback {
        private String name;
        public load_game(String name) {
            this.name = name;
        }
        public void execute() {
            SerialManager serial_manager = new SerialManager(frame);
            Game game = serial_manager.load_game(name);
            game.setFrame(frame);
            game.start();
        }
    }

    public static class save_game extends ButtonCallback {
        private Game game;
        public save_game(Game game) {
            this.game = game;
        }
        public void execute() {
            SerialManager serial_manager = new SerialManager(frame);
            serial_manager.save_game(game);
        }
    }

    public static class show_inventory extends ButtonCallback {
        public void execute() {
            frame.game_panel.inventory_panel.display();
        }
    }

    public static class hide_inventory extends ButtonCallback {
        public void execute() {
            frame.game_panel.inventory_panel.setVisible(false);
        }
    }

    public static class show_menu extends ButtonCallback {
        private Game game;
        public show_menu(Game game) {
            this.game = game;
        }
        public void execute() {
            game.pause();
            frame.game_panel.menu_panel.setVisible(true);

        }
    }

    public static class resume_game extends ButtonCallback {
        private Game game;
        public resume_game(Game game) {this.game = game;}
        public void execute() {
            frame.game_panel.menu_panel.setVisible(false);
            frame.game_panel.inventory_panel.setVisible(false);
            game.resume();
        }
    }

    public static class use_item extends ButtonCallback {
        private Player player;
        private Item item;
        public use_item(Player player, Item item) {
            this.player = player;
            this.item = item;
        }
        public void execute() {
            item.use(player);
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            frame.game_panel.inventory_panel.display();
        }
    }

    public static class throw_item extends ButtonCallback {
        private Player player;
        private Item item;
        public throw_item(Player player, Item item) {this.item = item;this.player = player;}
        public void execute() {
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            frame.game_panel.inventory_panel.display();
        }
    }
}
