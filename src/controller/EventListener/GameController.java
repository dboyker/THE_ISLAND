// Classe nécessaire au contrôle d'une partie. Permets de gérer les opérations principales: démarrer, quitter, game over,...

package controller.EventListener;

import model.Game;
import model.Person.Player.Player;
import view.Frame;
/**
 * Created by davidboyker on 30/04/16.
 */
public class GameController {

    private Game game;
    private Frame frame;

    public GameController(Game game, Frame frame) {
        this.game = game;
        this.frame = frame;
    }

    public Frame getFrame() {return this.frame;}
    public void setFrame(Frame frame) {this.frame = frame;}

    public void start() {
        game.start();
        frame.start_new_game(game);
        game.start_threading();
    }

    public void quit() {


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
