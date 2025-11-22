package puppy.code;

import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;

public class DisparoRecto implements DisparableStrategy<Alienigena> {

    private Sound sonido;

    public DisparoRecto(Sound sonido) {
        this.sonido = sonido;
    }

    @Override
    public void disparar(Alienigena alien, ArrayList<Bullet> balas) {

        float x = alien.getX() + alien.getAncho() / 2f;
        float y = alien.getY();

        // AGREGANDO LA BALA AL MISMO ARREGLO BALAS DE PANTALLAJUEGO
        balas.add(new Bullet(x, y, -90f, null, false));

        if (sonido != null) sonido.play(0.3f);
    }
}

