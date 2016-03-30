package model;
import controller.*;
import view.*;

/**
 * Created by davidboyker on 28/03/16.
 */

public class Person {
    protected int health;
    protected int[] position;

    public Person() {}

    public void setPosition(int[] position) {this.position = position;}
    public int[] getPosition() {return this.position;}
}
