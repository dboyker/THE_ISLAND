// Thread pour les PNJ

package controller.Thread;

import model.Person.NPC.Opponent;
import model.Person.Player.*;

/**
 * Created by davidboyker on 30/03/16.
 */
public class OpponentThread implements Runnable {

    private Opponent opponent;

    public OpponentThread(Opponent opponent) {
        this.opponent = opponent;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            Boolean need_to_act = false;
            // get player: get le player le plus près si 2 joueurs
            Player player_1 = opponent.getMap().game.getPlayer_1();
            Player player_2 = opponent.getMap().game.getPlayer_2();
            if (player_2 == null) {player_2 = player_1;}
            Player player;
            int distance_to_player_1 = (int) (Math.abs(player_1.getPosition()[0] - opponent.getPosition()[0]) + Math.abs(player_1.getPosition()[1] - opponent.getPosition()[1]));
            int distance_to_player_2 = (int) (Math.abs(player_2.getPosition()[0] - opponent.getPosition()[0]) + Math.abs(player_2.getPosition()[1] - opponent.getPosition()[1]));
            if (distance_to_player_1 <= distance_to_player_2) {
                player = player_1;
            }
            else {
                player = player_2;
            }
            //get his position and npc's position
            float player_posx = (int) player.getPosition()[0];
            float player_posy = (int) player.getPosition()[1];
            float npc_posx = (int) opponent.getPosition()[0];
            float npc_posy = (int) opponent.getPosition()[1];
            // compare the two positions
            int number_of_chunks_to_act = 10;
            if (Math.abs(npc_posx - player_posx) < number_of_chunks_to_act && Math.abs(npc_posy - player_posy) < number_of_chunks_to_act) {
                // player is close, do something!
                need_to_act = true;
            }
            if (need_to_act) {
                // Attack or flee
                if (opponent.getAttacker() && need_to_act) {
                    // go in the direction of the player
                    opponent.go_to_player(player);
                } else if (opponent.getCoward() && need_to_act) {
                    // flee: go in the opposite direction
                    opponent.escape_player(player);
                }
            }
            // move
            opponent.move();
        }
    }
}

