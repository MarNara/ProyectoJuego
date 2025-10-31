package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx; 
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private float x, y;
    private float speed;
    private float angle;
    private Texture texture;
    private boolean destroyed = false;
    
    //RAFA AGREGO AQUI
    private boolean fromPlayer;
    
    // 🔹 USAR VARIABLES DIRECTAS, no métodos
    private float anchoVisual;
    private float altoVisual;

    // En Bullet constructor - forzar tamaño VISIBLE:
    public Bullet(float x, float y, float angle, Texture texture, boolean fromPlayer) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.texture = texture;
        this.fromPlayer = fromPlayer;
        
        if (angle == -90f) { // Balas alien
            this.speed = 200f;
            this.anchoVisual = 20f; //el tamaño de la textura
            this.altoVisual = 20f;
        } else { // Balas nave
            this.speed = 400f;
            this.anchoVisual = 8f;
            this.altoVisual = 16f;
        }
    }
    
    public void update() {
        if (destroyed) {
            return;
        }
        
        float rad = angle * MathUtils.degreesToRadians;
        float deltaTime = Gdx.graphics.getDeltaTime();
        
        float moveX = MathUtils.cos(rad) * speed * deltaTime;
        float moveY = MathUtils.sin(rad) * speed * deltaTime;
        
        x += moveX;
        y += moveY;

        float margen = 100f; 
        float pantallaAncho = Gdx.graphics.getWidth();
        float pantallaAlto = Gdx.graphics.getHeight();

        if (x < -margen || x > pantallaAncho + margen ||
            y < -margen || y > pantallaAlto + margen) {
            
            destroyed = true;
        }
    }
    
    public void draw(SpriteBatch batch) {
        if (destroyed) {
            return;
        }
        if (texture == null) {
            return;
        }
        float pantallaAncho = Gdx.graphics.getWidth();
        float pantallaAlto = Gdx.graphics.getHeight();
        
        boolean enPantalla = (x >= -anchoVisual && x <= pantallaAncho + anchoVisual &&
                             y >= -altoVisual && y <= pantallaAlto + altoVisual);
        
        if (!enPantalla) {
            return;
        }
    
        if (destroyed || texture == null) return;
        batch.draw(texture, x, y, anchoVisual, altoVisual);
    }
    
    public boolean checkCollision(Entidad target) {
        if (destroyed) return false;

        Rectangle r1 = new Rectangle(x, y, anchoVisual, altoVisual);
        boolean colisiona = r1.overlaps(target.getArea());

        if (!colisiona) {
            return false;
        }

        // Si la bala viene del jugador, debe dañar a hostiles (Enemigo/Hostil/Destructible)
        if (fromPlayer) {
            if (target instanceof Destructible && target instanceof Hostil) {
                ((Destructible)target).recibirDanio(1);
                this.destroyed = true;
                return true;
            } else {
                // Si golpea otra cosa que no se debe dañar: no hacemos nada
                return false;
            }
        } else { // bala enemiga -> daña a la nave del jugador
            if (target instanceof Nave_Personaje) {
                Destructible nave = (Destructible) target;
                nave.recibirDanio(1);
                this.destroyed = true;
                return true;
            } else {
                return false;
            }
        }
    }
    
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    
    public Texture getTexture() {
        return texture;
    }
    
    public float getAngle() {
        return angle;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    
    public void destroy() {
        this.destroyed = true;
    }
    
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSpeed() { return speed; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    
    public float getAnchoVisual() { return anchoVisual; }
    public float getAltoVisual() { return altoVisual; }
}
