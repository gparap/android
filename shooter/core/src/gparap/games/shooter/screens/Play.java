/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.RandomXS128;

import java.util.ArrayList;
import java.util.List;

import gparap.games.shooter.Background;
import gparap.games.shooter.Globals;
import gparap.games.shooter.HUD;
import gparap.games.shooter.Shooter;
import gparap.games.shooter.objects.Bullet;
import gparap.games.shooter.objects.Enemy;
import gparap.games.shooter.objects.Player;
import gparap.games.shooter.objects.Shield;

public class Play implements Screen {
    private final Shooter game;
    private final Background background;
    private final gparap.games.shooter.objects.Player player;
    private final gparap.games.shooter.objects.Shield shield;
    private List<gparap.games.shooter.objects.Enemy> enemies;
    private int enemyActivationCounter;
    private int enemyActivationFactor;
    private final HUD hud;
    private Preferences preferences;
    private final Music music;

    public Play(Shooter game) {
        this.game = game;
        background = new Background(game);
        player = new Player(game);
        shield = new Shield(game);
        initEnemies();
        initPreferences();
        hud = new HUD(game);
        music = Gdx.audio.newMusic(Gdx.files.internal(Globals.TEXT_MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(0.25f);
        music.play();
    }

    @Override
    public void show() {

    }

    private void gameUpdate() {
        checkForNewHighScore();
        checkIfGameOver();
        handleDifficulty();
        activateRandomEnemy();
        checkForCollisions();
        randomActivateShield();
        hud.update();
    }

    @Override
    public void render(float delta) {
        //CLEAR
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //UPDATE
        gameUpdate();
        background.update(delta);
        player.update(delta);
        shield.update(delta);
        for (gparap.games.shooter.objects.Enemy enemy : enemies) {
            enemy.update(delta);
            for (Bullet bullet : enemy.getBullets()) {
                bullet.update(delta);
            }
        }

        //DRAW
        game.getSpriteBatch().begin();
        background.draw();
        shield.draw();
        player.draw();
        for (gparap.games.shooter.objects.Enemy enemy : enemies) {
            enemy.draw();
        }
        game.getSpriteBatch().end();
        game.getSpriteBatch().setProjectionMatrix(hud.getCamera().combined);
        hud.render();
    }

    @Override
    public void resize(int width, int height) {
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
    }

    //----------------------------------------------------------------------------------------------
    // HELPERS
    //----------------------------------------------------------------------------------------------

    private void initPreferences() {
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES_SHOOTER);
        preferences.putInteger(Globals.PREFERENCES_PREVIOUS_HIGH_SCORE, preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE));
        preferences.putInteger(Globals.PREFERENCES_SCORE, Globals.PREFERENCES_DEFAULT);
        preferences.flush();
    }

    private void initEnemies() {
        enemyActivationCounter = 0;
        enemyActivationFactor = 8;
        enemies = new ArrayList<>();

        //create 16 enemies (default)
        for (int i = 0; i < 16; i++) {
            enemies.add(new gparap.games.shooter.objects.Enemy(game));
        }
    }

    //add enemies with special counter
    private void activateRandomEnemy() {
        enemyActivationCounter += 1;
        if (enemyActivationCounter % enemyActivationFactor == 0) {
            int random = new RandomXS128().nextInt(10);
            if (random == 0) {
                //activate random enemy
                enemies.get(new RandomXS128().nextInt(15)).setActive(true);
            }
            //flush counter
            if (enemyActivationCounter > 1000) {
                enemyActivationCounter = 0;
            }
        }
    }

