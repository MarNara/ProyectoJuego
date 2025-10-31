package puppy.code;

// Hereda de Entidad e implementa las capacidades que todo enemigo tiene por defecto
public abstract class Enemigo extends Entidad implements Hostil, Destructible {
    
    private int vida;
    private final int puntosBase = 10; // Puntos estándar por destruir este enemigo

    // El constructor de Enemigo llama al constructor de Entidad
    public Enemigo(float x, float y, float ancho, float alto, int vidaInicial) {
        // Llama a Entidad(x, y, ancho, alto)
        super(x, y, ancho, alto); 
        this.vida = vidaInicial;
    }

    // 🔹 Implementación de Destructible (Lógica de vida compartida)
    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public void recibirDanio(int d) {
        if (estaActiva()) { // 🔹 Solo si está activo
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

    // 🔹 Implementación de Hostil (Lógica de recompensa compartida)
    @Override
    public int getPuntosPorDestruccion() {
        return puntosBase;
    }

    // El método actualizar DEBE ser implementado por la clase hija (Movimiento e IA)
    public abstract void actualizar(float delta);

    // El método dibujar(SpriteBatch) DEBE ser implementado por la clase hija para dibujar su textura específica.
}
