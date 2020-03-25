package videogame;

import java.awt.Graphics;

/**
 * Enemy
 * 
 * Clase que representa a un enemigo en el juego
 * @author Andrés Alam Sánchez Torres
 */
public class Enemy extends Item{
    private Game game;
    
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

        if (getX() < - getWidth()) {
            setNewPosition();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}
