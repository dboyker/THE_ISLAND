// Classe pour la frame principale de l'application. Contient les différentes fonctions pour afficher les différentes vues utilisateurs

package view;

import controller.GameController.GameController;
import controller.EventListener.InputListener;
import model.Game;
import view.GameDisplay.GamePanel;
import java.awt.*;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class Frame implements Serializable {
    private static JFrame frame;
    private static JPanel main_panel;
    private static JPanel home_panel;
    private static GamePanel game_panel_1;
    private static GamePanel game_panel_2;
    private static LoadGamePanel load_game_panel;
    private static NewGamePanel new_game_panel;
    private static int frame_width = 680;
    private static int frame_height = 500;

    public static GamePanel getGame_panel_1() {return game_panel_1;}
    public static GamePanel getGame_panel_2() {return game_panel_2;}

    public void run() {
        frame = new JFrame("");
        frame.getContentPane().setBackground(Color.black);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_panel = new JPanel(new BorderLayout());
        frame.getContentPane().add(main_panel);
        frame.setSize(frame_width,frame_height);
        frame.setLocation(200, 200);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
        main_menu();
    }


    public static void main_menu() {
        main_panel.removeAll();
        home_panel = new HomePanel();
        main_panel.add(home_panel);
        // change la taille de la frame principale
        frame.setBounds(200, 100, frame_width, frame_height);
        main_panel.revalidate();
    }

    public static void load_game_panel() {
        main_panel.removeAll();
        load_game_panel = new LoadGamePanel();
        main_panel.add(load_game_panel);
        main_panel.revalidate();
    }

    public static void new_game_panel() {
        main_panel.removeAll();
        Game game = new Game();
        GameController controller = new GameController(game);
        game.setController(controller);
        new_game_panel = new NewGamePanel(game);
        main_panel.add(new_game_panel);
        main_panel.revalidate();
    }

    public static void start_new_game(Game game) {
        frame.addKeyListener(new InputListener.KeyboardListener(game));
        main_panel.removeAll();
        if (game.getMultiplayer()) {
            // 2 players
            System.out.println("2 players");
            // change la taille de la frame principale
            int width = frame.getWidth();
            int height = frame.getHeight();
            frame.setBounds(100, 100, width*2, height);
            game_panel_1 = new GamePanel(game, game.getPlayer_1());
            game_panel_1.setBounds(0,0,width-1,height);
            game_panel_2 = new GamePanel(game, game.getPlayer_2());
            game_panel_2.setBounds(width+1,0,width-1,height);
            JPanel container = new JPanel(null);
            container.add(game_panel_1);
            container.add(game_panel_2);
            main_panel.add(container);
            main_panel.revalidate();
        }
        else {
            // 1 player
            System.out.println("1 player");
            game_panel_1 = new GamePanel(game, game.getPlayer_1());
            main_panel.add(game_panel_1);
            main_panel.revalidate();
        }
    }
}


