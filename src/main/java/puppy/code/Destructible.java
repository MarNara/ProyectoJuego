package puppy.code;

// Lo que todas las cosas que pueden morir necesitan
public interface Destructible {
    int getVida();
    void recibirDanio(int danio);
    boolean estaDestruido();
}
