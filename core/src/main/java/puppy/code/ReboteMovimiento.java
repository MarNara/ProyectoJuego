package puppy.code;

/**
 * Estrategia de movimiento que rebota en los bordes.
 */
public class ReboteMovimiento implements MovementStrategy<AsteroideHostil> {

    @Override
    public void move(AsteroideHostil a, float delta) {
        if (!a.estaActiva()) return;

        a.setX(a.getX() + a.getXSpeed() * delta);
        a.setY(a.getY() + a.getYSpeed() * delta);

        float anchoPantalla = 800f;
        float altoPantalla = 600f;

        if (a.getX() <= 0 || a.getX() + a.getAncho() >= anchoPantalla) {
            a.setXSpeed(-a.getXSpeed());
        }
        if (a.getY() <= 0 || a.getY() + a.getAlto() >= altoPantalla) {
            a.setYSpeed(-a.getYSpeed());
        }
    }
}
