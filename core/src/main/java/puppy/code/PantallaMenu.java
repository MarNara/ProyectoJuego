package puppy.code;
//
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;



public class PantallaMenu extends AbstractScreen {

	
	
	public PantallaMenu(SpaceNavigation game) {
		super(game, 0.4f,0.6f , 0.9f);
	}

	@Override
	protected void updateLogic(float delta) {
		// No hay lógica de juego en el menú
	}

	@Override
	protected void drawContent(float delta) {
		// Mover aquí solo las líneas de dibujado
		game.getFont().draw(game.getBatch(), "Bienvenido a Space Nav: Guerra Galáctica !", 140, 400);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
	}

	@Override
	protected void checkTransitions(float delta) {
		// Implementación de Template Method
		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			//GM2.1
			SpaceNavigation gameInstance = SpaceNavigation.getInstance();
			
			// Se necesitará aplicar el Abstract Factory (GM2.4) para crear PantallaJuego limpiamente, 
            // pero por ahora mantenemos el constructor explícito:
            // La factory del juego no se pasa aquí
			Screen ss = new PantallaJuego(gameInstance, 1, 3, 0, 1, 1, 10); 
			
			gameInstance.setScreen(ss); // Usando el Singleton
			dispose();
		}
	}
	
	// 7. ELIMINAR MÉTODOS VACÍOS (show, resize, pause, etc.)
	
	@Override
	public void dispose() {
		// Nada que liberar
	}
	
}