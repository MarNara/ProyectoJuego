package puppy.code;

import com.badlogic.gdx.math.MathUtils;
//
/**
 * Estrategia: Movimiento sinusoidal (Zig-Zag suave)
 * Cumple con GM2.3: Es un algoritmo de movimiento complejo y específico para Aliens.
 */
public class AlienMovimiento implements MovementStrategy<Alienigena> {
    
    // Variable auxiliar para el cálculo del seno (basado en posición X para no guardar estado tiempo)
    private float frecuencia = 0.05f; 
    private float amplitud = 2.5f;

    @Override
    public void move(Alienigena alien, float delta) {
        if (!alien.estaActiva()) return;
        
        // 1. Movimiento Lateral Básico (Lineal en X)
        float velocidadX = alien.getVelocidadX();
        float direccion = alien.getDireccion(); 
        float nuevaX = alien.getX() + velocidadX * direccion * delta;
        
        // 2. APLICAMOS EL MOVIMIENTO NO LINEAL (Onda Senoidal en Y)
        // Usamos la posición X para calcular una onda, creando un efecto de flotación/zig-zag
        float desplazamientoY = MathUtils.sin(alien.getX() * frecuencia) * amplitud;
        float nuevaY = alien.getY() + desplazamientoY;
        
        // Aplicamos cambios
        alien.setX(nuevaX);
        alien.setY(nuevaY);
        
        // 3. Rebote en bordes (Igual que antes)
        float anchoPantalla = 800f; // O Gdx.graphics.getWidth()
        
        if (alien.getX() <= 0) {
            alien.setX(0);
            alien.setDireccion(1f); 
        } else if (alien.getX() + alien.getAncho() >= anchoPantalla) { 
            alien.setX(anchoPantalla - alien.getAncho());
            alien.setDireccion(-1f); 
        }
    }
}