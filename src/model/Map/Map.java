/**
 * Created by davidboyker on 28/03/16.
 */


// Classe pour la map principale

package model.Map;

import model.Chunk.*;
import model.Game;
import model.Item.Activator.ChestActivator;
import model.Item.Activator.SellerActivator;
import model.Item.*;
import model.Item.Collectable.Drug;
import model.Item.Collectable.MediKit;
import model.Item.Instantaneous.Coin;
import model.Item.Instantaneous.Heart;
import model.Person.NPC.Opponent;
import model.Person.Person;
import model.Person.Player.Player;
import model.Person.NPC.Seller;
import java.io.Serializable;

public class Map implements MapInterface, Serializable{

    public Game game;
    protected Chunk chunks[][];
    protected Person persons[][];
    protected Item items[][];
    protected int chunk_size;
    protected int width;  // En termes de nombres de chunks
    protected int height;  // En termes de nombres de chunks
    protected int number_of_building;

    public Map(int width, int height, int chunk_size, Game game) {
        this.width = width;
        this.height = height;
        this.chunk_size = chunk_size;
        this.chunks = new Chunk[width][height];
        this.persons = new Person[width][height];
        this.items = new Item[width][height];
        this.number_of_building = (int) width/5;
        this.game = game;
        }

    public Chunk[][] getChunks() {return this.chunks;}
    public Item[][] getItems() {return this.items;}
    public void deletPerson(Person person) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (persons[i][j] == person) {
                    persons[i][j] = null;
                    person.suspendThread();
                }
            }
        }
    }
    public void deleteItem(Item item) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (items[i][j] == item) {
                    items[i][j] = null;
                   // item.suspendThread();
                }
            }
        }
    }
    public int getHeight() {return this.height;}
    public int getWidth() {return this.width;}
    public int getChunk_size() {return this.chunk_size;}
    public Person[][] getPersons() {return this.persons;}

    public void generate_map() {  // fonction pour la génération aléatoire de la map
        System.out.println("generating map");
        //1/ grass, water et sand
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int random1 = (int) (Math.random() * 4);
                int random2 = (int) (Math.random() * 4);
                if (i < width/4 || i > width*3/4 || j < width/4 || j > width*3/4) {chunks[i][j] = new Water();}
                else {
                    chunks[i][j] = new Grass();
                }
            }
        }

        //2/ buildings
        int c = 1;
        while (c <= number_of_building) {
            int randomx = (int) (Math.random() * width);
            int randomy = (int) (Math.random() * height);
            int building_height;
            int building_width;
                building_height = 3;
                building_width = (int) (Math.random()*4) + 5;
            boolean cont = true;
            for (int i = randomx - 2; i <= randomx + building_width+2; i++) {
                for (int j = randomy - 2; j <= randomy + building_height+2; j++) {
                    try{
                    if (chunks[i][j].getWalkable() == false) {
                        cont = false;
                    }}
                    catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
            if (!cont) {continue;}
            else {
                for (int i = randomx; i < randomx + building_width; i++) {
                    for (int j = randomy; j < randomy + building_height; j++) {
                        if (j == randomy) {
                            if (i == randomx) {chunks[i][j] = new Building("rtl");}
                            else if (i == randomx + building_width - 1) {chunks[i][j] = new Building("rtr");}
                            else {chunks[i][j] = new Building("rtm");}
                        }
                        else if (j == randomy + 1) {
                            if (i == randomx) {chunks[i][j] = new Building("rbl");}
                            else if (i == randomx + building_width - 1) {chunks[i][j] = new Building("rbr");}
                            else {chunks[i][j] = new Building("rbm");}
                        }
                        else {  // building base
                            if (i == randomx) {chunks[i][j] = new Building("bbl");}
                            else if (i == randomx + building_width - 1) {chunks[i][j] = new Building("bbr");}
                            else {
                                int random = (Math.random()<0.5)?0:1;
                                if (random == 1) { chunks[i][j] = new Building("bbmw"); }
                                else { chunks[i][j] = new Building("bbm"); }
                            }
                        }
                    }
                }
                // Pour chaque building, on crée une porte d'entrée
                int randomd = (int) (Math.random()*(building_width-2))+1;
                Door entrance_door = new Door(this);
                chunks[randomx+randomd][randomy + building_height - 1] = entrance_door;
                int[] pos = new int[2];
                int magnificient_index = 4;  // coefficient that multiplies the dimension of the building, when entering it
                pos[0] = randomd*magnificient_index;
                pos[1] = (building_height*magnificient_index)-1;
                // for each building, create a sub map and link the door
                BuildingMap building = new BuildingMap(building_width*magnificient_index, building_height*magnificient_index, this.chunk_size, this.game);
                game.setMaps(building);  // add this submap in the array of maps
                building.generate_map(entrance_door, pos);
                c ++;
            }
        }
        //3/ trees
        c = 0;
        int number_of_trees = (int) (width/3);
        while (c < number_of_trees) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);

            if (chunks[randomx][randomy].getClass() == model.Chunk.Grass.class && chunks[randomx][randomy+1].getClass() == model.Chunk.Grass.class && chunks[randomx][randomy-1].getClass() == model.Chunk.Grass.class)
            {
                    Tree new_tree_top = new Tree("top");
                    Tree new_tree_bottom = new Tree("bottom");
                    chunks[randomx][randomy] = new_tree_top;
                    chunks[randomx][randomy+1] = new_tree_bottom;

                c = c + 1;}
            else {continue;}
        }
        //4/ NPC
         c = 1;
        int number_of_npc = (int) width/3;
        //int number_of_npc = 100;
        while (c <= number_of_npc) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);
            if (chunks[randomx][randomy].getWalkable() == false || persons[randomx][randomy] != null || chunks[randomx][randomy].getClass() == model.Chunk.Door.class) {continue;}
            else {
                float[] position = new float[2];
                position[0] = randomx;
                position[1] = randomy;
                new Opponent(this, position);
                c += 1;
            }
        }
        //5/ Dock & player & chest & seller
        for (int a=1; a <= 10; a++) {
            chunks[(width/4) - a][(int) (height/2)] = new Dock("top");
            chunks[(width/4) - a][(int) (height/2) + 1] = new Dock("top");
            chunks[(width/4) - a][(int) (height/2) + 2] = new Dock("top");
            chunks[(width/4) - a][(int) (height/2) + 3] = new Dock("bottom");
            if (a == 10) {
                // player(s)
                Player player_1;
                Player player_2;
                float[] position_1 = new float[2];
                position_1[0] = (int) (width/4) - a;
                position_1[1] = (int) (height/2 + 3);
                player_1 = new Player(this, position_1);
                this.game.setPlayer_1(player_1);
                System.out.println(player_1);
                if (game.getMultiplayer()) {
                    // 2eme joueur si multiplayer
                    float[] position_2 = new float[2];
                    position_2 = new float[2];
                    position_2[0] = (int) (width / 4) - a + 1;
                    position_2[1] = (int) (height / 2 + 3);
                    player_2 = new Player(this, position_2);
                    System.out.println(player_2);
                    this.game.setPlayer_2(player_2);
                }

                // chest
                this.chunks[(width/4) - a + 1][(height/2 )] = new Chest();
                this.items[(width/4) - a + 1][(height/2 ) + 1] = new ChestActivator(null);
                // seller
                Seller seller;
                float[] position = new float[2];
                position[0] = (int) (width/4) - a;
                position[1] = (int) (height/2);
                new Seller(this, position);
                this.items[(int) position[0]][(int) position[1] + 1] = new SellerActivator(null);

            }

        }

        // Items
        c = 1;
        int number_of_items = 200;
        while (c <= number_of_items) {
            int randomx = (int) (Math.random()*width);
            int randomy = (int) (Math.random()*height);
            float[] position = new float[2];
            position[0] = randomx;
            position[1] = randomy;
            if (chunks[randomx][randomy].getWalkable() == true) {
                if (c > 150) {new Heart(this, position);}
                else if (c < 150 && c > 100) {
                    Drug drug = new Drug();
                    drug.setMap(this);
                    drug.setPosition(position);
                }
                else if (c < 100 && c > 50) {
                    MediKit medi_kit = new MediKit();
                    medi_kit.setMap(this);
                    medi_kit.setPosition(position);
                }
                else {new Coin(this, position, 30);}
            }
            c ++;
        }
    }
}
