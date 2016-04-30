package model.Map;
import model.Chunk.*;
import model.Game;
import model.Item.Heart;
import model.Item.Coin;
import model.Map.Map;
import model.Person.NPC.Opponent;

import java.awt.*;

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
        // put exit door
        chunks[entrance_door_position[0]][entrance_door_position[1]] = exit_door;
        int randomx = (int) (width/2 + Math.random()*width/4 - width/8);
        if (randomx == entrance_door_position[0]) { randomx += 1; }
        int randomy = (int) (height/2 + Math.random()*height/4 - height/8);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == entrance_door_position[0] && j == entrance_door_position[1]) {continue;}
                Chunk new_chunk;
                // Premièrement: murs et pièces
                if (i == randomx && j!= 3 && j != height - 3) {new_chunk = new Wall();}
                else if (j == randomy && i!= 3 && i != width - 3) {new_chunk = new Wall();}
                else if (i == 0 || i == width-1 || j == 0 || j == height-1 ) { new_chunk = new Wall(); }
                else {  new_chunk = new Floor(); }
                chunks[i][j] = new_chunk;
            }
        }
        // NPC
        int random_npc_number = (int) (Math.random()*3) + 3;
        int i = 1;
        while (i <= random_npc_number) {
            randomx = (int) (Math.random() * width);
            randomy = (int) (Math.random() * height);
            if (chunks[randomx][randomy].getWalkable() == false || persons[randomx][randomy] != null || chunks[randomx][randomy].getClass() == model.Chunk.Door.class) {
                continue;
            } else {
                float[] position = new float[2];
                position[0] = randomx;
                position[1] = randomy;
                Opponent new_npc = new Opponent(this, position, Color.blue);
                this.persons[randomx][randomy] = new_npc;
                i ++;
            }
        }
        // Items
        int c = 1;
        int number_of_items = 8;
        while (c <= number_of_items) {
            randomx = (int) (Math.random()*width);
            randomy = (int) (Math.random()*height);
            float[] position = new float[2];
            position[0] = randomx;
            position[1] = randomy;
            if (chunks[randomx][randomy].getWalkable() == true) {
                if (c > 4) {
                    new Heart(this, position);
                }
                else {
                    new Coin(this, position, 30);
                }
            }
            c ++;
        }
    }
}
