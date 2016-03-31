package model.Person;
import model.Map;
import model.Person.Person;
import view.Frame;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Player extends Person {

    public Player(Map map) {
        super(map);
        position = new int[2];
        position[0] = 20;
        position[1] = 20;
    }

    public void setDx(float dx) {this.dx = dx;}
    public void setDy(float dy) {this.dy = dy;}

}
