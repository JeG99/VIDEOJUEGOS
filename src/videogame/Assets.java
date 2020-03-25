package videogame;

import java.awt.image.BufferedImage;

/**
 * Assets
 * 
 * Clase para administrar y cargar recursos de multimedia.
 * 
 * @author Alam Sánchez
 */
public class Assets {

    public static BufferedImage background;     // imagen de fondo
    public static BufferedImage gameOverMsg;    // imagen de game over
    public static BufferedImage player;         // imagen del jugador
    public static BufferedImage enemy;          // imagen del enemigo
    public static BufferedImage ally;           // imagen del aliado
    public static SoundClip hit;                // sonido de colisión con enemigo
    public static SoundClip score;              // sonido de colisión con aliado
    public static SoundClip gameOverSound;      // sonido de game over

    /**
     * init
     * 
     * Método para cargar los recursos
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        gameOverMsg = ImageLoader.loadImage("/images/game_over.jpg");
        player = ImageLoader.loadImage("/images/player.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        ally = ImageLoader.loadImage("/images/ally.png");
        hit = new SoundClip("/sounds/hit.wav");
        score = new SoundClip("/sounds/score.wav");
        gameOverSound = new SoundClip("/sounds/gameOver.wav");
    }

}
