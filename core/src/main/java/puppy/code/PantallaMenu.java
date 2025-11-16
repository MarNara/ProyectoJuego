package puppy.code;
//
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;



public class PantallaMenu extends AbstractScreen {

	public PantallaMenu(SpaceNavigation game) {
		super(game, 0, 0, 0.2f);
	}

	@Override
	protected void updateLogic(float delta) {
		// No hay lógica de juego en el menú
	}

	@Override
	protected void drawContent(float delta) {
		// Mover aquí solo las líneas de dibujado
		game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation !", 140, 400);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
	}

	@Override
	protected void checkTransitions(float delta) {
		// Mover aquí la lógica que cambia de pantalla
		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaJuego(game, 1, 3, 0, 1, 1, 10);
			// ss.resize(1200, 800); // Mover a resize()
			game.setScreen(ss);
			dispose();
		}
	}
	
	// 7. ELIMINAR MÉTODOS VACÍOS (show, resize, pause, etc.)
	
	@Override
	public void dispose() {
		// Nada que liberar
	}
	
}