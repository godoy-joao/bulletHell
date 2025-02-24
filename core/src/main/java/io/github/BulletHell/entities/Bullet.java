package io.github.BulletHell.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet {

    private Texture texture;
    private Rectangle bounds;
    private float speed = 300;

    public Bullet(float x, float y) {
        texture = new Texture("textures/bullet.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        bounds.y += speed * delta; // Movimento para cima
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y);
    }

    public boolean isOutOfScreen() {
        return bounds.y > 600; // Se sair da tela, remover
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
