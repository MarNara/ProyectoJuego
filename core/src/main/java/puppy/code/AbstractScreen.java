package puppy.code;
//
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {

    protected final SpaceNavigation game;
    protected final OrthographicCamera camera;
    protected final SpriteBatch batch;
    
    private float clearR, clearG, clearB;
    
    public AbstractScreen(SpaceNavigation game, float r, float g, float b) {
        this.game = game;
        // Obtenemos los recursos globales desde el Singleton (si ya lo aplicaste)
        // this.batch = SpaceNavigation.getInstance().getBatch();
        this.batch = game.getBatch(); // O así, si prefieres pasarlo
        
        this.camera = new OrthographicCamera();
        // Usamos las dimensiones de la ventana del juego
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
     // Guardamos el color
        this.clearR = r;
        this.clearG = g;
        this.clearB = b;
    }

    /**
     * Constructor para pantallas que se limpian a negro (o tienen fondo de imagen)
     */
     public AbstractScreen(SpaceNavigation game) {
        this(game, 0, 0, 0); // Llama al otro constructor con negro por defecto
     }
    
    /**
     * EL MÉTODO PLANTILLA (final para que no se pueda sobreescribir)
     * Define el esqueleto del algoritmo de renderizado.
     */
    @Override
    public final void render(float delta) {
        
        // PASO 1: Lógica (Input, Colisiones, Movimiento)
        updateLogic(delta);
        
        // PASO 2: Dibujado (Limpiar pantalla y dibujar)
        Gdx.gl.glClearColor(clearR, clearG, clearB, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        batch.begin();
        drawContent(delta); // Hook para el dibujo específico
        batch.end();
        
        // PASO 3: Transiciones (Revisar si cambiamos de pantalla)
        checkTransitions(delta);
    }
    
    // --- Pasos Primitivos (Abstractos) ---
    // Las subclases DEBEN implementar esto.

    /**
     * Toda la lógica del juego: movimiento, colisiones, input, IA, etc.
     */
    protected abstract void updateLogic(float delta);
    
    /**
     * Todo el dibujado. Se ejecuta dentro de batch.begin() y batch.end().
     */
    protected abstract void drawContent(float delta);
    
    /**
     * Lógica para revisar si se debe cambiar a otra pantalla.
     * Se ejecuta fuera del batch.
     */
    protected abstract void checkTransitions(float delta);

    
    // --- Métodos de la Interfaz Screen (con implementación vacía) ---
    @Override public void resize(int width, int height) {
        camera.setToOrtho(false, width, height); // Implementación base útil
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void show() {}
    @Override public void dispose() {} // Las subclases deben implementar su propio dispose
}