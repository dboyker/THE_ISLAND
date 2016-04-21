package model;
import model.Chunk.*;

/**
 * Created by davidboyker on 4/04/16.
 */
public class BuildingMap extends Map {

    Door exit_door;
    int[] entrance_door_position;

    public BuildingMap(int width, int height, int chunk_size, Game game, Door entrance_door, int[] entrance_door_position) {
        super(width, height, chunk_size, game);
        this.exit_door = new Door(this);
        this.exit_door.setLeadTo(entrance_door); // les deux portes sont liées entre elles
        entrance_door.setLeadTo(exit_door);
        this.entrance_door_position = entrance_door_position;
    }

    @Override
    public void generate_map() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Chunk new_chunk;
                if (i == 0 || i == width-1 || j == 0 || j == height-1 ) { new_chunk = new Wall(); }
                else {  new_chunk = new Floor(); }
                chunks[i][j] = new_chunk;
            }
        }
        // put exit door
        chunks[entrance_door_position[0]][entrance_door_position[1]] = exit_door;
    }


}
