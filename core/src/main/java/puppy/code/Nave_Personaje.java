package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

// La nave hereda de Entidad e IMPLEMENTA Destructible y Disparos
public class Nave_Personaje extends Entidad implements Disparos, Destructible {

    private boolean destruida = false;
    private int vidas = 3;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    private float rotacion = 0f;
    private float velocidadRotacion = 200f;
    private float velocidadMovimiento = 200f;

    private Sprite spr;
    private Sound sonidoHerido;
    private Sound sonidoDisparo;
    private Texture txBala;

    public Nave_Personaje(float x, float y, Texture textura, Texture txBala, Sound sonidoDisparo, Sound sonidoHerido) {
        super(x, y, textura.getWidth(), textura.getHeight());
        this.spr = new Sprite(textura);
        this.spr.setPosition(x, y);
        this.spr.setOriginCenter();
        this.txBala = txBala;
        this.sonidoDisparo = sonidoDisparo;
        this.sonidoHerido = sonidoHerido;
    }

    @Override
    public void actualizar(float delta) {
        // Lógica de movimiento y tiempoHerido
        if (!herido) {
            // Lógica de movimiento y rotación
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) rotacion += velocidadRotacion * delta;
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) rotacion -= velocidadRotacion * delta;

            // Normalizar rotación y movimiento
            if (rotacion > 360) rotacion -= 360;
            if (rotacion < -360) rotacion += 360;
            spr.setRotation(rotacion);

            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                float rad = (rotacion + 90f) * MathUtils.degreesToRadians;
                float dx = MathUtils.cos(rad) * velocidadMovimiento * delta;
                float dy = MathUtils.sin(rad) * velocidadMovimiento * delta;
                spr.translate(dx, dy);
            }

            // Limitar dentro de pantalla
            spr.setX(Math.max(0, Math.min(spr.getX(), Gdx.graphics.getWidth() - spr.getWidth())));
            spr.setY(Math.max(0, Math.min(spr.getY(), Gdx.graphics.getHeight() - spr.getHeight())));
        } else {
            // Lógica de temblor al ser herido
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        spr.draw(batch);
    }

    @Override
    public void disparar(ArrayList<Bullet> balas) {
        // Lógica de disparo (se mantiene igual)
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            float anguloDisparo = rotacion + 90f;
            float rad = anguloDisparo * MathUtils.degreesToRadians;
            float puntaX = spr.getX() + spr.getWidth() / 2f + MathUtils.cos(rad) * spr.getHeight() / 2f;
            float puntaY = spr.getY() + spr.getHeight() / 2f + MathUtils.sin(rad) * spr.getHeight() / 2f;

            balas.add(new Bullet(puntaX, puntaY, anguloDisparo, txBala, true));
            sonidoDisparo.play();
        }
    }
    

    //IMPLEMENTACIÓN DE INTERFAZ DESTRUCTIBLE 
    @Override
    public int getVida() { 
        return vidas; 
    }

    @Override
    public void recibirDanio(int d) {
        if (!herido) {
            vidas -= d;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            if (vidas <= 0) destruida = true;
        }
    }
    
    @Override
    public boolean estaDestruido() { 
        return destruida && !herido; 
    }
    
    /*
    LÓGICA DE COLISIÓN (Simplificada)
    el metodo checkCollision ahora solo verifica la colisión geométrica
    y delega la aplicación de daño al método recibirDanio (que es parte de Destructible).
    esto es polimorfismo.*/


    public boolean checkCollision(Entidad enemigo) {
        if (!herido && spr.getBoundingRectangle().overlaps(enemigo.getArea())) {
            
            //  La nave recibe daño: La bala de alien/enemigo llama a este método,
            // y la nave USA SU PROPIA LÓGICA de ser herida.
            this.recibirDanio(1); 
            
            // Si el enemigo también es Destructible se destruye al chocar
            if (enemigo instanceof Destructible) {
                ((Destructible)enemigo).recibirDanio(9999); // Destrucción instantánea por choque
            } else {
                enemigo.desactivar();
            }
            return true;
        }
        return false;
    }

    //Getters y setters (se mantienen y se alinean con Destructible) ---
    //restos de getters y setters
    public int getVidas() { return vidas; }
    public void setVidas(int vidas) { this.vidas = vidas; }
    public float getX() { return spr.getX(); }
    public float getY() { return spr.getY(); }
}
