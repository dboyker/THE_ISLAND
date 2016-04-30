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
    void deletPerson(Person person);
    void deleteItem(Item item);
    int getHeight();
    int getWidth();
    int getChunk_size();
    Person[][] getPersons();
    void generate_map();
}
