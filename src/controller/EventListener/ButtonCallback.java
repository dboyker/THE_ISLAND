package controller.EventListener;

import controller.Main;
import controller.SerialManager;
import model.Game;
import model.Item.Collectable.Collectable;
import model.Person.Player.Inventory;
import model.Person.Player.Player;
import view.Frame;

/**
 * Created by davidboyker on 16/04/16.
 */
public interface ButtonCallback {
    void execute();


    class go_to_main_menu implements ButtonCallback {
        public void execute() {Main.frame.main_menu();}
    }

    class go_to_load_menu implements ButtonCallback {
        public void execute() {Main.frame.load_game_panel();}
    }

    class go_to_new_game_menu implements ButtonCallback {
        public void execute() {Main.frame.new_game_panel();}
    }

    class start_new_game implements ButtonCallback {
        private Game game;
        public start_new_game(Game game) {this.game = game;}
        public void execute() {game.start();}
    }

    class load_game implements ButtonCallback {
        private String name;
        public load_game(String name) {this.name = name;}
        public void execute() {
            SerialManager serial_manager = new SerialManager(Main.frame);
            Game game = serial_manager.load_game(name);
            game.getController().setFrame(Main.frame);
            game.start();
        }
    }

    class save_game implements ButtonCallback {
        private Game game;
        public save_game(Game game) {this.game = game;}
        public void execute() {
            SerialManager serial_manager = new SerialManager(Main.frame);
            serial_manager.save_game(game);
        }
    }

    class show_inventory implements ButtonCallback {
        private Game game;
        public show_inventory(Game game) {this.game = game;}
        public void execute() {
            game.pause();
            Main.frame.game_panel.inventory_panel.display();
        }
    }


    class resume_game implements ButtonCallback {
        private Game game;
        public resume_game(Game game) {this.game = game;}
        public void execute() {
            Main.frame.game_panel.inventory_panel.setVisible(false);
            Main.frame.game_panel.chest_panel.setVisible(false);
            Main.frame.game_panel.seller_panel.setVisible(false);
            game.resume();
        }
    }

    class use_item implements ButtonCallback {
        private Player player;
        private Collectable item;
        public use_item(Player player, Collectable item) {
            this.player = player;
            this.item = item;
        }
        public void execute() {
            item.use(player);
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            Main.frame.game_panel.inventory_panel.display();
        }
    }

    class throw_item implements ButtonCallback {
        private Player player;
        private Collectable item;
        public throw_item(Player player, Collectable item) {this.item = item;this.player = player;}
        public void execute() {
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            Main.frame.game_panel.inventory_panel.display();
        }
    }
}

