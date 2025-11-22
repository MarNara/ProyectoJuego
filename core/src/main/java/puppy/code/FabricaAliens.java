package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.Random;

public class FabricaAliens implements FabricaEnemigos {

    private int ronda;
    private Sound sonidoDisparo;
    private Nave_Personaje naveObjetivo; // Necesaria para las estrategias de persecución/disparo

    public FabricaAliens(int ronda, Sound sonidoDisparo, Nave_Personaje naveObjetivo) {
        this.ronda = ronda;
        this.sonidoDisparo = sonidoDisparo;
        this.naveObjetivo = naveObjetivo;
    }

    @Override
    public Enemigo<?> crearEnemigo() {
        Random r = new Random();
        float alienWidth = 40f;
        float alienHeight = 40f;

        // 1. Crear la instancia base
        Alienigena alien = new Alienigena(
            r.nextInt(700),
            400 + r.nextInt(100),
            alienWidth,
            alienHeight,
            2 + ronda, // Vida escala con ronda
            sonidoDisparo
        );

        // 2. Asignar ESTRATEGIAS según la ronda (Lógica movida desde PantallaJuego)
        if (ronda == 1) {
            alien.setStrategy(new AlienMovimientoLineal());
            alien.setDisparableStrategy(new DisparoDirigido(naveObjetivo, sonidoDisparo));
        } 
        else if (ronda == 2) {
            alien.setStrategy(new AlienMovimientoOscilante());
            alien.setDisparableStrategy(new DisparoRecto(sonidoDisparo));
        }
        else if (ronda >= 3) {
            alien.setStrategy(new AlienMovimientoPersecucion(naveObjetivo));
            alien.setDisparableStrategy(new DisparoDirigido(naveObjetivo, sonidoDisparo));
        }

        return alien;
    }
}