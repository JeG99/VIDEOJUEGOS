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
public class Player extends Item {

    private int direction;
    private Game game;
    private int lifes;
    private int score;
    private int lifeCounter;

    public Player(int x, int y, int direction, int width, int height, Game game, int lifes, int score, int lifeCounter) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.lifes = lifes;
        this.score = score;
        this.lifeCounter = lifeCounter;
    }

    public int getLifes() {
        return lifes;
    }

    public int getScore() {
        return score;
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLifeCounter(int lifeCounter) {
        this.lifeCounter = lifeCounter;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().q) {
            setY(getY() - 1);
            setX(getX() - 1);
        }
        if (game.getKeyManager().p) {
            setY(getY() - 1);
            setX(getX() + 1);
        }
        if (game.getKeyManager().a) {
            setY(getY() + 1);
            setX(getX() - 1);
        }
        if (game.getKeyManager().l) {
            setY(getY() + 1);
            setX(getX() + 1);
        }
        // reset x position and y position if colision
        if (getX() + 60 >= game.getWidth()) {
            setX(game.getWidth() - 60);
        } else if (getX() <= -30) {
            setX(-30);
        }
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
        } else if (getY() <= -20) {
            setY(-20);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
