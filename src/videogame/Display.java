package videogame;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;


/**
 * Display
 * 
 * Clase para crear una ventana gráfica
 * 
 * @author Alam Sánchez
 */
public class Display {
    private JFrame jframe;  
    private Canvas canvas;
    private String title;
    private int width;
    private int height;
    
    /**
     * Display
     * 
     * Constructor del objeto Display
     * @param title es el título de la ventana
     * @param width es el ancho de la ventana
     * @param height es el alto de la ventana
     */
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }
    
    /**
     * getJframe
     * 
     * Método para obtener el objeto jframe
     * 
     * @return  jframe
     */
    public JFrame getJframe() {
        return jframe;
    }
    
    /**
     * createDisplay
     * 
     * Método para generar la ventana
     */
    private void createDisplay() {
        // crea ventana de aplicación
        jframe = new JFrame(title);
        
        // asigna el tamaño de la ventana
        jframe.setSize(width, height);
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        
        // crea el canvas para pintar y sus configuraciones
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        
        // agrega el canvas a la ventana y lo 'enpaqueta'
        // para obtener las dimensiones adecuadas
        jframe.add(canvas);
        jframe.pack();
    }
    
    /**
     * getCanvas
     * 
     * Método para obtener el canvas de la ventana
     * 
     * @return canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
    
    
}