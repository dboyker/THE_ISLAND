package view.GameDisplay;

import javax.swing.*;
import java.awt.*;

import model.Chunk.Chunk;
import model.Game;
import model.Map;
import model.Person.Player.Player;

/**
 * Created by davidboyker on 17/04/16.
 */
public class MiniMap extends JPanel {

    private Game game;
    private Map map;

    MiniMap(Game game) {
        this.game = game;
        this.map = this.game.getPlayer().getMap();
        this.setBackground(Color.white);
    }

    public void setMap(Map map) {this.map = map;}

    public void paint(Graphics g)
    {
        Player player = game.getPlayer();
        Chunk[][] chunks = map.getChunks();
        //this.getWidth();
        //map.getWidth();
        int pixel_size = (int) (this.getWidth()/map.getWidth());

        super.paint(g);
        //generate map
        float x = player.getPosition()[0];
        float y = player.getPosition()[1];

        for (float i = 0; i < map.getHeight(); i++) {
            for (float j = 0; j < map.getWidth(); j ++) {
                Color color = Color.white;
                try {color = chunks[(int) j][(int) i].color;}
                catch (ArrayIndexOutOfBoundsException u) {}
                g.setColor(color);
                g.fillRect((int) ((j)*pixel_size),(int) ((i)*pixel_size), pixel_size, pixel_size);
            }
        }

        //player
        g.setColor(Color.RED);
        g.fillOval((int) ((x-1)*pixel_size), (int) ((y-1)*pixel_size), pixel_size*3, pixel_size*3);

        //NPC
        /*for (int i = 0; i < NPC.length; i++) {
            model.Person.NPC.NPC N = NPC[i];
            x = N.getPosition()[0];
            y = N.getPosition()[1];
            g.setColor(Color.CYAN);
            g.fillOval((int) ((x)*pixel_size), (int) ((y)*pixel_size), pixel_size, pixel_size);
        }*/
    }
}
