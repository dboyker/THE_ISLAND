package model.Item;

import model.Item.Item;

/**
 * Created by davidboyker on 17/04/16.
 */
public class Weapon extends Item{

    private int damage;
    private int cooldown;

    public Weapon(int damage, int cooldown) {
        this.damage = damage;
    }

    public int getDamage() {return this.damage;}
}
