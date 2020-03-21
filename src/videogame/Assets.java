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

    public static BufferedImage background; // to store background image
    public static BufferedImage finalScreen;
    public static BufferedImage player;     // to store the player image
    public static BufferedImage enemy;
    public static BufferedImage friend;
    public static SoundClip backSound;
    public static SoundClip gunShot;
    public static SoundClip score;

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background.jpg");
        finalScreen = ImageLoader.loadImage("/images/finalscreen.jpg");
        player = ImageLoader.loadImage("/images/mario.png");
        enemy = ImageLoader.loadImage("/images/enemy.png");
        friend = ImageLoader.loadImage("/images/friend.png");
        backSound = new SoundClip("/sounds/back.wav");
        gunShot = new SoundClip("/sounds/Gunshot.wav");
        score = new SoundClip("/sounds/score.wav");
    }

}
