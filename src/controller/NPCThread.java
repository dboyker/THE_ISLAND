package controller;

import model.Person.NPC.NPC;
import model.Person.Player.*;

/**
 * Created by davidboyker on 30/03/16.
 */
public class NPCThread implements Runnable {

    private NPC npc;

    public NPCThread(NPC npc) {
        this.npc = npc;
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
            Player player = npc.getMap().game.getPlayer();
            //get his position and npc's position
            float player_posx = player.getPosition()[0];
            float player_posy = player.getPosition()[1];
            float npc_posx = npc.getPosition()[0];
            float npc_posy = npc.getPosition()[1];
            // compare the two positions
            int number_of_chunks_to_act = 10;
            if (Math.abs(npc_posx - player_posx) < number_of_chunks_to_act && Math.abs(npc_posy - player_posy) < number_of_chunks_to_act) {
                // player is close, do something!
                need_to_act = true;
            }
            if (need_to_act) {
                // Attack or flee
                if (npc.getAttacker() && need_to_act) {  // atack
                    // go in the direction of the player
                    if (player_posx != npc_posx) {  // move in x
                        int dx = (int) ((player_posx - npc_posx) / Math.abs(player_posx - npc_posx));
                        npc.setDx(dx);
                    }
                    else if (player_posx == npc_posx && player_posy != npc_posy){  // move in y
                        int dy = (int) ((player_posy - npc_posy) / Math.abs(player_posy - npc_posy));
                        npc.setDy(dy);
                    }
                    if (Math.abs(player_posx - npc_posx) < 1 && Math.abs(player_posy - npc_posy) < 1) {npc.melee_attack();}
                    // attack him if on the next case
                } else if (npc.getCoward() && need_to_act) {  // flee: go in the opposite direction
                    double random = Math.random();
                    if (random > 0.5) {
                        int dx = -1 * (int) ((player_posx - npc_posx) / Math.abs(player_posx - npc_posx));
                        npc.setDx(dx);
                    }
                    else {
                        int dy = -1 * (int) ((player_posy - npc_posy) / Math.abs(player_posy - npc_posy));
                        npc.setDy(dy);
                    }
                }
            }
            // move
            npc.move();
        }
    }
}

