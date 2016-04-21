package model;
import model.Chunk.*;
import model.Person.NPC.NPC;
import model.Person.Player.Player;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Map implements Serializable{

    public Game game;
    protected Chunk chunks[][];
    protected int chunk_size;
    protected int width;  // in terms of number of chunks
    protected int height;  // in terms of number of chunks

    public Map(int width, int height, int chunk_size, Game game) {
        this.width = width;
        this.height = height;
        this.chunk_size = chunk_size;
        this.chunks = new Chunk[width][height];
        this.game = game;
        }

    public Chunk[][] getChunks() {return this.chunks;}
    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}
    public int getChunk_size() {return this.chunk_size;}

    public void generate_map() {
        // grass, water and sand
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < (int) (width/4) || i > width-((int) (width/4)) || j < (int) (width/4) || j > width-((int) (width/4))) {chunks[j][i] = new Water();}
                else if (i < (int) (width/4)+2 || i > width-((int) (width/4))-2 || j < (int) (width/4)+2 || j > width-((int) (width/4))-2) {chunks[j][i] = new Sand();}
                else {chunks[j][i] = new Grass();}
            }
        }
        // buildings
        int c = 0;
        while (c <= 10) {
            int randomx = (int) (Math.random() * width);
            int randomy = (int) (Math.random() * height);
            int building_height;
            int building_width;
            if (c == 10) {// player home
                building_height = 4;
                building_width = 6;
                 }
            else {
                building_height = 5;
                building_width = 10;
            }
            boolean cont = true;
            for (int i = randomy-2; i <= randomy + building_height+2; i++) {
                for (int j = randomx-2; j <= randomx + building_width+2; j++) {
                    try{
                    if (chunks[j][i].getWalkable() == false) {
                        cont = false;
                    }}
                    catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
            if (!cont) {continue;}
            else {
                for (int i = randomy; i <= randomy + building_height; i++) {
                    for (int j = randomx; j <= randomx + building_width; j++) {
                        chunks[j][i] = new Building();
                    }
                }
                // for each building, put a door
                int randomd = (int) (Math.random()*(building_width-2))+1;
                Door entrance_door = new Door(this);
                chunks[randomx+randomd][randomy + building_height] = entrance_door;
                int[] pos = new int[2];
                int magnificient_index = 4;
                pos[0] = randomd*magnificient_index;
                pos[1] = (building_height*magnificient_index)-1;
                // for each building, create a sub map and link the door
                BuildingMap building = new BuildingMap(building_width*magnificient_index, building_height*magnificient_index, this.chunk_size, this.game, entrance_door, pos);
                building.generate_map();
                // if player, put it in his home
                Player player;
                float[] position = new float[2];
                position[0] = 5;
                position[1] = 5;
                player = new Player(building, position, Color.lightGray);
                player.setPosition(position);
                game.setPlayer(player);
                c = c + 1;
            }
        }

        // trees
        c = 0;
        int number_of_tress = (int) (width/5);
        while (c < number_of_tress) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);

            if (chunks[randomx][randomy].getClass() == model.Chunk.Grass.class && chunks[randomx][randomy+1].getClass() == model.Chunk.Grass.class && chunks[randomx][randomy-1].getClass() == model.Chunk.Grass.class)
            {
                for (int i = randomy; i < randomy + 2; i++) {
                for (int j = randomx; j < randomx + 1; j++) {
                    Tree new_tree = new Tree();
                    chunks[j][i] = new_tree;
                }
            }
                c = c + 1;}
            else {continue;}
        }
        // NPC
        c = 0;
        int number_of_npc = (int) width/20;
        while (c < number_of_npc) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);
            if (chunks[randomx][randomy].getWalkable() == false) {continue;}
            else {
                float[] position = new float[2];
                position[0] = randomx;
                position[1] = randomy;
                NPC new_npc = new NPC(this, position, Color.blue );
                chunks[randomx][randomy].setPerson(new_npc);
                game.setNPC(new_npc);
            }
            c ++;
        }
    }
}
