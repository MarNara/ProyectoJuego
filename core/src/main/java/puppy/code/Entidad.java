package puppy.code;
//
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entidad {
    //ATRIBUTOS PRIVADOS ENCAPSULAMIENTO
    private float x;        
    private float y;        
    private float ancho;    
    private float alto;     
    private boolean activa; 
   

    // Constructor
    public Entidad(float x, float y, float ancho, float alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.activa = true;
    }

    //MÉTODOS PÚBLICOS (interfaz controlada)
    
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
    
    //MÉTODOS ABSTRACTOS
    public abstract void actualizar(float delta);
    public abstract void dibujar(SpriteBatch batch);
}
