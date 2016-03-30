package controller;
import model.*;
import view.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by davidboyker on 28/03/16.
 */
public class InputListener {
    private static view.Frame frame;

    InputListener(view.Frame frame) {
        this.frame = frame;
    }

    public static class ButtonListener implements MouseListener {
        private String type;
        public ButtonListener(String ty) {type = ty;}
        public void mouseClicked(MouseEvent e) {
            if (type == "game") {
                Main.start_new_game();

            }
            else if (type == "menu") {
                Main.go_to_main_menu();
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static class KeyboardListener implements KeyListener {
        public KeyboardListener() {}
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            float dx = 0;
            float dy = 0;
            Player player = frame.getGame_panel().getPlayer();
            if (e.getKeyCode() == KeyEvent.VK_UP) {dy = -40;} //key up pressed
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {dy = 40;} //key down pressed
            player.setDy(dy);
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {dx = -40;} //key left pressed
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {dx = 40;} //key right pressed
            player.setDx(dx);


        }
    }

}