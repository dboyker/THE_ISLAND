package view.GameDisplay;

import model.Game;
import model.Chunk.Chunk;
import model.Map;
import model.Person.Person;
import model.Person.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
                    Image img = chunks[j][i].getImage();
                    if (img != null) {
                        g.drawImage(chunks[j][i].getImage(), (int) xabs, (int) yabs, chunk_size, chunk_size, null);
                    }
                    else {
                        g.fillRect((int) (xabs), (int) (yabs), chunk_size, chunk_size);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {}
            }
        }

        Person persons[][];
        persons = map.getPersons();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Person person = persons[i][j];
                if (person != null && person.getClass() != model.Person.Player.Player.class) {
                    float pos_x = person.getPosition()[0];
                    float pos_y = person.getPosition()[1];
                    //  g.setColor(person.getColor());
                    //  g.fillOval((int) ((pos_x-offsetx)*map.getChunk_size()), (int) ((pos_y-offsety)*map.getChunk_size()),map.getChunk_size(),map.getChunk_size());
                    g.drawImage(person.getImage(), (int) ((pos_x - offsetx) * map.getChunk_size()), (int) ((pos_y - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
                }
            }
        }
        g.drawImage(game.getPlayer().getImage(), (int) ((x - offsetx) * map.getChunk_size()), (int) ((y - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
    }
}

