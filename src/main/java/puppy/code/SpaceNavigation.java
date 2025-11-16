package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;




public class SpaceNavigation extends Game {
	private static SpaceNavigation instance; //SINGLETON////////////////////////////////////////////////////////////////////////////
	private String nombreJuego = "Space Navigation";
	private SpriteBatch batch;
	private BitmapFont font;
	private int highScore;	
	
	//PARA SINGLETON
	private SpaceNavigation() {
        //HOLA BUENAS
    }
	
	@Override
	public void create() {
		if (instance == null)
		{
            instance = this;
        }
		highScore = 0;
		batch = new SpriteBatch();
		font = new BitmapFont(); // usa Arial font x defecto
		font.getData().setScale(2f);
		Screen ss = new PantallaMenu();
		this.setScreen(ss);

	}
	
	//PARA EL SINGLETON RESANDO QUE FUNCIONE
	public static SpaceNavigation getInstance() 
	{
        if (instance == null) 
        {
            instance = new SpaceNavigation(); 
        }
        // Devolvemos la instancia (siempre la misma)
        return instance;
    }

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	

}