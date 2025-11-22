package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.Random;

public class FabricaNivelAvanzado implements FabricaNivel {
    
    private Sound sonidoDisparo;
    private Nave_Personaje nave;
    private int ronda;

    public FabricaNivelAvanzado(Sound sonidoDisparo, Nave_Personaje nave, int ronda) {
        this.sonidoDisparo = sonidoDisparo;
        this.nave = nave;
        this.ronda = ronda;
    }

    @Override
    public AsteroideHostil crearAsteroide() {
        Random r = new Random();
        float size = 30 + r.nextInt(20); // Tamaños más variados
        // Nivel Avanzado: Asteroides Rápidos
        return new AsteroideHostil(
            r.nextInt(Gdx.graphics.getWidth()),
            50 + r.nextInt(Gdx.graphics.getHeight() - 50),
            size,
            2, // Más vida
            5 + r.nextInt(5), // MUCHO MÁS RÁPIDOS
            5 + r.nextInt(5)
        );
    }

    @Override
    public Alienigena crearAlien() {
        Random r = new Random();
        Alienigena alien = new Alienigena(
            r.nextInt(Gdx.graphics.getWidth() - 50),
            400 + r.nextInt(200),
            40, 40,
            2 + ronda, // Vida escala
            sonidoDisparo
        );
        
        // Configuración de FAMILIA Avanzada: Persecución o Oscilación
        if (ronda == 2) {
            alien.setStrategy(new AlienMovimientoOscilante());
            alien.setDisparableStrategy(new DisparoRecto(sonidoDisparo));
        } else {
            alien.setStrategy(new AlienMovimientoPersecucion(nave));
            alien.setDisparableStrategy(new DisparoDirigido(nave, sonidoDisparo));
        }
        
        return alien;
    }
}