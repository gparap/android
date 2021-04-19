/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dmn.games.platformer.Background;
import dmn.games.platformer.HUD;
import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;
import dmn.games.platformer.touch.TouchManager;
import dmn.games.platformer.touch.TouchPad;
import dmn.games.platformer.enemies.Enemy;
import dmn.games.platformer.enemies.Blocker;
import dmn.games.platformer.enemies.Slime;
import dmn.games.platformer.enemies.Snail;
import dmn.games.platformer.level.LevelFactory;
import dmn.games.platformer.level.LevelManager;

public class Play implements Screen {
    private final Platformer game;
    private final Viewport viewport;
    private final OrthographicCamera camera;
    private final Player player;
    private final TouchPad touchPad;
    private final TouchManager touchManager;
    private final HUD hud;
    private final LevelManager levelManager;
    private final Preferences preferences;
    private Background background;

    public Play(Platformer game, int currentLevelIndex) {
        this.game = game;
        preferences = Gdx.app.getPreferences("platformer_preferences");
        //get the user device dimensions in logical pixels (aspect ratio = height / width)
        float deviceWidth = Gdx.graphics.getWidth();
        float deviceHeight = Gdx.graphics.getHeight();
        //create the camera
        camera = new OrthographicCamera(15f, //how many units (map tiles) we want to display
                                        15f * deviceHeight / deviceWidth);
        //create the heads up display
        hud = new HUD(game.getSpriteBatch());
        //default background
        background = new Background(game, "background/colored_land.png",
                                          "background/colored_grass.png", camera);
        //the first time the game starts (starting level)
        if (currentLevelIndex == -1) {
            //game info that is passing through levels (preferences) is set to DEFAULT
            preferences.putInteger(Platformer.LEVEL, hud.getLevelDefault());
            preferences.putInteger(Platformer.SCORE, hud.getScoreDefault());
            preferences.putInteger(Platformer.KEY, hud.getKeyDefault());
            preferences.putInteger(Platformer.LIFE, hud.getLifeDefault());
            preferences.putInteger(Platformer.COINS_TOTAL, 0);
            preferences.putInteger(Platformer.COINS_TOTAL_GOLD, 0);
            preferences.putInteger(Platformer.COINS_TOTAL_SILVER, 0);
            preferences.putInteger(Platformer.COINS_TOTAL_BROWN, 0);
            preferences.putInteger(Platformer.ENEMIES_STAMPED_TOTAL, 0);
            preferences.putInteger(Platformer.CANDYS_TOTAL, 0);
            preferences.putInteger(Platformer.CANDYS_GUMMY_GREEN, 0);
            preferences.putInteger(Platformer.CANDYS_GUMMY_RED, 0);
            preferences.putInteger(Platformer.CANDYS_WAFFLE_CHOCO, 0);
            preferences.putInteger(Platformer.CANDYS_WAFFLE_WHITE, 0);
            preferences.flush();
        } else {
            //game info that is passing through levels (preferences)
            hud.setLevel(preferences.getInteger(Platformer.LEVEL) + 1);
            hud.setScore(preferences.getInteger(Platformer.SCORE));
            hud.setKey(preferences.getInteger(Platformer.KEY));
            hud.setLife(preferences.getInteger(Platformer.LIFE));
            preferences.flush();
            //change background (candy land)
            if (preferences.getInteger(Platformer.LEVEL) + 1 > 5) {
                background = new Background(game, "background/blue_land.png",
                                                  "background/blue_grass.png", camera);
            }
        }
        //create all levels
        LevelFactory levelFactory = new LevelFactory(game);
        //create the level manager
        levelManager = new LevelManager(this, hud, game);
        levelManager.createWorld();
        levelManager.addLevels(levelFactory.getLevels());
        levelManager.setCurrentLevelIndex(currentLevelIndex);
        levelManager.destroyWorld();
        levelManager.createWorld();
        levelManager.gotoNextLevel(levelManager.getCurrentLevelIndex());
        //create the player
        player = new Player(levelManager.getWorld(),
                new Vector2(3, 5), 56, 56, hud, game);
        //position the camera at the bottom left corner of the screen
        updateCamera();
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight);
        //create the touch stuff
        touchPad = new TouchPad(game);
        touchManager = new TouchManager(touchPad, player);
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    @Override
    public void show() {

    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //CLEAR
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background.draw();
        //UPDATE
        touchManager.update();
        hud.update(delta);
        //update game world
        levelManager.getWorld().step(delta, 6, 2);
        levelManager.getCurrentLevel().removeEnemies(levelManager.getWorld());  //remove bodies and textures
        levelManager.getCurrentLevel().createCoins(
                levelManager.getCoinsToBeCreated(), levelManager);
        levelManager.getWorld().getBodies(levelManager.getCurrentLevelWorldBodies());
        for (Body body : levelManager.getCurrentLevelWorldBodies()) {
            if (body.getUserData() != null) {
                //update player
                if (body.getUserData().getClass().isAssignableFrom(Player.class)) {
                    ((Player) body.getUserData()).update(delta);
                    //update (scroll) backgrounds (when player inside map)
                    if (body.getPosition().x > 0 &&
                        body.getPosition().x < levelManager.getCurrentLevel().getWidth() * Platformer.SCALE_FACTOR) {
                        updateCamera();
                        if (body.getLinearVelocity().x > 0) {
                            background.update(delta, 'R', body.getLinearVelocity().x, camera);
                        } else {
                            background.update(delta, 'L', body.getLinearVelocity().x, camera);
                        }
                    }
                    //if player respawns play song again from start
                    if (((Player) body.getUserData()).isRespawn()) {
                        ((Player) body.getUserData()).setRespawn(false);
                        background.reset();
                    }
                }
                //update enemy blocker
                else if (body.getUserData().getClass().isAssignableFrom(Blocker.class) ||
                         body.getUserData().getClass().isAssignableFrom(Slime.class) ||
                         body.getUserData().getClass().isAssignableFrom(Snail.class)) {
                    //activate enemies in a near distance from player
                    if (((body.getPosition().x > player.getPosition().x + camera.viewportWidth) &&
                         (body.getPosition().x < player.getPosition().x + camera.viewportWidth + (camera.viewportWidth / 3f)))
                            ||
                        ((body.getPosition().x > player.getPosition().x - camera.viewportWidth) &&
                         (body.getPosition().x < player.getPosition().x))) {
                        body.setActive(true);
                    }
                    //deactivate enemies in far distance from player
                    else if ((body.getPosition().x < player.getPosition().x - camera.viewportWidth - (camera.viewportWidth / 3f))
                            ||
                            (body.getPosition().x > player.getPosition().x + camera.viewportWidth + (camera.viewportWidth / 3f))) {
                        body.setActive(false);
                    }
                    if (body.isActive())
                        ((Enemy) body.getUserData()).update(delta);
                }
            }
        }
        updateCamera();
        //RENDER
        //render level background and foreground layers
        levelManager.getCurrentLevel().getMapRenderer().setView(camera);
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("background"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("foreground"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("earth"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("slope"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("exit"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("crates"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("coins"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("candys"));
        levelManager.getCurrentLevel().getMapRenderer().render(levelManager.getCurrentLevel().getMapLayerAsArray("enemies"));

        //render the player
        game.getSpriteBatch().begin();
        player.render(game.getSpriteBatch());
        for (Enemy enemy : levelManager.getCurrentLevel().getEnemies()) {
            enemy.render(game.getSpriteBatch());
        }
        game.getSpriteBatch().end();
        //render the touch pad
        game.getSpriteBatch().setProjectionMatrix(touchPad.getCamera().combined);
        touchPad.render();
        //render the heads up display
        game.getSpriteBatch().setProjectionMatrix(touchPad.getCamera().combined);
        hud.render();
        //check if game is over
        if (player.isDead()) {
            levelManager.getSong().stop();
            levelManager.getSong().pause();
            levelManager.getSong().dispose();
            //game info that is passing through levels (preferences) is set to DEFAULT
            preferences.putInteger(Platformer.COINS_TOTAL, getPreferenceTotal(Platformer.COINS_TOTAL, player.getCoinsTotal()));
            preferences.putInteger(Platformer.COINS_TOTAL_GOLD, getPreferenceTotal(Platformer.COINS_TOTAL_GOLD, player.getCoinsGold()));
            preferences.putInteger(Platformer.COINS_TOTAL_SILVER, getPreferenceTotal(Platformer.COINS_TOTAL_SILVER, player.getCoinsSilver()));
            preferences.putInteger(Platformer.COINS_TOTAL_BROWN, getPreferenceTotal(Platformer.COINS_TOTAL_BROWN, player.getCoinsBrown()));
            preferences.putInteger(Platformer.ENEMIES_STAMPED_TOTAL, getPreferenceTotal(Platformer.ENEMIES_STAMPED_TOTAL, player.getStamps()));
            preferences.putInteger(Platformer.CANDYS_TOTAL, getPreferenceTotal(Platformer.CANDYS_TOTAL, player.getCandysTotal()));
            preferences.putInteger(Platformer.CANDYS_GUMMY_GREEN, getPreferenceTotal(Platformer.CANDYS_GUMMY_GREEN, player.getGummyGreen()));
            preferences.putInteger(Platformer.CANDYS_GUMMY_RED, getPreferenceTotal(Platformer.CANDYS_GUMMY_RED, player.getGummyRed()));
            preferences.putInteger(Platformer.CANDYS_WAFFLE_CHOCO, getPreferenceTotal(Platformer.CANDYS_WAFFLE_CHOCO, player.getWaffleChoco()));
            preferences.putInteger(Platformer.CANDYS_WAFFLE_WHITE, getPreferenceTotal(Platformer.CANDYS_WAFFLE_WHITE, player.getWaffleWhite()));
            preferences.flush();
            game.setScreen(new GameOver(game,
                    levelManager.getCurrentLevelIndex(),    //didn't complete last level
                    background));
            dispose();
        }
        //check if level is completed and can exit from it
        if (levelManager.getExit().getCanExit()) {
            levelManager.getSong().stop();
            levelManager.getSong().pause();
            levelManager.getSong().dispose();
            //create game info that is passing through levels (preferences)
            createPreferences();
            player.resetStamps();
            player.resetCoins();
            // check if game is completed (no more levels)
            if ((levelManager.getCurrentLevelIndex() + 1) == Platformer.LEVELS_TOTAL) {
                game.setScreen(new GameOver(game,
               levelManager.getCurrentLevelIndex() + 1,    //last level completed
                               background));
            } else {
                //redirect to an after-level-complete screen (achievements)
                game.setScreen(new LevelComplete(game, levelManager.getCurrentLevelIndex(), background));
            }
            dispose();
        }
    }

    /**
     * Called when the application is resized.
     *
     * @param width  width
     * @param height height
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Called when the application is paused.
     */
    @Override
    public void pause() {
    }

    /**
     * Called when the application is resumed.
     */
    @Override
    public void resume() {
    }

    /**
     * Called when this screen is no longer the current screen for a game.
     */
    @Override
    public void hide() {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        levelManager.getCurrentLevel().getMap().dispose();
        levelManager.getCurrentLevel().getMapRenderer().dispose();
        levelManager.getWorld().dispose();
        touchPad.dispose();
        hud.dispose();
    }

    /**
     * Called when the player completes a level.
     */
    private void createPreferences() {
        preferences.putInteger(Platformer.LEVEL, hud.getLevel());
        preferences.putInteger(Platformer.KEY, hud.getKey());
        preferences.putInteger(Platformer.LIFE, hud.getLife());
        preferences.putInteger(Platformer.SCORE, hud.getScore());
        //coins of a completed level
        preferences.putInteger(Platformer.COINS_LEVEL, player.getCoinsTotal());
        preferences.putInteger(Platformer.COINS_GOLD, player.getCoinsGold());
        preferences.putInteger(Platformer.COINS_SILVER, player.getCoinsSilver());
        preferences.putInteger(Platformer.COINS_BROWN, player.getCoinsBrown());
        //candys of a completed level
        preferences.putInteger(Platformer.CANDYS_LEVEL, player.getCandysTotal());
        preferences.putInteger(Platformer.CANDYS_GUMMY_GREEN, player.getGummyGreen());
        preferences.putInteger(Platformer.CANDYS_GUMMY_RED, player.getGummyRed());
        preferences.putInteger(Platformer.CANDYS_WAFFLE_CHOCO, player.getWaffleChoco());
        preferences.putInteger(Platformer.CANDYS_WAFFLE_WHITE, player.getWaffleWhite());
        //coins of all levels combined
        preferences.putInteger(Platformer.COINS_TOTAL, getPreferenceTotal(Platformer.COINS_TOTAL, player.getCoinsTotal()));
        preferences.putInteger(Platformer.COINS_TOTAL_GOLD, getPreferenceTotal(Platformer.COINS_TOTAL_GOLD, player.getCoinsGold()));
        preferences.putInteger(Platformer.COINS_TOTAL_SILVER, getPreferenceTotal(Platformer.COINS_TOTAL_SILVER, player.getCoinsSilver()));
        preferences.putInteger(Platformer.COINS_TOTAL_BROWN, getPreferenceTotal(Platformer.COINS_TOTAL_BROWN, player.getCoinsBrown()));
        //candys of all levels combined
        preferences.putInteger(Platformer.CANDYS_TOTAL, getPreferenceTotal(Platformer.CANDYS_TOTAL, player.getCandysTotal()));
        //stamped enemies of a completed level
        preferences.putInteger(Platformer.ENEMIES_STAMPED_LEVEL, player.getStamps());
        //stamped enemies of all levels combined
        preferences.putInteger(Platformer.ENEMIES_STAMPED_TOTAL, getPreferenceTotal(Platformer.ENEMIES_STAMPED_TOTAL, player.getStamps()));
        //time elapsed of a completed level
        preferences.putInteger(Platformer.TIME_ELAPSED, hud.getTimeElapsed(hud.getTime()));
        preferences.flush();
    }

    /**
     * Add a new value to a preference.
     *
     * @param oldValue The value that exists in the preference.
     * @param newValue The new value that will be added to the preference.
     * @return preference
     */
    private int getPreferenceTotal(String oldValue, int newValue) {
        int preference = preferences.getInteger(oldValue, 0);
        preference += newValue;
        return preference;
    }

    /**
     * Updates camera based on player position and level map dimensions.
     */
    private void updateCamera() {
        camera.position.x = Math.min(Math.max
                        (camera.viewportWidth / 2f, player.getPosition().x),
                        (levelManager.getCurrentLevel().getWidth() * Platformer.SCALE_FACTOR) - camera.viewportWidth / 2f);
        camera.position.y = Math.min(Math.max
                        (camera.viewportHeight / 2f, player.getPosition().y),
                        (levelManager.getCurrentLevel().getHeight() * Platformer.SCALE_FACTOR) -  camera.viewportHeight / 2f);
        camera.position.set(camera.position.x, camera.position.y, 0);
        camera.update();
    }
}
