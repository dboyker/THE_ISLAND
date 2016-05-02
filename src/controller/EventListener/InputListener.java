// Ce fichier contient les classes nécessaires pour gérer les inputs claviers/souris

package controller.EventListener;
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

    public InputListener(view.Frame frame) {this.frame = frame;}

    public static class ButtonListener implements MouseListener {
        private ButtonCallback buttonCallback;
        public ButtonListener(ButtonCallback buttonCallback) {this.buttonCallback = buttonCallback;}
        public void mouseClicked(MouseEvent e) {buttonCallback.execute();}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static class KeyboardListener implements KeyListener {
        private Player player_1;
        private Player player_2;
        public KeyboardListener(Game game) {
            this.player_1 = game.getPlayer_1();
            this.player_2 = game.getPlayer_2();
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {
            int dx;
            int dy;
            if (player_1.getMap().game.getMultiplayer()) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    dy = 0;
                    player_2.setDy(dy);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    dx = 0;
                    player_2.setDx(dx);
                }
            }
                if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_S) {
                    dy = 0;
                    player_1.setDy(dy);
                }
                if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_D) {
                    dx = 0;
                    player_1.setDx(dx);
                }
        }
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                player_1.setMelee_attack(true);
                player_1.setFire_attack(false);
                player_1.setShoot_attack(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_X) {
                player_1.setFire_attack(true);
                player_1.setShoot_attack(false);
                player_1.setMelee_attack(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_E) {
                player_1.setShoot_attack(true);
                player_1.setFire_attack(false);
                player_1.setMelee_attack(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_I && player_1.getMap().game.getMultiplayer()) {
                player_2.setMelee_attack(true);
                player_2.setFire_attack(false);
                player_2.setShoot_attack(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_O && player_1.getMap().game.getMultiplayer()) {
                player_2.setFire_attack(true);
                player_2.setShoot_attack(false);
                player_2.setMelee_attack(false);
            }
            else if (e.getKeyCode() == KeyEvent.VK_P && player_1.getMap().game.getMultiplayer()) {
                player_2.setShoot_attack(true);
                player_2.setFire_attack(false);
                player_2.setMelee_attack(false);
            }
            else {
                int dx;
                int dy;
                if (e.getKeyCode() == KeyEvent.VK_UP && player_1.getMap().game.getMultiplayer()) {
                    dx = 0;
                    dy = -1;
                    player_2.setDy(dy);
                    player_2.setDx(dx);
                } //key up pressed
                if (e.getKeyCode() == KeyEvent.VK_DOWN && player_1.getMap().game.getMultiplayer()) {
                    dx = 0;
                    dy = 1;
                    player_2.setDx(dx);
                    player_2.setDy(dy);
                } //key down pressed
                if (e.getKeyCode() == KeyEvent.VK_LEFT && player_1.getMap().game.getMultiplayer()) {
                    dy = 0;
                    dx = -1;
                    player_2.setDx(dx);
                    player_2.setDy(dy);
                } //key left pressed
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && player_1.getMap().game.getMultiplayer()) {
                    dy = 0;
                    dx = 1;
                    player_2.setDx(dx);
                    player_2.setDy(dy);
                } //key right pressed
                if (e.getKeyCode() == KeyEvent.VK_Z) {
                    dx = 0;
                    dy = -1;
                    player_1.setDy(dy);
                    player_1.setDx(dx);
                } //key up pressed
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    dx = 0;
                    dy = 1;
                    player_1.setDx(dx);
                    player_1.setDy(dy);
                } //key down pressed
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    dy = 0;
                    dx = -1;
                    player_1.setDx(dx);
                    player_1.setDy(dy);
                } //key left pressed
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    dy = 0;
                    dx = 1;
                    player_1.setDx(dx);
                    player_1.setDy(dy);
                } //key right pressed
            }
        }
    }

}