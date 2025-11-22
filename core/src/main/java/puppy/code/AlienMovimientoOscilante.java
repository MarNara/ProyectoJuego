package puppy.code;

import com.badlogic.gdx.math.MathUtils;

public class AlienMovimientoOscilante implements MovementStrategy<Alienigena> {

    private float velocidadVertical = 50f;
    private float frecuencia = 2f;  // velocidad de oscilación

    @Override
    public void move(Alienigena alien, float delta) {
        if (!alien.estaActiva()) return;

        float nuevaX = alien.getX() + alien.getVelocidadX() * alien.getDireccion() * delta;
        alien.setX(nuevaX);

        float nuevaY = alien.getY() + MathUtils.sinDeg(alien.getX() * frecuencia);
        alien.setY(nuevaY);

        // Rebotes horizontales
        float anchoPantalla = 800f;
        if (alien.getX() <= 0) {
            alien.setX(0);
            alien.setDireccion(1f);
        } else if (alien.getX() + alien.getAncho() >= anchoPantalla) {
            alien.setX(anchoPantalla - alien.getAncho());
            alien.setDireccion(-1f);
        }
    }
}
