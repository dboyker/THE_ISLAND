package model;

/**
 * Created by davidboyker on 28/03/16.
 */

import controller.GameController.GameController;
import controller.GameController.MAPThread;
import model.Map.Map;
import model.Person.Player.Player;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    private static final long serialVersionUID = 51L;
    private GameController controller;
    // maps
    private ArrayList<Map> maps = new ArrayList<>();
    // players
    private Player player_1;
    private Player player_2;
    private Boolean multiplayer = false;
    // game parameters
    private String difficulty = "normal";
    private int map_size_x = 100;
    private int map_size_y = 100;
    // Threads
    private transient Thread map_thread;
    // GET & SET
    public String getDifficulty() {return this.difficulty;}
    public void setDifficulty(String difficulty) {if (difficulty.equals("hard") || difficulty.equals("normal")) {this.difficulty = difficulty;}}
    public void setMap_size_x(int size) {this.map_size_x = size;}
    public void setMap_size_y(int size) {this.map_size_y = size;}
    public Player getPlayer_1() {return this.player_1;}
    public void setPlayer_1(Player player) {this.player_1 = player;}
    public Player getPlayer_2() {return this.player_2;}
    public void setPlayer_2(Player player) {this.player_2 = player;}
    public void setMultiplayer(Boolean multiplayer) {this.multiplayer = multiplayer;}
    public Boolean getMultiplayer() {return this.multiplayer;}
    public void setMaps(Map map) {this.maps.add(map);}
    public ArrayList<Map> getMaps() {return this.maps;}
    public GameController getController() {return this.controller;}
    public void setController(GameController controller) {this.controller = controller;}

    // fonction pour le décompte des npc dans le village
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
        number_of_villagers -= 2;  // remove 1 because it has counted the player & the seller as a villager
        return number_of_villagers;
    }

    // commence la partie: crée une map, lance la fenêtre et démarre les threads
    public Boolean start() {
        System.out.println("creating game");
        //generate map
        Map map = new Map(map_size_x,map_size_y,25,this);
        maps.add(map);
        map.generate_map();
        return true;
}
    // lance les différents threads: pour la map, la mini map, les items et les persons
    public void start_threading() {
        System.out.println("----------start game----------");
        //------------- Starting the threads -------------//
        this.map_thread = new Thread(new MAPThread());
        map_thread.start();
        Map map;
        for (int c = 0; c < maps.size(); c++) {  // start the persons threads
            map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                        map.getPersons()[i][j].startThread();
                    }
                }
            }
        }
    }

    // mets en pause la partie: arrete les threads
    public void pause() {
        System.out.println("----------pause game----------");
        Map map = this.maps.get(0);
        this.map_thread.suspend();
        this.player_1.suspendThread();
        if (multiplayer) {
            this.player_2.suspendThread();
        }
        for (int c = 0; c < maps.size(); c++) {  // start the persons threads
            map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                        map.getPersons()[i][j].suspendThread();
                    }
                }
            }
        }
    }

    // continue le jeu: relance les threads
    public void resume() {
        System.out.println("----------resume game----------");
        Map map = this.maps.get(0);
        this.map_thread.resume();
        this.player_1.resumeThread();
        if (multiplayer) {
            this.player_2.resumeThread();
        }
        for (int c = 0; c < maps.size(); c++) {  // start the persons threads
            map = maps.get(c);
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j ++) {
                    if (map.getPersons()[i][j] != null) {
                        map.getPersons()[i][j].resumeThread();
                    }
                }
            }
        }
    }
}
