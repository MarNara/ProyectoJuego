package puppy.code;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;

public class PantallaJuego extends AbstractScreen {
	
    private Texture fondoGalaxia;
    private Sound explosionSound;
    private Music gameMusic;
    private int score;
    private int ronda;
    private int velXAsteroides; 
    private int velYAsteroides; 
    private int cantAsteroides;
    private Texture asteroideTexture;
    
    private Nave_Personaje nave;
    private ArrayList<Entidad> hostileEntities = new ArrayList<>();//usando polimorfismo
    private ArrayList<Bullet> balas = new ArrayList<>();
    
    //para los aliens
    private Texture alienTexture;
    private Texture alienBalaTexture;
    private Texture naveBalaTexture;
    //private Sound alienDisparoSound;
    private Sound naveDisparoSound;

    public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score,  
            int velXAsteroides, int velYAsteroides, int cantAsteroides) {
        super(game);
        this.ronda = ronda;
        this.score = score;
        this.velXAsteroides = velXAsteroides;
        this.velYAsteroides = velYAsteroides;
        this.cantAsteroides = cantAsteroides;
        
        //inicializar assets; musica de fondo y efectos de sonido
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        naveDisparoSound = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
        
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.1f);
        gameMusic.play();
        
        //cargar imagen del fondo
        fondoGalaxia = new Texture(Gdx.files.internal("fondoGalaxia.png"));
        asteroideTexture = new Texture(Gdx.files.internal("aGreyMedium4.png"));
        
        // cargar imagen de la nave, 64x64   
        nave = new Nave_Personaje(
            Gdx.graphics.getWidth()/2-50,
            30,
            new Texture(Gdx.files.internal("MainShip3.png")),
            new Texture(Gdx.files.internal("Rocket2.png")),
            Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"))
        );
        nave.setVidas(vidas);

        //crear asteroides
        Random r = new Random();
        for (int i = 0; i < cantAsteroides; i++) {
            float size = 20 + r.nextInt(10);
            int initialLife = 1;// >:C
            
            AsteroideHostil ah = new AsteroideHostil(
                r.nextInt((int)Gdx.graphics.getWidth()),
                50 + r.nextInt((int)Gdx.graphics.getHeight() - 50),
                size,
                initialLife,
                velXAsteroides + r.nextInt(4), 
                velYAsteroides + r.nextInt(4)
            );       
            hostileEntities.add(ah);
        }
        
        //CARGAR TEXTURAS UNA SOLA VEZ
        alienTexture = new Texture(Gdx.files.internal("alien.png"));
        alienBalaTexture = new Texture(Gdx.files.internal("alien_bullet3.png"));
        naveBalaTexture = new Texture(Gdx.files.internal("Rocket2.png"));
        
        //CREAR ALIENÍGENAS
        crearAliens();    
        
    }
    
    private void crearAliens() {
        Random r = new Random();
        int cantAliens = 3 + ronda;
        
        for (int i = 0; i < cantAliens; i++) {
            float alienWidth = 40f;
            float alienHeight = 40f;
            
            Alienigena alien = new Alienigena(
                r.nextInt(700),
                400 + r.nextInt(100),
                alienWidth,
                alienHeight,
                2 + ronda,
                naveDisparoSound
            );
            
            //Agregar el alien a la lista global
            hostileEntities.add(alien); 
        }
    }
    
    public void dibujaEncabezado() {
        CharSequence str = "Vidas: "+nave.getVidas()+" Ronda: "+ronda;
        game.getFont().getData().setScale(2f);        
        game.getFont().draw(batch, str, 10, 30);
        game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
        game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
    }
    
    @Override
    protected void updateLogic(float delta) {
        // Mover aquí TODA la lógica de actualización, input y colisiones
        
        // ACTUALIZAR NAVE (Input y Lógica)
        nave.actualizar(delta); 
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            nave.disparar(balas);
        }

        // ACTUALIZAR Y DISPARAR HOSTILES (Lógica y Colisiones)
        for (int i = 0; i < hostileEntities.size(); i++) {
            Entidad e = hostileEntities.get(i);
            e.actualizar(delta);
            
            // Disparos automáticos (Lógica)
            if (e instanceof Alienigena) {
                ((Alienigena)e).disparar(balas);
            }
            
            // COLISIÓN NAVE-HOSTILES
            if (nave.checkCollision(e)) { 
                hostileEntities.remove(i);
                i--;
            }
            
            // REBOTES ASTEROIDES
            if (e instanceof AsteroideHostil) {
                AsteroideHostil ah1 = (AsteroideHostil)e;
                for (int j = i + 1; j < hostileEntities.size(); j++) {
                    Entidad e2 = hostileEntities.get(j);
                    if (e2 instanceof AsteroideHostil) {
                        AsteroideHostil ah2 = (AsteroideHostil)e2;
                        ah1.checkCollision(ah2);
                    }
                }
            }
        }
        
        // ACTUALIZAR BALAS (Lógica y Colisiones)
        for (int i = 0; i < balas.size(); i++) {
            Bullet b = balas.get(i);
            
            if (b.getTexture() == null) {
                Texture texturaUsar = (b.getAngle() == -90f) ? alienBalaTexture : naveBalaTexture;
                b.setTexture(texturaUsar);
            }
            
            b.update();
            
            // COLISIONES BALAS-HOSTILES
            for (int j = 0; j < hostileEntities.size(); j++) {
                Entidad e = hostileEntities.get(j);
                if (b.checkCollision(e)) {
                    explosionSound.play(0.1f);
                    if (e instanceof Hostil && !e.estaActiva()) { 
                        score += ((Hostil)e).getPuntosPorDestruccion();
                    }
                    if (!e.estaActiva()) {
                        hostileEntities.remove(j);
                        j--;
                    }
                }
            }
            
            b.checkCollision(nave);
            
            if (b.isDestroyed()) {
                balas.remove(i);
                i--;
            }
        }
    }

    @Override
    protected void drawContent(float delta) {
        // Mover aquí SÓLO el código de dibujado
        
        //DIBUJAR FONDO Y ENCABEZADO
        batch.draw(fondoGalaxia, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        dibujaEncabezado();

        // DIBUJAR HOSTILES
        // (Esto requiere un nuevo bucle, lo cual es normal en esta separación)
        for (Entidad e : hostileEntities) {
            if (e instanceof AsteroideHostil) {
                batch.draw(asteroideTexture, e.getX(), e.getY(), e.getAncho(), e.getAlto());
            } else if (e instanceof Alienigena) {
                batch.draw(alienTexture, e.getX(), e.getY(), e.getAncho(), e.getAlto());
            }
        }
        
        // DIBUJAR BALAS
        // (Nuevo bucle)
        for (Bullet b : balas) {
            b.draw(batch); //lugar donde se dibuja la bala
        }
        
        //DIBUJAR NAVE
        nave.dibujar(batch);
    }

    @Override
    protected void checkTransitions(float delta) {
        // Mover aquí la lógica que cambia de pantalla
        
        if (nave.estaDestruido()) {
            if (score > game.getHighScore()) game.setHighScore(score);
            Screen ss = new PantallaGameOver(game);
            // ss.resize(1200, 800);
            game.setScreen(ss);
            dispose();
        }

        if (hostileEntities.isEmpty()) { 
            Screen ss = new PantallaJuego(game, ronda + 1, nave.getVidas(), score,
                    velXAsteroides + 3, velYAsteroides + 3, cantAsteroides + 10);
            // ss.resize(1200, 800);
            game.setScreen(ss);
            dispose();
        }
    }
    
    // ... (agregarBala se mantiene)
    
    // 7. ELIMINAR MÉTODOS VACÍOS (resize, pause, resume, hide)
    
    @Override
    public void show() {
        // Este SÍ tenía lógica, así que se mantiene
        gameMusic.play();
    }
    
    @Override
    public void dispose() {
        // Este SÍ tenía lógica, así que se mantiene
        this.fondoGalaxia.dispose();
        this.explosionSound.dispose();
        this.gameMusic.dispose();
        
        if (asteroideTexture != null) asteroideTexture.dispose();
        if (alienTexture != null) alienTexture.dispose();
        if (alienBalaTexture != null) alienBalaTexture.dispose();
        if (naveBalaTexture != null) naveBalaTexture.dispose();
    }
    
    
    public boolean agregarBala(Bullet bb) {
        return balas.add(bb);
    }
    
}
