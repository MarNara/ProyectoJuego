package puppy.code;

import java.util.ArrayList;

public interface DisparableStrategy<T extends Disparable> {
    void disparar(T entidad, ArrayList<Bullet> balas);
}