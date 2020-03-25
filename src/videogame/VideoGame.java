package videogame;

/**
 * VideoGame
 * 
 * Clase para ejecutar un juego
 * 
 * @author Alam Sánchez
 */
public class VideoGame {

    /**
     * Inicializa el juego
     * 
     * @param args argumentos de línea de comando
     */
    public static void main(String[] args) {
        Game g = new Game("Juego", 800, 500);
        g.start();
    }
    
}
