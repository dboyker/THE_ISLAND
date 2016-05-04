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

    private GamePanel game_panel;

    MiniMap(GamePanel game_panel) {
        this.game_panel = game_panel;
        this.setBackground(Color.white);
    }

    public void paint(Graphics g)
    {
        Chunk[][] chunks = game_panel.getPlayer().getMap().getChunks();
        int pixel_size = (int) (this.getWidth()/game_panel.getPlayer().getMap().getWidth());

        super.paint(g);
        //generate map
        float x = game_panel.getPlayer().getPosition()[0];
        float y = game_panel.getPlayer().getPosition()[1];

        for (float i = 0; i < game_panel.getPlayer().getMap().getHeight(); i++) {
            for (float j = 0; j < game_panel.getPlayer().getMap().getWidth(); j ++) {
                Color color = Color.white;
                try {color = chunks[(int) j][(int) i].color;}
                catch (ArrayIndexOutOfBoundsException u) {}
                g.setColor(color);
                g.fillRect((int) ((j)*pixel_size),(int) ((i)*pixel_size), pixel_size, pixel_size);
            }
        }

        //player 1
        g.setColor(Color.RED);
        if (game_panel.getPlayer().getMap().getClass() == model.Map.BuildingMap.class) {
            g.fillOval((int) (x * pixel_size), (int) (y * pixel_size), pixel_size, pixel_size);
        }
        else {
            g.fillOval((int) ((x - 1) * pixel_size), (int) ((y - 1) * pixel_size), pixel_size * 3, pixel_size * 3);
        }
        // player 2
        if (game_panel.getGame().getMultiplayer()) {
            g.setColor(Color.BLUE);
            if (game_panel.getPlayer() != game_panel.getGame().getPlayer_2()) {
                x = game_panel.getGame().getPlayer_2().getPosition()[0];
                y = game_panel.getGame().getPlayer_2().getPosition()[1];
            }
            else {
                x = game_panel.getGame().getPlayer_1().getPosition()[0];
                y = game_panel.getGame().getPlayer_1().getPosition()[1];
            }
            if (game_panel.getPlayer().getMap().getClass() == model.Map.BuildingMap.class) {
                g.fillOval((int) (x * pixel_size), (int) (y * pixel_size), pixel_size, pixel_size);
            }
            g.fillOval((int) ((x-1)*pixel_size), (int) ((y-1)*pixel_size), pixel_size*3, pixel_size*3);
        }
    }
}
