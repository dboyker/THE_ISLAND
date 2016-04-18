package controller;

import model.Person.NPC.NPC;
import view.Frame;

/**
 * Created by davidboyker on 30/03/16.
 */
public class NPCThread implements Runnable {

    private NPC npc;
    private Frame frame;

    public NPCThread(NPC npc, Frame frame) {
        this.npc = npc;
        this.frame = frame;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            /*int random1 = (Math.random()<0.5)?0:1;
            int random2 = (Math.random()<0.5)?0:1;
            int random3 = (Math.random()<0.5)?0:1;
            float a;
            if (random3 == 1) {a = 1;}
            else {a = -1;}
            if (random1 == 1) {npc.setDx(a*random2);}
            else {npc.setDy(a*random2);}*/
            npc.move(frame);
        }
    }
}

