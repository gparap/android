/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;
import java.util.List;

import gparap.games.racing.Globals;
import gparap.games.racing.HUD;
import gparap.games.racing.Racing;
import gparap.games.racing.Road;
import gparap.games.racing.vehicles.Driver;
import gparap.games.racing.vehicles.Enemy;

public class Race implements Screen {
    private final Racing game;
    private final Road road;
    private final Driver driver;
    private List<Enemy> enemies;
    private final HUD hud;
    Preferences preferences;
    private final float startPosition1, startPosition2, startPosition3, startPosition4;
    private final Music music;
    private boolean isGamePlaying;
    private float gameStartTimer;
    private final BitmapFont font;

    public Race(Racing game) {
        this.game = game;
        road = new Road(game);
        driver = new Driver(game);

        //init audio
        music = Gdx.audio.newMusic(Gdx.files.internal(Globals.AUDIO_RACING_PATH));
        music.setLooping(true);
        music.setVolume(0.5f);

        //init enemy starting positions Y based on game dimensions
        startPosition1 = game.getHeight();
        startPosition2 = game.getHeight() + game.getHeight() / 2f;
        startPosition3 = game.getHeight() + game.getHeight() / 2f + game.getHeight() / 2f;
        startPosition4 = game.getHeight() + game.getHeight() / 2f + game.getHeight() / 2f + game.getHeight() / 2f;

        initEnemies();
        initPreferences();
        hud = new HUD(game);

        //init start of game
        isGamePlaying = false;
        gameStartTimer = 4.0f;
        font = new BitmapFont(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_FNT));
    }

    @Override
    public void show() {

    }

    private void gameUpdate() {
        checkForNewRecord();
        checkIfGameOver();
        fixEnemyPosition();
        checkForCollisions();
        hud.update();
    }

    @Override
    public void render(float delta) {
        //clear
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (isGamePlaying) {
            driver.setDistance(driver.getDistance() + delta * 100);

            //play car engine audio
            music.play();

            //update
            road.update(delta);
            driver.update(delta);
            for (gparap.games.racing.vehicles.Enemy enemy : enemies) {
                enemy.update(delta);
            }
            gameUpdate();

            //draw
            game.getSpriteBatch().begin();
            road.draw();
            driver.draw();
            for (gparap.games.racing.vehicles.Enemy enemy : enemies) {
                enemy.draw();
            }
            game.getSpriteBatch().end();

            //render the heads up display
            hud.setDistance((int) driver.getDistance());
            game.getSpriteBatch().setProjectionMatrix(hud.getCamera().combined);
            hud.render();
        }
        //wait a while before start
        else {
            gameStartTimer -= delta;
            if (gameStartTimer <= 1) {
                isGamePlaying = true;
            }
            game.getSpriteBatch().begin();
            road.draw();
            driver.draw();
            font.draw(game.getSpriteBatch(), "Race in " + " " + (int) gameStartTimer, game.getWidth() / 2f - 2 * (float) font.getData().xChars.length, game.getHeight() / 2f);
            font.setColor(0, 0, 0, gameStartTimer);
            game.getSpriteBatch().end();
        }
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
        font.dispose();
    }

    //--------
    // HELPERS
    //--------
    private void initPreferences() {
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
        preferences.putInteger(Globals.PREFERENCES_PREVIOUS_RECORD, preferences.getInteger(Globals.PREFERENCES_RECORD));
        preferences.putInteger(Globals.PREFERENCES_DISTANCE, Globals.PREFERENCES_DEFAULT);
        preferences.flush();
    }

    private void initEnemies() {
        enemies = new ArrayList<>();
        enemies.add(new gparap.games.racing.vehicles.Enemy(game, startPosition1));
        enemies.add(new gparap.games.racing.vehicles.Enemy(game, startPosition2));
        enemies.add(new gparap.games.racing.vehicles.Enemy(game, startPosition3));
        enemies.add(new gparap.games.racing.vehicles.Enemy(game, startPosition4));
    }

    //perform collision detection in all game vehicles
    private void checkForCollisions() {
        for (gparap.games.racing.vehicles.Enemy enemy : enemies) {
            //enemy with driver collision
            if (driver.getCollisionRectangle().x + driver.getWidth() > enemy.getCollisionRectangle().x &&
                    driver.getCollisionRectangle().x < enemy.getCollisionRectangle().width &&
                    driver.getCollisionRectangle().y + driver.getHeight() > enemy.getCollisionRectangle().y &&
                    driver.getCollisionRectangle().y < enemy.getCollisionRectangle().height) {
                //game over
                if (!driver.isHit()) {
                    driver.setHit(true);
                }
                break;
            }
        }
    }

    private void checkIfGameOver() {
        if (driver.isHit()) {
            isGamePlaying = false;
            handleSound();
            preferences.putInteger(Globals.PREFERENCES_DISTANCE, (int) driver.getDistance());
            preferences.flush();
            game.setScreen(new Menu(game, true));
            dispose();
        }
    }

    private void checkForNewRecord() {
        if (driver.getDistance() > getRecord()) {
            setRecord((int) driver.getDistance());
        }
    }

    private void setRecord(int newValue) {
        preferences.putInteger(Globals.PREFERENCES_RECORD, newValue);
        preferences.flush();
    }

    private int getRecord() {
        return preferences.getInteger(Globals.PREFERENCES_RECORD);
    }

    //fix the position of the enemies before they become visible...
    //...so as not to take up the same space
    private void fixEnemyPosition() {
        for (int i = 0; i < enemies.size() - 1; i++) {
            //helper
            gparap.games.racing.vehicles.Enemy enemy1 = enemies.get(i);
            Enemy enemy2 = enemies.get(i + 1);

            //check for overlapping
            if (enemy1.getCollisionRectangle().x + enemy1.getWidth() > enemy2.getCollisionRectangle().x &&
                enemy1.getCollisionRectangle().x < enemy2.getCollisionRectangle().width &&
                enemy1.getCollisionRectangle().y + enemy1.getHeight() > enemy2.getCollisionRectangle().y &&
                enemy1.getCollisionRectangle().y < enemy2.getCollisionRectangle().height) {
                //fix position
                enemy1.getPosition().set(enemy2.getPosition().x - enemy1.getWidth(), enemy2.getPosition().y + 2f * enemy1.getHeight());

                //if enemy is outside of screen fix position again (inside screen)
                if (enemy1.getPosition().x < 0) {
                    enemy1.getPosition().set(enemy2.getPosition().x + enemy1.getWidth(),
                                             enemy2.getPosition().y + 2f * enemy1.getHeight());
                } else if (enemy1.getPosition().x + enemy1.getWidth() > game.getWidth()) {
                    enemy1.getPosition().set(
                            game.getWidth() - (enemy2.getPosition().x + enemy2.getWidth()) - (enemy1.getPosition().x + enemy1.getWidth()),
                            enemy2.getPosition().y + 2f * enemy1.getHeight());
                }
            }
        }
    }

    private void handleSound() {
        music.stop();
        music.pause();
        music.dispose();
    }
}
