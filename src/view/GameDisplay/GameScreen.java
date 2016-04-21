package view.GameDisplay;

import model.Game;
import model.Chunk.Chunk;
import model.Map;
import model.Person.Person;
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
    //items

    public GameScreen(Game game) {
        this.game = game;
        this.player = game.getPlayer();
        this.map = player.getMap();
        this.chunks = map.getChunks();
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
        this.setBackground(Color.WHITE);
        float x = player.getPosition()[0];
        float y = player.getPosition()[1];
        int middlex =  this.getWidth()/(2*map.getChunk_size());
        int middley = this.getHeight()/(2*map.getChunk_size());
        float offsetx = x - middlex;
        float offsety = y - middley;
        for (int i = (int) offsety; i < map.getHeight(); i++) {
            for (int j = (int) offsetx; j < map.getWidth(); j ++) {
                Color color = Color.white;
                try {color = chunks[j][i].color;}
                catch (ArrayIndexOutOfBoundsException u) {}
                g.setColor(color);
                int chunk_size = map.getChunk_size();

                float xabs = (float) (Math.round(((j-offsetx)*chunk_size) * 100.0) / 100.0);
                float yabs = (float) (Math.round(((i-offsety)*chunk_size) * 100.0) / 100.0);
                try {
                    if (chunks[j][i].getClass() == model.Chunk.Grass.class) {
                        g.drawImage(new ImageIcon("image/grass.png").getImage(), (int) xabs, (int) yabs, chunk_size, chunk_size, null);
                    } else {
                        g.fillRect((int) (xabs), (int) (yabs), chunk_size, chunk_size);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {}
                //check if person on this chunk
                try {if (chunks[j][i].getPerson() != null) {
                    Person person = chunks[j][i].getPerson();
                    if (person.getClass() == model.Person.NPC.NPC.class) {
                        g.setColor(person.getColor());
                        g.fillOval((int) ((j - offsetx) * chunk_size), (int) ((i - offsety) * chunk_size), chunk_size, chunk_size);
                    }
                }}
                catch (ArrayIndexOutOfBoundsException u) {}
            }
        }
        //player
        //g.setColor(player.getColor());
        //g.fillOval((int) ((x-offsetx)*map.getChunk_size()), (int) ((y-offsety)*map.getChunk_size()), map.getChunk_size(),map.getChunk_size());
        g.drawImage(new ImageIcon("image/playerd.png").getImage(),(int) ((x-offsetx)*map.getChunk_size()), (int) ((y-offsety)*map.getChunk_size()), map.getChunk_size(),map.getChunk_size(), null);
    }
}

