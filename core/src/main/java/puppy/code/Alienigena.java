package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
/*El alienigena le genera daño a la nave*/
public class Alienigena extends Enemigo implements Disparos {
    
    private float velocidadX = 100f;
    private float tiempoEntreDisparos = 3.0f;
    private float tiempoUltimoDisparo = 0f;
    private float direccion = 1f;
    private Sound sonidoDisparo;

    public Alienigena(float x, float y, float ancho, float alto, int vidaInicial, Sound sonidoDisparo) {
        super(x, y, ancho, alto, vidaInicial, new AlienMovimiento());
        this.sonidoDisparo = sonidoDisparo;
    }
    
    public void setVelocidad(float velocidad) {
        this.velocidadX = velocidad;
    }
    
    public void setFrecuenciaDisparos(float frecuencia) {
        this.tiempoEntreDisparos = frecuencia;
    }

    @Override
    public void actualizar(float delta) {
        // 1. Ejecuta el movimiento (a través de Entidad -> AlienMovement)
        super.actualizar(delta); 
        
        // 2. Mantiene la lógica de tiempo/disparo (que NO es movimiento)
        tiempoUltimoDisparo += delta;
    }

    @Override
    public void disparar(ArrayList<Bullet> balas) {
        
        if (tiempoUltimoDisparo >= tiempoEntreDisparos && estaActiva() && getVida() > 0) {
            // Usar getters para posición y dimensiones
            float centroX = getX() + getAncho() / 2f;
            float baseY = getY() + 5f;
            float anguloDisparo = -90f;
            
            balas.add(new Bullet(centroX, baseY, anguloDisparo, null, false));
            
            if (sonidoDisparo != null) {
                sonidoDisparo.play(0.3f); 
            }
            
            tiempoUltimoDisparo = 0f;
        }     }
    
    @Override
    public void dibujar(SpriteBatch batch) {
        // Vacío - renderizado por PantallaJuego
    }
    
    //GETTERS para debug
    public float getDireccion() { return direccion; }//getters para el campo direccion
    public void setDireccion(float direccion) { this.direccion = direccion; }//setters para el campo direccion
    public float getTiempoUltimoDisparo() { return tiempoUltimoDisparo; }
    public float getTiempoEntreDisparos() { return tiempoEntreDisparos; }
    public float getVelocidadX() { return velocidadX; }
}