    //perform collision detection in all game objects (except bullet to bullet)
    private void checkForCollisions() {
        for (Enemy enemy : enemies) {
            if (!enemy.isVisible()) {
                continue;
            }
            //enemy with player collision
            if (player.getCollisionRectangle().x + player.getWidth() > enemy.getCollisionRectangle().x &&
                player.getCollisionRectangle().x < enemy.getCollisionRectangle().width &&
                player.getCollisionRectangle().y + player.getHeight() > enemy.getCollisionRectangle().y &&
                player.getCollisionRectangle().y < enemy.getCollisionRectangle().height) {
                    enemy.remove();
                    player.removeLife();
                    hud.setLife(player.getLife());
                    break;
            }
            //enemy with player bullets collision
            for (Bullet bullet : player.getBullets()) {
                if (bullet.getCollisionRectangle().x > enemy.getCollisionRectangle().x &&
                    bullet.getCollisionRectangle().x < enemy.getCollisionRectangle().width &&
                    bullet.getCollisionRectangle().y > enemy.getCollisionRectangle().y &&
                    bullet.getCollisionRectangle().y < enemy.getCollisionRectangle().height) {
                        enemy.remove();
                        bullet.setFired(false);
                        bullet.setHiddenPosition(); //disappear while not fired
                        player.addScore();
                        hud.setScore(player.getScore());
                        break;
                }
            }
            //enemy bullets with player collision
            for (Bullet bullet : enemy.getBullets()) {
                if (player.getCollisionRectangle().x + player.getWidth() > bullet.getCollisionRectangle().x &&
                    player.getCollisionRectangle().x < bullet.getCollisionRectangle().width &&
                    player.getCollisionRectangle().y + player.getHeight() / 2 > bullet.getCollisionRectangle().y &&
                    player.getCollisionRectangle().y < bullet.getCollisionRectangle().height) {
                        if (bullet.isFired()) {
                            bullet.setFired(false);
                            player.removeLife();
                            hud.setLife(player.getLife());
                            break;
                        }
                }
            }
        }

        //player with shield collision
        if (player.getCollisionRectangle().x + player.getWidth() > shield.getCollisionRectangle().x &&
            player.getCollisionRectangle().x < shield.getCollisionRectangle().width &&
            player.getCollisionRectangle().y + player.getHeight() > shield.getCollisionRectangle().y &&
            player.getCollisionRectangle().y < shield.getCollisionRectangle().height) {
                if (shield.isActive()) {
                    shield.remove();
                    player.addLife();
                    hud.setLife(player.getLife());
                }
        }
    }

    private void checkIfGameOver() {
        if (player.getLife() < 0) {
            music.stop();
            music.pause();
            music.dispose();
            preferences.putInteger(Globals.PREFERENCES_SCORE, player.getScore());
            preferences.flush();
            game.setScreen(new Menu(game, true));
            dispose();
        }
    }

    private void handleDifficulty() {
        int score = player.getScore();
        if (score > 100 && score < 200) {
            Globals.ENEMY_BULLET_MAX = 3;
            enemyActivationFactor = 7;
        } else if (score > 200 && score < 300) {
            Globals.ENEMY_BULLET_MAX = 4;
            enemyActivationFactor = 6;
        } else if (score > 300 && score < 400) {
            Globals.ENEMY_BULLET_MAX = 5;
            enemyActivationFactor = 5;
        } else if (score > 400 && score < 500) {
            Globals.ENEMY_BULLET_MAX = 6;
            enemyActivationFactor = 4;
        } else if (score > 500) {
            Globals.ENEMY_BULLET_MAX = 7;
            enemyActivationFactor = 3;
        }
    }

    private void randomActivateShield() {
        int score = player.getScore();
        if (score > 100) {
            int random = new RandomXS128().nextInt(1000);
            if (random == 0) {
                shield.setActive(true);
            }
        }
    }

    private void checkForNewHighScore() {
        if (player.getScore() > getHighScore()) {
            setHighScore(player.getScore());
            hud.setHighScore(getHighScore());
        }
    }

    private void setHighScore(int newValue) {
        preferences.putInteger(Globals.PREFERENCES_HIGH_SCORE, newValue);
        preferences.flush();
    }

    private int getHighScore() {
        return preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE);
    }
}
