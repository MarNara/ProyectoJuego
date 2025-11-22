package puppy.code;

// ABSTRACT FACTORY
public interface FabricaNivel {
    // Métodos para crear los distintos productos de la familia "Nivel"
    AsteroideHostil crearAsteroide();
    Alienigena crearAlien();
}