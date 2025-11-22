package puppy.code;

// Estrategia de movimiento que rebota en los bordes.
public class ReboteMovimiento implements MovementStrategy<AsteroideHostil> {

	private float anchoPantalla;
    private float altoPantalla;

    //Constructor
    public ReboteMovimiento(float anchoPantalla, float altoPantalla) {
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
    }

    @Override
    public void move(AsteroideHostil a, float delta) {
        if (!a.estaActiva()) return;

        a.setX(a.getX() + a.getXSpeed() * delta);
        a.setY(a.getY() + a.getYSpeed() * delta);

        //Rebote horizontal
        if (a.getX() <= 0 || a.getX() + a.getAncho() >= anchoPantalla) {
            a.setXSpeed(-a.getXSpeed());
        }

        //Rebote vertical
        if (a.getY() <= 0 || a.getY() + a.getAlto() >= altoPantalla) {
            a.setYSpeed(-a.getYSpeed());
        }
    }
}
