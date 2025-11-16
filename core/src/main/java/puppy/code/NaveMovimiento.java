package puppy.code;

/**
 * Estrategia de movimiento para Nave_Personaje: basada en el input del usuario.
 */
public class NaveMovimiento implements MovementStrategy<Nave_Personaje> {

    @Override
    public void move(Nave_Personaje nave, float delta) {
        // La Strategy solo decide QUÉ acción tomar (Input o Efecto Herido)
        if (!nave.getHerido()) {
            nave.handlePlayerInputMovement(delta); 
        } else {
            nave.handleHurtEffect();
        }
    }
}