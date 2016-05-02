// Ce fichier contient les classes nécessaires à la gestion des divers buttons présents sur l'interface utilisateur

package controller.EventListener;

import controller.Main;
import controller.SerialManager;
import model.Game;
import model.Item.Collectable.Collectable;
import model.Person.Player.Inventory;
import model.Person.Player.Player;
import view.GameDisplay.GamePanel;

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
        public void execute() {game.getController().start();}
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
        private GamePanel game_panel;
        public show_inventory(Game game, GamePanel game_panel) {
            this.game = game;
            this.game_panel = game_panel;
        }
        public void execute() {
            game.pause();
            this.game_panel.inventory_panel.display();
        }
    }


    class resume_game implements ButtonCallback {
        private Game game;
        private GamePanel game_panel;
        public resume_game(Game game, GamePanel game_panel) {
            this.game = game;
            this.game_panel = game_panel;
        }
        public void execute() {
            this.game_panel.inventory_panel.setVisible(false);
            this.game_panel.chest_panel.setVisible(false);
            this.game_panel.seller_panel.setVisible(false);
            game.resume();
        }
    }

    class use_item implements ButtonCallback {
        private Player player;
        private Collectable item;
        private GamePanel game_panel;

        public use_item(Player player, Collectable item, GamePanel game_panel) {
            this.player = player;
            this.item = item;
            this.game_panel = game_panel;
        }
        public void execute() {
            item.use(player);
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            this.game_panel.inventory_panel.display();
        }
    }

    class throw_item implements ButtonCallback {
        private Player player;
        private Collectable item;
        private GamePanel game_panel;

        public throw_item(Player player, Collectable item, GamePanel game_panel) {
            this.item = item;
            this.player = player;
            this.game_panel = game_panel;
        }
        public void execute() {
            Inventory inventory = player.getInventory();
            inventory.removeItem(item);
            this.game_panel.inventory_panel.display();
        }
    }
}

