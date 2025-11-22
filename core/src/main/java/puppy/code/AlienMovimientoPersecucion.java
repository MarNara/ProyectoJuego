package puppy.code;

public class AlienMovimientoPersecucion implements MovementStrategy<Alienigena> {

    private Nave_Personaje objetivo;  // la nave
    private float velocidad = 120f;   // velocidad de persecución

    public AlienMovimientoPersecucion(Nave_Personaje objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public void move(Alienigena alien, float delta) {
        if (!alien.estaActiva() || objetivo == null) return;

        // POSICIÓN ACTUAL DEL ALIEN
        float ax = alien.getX();
        float ay = alien.getY();

        // POSICIÓN DEL OBJETIVO
        float tx = objetivo.getX();
        float ty = objetivo.getY();

        // CALCULAR DIRECCIÓN NORMALIZADA (vector unitario)
        float dx = tx - ax;
        float dy = ty - ay;

        float distancia = (float)Math.sqrt(dx*dx + dy*dy);

        if (distancia > 1f) { // evitar dividir por cero
            dx /= distancia;
            dy /= distancia;
        }

        // MOVER ALIEN HACIA LA NAVE
        float nuevaX = ax + dx * velocidad * delta;
        float nuevaY = ay + dy * velocidad * delta;

        alien.setX(nuevaX);
        alien.setY(nuevaY);

        // (Opcional) Rebote en bordes
        float anchoPantalla = com.badlogic.gdx.Gdx.graphics.getWidth();
        float altoPantalla = 600f;

        if (alien.getX() < 0) alien.setX(0);
        if (alien.getX() + alien.getAncho() > anchoPantalla)
            alien.setX(anchoPantalla - alien.getAncho());

        if (alien.getY() < 0) alien.setY(0);
        if (alien.getY() + alien.getAlto() > altoPantalla)
            alien.setY(altoPantalla - alien.getAlto());
    }
}

