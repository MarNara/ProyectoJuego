package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entidad {
    //ATRIBUTOS PRIVADOS ENCAPSULAMIENTO
    private float x;        
    private float y;        
    private float ancho;    
    private float alto;     
    private boolean activa; 
    //para el strategy
    @SuppressWarnings("rawtypes")
    private MovementStrategy movementStrategy;

    // Constructor
    @SuppressWarnings("rawtypes")
    public Entidad(float x, float y, float ancho, float alto, MovementStrategy strategy) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.activa = true;
        this.movementStrategy = strategy;
    }

    //MÉTODOS PÚBLICOS (interfaz controlada)
    // Setter para cambiar la estrategia dinámicamente
    @SuppressWarnings("rawtypes")
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
    public Rectangle getArea() {
        return new Rectangle(x, y, ancho, alto);
    }

    public boolean estaActiva() {
        return activa;
    }

    public void desactivar() {
        this.activa = false;
    }
    
    //GETTERS (acceso controlado)
    public float getX() { return x; }
    public float getY() { return y; }
    public float getAncho() { return ancho; }
    public float getAlto() { return alto; }
    
    //SETTERS (modificación controlada)
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setPosicion(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setDimensiones(float ancho, float alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public void setActiva(boolean activa) { this.activa = activa; }
    
    // MÉTODO ACTUALIZAR DELEGADO (Ya no es abstracto)
    @SuppressWarnings("unchecked") // Suprime el warning generado por la llamada raw type a move()
    public void actualizar(float delta) {
        if (movementStrategy != null) {
            // Delega la ejecución, pasando 'this' (la Entidad).
            // Java maneja la llamada al método move correcto a través de la interfaz.
            movementStrategy.move(this, delta); 
        }
    }
    //MÉTODOS ABSTRACTOS
    public abstract void dibujar(SpriteBatch batch);
}
