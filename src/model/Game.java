package model;
import controller.MAPThread;
import controller.MiniMapThread;
import controller.NPCThread;
import controller.PlayerThread;
import model.Map;
import model.Person.NPC.NPC;
import model.Person.Person;
import model.Person.Player.Player;
import view.*;
import view.Frame;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 51L;
    private Frame frame;
    private Map map;
    private Player player;
    private ArrayList<NPC> NPC = new ArrayList<>(); // to be changed
    // Threads
    private Thread map_thread;
    private Thread mini_map_thread;
    private Thread player_thread;

    public Player getPlayer() {return player;}
    public void setPlayer(Player player) {this.player = player;}
    public void setNPC(NPC npc) {NPC.add(npc);}
    public ArrayList<NPC> getNPC() {return this.NPC;}
    public Map getMap() {return map;}
    public Frame getFrame() {return this.frame;}
    public void setFrame(Frame frame) {this.frame = frame;}

    public Game(Frame frame) {
        this.frame = frame;
        //generate map
        map = new Map(100,100,20,this);
        map.generate_map();
        //start game
        start();
    }

    public void start() {
        //------------- Starting the threads -------------//
        frame.start_new_game(this);
        this.map_thread = new Thread(new MAPThread(frame));
        this.mini_map_thread = new Thread(new MiniMapThread(frame));
        this.player_thread = new Thread(new PlayerThread(player,frame));
        map_thread.start();
        mini_map_thread.start();
        player_thread.start();
       // for (int i = 0; i < NPC.size(); i ++) {(new Thread(new NPCThread(NPC.get(i),frame))).start();}
}

    public void pause() {
        this.map_thread.suspend();
        this.mini_map_thread.suspend();
        this.player_thread.suspend();
    }

    public void resume() {
        this.map_thread.resume();
        this.mini_map_thread.resume();
        this.player_thread.resume();
    }
}
