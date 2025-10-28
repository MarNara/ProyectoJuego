package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave4 {

    private boolean destruida = false;
    private int vidas = 3;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;

    private float rotacion = 0f;
    private float velocidadRotacion = 200f;
    private float velocidadMovimiento = 200f;

    private float xVel = 0;
    private float yVel = 0;

    private Sprite spr;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;

    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        this.sonidoHerido = soundChoque;
        this.soundBala = soundBala;
        this.txBala = txBala;
        this.spr = new Sprite(tx);
        spr.setPosition(x, y);
        spr.setBounds(x, y, 45, 45);
        spr.setOriginCenter();
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = spr.getX();
        float y = spr.getY();

        if (!herido) {
            float dt = Gdx.graphics.getDeltaTime();

            // ROTACIÓN
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
                rotacion += velocidadRotacion * dt;
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                rotacion -= velocidadRotacion * dt;

            // NORMALIZA
            if (rotacion > 360) rotacion -= 360;
            if (rotacion < -360) rotacion += 360;

            spr.setRotation(rotacion);

            // MOVIMIENTO — ajustado a que el sprite apunta hacia ARRIBA
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                float rad = (rotacion + 90f) * MathUtils.degreesToRadians;
                float dx = MathUtils.cos(rad) * velocidadMovimiento * dt;
                float dy = MathUtils.sin(rad) * velocidadMovimiento * dt;
                spr.translate(dx, dy);
            }

            // BORDES DE PANTALLA
            if (spr.getX() < 0) spr.setX(0);
            if (spr.getX() + spr.getWidth() > Gdx.graphics.getWidth())
                spr.setX(Gdx.graphics.getWidth() - spr.getWidth());
            if (spr.getY() < 0) spr.setY(0);
            if (spr.getY() + spr.getHeight() > Gdx.graphics.getHeight())
                spr.setY(Gdx.graphics.getHeight() - spr.getHeight());

            spr.draw(batch);
        } else {
            spr.setX(spr.getX() + MathUtils.random(-2, 2));
            spr.draw(batch);
            spr.setX(x);
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }

        // DISPARO alineado a la dirección visual
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            float anguloDisparo = rotacion + 90f;
            float rad = anguloDisparo * MathUtils.degreesToRadians;
            float puntaX = spr.getX() + spr.getWidth() / 2f + MathUtils.cos(rad) * spr.getHeight() / 2f;
            float puntaY = spr.getY() + spr.getHeight() / 2f + MathUtils.sin(rad) * spr.getHeight() / 2f;

            Bullet bala = new Bullet(puntaX, puntaY, anguloDisparo, txBala);
            juego.agregarBala(bala);
            soundBala.play();
        }
    }

    // --- COLISIÓN ---
    public boolean checkCollision(Ball2 b) {
        if (!herido && b.getArea().overlaps(spr.getBoundingRectangle())) {
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            if (vidas <= 0)
                destruida = true;
            return true;
        }
        return false;
    }

    // --- GETTERS ---
    public boolean estaDestruido() {
        return !herido && destruida;
    }

    public boolean estaHerido() {
        return herido;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getX() {
        return (int) spr.getX();
    }

    public int getY() {
        return (int) spr.getY();
    }
}
