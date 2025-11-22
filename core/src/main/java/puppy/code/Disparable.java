package puppy.code;

import java.util.ArrayList;

public interface Disparable {
	// Método que define la acción de disparar (debe implementarse en quien dispare)
    void disparar(ArrayList<Bullet> balas);
}
