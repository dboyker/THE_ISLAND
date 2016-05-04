// Classe nécessaire au contrôle d'une partie. Permets de gérer les opérations principales: démarrer, quitter, game over,...

package controller.GameController;

import model.Game;
import model.Map.Map;
import model.Person.Player.Player;
import view.Frame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by davidboyker on 30/04/16.
 */
public class GameController implements Serializable{

    private static final long serialVersionUID = 51L;
    private Game game;
    private Frame frame;

    public GameController(Game game, Frame frame) {
        this.game = game;
        this.frame = frame;
    }

    public Frame getFrame() {return this.frame;}
    public void setFrame(Frame frame) {this.frame = frame;}

    public void start() { // démarre une nouvelle partie
        game.start();
        frame.start_new_game(game);  // affiche le panel du jeu
        game.start_threading();  // lance les thread
    }

    public void load() {  // charge une partie en mémoire
        frame.start_new_game(game);  // affiche le panel du jeu
        ArrayList<Map> maps = game.getMaps();   // reset les images
        for (int c = 0; c < maps.size(); c++) {
            Map map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                        map.getPersons()[i][j].reset_image();
                    }
                    if (map.getChunks()[i][j] != null) {
                        map.getChunks()[i][j].reset_image();
                    }
                    if (map.getItems()[i][j] != null) {
                        map.getItems()[i][j].reset_image();
                    }
                }
            }
        }
        game.start_threading();  // lance les threads
    }

    // fin de partie
    public void game_over() {
        System.out.println("game over");
        this.frame.game_panel_1.status_bar.game_over();
        if (game.getMultiplayer()) {
            this.frame.game_panel_2.status_bar.game_over();
        }
        game.pause();
    }

    // ouverture d'une chest
    public void chest(Player player) {
        System.out.println("chest");
        if (this.frame.game_panel_1.getPlayer() == player) {
            this.frame.game_panel_1.chest_panel.display();
        }
        else {
            this.frame.game_panel_2.chest_panel.display();
        }
        game.pause();
    }

    // interface seller
    public void seller(Player player) {
        System.out.println("seller");
        if (this.frame.game_panel_1.getPlayer() == player) {
            this.frame.game_panel_1.seller_panel.display();
        }
        else {
            this.frame.game_panel_2.seller_panel.display();
        }
        game.pause();
    }

}