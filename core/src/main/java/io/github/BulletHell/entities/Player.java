package io.github.BulletHell.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    private Texture texture;
    private Rectangle bounds;
    private float speed = 200;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private float shootCooldown = 0.2f;
    private float shootTimer = 0f;

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Player() {
        texture = new Texture("textures/player.png");
        bounds = new Rectangle(100, 100, texture.getWidth(), texture.getHeight());
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) bounds.y += speed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)) bounds.y -= speed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) bounds.x -= speed * delta;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) bounds.x += speed * delta;

        shootTimer += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer >= shootCooldown) {
            bullets.add(new Bullet(bounds.x + bounds.width / 2, bounds.y + bounds.height));
            shootTimer = 0;
        }

        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            bullet.update(delta);
            if (bullet.isOutOfScreen()) iter.remove();
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y);
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        }
    }

    public void dispose() {
        texture.dispose();
    }
}
