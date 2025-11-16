package puppy.code;

//import com.badlogic.gdx.Gdx;

/**
 * Estrategia de movimiento para Alienigenas: movimiento lateral con rebote en 800px.
 */
public class AlienMovimiento implements MovementStrategy<Alienigena> {
    
    @Override
    public void move(Alienigena alien, float delta) {
        if (!alien.estaActiva()) return;
        
        float velocidadX = alien.getVelocidadX();
        float direccion = alien.getDireccion(); 
        
        float nuevaX = alien.getX() + velocidadX * direccion * delta;
        alien.setX(nuevaX);
        
        // Lógica de rebote en bordes de pantalla (800f es el límite asumido)
        float anchoPantalla = 800f; 
        
        if (alien.getX() <= 0) {
            alien.setX(0);
            alien.setDireccion(1f); // Mueve a la derecha
        } else if (alien.getX() + alien.getAncho() >= anchoPantalla) { 
            alien.setX(anchoPantalla - alien.getAncho());
            alien.setDireccion(-1f); // Mueve a la izquierda
        }
    }
}