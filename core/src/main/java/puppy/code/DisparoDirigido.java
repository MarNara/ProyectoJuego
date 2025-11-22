package puppy.code;

import com.badlogic.gdx.audio.Sound;
import java.util.ArrayList;

public class DisparoDirigido implements DisparableStrategy<Alienigena> {

    private Nave_Personaje objetivo;
    private Sound sound;

    public DisparoDirigido(Nave_Personaje objetivo, Sound sonido) {
        this.objetivo = objetivo;
        this.sound = sonido;
    }

    @Override
    public void disparar(Alienigena alien, ArrayList<Bullet> balas) {

        float ax = alien.getX() + alien.getAncho()/2;
        float ay = alien.getY() + alien.getAlto()/2;

        float tx = objetivo.getX();
        float ty = objetivo.getY();

        float angle = (float)Math.toDegrees(Math.atan2(ty - ay, tx - ax));

        balas.add(new Bullet(ax, ay, angle, null, false));

        if (sound != null)
            sound.play(0.4f);
    }
}

