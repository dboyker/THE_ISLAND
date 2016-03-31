package controller;
import java.io.*;
import java.util.Optional;

/**
 * Created by davidboyker on 31/03/16.
 */
public class SerialManager {

    view.Frame frame;
    public SerialManager(view.Frame frame) {
        this.frame = frame;
    }

    public void save_game(Game game) {
    try
    {
        FileOutputStream fileOut = new FileOutputStream("SavedGames.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(game);
        out.close();
        fileOut.close();
    }
    catch(IOException i) {i.printStackTrace();}
    }

    public Game load_game() {
        Game game;

        try {
            FileInputStream fileIn = new FileInputStream("SavedGames.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException i) {i.printStackTrace(); game = new Game(frame);}
        catch(ClassNotFoundException c) {c.printStackTrace();game = new Game(frame);}
        return game;
    }

}
