/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {

    public static BufferedImage background;    // to store background image
    public static BufferedImage player;        // to store the player image
    public static BufferedImage enemy;         // to store the player image
    public static BufferedImage sprites;       // to store the sprites
    public static BufferedImage playerUp[];    // pictures to go up
    public static BufferedImage playerLeft[];  // pictures to go left
    public static BufferedImage playerRight[]; // pictures to go right
    public static BufferedImage playerDown[];  // pictures to go down

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/Background.jpg");
        player = ImageLoader.loadImage("/images/Player.png");
        enemy = ImageLoader.loadImage("/images/Enemy.png");

        sprites = ImageLoader.loadImage("/images/SpriteSheet.png");
        SpriteSheet spritesheet = new SpriteSheet(sprites);

        playerUp = new BufferedImage[9];
        playerDown = new BufferedImage[9];
        playerRight = new BufferedImage[9];
        playerLeft = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {
            playerUp[i] = spritesheet.crop(i * 64, 0, 64, 64);
            playerLeft[i] = spritesheet.crop(i * 64, 64, 64, 64);
            playerDown[i] = spritesheet.crop(i * 64, 128, 64, 64);
            playerRight[i] = spritesheet.crop(i * 64, 192, 64, 64);
        }
    }

}
