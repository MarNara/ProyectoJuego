package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.Random;

public class FabricaNivel1 implements FabricaNivel {
    
    private Sound sonidoDisparo;
    public FabricaNivel1(Sound sonidoDisparo, Nave_Personaje nave) {
        this.sonidoDisparo = sonidoDisparo;
    }

    @Override
    public AsteroideHostil crearAsteroide() {
        Random r = new Random();
        float size = 40 + r.nextInt(15);
        // Nivel 1: Asteroides Lentos (Velocidad base baja)
        return new AsteroideHostil(
            r.nextInt(Gdx.graphics.getWidth()),
            50 + r.nextInt(Gdx.graphics.getHeight() - 50),
            size,
            1, // 1 vida
            2 + r.nextInt(2), // Velocidad X lenta
            2 + r.nextInt(2)  // Velocidad Y lenta
        );
    }

    @Override
    public Alienigena crearAlien() {
        Random r = new Random();
        Alienigena alien = new Alienigena(
            r.nextInt(Gdx.graphics.getWidth() - 50),
            400 + r.nextInt(100),
            40, 40,
            2, // Vida base
            sonidoDisparo
        );
        
        // Configuración de FAMILIA Nivel 1: Movimiento Lineal + Disparo Dirigido Simple
        alien.setStrategy(new AlienMovimientoLineal());
        alien.setDisparableStrategy(new DisparoRecto(sonidoDisparo));
        
        return alien;
    }
}