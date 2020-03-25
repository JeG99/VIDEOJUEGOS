package videogame;

import java.awt.Graphics;

/**
 *
 * @author Andrés ALam Sánchez Torres
 */
public class Ally extends Item {

    private Game game;
    private Animation animationRight;
    private Animation currentAnimation;

    /**
     * Ally
     *
     * Constructor de la clase
     *
     * @param width es el ancho del aliado
     * @param height es el alto del aliado
     * @param game es la referencia al objeto del juego
     */
    public Ally(int width, int height, Game game) {
        super(0, 0, width, height);
        this.game = game;
        this.animationRight = new Animation(Assets.allyRight, 100);
        this.currentAnimation = animationRight; // for future use
        setNewPosition();
    }

    /**
     * setNewPosition
     *
     * Da una posición inicial
     */
    public void setNewPosition() {
        setX(-((int) (Math.random() * game.getWidth() / 2)));
        setY((int) (Math.random() * game.getHeight()));
    }

    @Override
    public void tick() {
        int moveDistance = (int) (Math.random() * 3) + 1;
        setX(getX() + moveDistance);
        currentAnimation = animationRight;
        currentAnimation.tick();

        if (getX() + getWidth() > game.getWidth()) {
            setNewPosition();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(currentAnimation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        //g.drawImage(Assets.ally, getX(), getY(), getWidth(), getHeight(), null);
    }
}
