package videogame;

import java.awt.Graphics;

/**
 *
 * @author Andrés ALam Sánchez Torres
 */
public class Player extends Item {

    private int direction;
    private int width;
    private int height;
    private int life;
    private int score;
    private int hitCounter;
    private Game game;

    // for player to animate
    private Animation animationUp;
    private Animation animationDown;
    private Animation animationLeft;
    private Animation animationRight;
    private Animation currentAnimation;

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        life = (int) (Math.random() * 3) + 3;
        hitCounter = 0;

        // loading animations up
        this.animationUp = new Animation(Assets.playerUp, 100);
        this.animationDown = new Animation(Assets.playerDown, 100);
        this.animationLeft = new Animation(Assets.playerLeft, 100);
        this.animationRight = new Animation(Assets.playerRight, 100);
        this.currentAnimation = animationRight;
    }

    public int getDirection() {
        return direction;
    }

    public int getLife() {
        return life;
    }

    public int getHitCounter() {
        return hitCounter;
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

    public void setHitCounter(int hitCounter) {
        this.hitCounter = hitCounter;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hit() {
        hitCounter++;
        if (hitCounter >= 5) {
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
        if (getX() + getWidth() >= game.getWidth()) {
            setX(game.getWidth() - getWidth());
        } else if (getX() <= 0) {
            setX(0);
        }
        if (getY() + getHeight() >= game.getHeight()) {
            setY(game.getHeight() - getHeight());
        } else if (getY() <= 0) {
            setY(0);
        }

        currentAnimation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
    }
}
