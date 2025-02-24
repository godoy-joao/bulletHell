package io.github.BulletHell.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import io.github.BulletHell.BulletHellGame;
import io.github.BulletHell.entities.Bullet;
import io.github.BulletHell.entities.Enemy;
import io.github.BulletHell.entities.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {

    private BulletHellGame game;
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private float enemySpawnTimer = 0f;
    private float enemySpawnRate = 1f; ;

    public GameScreen(BulletHellGame game) {
        this.game = game;
        player = new Player();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    player.update(delta);
        enemySpawnTimer += delta;
        if (enemySpawnTimer >= enemySpawnRate) {
            enemies.add(new Enemy(new Random().nextInt(700), 600));
            enemySpawnTimer = 0;
        }
        Iterator<Enemy> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();

            Iterator<Bullet> bulletIter = player.getBullets().iterator();
            while (bulletIter.hasNext()) {
                Bullet bullet = bulletIter.next();

                if (bullet.getBounds().overlaps(enemy.getBounds())) {
                    bulletIter.remove();
                    enemyIter.remove();
                    break; // Sai do loop ao remover um inimigo
                }
            }
        }

        // Atualiza e remove inimigos fora da tela
        Iterator<Enemy> iter = enemies.iterator();
        while (iter.hasNext()) {
            Enemy enemy = iter.next();
            enemy.update(delta);
            if (enemy.isOutOfScreen()) iter.remove();
        }

    game.batch.begin();
    player.draw(game.batch);
        for (Enemy enemy : enemies) {
            enemy.draw(game.batch);
        }
    game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    player.dispose();
    }
}
