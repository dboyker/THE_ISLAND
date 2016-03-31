package controller;
import model.*;
import view.*;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Game implements Runnable{
    private Frame frame;
    //map
    private Map map;
    private int chunks[][];
    //persons
    private Player player;

    public Game(Frame frame) {
        this.frame = frame;
        //generate map
        map = new Map();
        chunks = map.getChunks();
        //create items
        //create NPC
        //create player
        player = new Player();
        frame.start_new_game(chunks, player, map);
        (new Thread(new PlayerThread(player,frame))).start();
    }

    //main loop
    public void run() {
    }
}
