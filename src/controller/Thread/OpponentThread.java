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
            // get player
            Player player = opponent.getMap().game.getPlayer();
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
                if (opponent.getAttacker() && need_to_act) {  // atack
                    // go in the direction of the player
                    if (player_posx != npc_posx) {  // move in x
                        int dx = (int) ((player_posx - npc_posx) / Math.abs(player_posx - npc_posx));
                        opponent.setDx(dx);
                        opponent.setDy(0);
                    }
                    else if (player_posy != npc_posy){  // move in y
                        int dy = (int) ((player_posy - npc_posy) / Math.abs(player_posy - npc_posy));;
                        opponent.setDx(0);
                        opponent.setDy(dy);
                    }
                    if (Math.abs(player_posx - npc_posx) < 2 && Math.abs(player_posy - npc_posy) < 2) {
                        // attack player if on the next case
                        opponent.melee_attack();
                    }
                } else if (opponent.getCoward() && need_to_act) {  // flee: go in the opposite direction
                    double random = Math.random();
                    if (random > 0.5) {
                        int dx = -1 * (int) ((player_posx - npc_posx) / Math.abs(player_posx - npc_posx));
                        opponent.setDx(dx);
                    }
                    else {
                        int dy = -1 * (int) ((player_posy - npc_posy) / Math.abs(player_posy - npc_posy));
                        opponent.setDy(dy);
                    }
                }
            }
            // move
            opponent.move();
        }
    }
}

