package io.github.BulletHell.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private Texture texture;
    private Rectangle bounds;
    private float speed = 100;

    public Enemy(float x, float y) {
        texture = new Texture("textures/enemy.png");
        bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        bounds.y -= speed * delta; // Movimento para baixo
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y);
    }

    public boolean isOutOfScreen() {
        return bounds.y < -bounds.height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
