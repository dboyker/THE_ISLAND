package controller;
import model.Map;
import model.Person.NPC.NPC;
import model.Person.Player.Player;
import view.*;
import view.Frame;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 51L;
    private Frame frame;
    private Map map;
    private Player player;
    private model.Person.NPC.NPC NPC[] = new NPC[1];

    public Player getPlayer() {return player;}
    public Map getMap() {return map;}
    public NPC[] getNPC() {return NPC;}
    public Frame getFrame() {return this.frame;}
    public void setFrame(Frame frame) {this.frame = frame;}

    public Game(Frame frame) {
        this.frame = frame;
        //generate map
        map = new Map(200,200,20,this);
        //create items
        //create player
        player = new Player(map, Color.lightGray);
        //create NPC
        NPC[0] = new NPC(map);
        //start game
        start();
    }

    public void start() {
        //------------- Starting the threads -------------//
        frame.start_new_game(this);
        (new Thread(new MAPThread(frame))).start();
        (new Thread(new MiniMapThread(frame))).start();
        (new Thread(new PlayerThread(player,frame))).start();
        (new Thread(new NPCThread(NPC[0],frame))).start();

    }
}
