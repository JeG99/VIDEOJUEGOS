/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 *
 * @author Elías Garza
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
    private LinkedList<Enemy> enemies;  // to store the enemies
    private LinkedList<Friend> friends;  // to store the friends
    private SoundClip soundClip;

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
        running = false;
        keyManager = new KeyManager();
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
        enemies = new LinkedList<Enemy>();
        int rand = (int) (Math.random() * (10 - 8)) + 8;
        for (int i = 1; i <= rand; i++) {
            Enemy enemy = new Enemy(getWidth() + 200, ((int) (Math.random() * ((getHeight() - 100) - 0)) + 0), 1, 100, 100, this);
            enemies.add(enemy);
        }
        friends = new LinkedList<Friend>();
        rand = (int) (Math.random() * (15 - 10)) + 10;
        for (int i = 1; i <= rand; i++) {
            Friend friend = new Friend(-200, ((int) (Math.random() * ((getHeight() - 100) - 0)) + 0), 1, 100, 100, this);
            friends.add(friend);
        }
        player = new Player(getWidth() / 2 - 50, getHeight() / 2 - 50, 1, 100, 100, this, ((int) (Math.random() * (5 - 3)) + 3), 0, 5);
        display.getJframe().addKeyListener(keyManager);
        //Assets.backSound.setLooping(true);
        //Assets.backSound.play();
    }

    /**
     * To run an instance of the game
     */
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
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    /**
     * Returns keyManager
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * Death sound
     */
    public void beep() {
        Assets.gunShot.play();
    }

    /**
     * Friend sound
     */
    public void boop() {
        Assets.score.play();
    }

    /**
     * actualize everyone's positions
     */
    private void tick() {
        keyManager.tick();
        for (Enemy enemy : enemies) {
            enemy.tick();
            if (player.collision(enemy)) {
                enemy.setX(getWidth() + ((int) (Math.random() * (500 - 200)) + 200));
                enemy.setY(((int) (Math.random() * ((getHeight() - 100) - 0)) + 0));
                player.setLifeCounter(player.getLifeCounter() - 1);
                if (player.getLifeCounter() <= 0) {
                    player.setLifes(player.getLifes() - 1);
                    player.setLifeCounter(5);
                    if (player.getLifes() <= 0) {
                        beep();
                        //Assets.backSound.stop();
                        renderFinalScreen(g);
                        stop();
                    }
                }
            }
        }
        for (Friend friend : friends) {
            friend.tick();
            if (player.collision(friend)) {
                boop();
                friend.setX(-((int) (Math.random() * (60 - 30)) + 30));
                friend.setY(((int) (Math.random() * ((getHeight() - 100) - 0)) + 0));
                player.setScore(player.getScore() + 5);
            }
        }
        // avancing player with colision
        player.tick();
    }

    private void renderFinalScreen(Graphics g) {
        g.drawImage(Assets.finalScreen, 0, 0, width, height, null);
    }

    public void write(Graphics g) {

        int fontSize = 80;
        //g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g.setColor(Color.red);
        g.drawString("Score: " + Integer.toString(player.getScore()), 10, 20);
        g.drawString("Lifes: " + Integer.toString(player.getLifes()), 200, 20);
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
        } else {

            g = bs.getDrawGraphics();
            g.drawImage(Assets.background, 0, 0, width, height, null);

            for (Enemy enemy : enemies) {
                enemy.render(g);
            }
            for (Friend friend : friends) {
                friend.render(g);
            }
            player.render(g);

            write(g);
            bs.show();
            g.dispose();
        }

    }

    /**
     * setting the thread for the game
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
