package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.Random;

public class FabricaNivel1 implements FabricaNivel {
    
    private Sound sonidoDisparo;
    //private Nave_Personaje nave; // Por si se necesita en el futuro

    public FabricaNivel1(Sound sonidoDisparo, Nave_Personaje nave) {
        this.sonidoDisparo = sonidoDisparo;
        //this.nave = nave;
    }

    @Override
    public AsteroideHostil crearAsteroide() {
        Random r = new Random();
        float size = 40 + r.nextInt(15);
        // PRODUCTO A1: Asteroide Lento
        return new AsteroideHostil(
            r.nextInt(Gdx.graphics.getWidth()),
            50 + r.nextInt(Gdx.graphics.getHeight() - 50),
            size,
            1, // Vida 1
            2 + r.nextInt(2), // Lento X
            2 + r.nextInt(2)  // Lento Y
        );
    }

    @Override
    public Alienigena crearAlien() {
        Random r = new Random();
        // PRODUCTO B1: Alien Básico
        Alienigena alien = new Alienigena(
            r.nextInt(Gdx.graphics.getWidth() - 50),
            400 + r.nextInt(100),
            40, 40,
            2, // Vida 2
            sonidoDisparo
        );
        
        // Configuración específica de la familia Nivel 1
        alien.setStrategy(new AlienMovimientoLineal());
        
        // TU PEDIDO: En Nivel 1 usan Disparo Recto
        alien.setDisparableStrategy(new DisparoRecto(sonidoDisparo));
        
        return alien;
    }
}