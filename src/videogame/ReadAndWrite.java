/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author JeG99
 */
public class ReadAndWrite {

    private static Game game;

    public static void Save(Game game, String strFileName) {

        try {

            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));

            // player
            int life = game.getPlayer().getLife();
            int hitCounter = game.getPlayer().getHitCounter();
            int score = game.getPlayer().getScore();
            int x = game.getPlayer().getX();
            int y = game.getPlayer().getY();
            /**
             * Player data arrangement:
             *
             * life/hitCounter/score/xPos/yPos
             */
            writer.println("" + life + "/" + hitCounter + "/" + score + "/" + x + "/" + y);

            // enemies
            writer.println(game.getEnemies().size());
            for (Enemy enemy : game.getEnemies()) {
                /**
                 * Enemy data arrangement:
                 *
                 * xPos/yPos
                 */
                writer.println("" + enemy.getX() + "/" + enemy.getY());
            }

            // allies
            writer.println(game.getAllies().size());
            for (Ally ally : game.getAllies()) {
                /**
                 * Ally data arrangement:
                 *
                 * xPos/yPos
                 */
                writer.println("" + ally.getX() + "/" + ally.getY());
            }
            writer.close();

        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }

    }

    public void Load(String strFileName) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            System.out.println("Se leyo  vidas = " + vidas + " y score = " + score);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        // TODO code application logic here

        Save("savedata/SAVE.txt");
        System.out.println("HOLA");
        //Load("SAVE.txt");
    }
     */
}
