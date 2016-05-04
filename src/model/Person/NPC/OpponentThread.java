/**
 * Created by davidboyker on 30/03/16.
 */

// Thread pour les PNJ de type adversaire (Opponent)

package model.Person.NPC;

import model.Person.Player.*;


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
            // si il y a deux joueurs sur la partie, calcul des distances entre ces deux joeurs et le PNJ auquel se thread se rapporte
            Player player_1 = opponent.getMap().game.getPlayer_1();
            Player player_2 = opponent.getMap().game.getPlayer_2();
            if (player_2 == null) {player_2 = player_1;}
            Player player;
            int distance_to_player_1 = (int) (Math.abs(player_1.getPosition()[0] - opponent.getPosition()[0]) + Math.abs(player_1.getPosition()[1] - opponent.getPosition()[1]));
            int distance_to_player_2 = (int) (Math.abs(player_2.getPosition()[0] - opponent.getPosition()[0]) + Math.abs(player_2.getPosition()[1] - opponent.getPosition()[1]));
            // sélection du joueur le plus proche
            if (distance_to_player_1 <= distance_to_player_2) {
                player = player_1;
            }
            else {
                player = player_2;
            }
            // comparaison des position du joueur choisis et du PNJ
            float player_posx = (int) player.getPosition()[0];
            float player_posy = (int) player.getPosition()[1];
            float npc_posx = (int) opponent.getPosition()[0];
            float npc_posy = (int) opponent.getPosition()[1];
            // si joueur suffisament proche, le PNJ doit agir
            int number_of_chunks_to_act = 10;
            if (Math.abs(npc_posx - player_posx) < number_of_chunks_to_act && Math.abs(npc_posy - player_posy) < number_of_chunks_to_act) {
                need_to_act = true; // le joueur est près du PNJ, celui doit faire quelque chose!
            }
            if (need_to_act) {
                // check si le PNJ est un "attacker" ou un "coward"
                if (opponent.getAttacker()) {
                    opponent.go_to_player(player);  // le PNJ fonce vers le joueur
                } else if (opponent.getCoward()) {
                    opponent.escape_player(player);  // le PNJ est coward et cherche à s'éloigner du joueur
                }
            }
            opponent.move();
        }
    }
}

