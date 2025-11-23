package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;


public class PantallaGameOver extends AbstractScreen {

	public PantallaGameOver(SpaceNavigation game) {
		//super(game, 0, 0, 0.2f);
		super(game, 0.5f, 0, 0);
	}
	
	@Override
	protected void updateLogic(float delta) {
		// Esta pantalla no tiene lógica de juego, solo revisa input para transición.
		// Dejamos eso para checkTransitions.
	}
	
	@Override
	protected void drawContent(float delta) {
		// Mover aquí solo las líneas de dibujado (las que estaban entre begin/end)
		game.getFont().setColor(Color.WHITE);
		game.getFont().draw(game.getBatch(), "FIN DEL JUEGO !!! ", 375  , 500, 500, 1, true);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado para reiniciar ...", 350, 300);
		
	}
	
	@Override
	protected void checkTransitions(float delta) {
		// Mover aquí la lógica que cambia de pantalla
		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			// NOTA: El AbstractScreen no maneja el re-dimensionamiento fijo (1200, 800)
			// Deberías manejar el re-dimensionamiento en el método resize()
			Screen ss = new PantallaJuego(game, 1, 3, 0, 1, 1, 10);
			// ss.resize(1200, 800); // Esta línea se puede mover al método resize de PantallaJuego
			game.setScreen(ss);
			dispose();
		}
	}
	
	@Override
	public void dispose() {
		// El dispose sí debe mantenerse si libera recursos
		// (En este caso, no hay nada que liberar)
	}
}
	