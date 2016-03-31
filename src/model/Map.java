package model;
import model.Chunk.Building;
import model.Chunk.Chunk;
import model.Chunk.Grass;
import model.Chunk.Water;
import model.Person.Person;
import java.util.*;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Map {
    private Chunk chunks[][];
    private int chunk_size = 4;
    private int width = 100;  // in terms of number of chunks
    private int height = 100;  // in terms of number of chunks
    private Person persons[];

    public Map() {
        chunks = new Chunk[width][height];
        generate_map();
        }

    public Chunk[][] getChunks() {return this.chunks;}

    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}
    public int getChunk_size() {return this.chunk_size;}

    public void generate_map() {
        //to be completed
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < 5 || i > 95 || j < 5 || j > 95) {chunks[j][i] = new Water();}
                else {chunks[j][i] = new Grass();}
            }
        }
        for (int i = 20; i<24; i++) {
            for (int j = 50; j<56; j++) {
            chunks[j][i] = new Building();
        }}


    }
}
