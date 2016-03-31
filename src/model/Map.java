package model;
import controller.*;
import view.*;
/**
 * Created by davidboyker on 28/03/16.
 */

public class Map {
    private int chunks[][];
    private int chunk_size = 4;
    private int width = 100;  // in terms of number of chunks
    private int height = 100;  // in terms of number of chunks

    public Map() {
        chunks = new int[width][height];
        generate_map();
        }

    public int[][] getChunks() {return this.chunks;}

    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}
    public int getChunk_size() {return this.chunk_size;}

    public void generate_map() {
        //to be completed
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i < 5 || i > 95 || j < 5 || j > 95) {chunks[j][i] = 0;}
                else {chunks[j][i] = 1;}

            }
        }
    }
}
