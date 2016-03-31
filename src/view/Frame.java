package view;
import controller.*;
import model.*;
import model.Chunk.Chunk;
import model.Person.Player;

import java.awt.*;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
    private JFrame frame;
    private JPanel main_panel;
    private JButton quit_button;
    private JButton start_button;
    private JPanel menu_panel;
    public GamePanel game_panel;
    private int frame_width = 680;
    private int frame_height = 500;

    public JFrame getFrame() {return this.frame;}
    public GamePanel getGame_panel() {return this.game_panel;}

    public void run() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_panel = new JPanel(new BorderLayout());
        menu_panel = new JPanel();
        menu_panel.setPreferredSize(new Dimension(680,40));
        main_panel.add(menu_panel,BorderLayout.SOUTH);
        frame.getContentPane().add(main_panel);
        frame.setSize(frame_width,frame_height);
        frame.setLocation(200, 200);
        frame.addKeyListener(new InputListener.KeyboardListener());
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }


    public void main_menu() {
        try {main_panel.remove(game_panel);menu_panel.remove(quit_button);}
        catch (NullPointerException e) {}
        start_button = new JButton("start");
        menu_panel.add(start_button);
        start_button.addMouseListener(new InputListener.ButtonListener("game"));
        main_panel.revalidate();
        main_panel.repaint();
    }

    public void load_game_panel() {}

    public void new_game_panel() {}

    public void start_new_game(Game game) {

        menu_panel.remove(start_button);
        quit_button = new JButton("quit");
        menu_panel.add(quit_button);
        quit_button.addMouseListener(new InputListener.ButtonListener("menu"));
        main_panel.revalidate();
        main_panel.repaint();
        game_panel = new GamePanel(game);
        main_panel.add(game_panel);
        main_panel.revalidate();
        main_panel.repaint();
    }
}


