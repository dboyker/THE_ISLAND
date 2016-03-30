package model;
import controller.*;
import view.*;
/**
 * Created by davidboyker on 28/03/16.
 */

public class Map {
    private int chunks[][];
    private int chunk_size = 40;

    public Map() {
        //generate map
        chunks = new int[26][21];
        for (int i = 0; i < 21; i++) {for (int j = 0; j < 26; j++) {chunks[j][i] = (int) Math.floor(Math.random() * 3);}}
        }

    public int[][] getChunks() {return this.chunks;}
}
