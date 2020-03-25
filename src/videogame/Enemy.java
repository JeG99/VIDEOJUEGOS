package videogame;

import java.awt.Graphics;

/**
 * Enemy
 *
 * Clase que representa a un enemigo en el juego
 *
 * @author Andrés Alam Sánchez Torres
 */
public class Enemy extends Item {

    private Game game;
    private Animation animationLeft;
    private Animation currentAnimation;

    /**
     * Enemy
     *
     * Constructor de la clase
     *
     * @param width es el ancho del enemigo
     * @param height es el alto del enemigo
     * @param game es la referencia al juego
     */
    public Enemy(int width, int height, Game game) {
        super(0, 0, width, height);
        this.game = game;
        this.animationLeft = new Animation(Assets.enemyLeft, 100);
        this.currentAnimation = animationLeft; // for future use
        setNewPosition();
    }

    /**
     * setNewPosition
     *
     * Da una posición inicial
     */
    public void setNewPosition() {
        setX(game.getWidth() + (int) (Math.random() * game.getWidth()));
        setY((int) (Math.random() * game.getHeight()));
    }

    @Override
    public void tick() {
        int moveDistance = (int) (Math.random() * 3) + 3;
        setX(getX() - moveDistance);
        currentAnimation = animationLeft;
        currentAnimation.tick();

        if (getX() < -getWidth()) {
            setNewPosition();

        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
