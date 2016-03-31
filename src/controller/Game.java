package controller;
import model.Chunk.*;
import model.Map;
import model.Person.*;
import view.*;

import java.io.Serializable;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Game implements Serializable {
    private Frame frame;
    private Map map;
    private Chunk chunks[][];
    private Player player;
    private NPC NPC[] = new NPC[1];

    public Chunk[][] getChunk() {return chunks;}
    public Player getPlayer() {return player;}
    public Map getMap() {return map;}
    public NPC[] getNPC() {return NPC;}

    public Game(Frame frame) {
        this.frame = frame;
        //generate map
        map = new Map();
        chunks = map.getChunks();
        //create items
        //create NPC
        //create player
        player = new Player(map);
        NPC[0] = new NPC(map);

        (new Thread(new PlayerThread(player,frame))).start();
        frame.start_new_game(this);
    }
}
