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
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationLeft;
    private Animation animationRight;
    private Animation currentAnimation;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.currentAnimation = animationRight;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {

        // moving player depending on keyboard flags
        if (game.getKeyManager().up) {
            currentAnimation = animationUp;
            setY(getY() - 1);
        }
        if (game.getKeyManager().down) {
            currentAnimation = animationDown;
            setY(getY() + 1);
        }
        if (game.getKeyManager().left) {
            currentAnimation = animationLeft;
            setX(getX() - 1);
        }
        if (game.getKeyManager().right) {
            currentAnimation = animationRight;
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

        // moving player depending on mouse flags
        if (game.getMouseManager().isIzquierdo()) {
            setX(game.getMouseManager().getX());
            setY(game.getMouseManager().getY());
            game.getMouseManager().setIzquierdo(false);
        }

        currentAnimation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
