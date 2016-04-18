package controller;
import model.Person.Player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by davidboyker on 28/03/16.
 */
public class InputListener {
    private static view.Frame frame;

    InputListener(view.Frame frame) {this.frame = frame;}

    public static class ButtonListener implements MouseListener {
        private ButtonCallback buttonCallback;
        public ButtonListener(ButtonCallback buttonCallback) {this.buttonCallback = buttonCallback;}
        public void mouseClicked(MouseEvent e) {
                buttonCallback.execute();
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static class KeyboardListener implements KeyListener {
        private Game game;
        public KeyboardListener(Game game) {this.game = game;}
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                Player player = game.getPlayer();
            }
            else {
                float dx = 0;
                float dy = 0;
                Player player = game.getPlayer();
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    dy = -1;
                } //key up pressed
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    dy = 1;
                } //key down pressed
                player.setDy(dy);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    dx = -1;
                } //key left pressed
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    dx = 1;
                } //key right pressed
                player.setDx(dx);
            }
        }
    }

}