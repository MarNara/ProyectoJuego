package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
/*El alienigena le genera daño a la nave*/
public class Alienigena extends Enemigo<Alienigena> implements Disparable {
    
    private float velocidadX = 100f;
    private float tiempoEntreDisparos = 3.0f;
    private float tiempoUltimoDisparo = 0f;
    private float direccion = 1f;
    private Sound sonidoDisparo;
    private DisparableStrategy<Alienigena> DisparoStrategy;

    public Alienigena(float x, float y, float ancho, float alto,int vidaInicial,Sound sonidoDisparo) {
        super(x, y, ancho, alto, vidaInicial, new AlienMovimientoLineal());
        this.sonidoDisparo = sonidoDisparo;
    }
    
    @Override
    protected Alienigena getThis() {
        return this;
    }
    
    public void setVelocidad(float velocidad) {
        this.velocidadX = velocidad;
    }
    
    public void setFrecuenciaDisparos(float frecuencia) {
        this.tiempoEntreDisparos = frecuencia;
    }

    @Override
    public void actualizar(float delta) {
        super.actualizar(delta); // Aquí se ejecuta la Strategy
        tiempoUltimoDisparo += delta; // Lógica específica del alien
    }

    @Override
    public void disparar(ArrayList<Bullet> balas) {
        if (DisparoStrategy == null || !estaActiva()) return;

        tiempoUltimoDisparo += Gdx.graphics.getDeltaTime();

        if (tiempoUltimoDisparo >= tiempoEntreDisparos) {
        	DisparoStrategy.disparar(this, balas);
            tiempoUltimoDisparo = 0;
        }
    }
    
    @Override
    public void dibujar(SpriteBatch batch) {
        // Vacío porque es renderizado por PantallaJuego
    }
    
    //GETTERS para debug
    public float getDireccion() { return direccion; }//getters para el campo direccion
    public void setDireccion(float direccion) { this.direccion = direccion; }//setters para el campo direccion
    public void setDisparableStrategy(DisparableStrategy<Alienigena> s) {this.DisparoStrategy = s;} //setter para la estrategia de disparo
    public float getTiempoUltimoDisparo() { return tiempoUltimoDisparo; }
    public float getTiempoEntreDisparos() { return tiempoEntreDisparos; }
    public float getVelocidadX() { return velocidadX; }
}
