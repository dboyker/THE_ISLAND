// Ce fichier contient le code nécessaire pour gérer les sauvegardes/chargements

package model;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by davidboyker on 31/03/16.
 */
public class SerialManager {

    public SerialManager() {}

    public void save_game(Game game) {  // fonction pour la sauvegarde d'une partie
        try
        {
            System.out.println("save game");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            String name = dateFormat.format(date);
            // écriture d'une fichier .ser dans le dossier /saved_games
            String path = "src/saved_games/"+name+".ser";
            File f = new File(path);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileOutputStream fileOut = new FileOutputStream("src/saved_games/"+name+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        }
        catch(IOException i) {i.printStackTrace();}
    }


    public Game load_game(String name) {  // fonction pour le chargement d'une partie sauvegardé
        System.out.println("load game");
        Game game;

        try {  // lecture d'un fichier .ser, désérializaion et re-création de l'objet Game
            FileInputStream fileIn = new FileInputStream("src/saved_games/"+name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException i) {System.out.println("something went wrong with loading");i.printStackTrace(); game = new Game();}
        catch(ClassNotFoundException c) {System.out.println("something went wrong with loading");c.printStackTrace();game = new Game();}
        return game;
    }

}
