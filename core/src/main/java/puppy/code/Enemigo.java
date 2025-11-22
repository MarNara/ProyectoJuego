package puppy.code;

// Hereda de Entidad e implementa las capacidades que todo enemigo tiene por defecto
public abstract class Enemigo<T extends Enemigo<T>> extends Entidad implements Hostil, Destructible{
    
    private int vida;
    private final int puntosBase = 10; // Puntos estándar por destruir este enemigo
    //para el strategy
    
    private MovementStrategy<T> movementStrategy;

    // El constructor de Enemigo llama al constructor de Entidad
    public Enemigo(float x, float y, float ancho, float alto, int vidaInicial, MovementStrategy<T> strategy) {
        // Llama a Entidad(x, y, ancho, alto)
        super(x, y, ancho, alto); 
        this.vida = vidaInicial;
        this.movementStrategy = strategy;
    }
    
    // Setter para cambiar la estrategia dinámicamente
    public void setStrategy(MovementStrategy<T> strategy) {
        this.movementStrategy = strategy;
    }


    // IMPLEMENTACIÓN CONCRETA: Delega el movimiento a la Strategy
    @Override
    public void actualizar(float delta) {
        if (movementStrategy != null) {
            // Seguro: mueve un T
            movementStrategy.move(getThis(), delta);
        }
    }

    // Truco para obtener el tipo real T en clases heredadas
    protected abstract T getThis();


    // Implementación de Destructible (Lógica de vida compartida)
    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public void recibirDanio(int d) {
        if (estaActiva()) { //Solo si está activo
            this.vida -= d;
            if (this.vida <= 0) {
                this.desactivar();
            }
        }
    }

    @Override
    public boolean estaDestruido() {
        return !estaActiva(); 
    }

    //Implementación de Hostil (Lógica de recompensa compartida)
    @Override
    public int getPuntosPorDestruccion() {
        return puntosBase;
    }

    // El método dibujar(SpriteBatch) DEBE ser implementado por la clase hija para dibujar su textura específica.
}
