/**
 * Created by davidboyker on 28/04/16.
 */

// Classe pour le PNJ de type seller. Ce pnj permet au joeur d'améliorer ses attaques

package model.Person.NPC;

import model.Map.Map;
import model.Person.Person;
import javax.swing.*;

public class Seller extends Person implements NPC {

    private static final long serialVersionUID = 51L;

    public Seller(Map map, float[] position) {
        super(map, position);
        reset_image();
        this.health = 1;
    }

    // GET & SET
    public Boolean getAttacker() {return false;}
    public Boolean getCoward() {return false;}

    @Override
    public void reset_image() {
        this.image = new ImageIcon("image/seller.png").getImage();
    }
}
