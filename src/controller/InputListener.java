package controller;
import model.Game;
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
        public void keyReleased(KeyEvent e) {
            int dx;
            int dy;
            Player player = game.getPlayer();
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                dy = 0;
                player.setDy(dy);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                dx = 0;
                player.setDx(dx);
            }
        }
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                Player player = game.getPlayer();
                player.melee_attack();
            }
            else {
                int dx;
                int dy;
                Player player = game.getPlayer();
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    dx = 0;
                    dy = -1;
                    player.setDy(dy);
                    player.setDx(dx);
                } //key up pressed
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    dx = 0;
                    dy = 1;
                    player.setDx(dx);
                    player.setDy(dy);
                } //key down pressed
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    dy = 0;
                    dx = -1;
                    player.setDx(dx);
                    player.setDy(dy);
                } //key left pressed
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    dy = 0;
                    dx = 1;
                    player.setDx(dx);
                    player.setDy(dy);
                } //key right pressed
                //player.move(frame);
            }
        }
    }

}