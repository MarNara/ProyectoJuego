package puppy.code;

import com.badlogic.gdx.Gdx;

/**
 * Estrategia de movimiento para asteroides: rebote en los bordes de la pantalla.
 */
public class ReboteMovimiento implements MovementStrategy<AsteroideHostil> {

    @Override
    public void move(AsteroideHostil asteroide, float delta) {
        if (!asteroide.estaActiva()) return;

        int xSpeed = asteroide.getXSpeed();
        int ySpeed = asteroide.getYSpeed();
        
        float multiplicador = 1.5f; 
        float nuevaX = asteroide.getX() + xSpeed * delta * multiplicador;
        float nuevaY = asteroide.getY() + ySpeed * delta * multiplicador;

        asteroide.setX(nuevaX);
        asteroide.setY(nuevaY);

        // Lógica de rebote
        if (asteroide.getX() < 0) { 
            asteroide.setX(0); 
            asteroide.setXSpeed(-xSpeed); 
        }
        if (asteroide.getX() + asteroide.getAncho() > Gdx.graphics.getWidth()) { 
            asteroide.setX(Gdx.graphics.getWidth() - asteroide.getAncho()); 
            asteroide.setXSpeed(-xSpeed); 
        }
        if (asteroide.getY() < 0) { 
            asteroide.setY(0); 
            asteroide.setYSpeed(-ySpeed); 
        }
        if (asteroide.getY() + asteroide.getAlto() > Gdx.graphics.getHeight()) { 
            asteroide.setY(Gdx.graphics.getHeight() - asteroide.getAlto()); 
            asteroide.setYSpeed(-ySpeed); 
        }
    }
}
