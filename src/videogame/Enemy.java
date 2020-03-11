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

    public Enemy(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        // following the player
        if (this.getX() > game.getPlayer().getX()) {
            this.setX(this.getX() - 1);
        } else {
            this.setX(this.getX() + 1);
        }
        if (this.getY() > game.getPlayer().getY()) {
            this.setY(this.getY() - 1);
        } else {
            this.setY(this.getY() + 1);
        }

        // reset x position and y position if colision with borders
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
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

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
