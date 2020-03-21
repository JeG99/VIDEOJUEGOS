/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author antoniomejorado
 */
public class Enemy extends Item {

    private int direction;
    private Game game;

    /**
     * Enemy constructor
     *
     * @param x
     * @param y
     * @param direction
     * @param width
     * @param height
     * @param game
     */
    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
    }

    /**
     * To update enemy's position
     */
    @Override
    public void tick() {
        // comming from the right side of the window
        setX(getX() - ((int) (Math.random() * (5 - 3)) + 3));
        // reset x position and y position if colision with borders
        if (getX() <= -30) {
            setX(game.getWidth() + ((int) (Math.random() * (500 - 200)) + 200));
        }

        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        }

        // enemies must come from above the upper border
        /*
        else if (getY() <= -20) {
            setY(-20);
        }
         */
    }

    /**
     * To draw the enemy
     *
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
