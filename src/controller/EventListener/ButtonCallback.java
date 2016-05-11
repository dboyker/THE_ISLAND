// Ce fichier contient les classes nécessaires à la gestion des divers buttons présents sur l'interface utilisateur

/**
 * Created by davidboyker on 16/04/16.
 */

package controller.EventListener;

import view.Frame;
import model.SerialManager;
import model.Game;
import model.Item.Collectable.Collectable;
import model.Person.Player.Inventory;
import model.Person.Player.Player;
import view.GameDisplay.GamePanel;

public interface ButtonCallback {

    void execute();

    //---------- Callback en dehors d'une partie ----------
    class go_to_main_menu implements ButtonCallback {
        public void execute() {Frame.main_menu();}
    }

    class go_to_load_menu implements ButtonCallback {
        public void execute() {
            Frame.load_game_panel();}
    }

    class go_to_new_game_menu implements ButtonCallback {
        public void execute() {
            Frame.new_game_panel();}
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
            SerialManager serial_manager = new SerialManager();
            Game game = serial_manager.load_game(name);
            game.getController().load();
        }
    }

    //---------- Callback pendant une partie ----------
    class save_game implements ButtonCallback {
        private Game game;
        public save_game(Game game) {this.game = game;}
        public void execute() {
            SerialManager serial_manager = new SerialManager();
            try {serial_manager.save_game(game);}
            catch (Exception e) {System.out.println("Couldn't save the game. Reason: "+e.getMessage());}
        }
    }

    class quit_game implements ButtonCallback {
        private Game game;
        public quit_game(Game game) {this.game = game;}
        public void execute() {
            game.getController().quit_game();
        }
    }


    class show_inventory implements ButtonCallback {
        private GamePanel game_panel;
        public show_inventory(GamePanel game_panel) {this.game_panel = game_panel;}
        public void execute() {
            game_panel.getGame().pause();
            this.game_panel.inventory_panel.display();
        }
    }


    class resume_game implements ButtonCallback {
        private GamePanel game_panel;
        public resume_game(GamePanel game_panel) {
            this.game_panel = game_panel;
        }
        public void execute() {
            this.game_panel.inventory_panel.setVisible(false);
            this.game_panel.chest_panel.setVisible(false);
            this.game_panel.seller_panel.setVisible(false);
            game_panel.getGame().resume();
        }
    }

    class use_item implements ButtonCallback {
        private Collectable item;
        private GamePanel game_panel;

        public use_item(Collectable item, GamePanel game_panel) {
            this.item = item;
            this.game_panel = game_panel;
        }
        public void execute() {
            item.use(game_panel.getPlayer());
            Inventory inventory = game_panel.getPlayer().getInventory();
            inventory.remove_item(item);
            this.game_panel.inventory_panel.update();
        }
    }

    class throw_item implements ButtonCallback {
        private Collectable item;
        private GamePanel game_panel;

        public throw_item(Collectable item, GamePanel game_panel) {
            this.item = item;
            this.game_panel = game_panel;
        }
        public void execute() {
            Inventory inventory = game_panel.getPlayer().getInventory();
            inventory.remove_item(item);
            this.game_panel.inventory_panel.update();
        }
    }

    class get_item implements ButtonCallback {
        private Collectable item;
        private GamePanel game_panel;

        public get_item(Collectable item, GamePanel game_panel) {
            this.item = item;
            this.game_panel = game_panel;
        }
        public void execute() {
            Inventory inventory = game_panel.getPlayer().getInventory();  // inventory
            Inventory chest = game_panel.getPlayer().getChest();  // chest
            chest.remove_item(item);
            inventory.setItems(item);
            this.game_panel.chest_panel.update();
        }
    }

    class store_item implements ButtonCallback {
        private Collectable item;
        private GamePanel game_panel;

        public store_item(Collectable item, GamePanel game_panel) {
            this.item = item;
            this.game_panel = game_panel;
        }
        public void execute() {
            Inventory chest = game_panel.getPlayer().getChest();  // chest
            Inventory inventory = game_panel.getPlayer().getInventory();  // inventory
            inventory.remove_item(item);
            chest.setItems(item);
            this.game_panel.chest_panel.update();
        }
    }

    class upgrade_attack implements ButtonCallback {
        private String type;
        private GamePanel game_panel;

        public upgrade_attack(GamePanel game_panel, String type) {
            this.game_panel = game_panel;
            this.type = type;
        }
        public void execute() {
            Player player = game_panel.getPlayer();
            player.upgrade_attack(type);
            this.game_panel.status_bar.update();
        }
    }
}

