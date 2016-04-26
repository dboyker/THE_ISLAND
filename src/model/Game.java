package model;
import controller.MAPThread;
import controller.MiniMapThread;
import controller.NPCThread;
import controller.PlayerThread;
import model.Chunk.Chunk;
import model.Chunk.Door;
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
    private ArrayList<Map> maps = new ArrayList<>();
    private Player player;
    // Threads
    private Thread map_thread;
    private Thread mini_map_thread;
    public Player getPlayer() {return this.player;}
    public void setPlayer(Player player) {this.player = player;}
    public ArrayList<Map> getMaps() {return maps;}
    public void setMaps(Map map) {this.maps.add(map);}
    public Frame getFrame() {return this.frame;}
    public void setFrame(Frame frame) {this.frame = frame;}

    public Game(Frame frame) {
        this.frame = frame;
        //generate map
        Map map = new Map(80,80,25,this);
        maps.add(map);
        map.generate_map();
        //start game
        start();
    }

    public int count_villagers() {
        int number_of_villagers = 0;
        for (int c = 0; c < maps.size(); c++) {
            Map map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                        number_of_villagers += 1;
                    }
                }
            }
        }
        number_of_villagers -= 1;  // remove 1 because it has counted the player as a villager
        return number_of_villagers;
    }

    public void start() {
        //------------- Starting the threads -------------//
        frame.start_new_game(this);
        this.map_thread = new Thread(new MAPThread(frame));
        this.mini_map_thread = new Thread(new MiniMapThread(frame));
        map_thread.start();
        mini_map_thread.start();
        for (int c = 0; c < maps.size(); c++) {  // start the persons threads
            Map map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                       // System.out.println(map.getPersons()[i][j]);
                        map.getPersons()[i][j].startThread();
                    }
                }
            }
        }
}

    public void pause() {
        this.map_thread.suspend();
        this.mini_map_thread.suspend();
       // this.player_thread.suspend();
       // for (int i = 0; i < NPC.size(); i ++) {(new Thread(new NPCThread(NPC.get(i),frame))).suspend();}
    }

    public void resume() {
        this.map_thread.resume();
        this.mini_map_thread.resume();
        //this.player_thread.resume();
       // for (int i = 0; i < NPC.size(); i ++) {(new Thread(new NPCThread(NPC.get(i),frame))).resume();}
    }
}
