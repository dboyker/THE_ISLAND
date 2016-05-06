// Interface pour les objets correspondants à des maps du jeu

package model.Map;
import model.Chunk.*;
import model.Item.Item;
import model.Person.Person;

/**
 * Created by davidboyker on 29/04/16.
 */
public interface MapInterface {
    Chunk[][] getChunks();
    Item[][] getItems();
    void delete_person(Person person);
    void delete_item(Item item);
    int getHeight();
    int getWidth();
    int getChunk_size();
    Person[][] getPersons();
    void generate_map();
}
