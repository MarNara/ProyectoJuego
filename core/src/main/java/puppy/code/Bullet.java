package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private float x, y;
    private float speed = 400f;
    private float angle; // rotación en grados
    private Texture texture;
    private boolean destroyed = false;

    public Bullet(float x, float y, float angle, Texture texture) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.texture = texture;
    }

    public void update() {
        // Movimiento en dirección del ángulo de disparo
        float rad = angle * MathUtils.degreesToRadians;
        x += MathUtils.cos(rad) * speed * com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        y += MathUtils.sin(rad) * speed * com.badlogic.gdx.Gdx.graphics.getDeltaTime();

        // Si sale de pantalla, destruirla
        if (x < -texture.getWidth() || x > com.badlogic.gdx.Gdx.graphics.getWidth() + texture.getWidth()
                || y < -texture.getHeight() || y > com.badlogic.gdx.Gdx.graphics.getHeight() + texture.getHeight()) {
            destroyed = true;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth() / 2f, texture.getHeight() / 2f,
                texture.getWidth(), texture.getHeight(), 1, 1, angle, 0, 0,
                texture.getWidth(), texture.getHeight(), false, false);
    }

    public boolean checkCollision(Ball2 ball) {
        Rectangle r1 = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        if (r1.overlaps(ball.getArea())) {
            destroyed = true;
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
