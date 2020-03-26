/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author antoniomejorado
 */
public class KeyManager implements KeyListener {

    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean pause;   // flag to pause game
    public boolean save;    // flag to save the game

    private boolean pressedKeys[];  // to store all the flags for every key
    private boolean releasedKeys[];  // to store all the flags for every released key

    public KeyManager() {
        pressedKeys = new boolean[256];
        releasedKeys = new boolean[256];
    }

    public void clear() {
        for (int i = 0; i < 256; i++) {
            releasedKeys[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        pressedKeys[e.getKeyCode()] = false;
        releasedKeys[e.getKeyCode()] = true;
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = pressedKeys[KeyEvent.VK_UP];
        down = pressedKeys[KeyEvent.VK_DOWN];
        left = pressedKeys[KeyEvent.VK_LEFT];
        right = pressedKeys[KeyEvent.VK_RIGHT];
        pause = releasedKeys[KeyEvent.VK_P];
        save = releasedKeys[KeyEvent.VK_G];
        clear();
    }
}
