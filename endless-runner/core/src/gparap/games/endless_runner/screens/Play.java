/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.endless_runner.Background;
import gparap.games.endless_runner.objects.spawnables.Bonus;
import gparap.games.endless_runner.managers.DifficultyManager;
import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.managers.EnemyManager;
import gparap.games.endless_runner.physics.GameContactListener;
import gparap.games.endless_runner.objects.GameObject;
import gparap.games.endless_runner.objects.spawnables.GameSpawnable;
import gparap.games.endless_runner.HUD;
import gparap.games.endless_runner.managers.PlatformManager;
import gparap.games.endless_runner.objects.Player;

import static gparap.games.endless_runner.utils.Globals.*;

public class Play implements Screen {
    private final EndlessRunner game;
    private World world;
    private OrthographicCamera camera;
    private Viewport viewport;
    private PlatformManager platformManager;
    private GameObject player;
    private boolean isGamePlaying;
    private float gameStartTimer;
    private GameSpawnable bonus;
    private EnemyManager enemyManager;
    private HUD hud;
    private Preferences preferences;
    private float distance;
    private BitmapFont font;
    private Background background;
    private Music music;
    private DifficultyManager difficultyManager;

    public Play(EndlessRunner game) {
        this.game = game;
        init();
    }

    private void init() {
        //game world
        Box2D.init();
        world = new World(new Vector2(0, -GRAVITY), true);
        world.setContactListener(new GameContactListener());

        //game camera
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(WIDTH * SCALE_FACTOR, HEIGHT * SCALE_FACTOR, camera);

        //game objects
        background = new Background(game);
        platformManager = new PlatformManager(world);
        player = new Player(world, game.getAssetManager());
        bonus = new Bonus(game, world, -100, -100);
        enemyManager = new EnemyManager(game, world);

        //game helpers
        isGamePlaying = false;
        gameStartTimer = 2.0f;
        difficultyManager = new DifficultyManager();

        //heads up display
        distance = 0;
        initPreferences();
        hud = new HUD(game.getSpriteBatch());
        font = new BitmapFont();

        //audio
        music = Gdx.audio.newMusic(Gdx.files.internal(TEXT_MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(0.25f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //clear
        Gdx.gl.glClearColor(208, 244, 247, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (isGamePlaying) {
            music.play();
            distance += delta * 10;
            checkIfGameOver();
            checkForNewHighScore();

            //update game objects
            world.step(Gdx.graphics.getDeltaTime(), 6, 2);
            background.update(delta);
            platformManager.update(delta);
            player.update(delta);
            difficultyManager.update(((Player) player).getScore());
            if (!bonus.isActive()) {
                updateSpawnableWithGeneratedPlatform(bonus);
            }
            bonus.setDifficultyBonus(difficultyManager.getDifficultyBonus());
            bonus.update(delta);
            if (!enemyManager.hasActiveEnemy()) {
                updateSpawnableWithGeneratedPlatform(enemyManager.getRandomEnemy());
            }
            enemyManager.setDifficulty(difficultyManager.getDifficultyEnemy());
            enemyManager.update(delta);
            hud.setScore(((Player) player).getScore());
            hud.setDistance((int) distance);
            hud.update();
        }
        //wait a little before start
        else {
            gameStartTimer -= delta;
            if (gameStartTimer <= 0) {
                isGamePlaying = true;
                ((Player) player).setIdle(false);
            }
        }

        //draw game objects
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        background.draw();
        platformManager.draw(game.getSpriteBatch());
        player.draw(game.getSpriteBatch());

        bonus.draw(game.getSpriteBatch());
        if (enemyManager.hasActiveEnemy()) {
            enemyManager.draw(game.getSpriteBatch());
        }
        game.getSpriteBatch().end();

        //draw heads up display
        game.getSpriteBatch().setProjectionMatrix(hud.getCamera().combined);
        hud.render();

        //display label "Get Ready!"
        if (!isGamePlaying) {
            game.getSpriteBatch().begin();
            font.draw(game.getSpriteBatch(), TEXT_GET_READY, WIDTH / 2f - GET_READY_OFFSET_X, HEIGHT / 2f);
            font.setColor(1, 1, 0, gameStartTimer);
            game.getSpriteBatch().end();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
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
        world.dispose();
        font.dispose();
        background.dispose();
    }

    private void updateSpawnableWithGeneratedPlatform(GameSpawnable spawnable) {
        GameObject platform = platformManager.getLastGeneratedPlatform();
        spawnable.setAvailableX(platform.getX());
        spawnable.setAvailableY(platform.getY());
        spawnable.setAvailableWidth(platform.getWidth());
        spawnable.setAvailableHeight(platform.getHeight());
    }

    private void initPreferences() {
        preferences = Gdx.app.getPreferences(PREFERENCES);
        preferences.putInteger(PREFERENCES_PREVIOUS_HIGH_SCORE, preferences.getInteger(PREFERENCES_HIGH_SCORE));
        preferences.putInteger(PREFERENCES_SCORE, PREFERENCES_DEFAULT);
        preferences.putInteger(PREFERENCES_DISTANCE, PREFERENCES_DEFAULT);
        preferences.flush();
    }

    private void checkIfGameOver() {
        if (((Player) player).getLife() == 0 || player.getY() < 0) {
            isGamePlaying = false;

            //handle audio
            ((Player) player).sfxGameOVer();
            music.stop();
            music.pause();
            music.dispose();

            //save preferences
            preferences.putInteger(PREFERENCES_SCORE, ((Player) player).getScore());
            preferences.putInteger(PREFERENCES_DISTANCE, (int) distance);
            preferences.flush();

            //goto menu
            game.setScreen(new Menu(game, true));
        }
    }

    private void checkForNewHighScore() {
        if (((Player) player).getScore() > getHighScore()) {
            setHighScore(((Player) player).getScore());
            hud.setHighScore(getHighScore());
        }
    }

    private int getHighScore() {
        return preferences.getInteger(PREFERENCES_HIGH_SCORE);
    }

    private void setHighScore(int newValue) {
        preferences.putInteger(PREFERENCES_HIGH_SCORE, newValue);
        preferences.flush();
    }
}
