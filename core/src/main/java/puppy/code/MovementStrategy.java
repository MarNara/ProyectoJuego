package puppy.code;

/**
 * Define la interfaz para los algoritmos de movimiento.
 * Es genérica para garantizar la seguridad de tipos, eliminando la necesidad de casting explícito.
 * @param <T> El tipo específico de Entidad a mover (ej. AsteroideHostil, ej. Alienigena, ej. Nave_Personaje).
 */
public interface MovementStrategy<T extends Entidad> {
    
    /**
     * Aplica la lógica de movimiento al tipo de entidad especificado.
     * @param entity La entidad específica (Contexto) a mover.
     * @param delta El tiempo transcurrido (para movimiento suave).
     */
    void move(T entity, float delta);
}