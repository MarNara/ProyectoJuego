package puppy.code;
//
/**
 * Define la interfaz para los algoritmos de movimiento.
 * Es genérica para garantizar la seguridad de tipos.
 * @param <T> El tipo específico de Enemigo a mover (ej. AsteroideHostil o Alienigena).
 */
public interface MovementStrategy<T extends Enemigo> {
    
    /**
     * Aplica la lógica de movimiento al tipo de Enemigo especificado.
     * @param enemigo La entidad Enemigo específica a mover.
     * @param delta El tiempo transcurrido (para movimiento suave).
     */
    void move(T enemigo, float delta);
}