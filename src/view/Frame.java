package view;
import controller.*;
import model.Game;
import view.GameDisplay.GamePanel;

import java.awt.*;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame implements Serializable {
    private JFrame frame;
    private JPanel main_panel;

    private JPanel home_panel;
    public GamePanel game_panel;
    public LoadGamePanel load_game_panel;
    public NewGamePanel new_game_panel;

    private int frame_width = 680;
    private int frame_height = 500;

    public JFrame getFrame() {return this.frame;}

    public void run() {
        frame = new JFrame("The Island");
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


    public void main_menu() {
        main_panel.removeAll();
        home_panel = new HomePanel();
        main_panel.add(home_panel);
        main_panel.revalidate();
    }

    public void load_game_panel() {
        main_panel.removeAll();
        load_game_panel = new LoadGamePanel();
        main_panel.add(load_game_panel);
        main_panel.revalidate();
    }

    public void new_game_panel() {
        main_panel.removeAll();
        Game game = new Game(this);
        new_game_panel = new NewGamePanel(game);
        main_panel.add(new_game_panel);
        main_panel.revalidate();
    }

    public void start_new_game(Game game) {
        frame.addKeyListener(new InputListener.KeyboardListener(game));
        main_panel.removeAll();
        game_panel = new GamePanel(game);
        main_panel.add(game_panel);
        main_panel.revalidate();
    }
}


