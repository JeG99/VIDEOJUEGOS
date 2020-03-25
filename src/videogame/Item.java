package videogame;

import java.awt.Graphics;

import java.awt.Rectangle;

/**
 * Item
 * 
 * Clase para representar cualquier objeto
 * con posición dentro del juego
 * 
 * @author Andrés Alam Sánchez Torres
 */
public abstract class Item {
    protected int x;        // posición en x
    protected int y;        // posición en y
    protected int width;    // ancho del item
    protected int height;   // alto del item
    
    /**
     * Item
     * 
     * Constructor del objeto Item
     * 
     * @param x es la posición en x
     * @param y es la posición en y 
     * @param x es el ancho del objeto
     * @param y es el alto del objeto 
     */
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * getX
     * 
     * Método para obtener la posición en x
     * 
     * @return x es la posición en x
     */
    public int getX() {
        return x;
    }

    /**
     * getY
     * 
     * Método para obtener la posición en y
     * 
     * @return y es la posición en y
     */
    public int getY() {
        return y;
    }

    /**
     * getWidth
     * 
     * Método para obtener el acnho
     * 
     * @return width es el ancho
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * getHeight
     * 
     * Método para obtener la altura
     * 
     * @return height es la altura
     */
    public int getHeight() {
        return height;
    }

    /**
     * setX
     * 
     * Método para modificar la posición en x
     * 
     * @param x es la nueva posición en x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setY
     * 
     * Método para modificar la posición en y
     * 
     * @param y es la nueva posición en y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * setWidth
     * 
     * Método para modificar el ancho
     * 
     * @param width es el nuevo ancho
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * setHeight
     * 
     * Método para modificar el alto
     * 
     * @param height es la nueva altura
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * collision
     * 
     * Método para saber si el objeto esta colisionando con
     * otro objeto o
     * 
     * @param o es el objeto con el que se va a revisar la colisión
     * @return status es el indicador de si sucede la colisión
     */
    public boolean collision(Object o) {
        boolean status = false;
        if (o instanceof Item) {
            Rectangle rItem = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            Item i = (Item) o;
            Rectangle rOther = new Rectangle(i.getX(), i.getY(), i.getWidth(), i.getHeight());
            status = rItem.intersects(rOther);
        }
        return status;
    }

    /**
     * tick
     * 
     * Método para actualizar el item
     */
    public abstract void tick();
    
    /**
     * render
     * 
     * Método para renderizar el objeto en pantalla
     * 
     * @param g es el objeto de gráficos que se usará
     *          para dibujar el item
     */
    public abstract void render(Graphics g);
}
