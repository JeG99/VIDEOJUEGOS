/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author JeG99
 */
public class Friend extends Item {

    private int direction;
    private Game game;

    /**
     * Friend constructor
     *
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game
     */
    public Friend(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
    }

    /**
     * To update friend's position
     */
    @Override
    public void tick() {
        // comming from the right side of the window
        setX(getX() + ((int) (Math.random() * (3 - 1)) + 1));
        // reset x position and y position if colision with borders
        if (getX() <= -30) {
            setX(-((int) (Math.random() * (60 - 30)) + 30));
        }

        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }

    }

    /**
     * to draw friend
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.friend, getX(), getY(), getWidth(), getHeight(), null);
    }
}
