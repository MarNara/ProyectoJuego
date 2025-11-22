package puppy.code;

public class AlienMovimientoLineal implements MovementStrategy<Alienigena> {

    @Override
    public void move(Alienigena alien, float delta) {
        if (!alien.estaActiva()) return;

        float nuevaX = alien.getX() + alien.getVelocidadX() * alien.getDireccion() * delta;
        alien.setX(nuevaX);

        // Rebote en bordes
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

