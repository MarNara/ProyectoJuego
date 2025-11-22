package puppy.code;

import com.badlogic.gdx.Gdx;
import java.util.Random;

public class FabricaAsteroides implements FabricaEnemigos {

    private int velX;
    private int velY;

    public FabricaAsteroides(int velX, int velY) {
        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public Enemigo<?> crearEnemigo() {
        Random r = new Random();
        float size = 40 + r.nextInt(15);
        int initialLife = 1;

        // Lógica movida desde PantallaJuego
        return new AsteroideHostil(
            r.nextInt((int)Gdx.graphics.getWidth()),
            50 + r.nextInt((int)Gdx.graphics.getHeight() - 50),
            size,
            initialLife,
            velX + r.nextInt(4), 
            velY + r.nextInt(4)
        );
    }
}