package view;
import controller.*;
import model.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JPanel {

    private int[][] chunks;
    //building
    private Player player;
    //NPC
    //items

    GamePanel(int[][] chunks, Player player) {
        this.chunks = chunks;
        this.player = player;
        //this.setPreferredSize(new Dimension(1040,1040));
    }

    public Player getPlayer() {return this.player;}

    public void paint(Graphics g)
    {
        //generate map
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 26; j ++) {
                int a = chunks[j][i];
                Color color = Color.black;
                if (a == 0) {color = Color.BLUE;}
                else if (a == 1) {color = Color.GREEN;}
                else if (a == 2) {color = Color.GRAY;}
                g.setColor(color);
                g.fillRect(j*40,i*40,40,40);
            }
        }
        //generate player
        int x = player.getPosition()[0];
        int y = player.getPosition()[1];
        g.setColor(Color.RED);
        g.fillRect(x+5,y+5,20,20);
    }

    public void move_window() {
    }
}

