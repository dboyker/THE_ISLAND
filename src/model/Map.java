package model;
import controller.*;
import model.Chunk.*;
import model.Person.Person;
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
        generate_map();
        }

    public Chunk[][] getChunks() {return this.chunks;}
    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}
    public int getChunk_size() {return this.chunk_size;}

    public void generate_map() {
        // grass, water and sand
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < 50 || i > 150 || j < 50 || j > 150) {chunks[j][i] = new Water();}
                else if (i < 52 || i > 148 || j < 52 || j > 148) {chunks[j][i] = new Sand();}
                else {chunks[j][i] = new Grass();}
            }
        }

        // buildings
        int c = 0;
        while (c < 10) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);
            if (chunks[randomx][randomy].getWalkable() == false || chunks[randomx + 10][ randomy + 5].getWalkable() == false) {
                continue;
            }
            else {

                int building_height = 5;
                int building_width = 10;

                for (int i = randomy; i <= randomy + building_height; i++) {
                    for (int j = randomx; j <= randomx + building_width; j++) {
                        chunks[j][i] = new Building();
                    }
                }
                // for each building, put a door
                int randomd = (int) (Math.random()*8 + 1);
                chunks[randomx+randomd][randomy + building_height] = new Door(this);
                c = c + 1;
                // for each building, create a sub map and link the door
            }
        }

        // trees
        c = 0;
        while (c < 40) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);
            if (chunks[randomx][randomy].getWalkable() == false || chunks[randomx + 2][ randomy + 2].getWalkable() == false) {
                continue;
            }
            else {

                for (int i = randomy; i < randomy + 1; i++) {
                    for (int j = randomx; j < randomx + 1; j++) {
                        chunks[j][i] = new Tree();
                    }
                }
                c = c + 1;
            }
        }

/*
        Door door_entrance = new Door(this);
        BuildingMap building = new BuildingMap(100,100,10,game);
        Door door_exit = new Door(building);
        building.door_exit = door_exit;
        door_entrance.setLeadTo(door_exit);
        door_exit.setLeadTo(door_entrance);
        chunks[54][23] = door_entrance;*/
        // items
    }
}
