package view;
import controller.*;
import model.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JPanel {

    private Map map;
    private int[][] chunks;
    //building
    private Player player;
    //NPC
    //items

    GamePanel(int[][] chunks, Player player, Map map) {
        this.map = map;
        this.chunks = chunks;
        this.player = player;
    }

    public Player getPlayer() {return this.player;}

    public void paint(Graphics g)
    {
        //generate map and put it around the player
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j ++) {
                int a = chunks[j][i];
                Color color = Color.black;
                if (a == 0) {color = Color.BLUE;}
                else if (a == 1) {color = Color.GREEN;}
                else if (a == 2) {color = Color.GRAY;}
                g.setColor(color);
                int chunk_size = map.getChunk_size();
                g.fillRect(j*chunk_size,i*chunk_size ,chunk_size, chunk_size);
            }
        }

        //generate player
        int x = player.getPosition()[0];
        int y = player.getPosition()[1];
        g.setColor(Color.RED);
        g.fillRect(x+map.getChunk_size()/4,y+map.getChunk_size()/4, map.getChunk_size()/2,map.getChunk_size()/2);

    }

    public void move_player(float dx, float dy) {
        double x = this.getX();
        double y = this.getY();
        this.setLocation((int) (x-dx), (int) (y-dy));
        this.repaint();
    }
}

