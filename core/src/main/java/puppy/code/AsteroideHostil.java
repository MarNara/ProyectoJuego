package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class AsteroideHostil extends Enemigo {

    private int xSpeed;
    private int ySpeed;

    public AsteroideHostil(float x, float y, float size, int vidaInicial, int xSpeed, int ySpeed) {
        super(x, y, size, size, vidaInicial);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void dibujar(SpriteBatch batch) {
    }

    @Override
    public void actualizar(float delta) {
        if (!estaActiva()) return;
        
        float multiplicador = 1.5f; 
        float nuevaX = getX() + xSpeed * delta * multiplicador;
        float nuevaY = getY() + ySpeed * delta * multiplicador;
        
        setX(nuevaX);
        setY(nuevaY);

        if (getX() < 0) { 
            setX(0); 
            xSpeed = -xSpeed; 
        }
        if (getX() + getAncho() > Gdx.graphics.getWidth()) { 
            setX(Gdx.graphics.getWidth() - getAncho()); 
            xSpeed = -xSpeed; 
        }
        if (getY() < 0) { 
            setY(0); 
            ySpeed = -ySpeed; 
        }
        if (getY() + getAlto() > Gdx.graphics.getHeight()) { 
            setY(Gdx.graphics.getHeight() - getAlto()); 
            ySpeed = -ySpeed; 
        }
    }

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

    public int getXSpeed() { return xSpeed; }
    public int getYSpeed() { return ySpeed; }
    public void setXSpeed(int xSpeed) { this.xSpeed = xSpeed; }
    public void setYSpeed(int ySpeed) { this.ySpeed = ySpeed; }
}
