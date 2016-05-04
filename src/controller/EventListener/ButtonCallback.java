// Ce fichier contient les classes nécessaires à la gestion des divers buttons présents sur l'interface utilisateur

package controller.EventListener;

import controller.Main;
import model.SerialManager;
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
            SerialManager serial_manager = new SerialManager();
            Game game = serial_manager.load_game(name);
            game.getController().setFrame(Main.frame);
            game.getController().load();
        }
    }

    class save_game implements ButtonCallback {
        private Game game;
        public save_game(Game game) {this.game = game;}
        public void execute() {
            SerialManager serial_manager = new SerialManager();
            serial_manager.save_game(game);
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
            inventory.removeItem(item);
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
            inventory.removeItem(item);
            this.game_panel.inventory_panel.display();
        }
    }

    class upgrade_weapon implements ButtonCallback {
        private String type;
        private GamePanel game_panel;

        public upgrade_weapon(GamePanel game_panel, String type) {
            this.game_panel = game_panel;
            this.type = type;
        }
        public void execute() {
            Player player = game_panel.getPlayer();
            if (player.getMoney() >= 1000) {  // le joueur a assez d'argent pour upgrader ses armes
                player.setMoney(-1000);
                if (type == "melee") {
                    player.setMelee_damage(2);
                } else if (type == "fire") {
                    player.setFire_damage(2);
                } else if (type == "shoot") {
                    player.setShoot_damage(2);
                }
                this.game_panel.status_bar.update();
            }
        }
    }
}

