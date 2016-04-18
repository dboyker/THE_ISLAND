package model;
import model.Chunk.*;
import controller.*;

/**
 * Created by davidboyker on 4/04/16.
 */
public class BuildingMap extends Map {

    public Door door_exit;

    public BuildingMap(int width, int height, int chunk_size, Game game) {
        super(width, height, chunk_size, game);
        chunks = new Chunk[width][height];
        generate_map();
    }

    @Override
    public void generate_map() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                chunks[j][i] = new Grass();
            }
        }
        chunks[width/2][height-1] = door_exit;
    }


}
