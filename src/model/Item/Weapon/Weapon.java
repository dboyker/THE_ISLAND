package model.Item.Weapon;

/**
 * Created by davidboyker on 17/04/16.
 */
public class Weapon {

    private int damage;
    private int cooldown;

    Weapon(int damage, int cooldown) {
        this.damage = damage;
    }

    public int getDamage() {return this.damage;}
}
