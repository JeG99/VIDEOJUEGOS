/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 * Game
 *
 * Clase que representa el juego
 *
 * @author Andrés Alam Sánchez Torres
 *
 */
public class Game implements Runnable {

    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private Player player;          // to use a player
    private KeyManager keyManager;  // to manage the keyboard
    private boolean gameOver;
    private boolean paused;
    private ReadAndWrite dataSaveManager; // to manage data saves

    private LinkedList<Enemy> enemies;
    private LinkedList<Ally> allies;

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        gameOver = false;
        paused = false;
        running = false;
        keyManager = new KeyManager();
        enemies = new LinkedList<Enemy>();
        allies = new LinkedList<Ally>();
    }

    public Player getPlayer() {
        return player;
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public LinkedList<Ally> getAllies() {
        return allies;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2, getHeight() / 2, 1, 64, 64, this);

        int enemyCount = (int) (Math.random() * 3) + 8;
        for (int i = 0; i < enemyCount; i++) {
            enemies.add(new Enemy(64, 64, this));
        }

        int allyCount = (int) (Math.random() * 6) + 10;
        for (int i = 0; i < allyCount; i++) {
            allies.add(new Ally(64, 64, this));
        }

        display.getJframe().addKeyListener(keyManager);
        //  Assets.backSound.setLooping(true);
        //  Assets.backSound.play();
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                if (!gameOver) {
                    tick();
                }
                render();
                delta--;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public void beep(SoundClip sound) {
        sound.play();
    }

    private void tick() {
        keyManager.tick();
        if (keyManager.pause) {
            paused = !paused;
        }
        if (!paused) {
            // avancing player with colision
            player.tick();

            for (Enemy enemy : enemies) {
                enemy.tick();
                if (player.collision(enemy)) {
                    player.hit();
                    enemy.setX(getWidth() + (int) (Math.random() * getWidth()));
                    enemy.setY((int) (Math.random() * getHeight()));
                    beep(Assets.hit);
                }
            }
            for (Ally ally : allies) {
                ally.tick();
                if (player.collision(ally)) {
                    player.setScore(player.getScore() + 5);
                    ally.setX(-(getWidth() + (int) (Math.random() * getWidth())));
                    ally.setY((int) (Math.random() * getHeight()));
                    beep(Assets.score);
                }
            }
            if (keyManager.save) {
                dataSaveManager.Save(this, "src/savedata/SAVE.txt");
            }
        }
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the
        buffer strategy element.
        show the graphic and dispose it to the trash system
         */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else if (!gameOver) {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);

            for (Enemy enemy : enemies) {
                enemy.render(g);
            }

            for (Ally ally : allies) {
                ally.render(g);
            }

            g.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
            g.setColor(Color.WHITE);

            g.drawString("LIFE: " + String.valueOf(player.getLife()), 50, 50);
            g.drawString("SCORE: " + String.valueOf(player.getScore()), 50, 100);

            bs.show();
            g.dispose();
        } else {
            g = bs.getDrawGraphics();
            g.drawImage(Assets.gameOverMsg, 0, 0, width, height, null);

            bs.show();
            g.dispose();
        }

    }

    public void gameOver() {
        beep(Assets.gameOverSound);
        gameOver = true;
    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

}
