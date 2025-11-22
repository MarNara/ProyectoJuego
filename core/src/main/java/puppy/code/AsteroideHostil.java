package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/*Está clase representa los asteroides que le hacen daño a la nave*/
public class AsteroideHostil extends Enemigo<AsteroideHostil> {

    private int xSpeed;
    private int ySpeed;
    //constructor de asteroide hostil
    public AsteroideHostil(float x, float y, float size, int vidaInicial,int xSpeed, int ySpeed) {
        super(x, y, size, size, vidaInicial,new ReboteMovimiento(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    
    @Override
    public void actualizar(float delta) {
        super.actualizar(delta); //se mueve usando ReboteMovimiento
    }

    @Override
    public void dibujar(SpriteBatch batch) {
    }

    //colisiones de asteroides(las rocas)
    public void checkCollision(AsteroideHostil otra) {
        if (!estaActiva() || !otra.estaActiva()) return;
        
        if (this.getArea().overlaps(otra.getArea())) {
            int tempX = this.xSpeed;
            int tempY = this.ySpeed;

            this.xSpeed = otra.xSpeed;
            this.ySpeed = otra.ySpeed;

            otra.xSpeed = tempX;
            otra.ySpeed = tempY;
        }
    }
    
    @Override
    protected AsteroideHostil getThis() {
        return this;
    }

    public int getXSpeed() { return xSpeed; }
    public int getYSpeed() { return ySpeed; }
    public void setXSpeed(int xSpeed) { this.xSpeed = xSpeed; }
    public void setYSpeed(int ySpeed) { this.ySpeed = ySpeed; }
}
