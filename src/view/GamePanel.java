package view;
import model.*;
import model.Person.*;
import model.Chunk.*;
import controller.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 29/03/16.
 */

public class GamePanel extends JPanel {

    private Map map;
    private Chunk[][] chunks;
    //building
    private Player player;
    //NPC
    private NPC[] NPC;
    //items

    GamePanel(Game game) {
        this.map = game.getMap();
        this.chunks = game.getChunk();
        this.player = game.getPlayer();
        this.NPC = game.getNPC();
    }


    public Player getPlayer() {return this.player;}

    public void paint(Graphics g)
    {
        //generate map
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j ++) {
                int a = chunks[j][i].color;
                Color color = Color.black;
                if (a == 0) {color = new Color(41,62,81);}
                else if (a == 1) {color = new Color(0, 255, 114);}
                else if (a == 2) {color = Color.GRAY;}
                else if (a == 3) {color = Color.lightGray;}
                g.setColor(color);
                int chunk_size = map.getChunk_size();
                g.fillRect(j*chunk_size,i*chunk_size ,chunk_size, chunk_size);
            }
        }

        //player
        int x = player.getPosition()[0];
        int y = player.getPosition()[1];
        g.setColor(Color.RED);
        g.fillRect(x*map.getChunk_size(), y*map.getChunk_size(), map.getChunk_size(),map.getChunk_size());

        //NPC
        for (int i = 0; i < NPC.length; i++) {
            NPC N = NPC[i];
            x = N.getPosition()[0];
            y = N.getPosition()[1];
            g.setColor(Color.CYAN);
            g.fillRect(x*map.getChunk_size(), y*map.getChunk_size(), map.getChunk_size(), map.getChunk_size());
        }

    }

    public void move_player(float dx, float dy) {
        //double x = this.getX();
        //double y = this.getY();
        //this.setLocation((int) (x-dx), (int) (y-dy));
        this.repaint();
    }
}

