package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaMenu implements Screen {

	//private final SpaceNavigation game; //MODIFICADA "final" POR SINGLETON
	private OrthographicCamera camera;
	
	public PantallaMenu() {

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
        
        // 3. Reemplazar 'game.getBatch()' con 'SpaceNavigation.getInstance().getBatch()'
		SpaceNavigation.getInstance().getBatch().setProjectionMatrix(camera.combined);

		SpaceNavigation.getInstance().getBatch().begin();
        
        // 4. Reemplazar 'game.getFont()' con 'SpaceNavigation.getInstance().getFont()'
		SpaceNavigation.getInstance().getFont().draw(SpaceNavigation.getInstance().getBatch(), "Bienvenido a Space Navigation !", 140, 400);
		SpaceNavigation.getInstance().getFont().draw(SpaceNavigation.getInstance().getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 300);
	
		SpaceNavigation.getInstance().getBatch().end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            
            // 5. La clase PantallaJuego debe ser modificada también
            // La llamada debe reflejar que PantallaJuego ya no recibe 'game'
            // (Nota: esta línea todavía tiene los parámetros del Abstract Factory, que harás después)
			Screen ss = new PantallaJuego(SpaceNavigation.getInstance(), 1,3,0,1,1,10);
            ss.resize(1200, 800);
            
            // 6. Reemplazar 'game.setScreen()' con 'SpaceNavigation.getInstance().setScreen()'
			SpaceNavigation.getInstance().setScreen(ss);
			dispose();
		}
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
   
}