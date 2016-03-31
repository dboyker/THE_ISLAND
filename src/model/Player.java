package model;
import controller.*;
import view.*;
import view.Frame;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by davidboyker on 28/03/16.
 */
public class Player extends Person {
    private float dx = 0;
    private float dy = 0;

    public Player() {
        position = new int[2];
        position[0] = 400;
        position[1] = 400;
    }

    public void setDx(float dx) {this.dx = dx*4;}
    public void setDy(float dy) {this.dy = dy*4;}

    public void move(Frame frame) {
        if (dx != 0 || position[0] % 4 != 0) {  // need to move in the x-axis
            position[0] += dx;
            frame.game_panel.move_player(dx,0);
            this.dx = 0;
        }
        if (dy != 0 || position[1] % 4 != 0) {  // need to move in the y-axis
            position[1] += dy;
            frame.game_panel.move_player(0,dy);
            this.dy = 0;
        }

    }




}
