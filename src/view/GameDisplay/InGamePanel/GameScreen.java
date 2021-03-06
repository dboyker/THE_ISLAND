/**
 * Created by davidboyker on 16/04/16.
 */

package view.GameDisplay.InGamePanel;

import model.Chunk.Chunk;
import model.Map.Map;
import model.Person.Person;
import model.Person.Player.Player;
import model.Item.*;
import view.GameDisplay.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    private GamePanel game_panel;

    public GameScreen(GamePanel game_panel) {this.game_panel = game_panel;}

    public void paint(Graphics g)
    {
        Player player = game_panel.getPlayer();
        Map map = player.getMap();
        Chunk[][] chunks = map.getChunks();
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
                try {color = chunks[j][i].getColor();}
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
        // items
        Item items[][];
        items = map.getItems();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Item item = items[i][j];
                if (item != null) {
                    if (item.getPosition() != null) {
                        float pos_x = item.getPosition()[0];
                        float pos_y = item.getPosition()[1];
                        g.drawImage(item.getImage(), (int) ((pos_x - offsetx) * map.getChunk_size()), (int) ((pos_y - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
                    }
                }
            }
        }

        // persons
        Person persons[][];
        persons = map.getPersons();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                Person person = persons[i][j];
                if (person != null && person.getClass() != model.Person.Player.Player.class) {
                    float pos_x = person.getPosition()[0];
                    float pos_y = person.getPosition()[1];
                    g.drawImage(person.getImage(), (int) ((pos_x - offsetx) * map.getChunk_size()), (int) ((pos_y - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
                }
            }
        }
        g.drawImage(player.getImage(), (int) ((x - offsetx) * map.getChunk_size()), (int) ((y - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
        if (game_panel.getGame().getMultiplayer()) {
            if (player == game_panel.getGame().getPlayer_1()) {
                g.drawImage(game_panel.getGame().getPlayer_2().getImage(), (int) ((game_panel.getGame().getPlayer_2().getPosition()[0] - offsetx) * map.getChunk_size()), (int) ((game_panel.getGame().getPlayer_2().getPosition()[1] - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
            }
            else {
                g.drawImage(game_panel.getGame().getPlayer_1().getImage(), (int) ((game_panel.getGame().getPlayer_1().getPosition()[0] - offsetx) * map.getChunk_size()), (int) ((game_panel.getGame().getPlayer_1().getPosition()[1] - offsety) * map.getChunk_size()), map.getChunk_size(), map.getChunk_size(), null);
            }
            }
        }
    }


