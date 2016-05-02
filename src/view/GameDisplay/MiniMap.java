package view.GameDisplay;

import javax.swing.*;
import java.awt.*;

import model.Chunk.Chunk;
import model.Game;
import model.Map.Map;
import model.Person.Player.Player;

/**
 * Created by davidboyker on 17/04/16.
 */
public class MiniMap extends JPanel {

    private Game game;
    private Map map;
    private Player player;

    MiniMap(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.map = player.getMap();
        this.setBackground(Color.white);
    }

    public void setMap(Map map) {this.map = map;}

    public void paint(Graphics g)
    {
        Chunk[][] chunks = map.getChunks();
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

        //player 1
        g.setColor(Color.RED);
        if (map.getClass() == model.Map.BuildingMap.class) {
            g.fillOval((int) (x * pixel_size), (int) (y * pixel_size), pixel_size, pixel_size);
        }
        else {
            g.fillOval((int) ((x - 1) * pixel_size), (int) ((y - 1) * pixel_size), pixel_size * 3, pixel_size * 3);
        }
        // player 2
        if (game.getMultiplayer()) {
            g.setColor(Color.BLUE);
            if (player != game.getPlayer_2()) {
                x = game.getPlayer_2().getPosition()[0];
                y = game.getPlayer_2().getPosition()[1];
            }
            else {
                x = game.getPlayer_1().getPosition()[0];
                y = game.getPlayer_1().getPosition()[1];
            }
            if (map.getClass() == model.Map.BuildingMap.class) {
                g.fillOval((int) (x * pixel_size), (int) (y * pixel_size), pixel_size, pixel_size);
            }
            g.fillOval((int) ((x-1)*pixel_size), (int) ((y-1)*pixel_size), pixel_size*3, pixel_size*3);
        }
    }
}
