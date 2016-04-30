package controller.EventListener;

import model.Game;
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

    }

    public void quit() {


    }

    // fin de partie
    public void game_over() {
        System.out.println("game over");
        this.frame.game_panel.status_bar.game_over();
        game.pause();
    }

    // ouverture d'une chest
    public void chest() {
        System.out.println("chest");
        this.frame.game_panel.chest_panel.display();
        game.pause();
    }

    // interface seller
    public void seller() {
        System.out.println("seller");
        this.frame.game_panel.seller_panel.display();
        game.pause();
    }

}
