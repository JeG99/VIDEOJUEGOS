package videogame;

import java.awt.Graphics;

/**
 *
 * @author Andrés ALam Sánchez Torres
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private int life;
    private int score;
    private int hitCounter;
    private Game game;
    
    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        life = (int) (Math.random() * 3) + 3;
        hitCounter = 0;
    }

    public int getDirection() {
        return direction;
    }

    public int getLife() {
        return life;
    }

    public int getScore() {
        return score;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hit() {
        hitCounter++;
        if (hitCounter > 5) {
            hitCounter = 0;
            setLife(getLife() - 1);
            if (getLife() <= 0) {
                game.gameOver();
            }
        }
    }

    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().qPressed) {
           setY(getY() - 1);
           setX(getX() - 1);
        //    game.beep();
        }
        else if (game.getKeyManager().pPressed) {
            setY(getY() - 1);
            setX(getX() + 1);
        //    game.beep();
        }
        else if (game.getKeyManager().aPressed) {
            setY(getY() + 1);
            setX(getX() - 1);
        //    game.beep();
        }
        else if (game.getKeyManager().lPressed) {
            setY(getY() + 1);
            setX(getX() + 1);
        //    game.beep();
        }
        // reset x position and y position if colision
        if (getX() + getWidth() >= game.getWidth()) {
            setX(game.getWidth() - getWidth());
        }
        else if (getX() <= 0) {
            setX(0);
        }
        if (getY() + getHeight() >= game.getHeight()) {
            setY(game.getHeight() - getHeight());
        }
        else if (getY() <= 0) {
            setY(0);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
