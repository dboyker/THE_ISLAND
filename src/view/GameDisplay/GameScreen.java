package view.GameDisplay;

import controller.Game;
import controller.InputListener;
import controller.Main;
import model.Chunk.Chunk;
import model.Map;
import model.Person.NPC.NPC;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by davidboyker on 16/04/16.
 */
public class GameScreen extends JPanel {

    private Game game;
    private Map map;
    private Chunk[][] chunks;
    //building
    private Player player;
    //NPC
    private model.Person.NPC.NPC[] NPC;
    //items

    public GameScreen(Game game) {
        this.game = game;
        this.map = game.getMap();
        this.chunks = map.getChunks();
        this.player = game.getPlayer();
        this.NPC = game.getNPC();
    }

    public Map getMap() {return  this.map; }

    public void setMap(Map map) {
        this.map = map;
        this.chunks = map.getChunks();
        this.repaint();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        //generate map
        float x = player.getPosition()[0];
        float y = player.getPosition()[1];
        int middlex =  this.getWidth()/(2*map.getChunk_size());
        int middley = this.getHeight()/(2*map.getChunk_size());

        float offsetx = x - middlex;
        float offsety = y - middley;

        for (float i = offsety; i < map.getHeight(); i++) {
            for (float j = offsetx; j < map.getWidth(); j ++) {
                Color color = Color.white;
                try {color = chunks[(int) j][(int) i].color;}
                catch (ArrayIndexOutOfBoundsException u) {}
                g.setColor(color);
                int chunk_size = map.getChunk_size();
                g.fillRect((int) ((j-offsetx)*chunk_size),(int) ((i-offsety)*chunk_size), chunk_size, chunk_size);
            }
        }

        //player
        g.setColor(player.getColor());
        g.fillOval((int) ((x-offsetx)*map.getChunk_size()), (int) ((y-offsety)*map.getChunk_size()), map.getChunk_size(),map.getChunk_size());

        //NPC
        for (int i = 0; i < NPC.length; i++) {
            model.Person.NPC.NPC N = NPC[i];
            x = N.getPosition()[0];
            y = N.getPosition()[1];
            g.setColor(Color.CYAN);
            g.fillOval((int) ((x-offsetx)*map.getChunk_size()), (int) ((y-offsety)*map.getChunk_size()), map.getChunk_size(), map.getChunk_size());
        }
    }
}

