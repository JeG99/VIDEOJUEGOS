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

    // for animations loading
    public static BufferedImage playerSprites; // to store the player sprites
    public static BufferedImage enemySprites;  // to store the enemy sprites
    public static BufferedImage allySprites;   // to store the ally sprites

    // for player animations
    public static BufferedImage playerUp[];    // pictures to go up
    public static BufferedImage playerLeft[];  // pictures to go left
    public static BufferedImage playerRight[]; // pictures to go right
    public static BufferedImage playerDown[];  // pictures to go down

    //for enemy animations
    public static BufferedImage enemyLeft[];   // pictures to go left

    //for ally animations
    public static BufferedImage allyRight[];   // pictures to go right

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

        // loading player sprites
        playerSprites = ImageLoader.loadImage("/images/PlayerSS.png");
        Spritesheet playerSpritesheet = new Spritesheet(playerSprites);

        playerUp = new BufferedImage[9];
        playerDown = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        playerLeft = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {
            playerUp[i] = playerSpritesheet.crop(i * 64, 0, 64, 64);
            playerLeft[i] = playerSpritesheet.crop(i * 64, 64, 64, 64);
            playerDown[i] = playerSpritesheet.crop(i * 64, 128, 64, 64);
            playerRight[i] = playerSpritesheet.crop(i * 64, 192, 64, 64);
        }

        //loading enemy sprites
        enemySprites = ImageLoader.loadImage("/images/PlayerSS.png");
        Spritesheet enemySpritesheet = new Spritesheet(enemySprites);

        enemyLeft = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {
            enemyLeft[i] = enemySpritesheet.crop(i * 64, 64, 64, 64);
        }

        //loading enemy sprites
        allySprites = ImageLoader.loadImage("/images/PlayerSS.png");
        Spritesheet allySpritesheet = new Spritesheet(allySprites);

        allyRight = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {
            allyRight[i] = allySpritesheet.crop(i * 64, 192, 64, 64);
        }

    }

}
