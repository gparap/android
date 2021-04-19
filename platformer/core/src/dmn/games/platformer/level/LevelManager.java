/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import dmn.games.platformer.physics.BodyFactory;
import dmn.games.platformer.physics.CollisionDetector;
import dmn.games.platformer.HUD;
import dmn.games.platformer.Platformer;
import dmn.games.platformer.screens.Play;
import dmn.games.platformer.tiles.Candy;
import dmn.games.platformer.tiles.Coin;
import dmn.games.platformer.tiles.Crate;
import dmn.games.platformer.tiles.Exit;
import dmn.games.platformer.enemies.Blocker;
import dmn.games.platformer.enemies.Slime;
import dmn.games.platformer.enemies.Snail;

public class LevelManager {
    private Level[] levels;
    private Level currentLevel;
    private int currentLevelIndex;
    private World world;
    private Array<Body> currentLevelWorldBodies;
    private Array<Coin> coinsToBeCreated;
    private final HUD hud;
    private Exit exit;
    private final Play screen;
    private final Platformer game;
    private Music song;

    public Music getSong() {
        return song;
    }

    public LevelManager(Play screen, HUD hud, Platformer game) {
        this.screen = screen;
        this.hud = hud;
        this.game = game;
        init();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentLevelIndex() {
        return currentLevelIndex;
    }

    public void setCurrentLevelIndex(int currentLevelIndex) {
        this.currentLevelIndex = currentLevelIndex + 1;
    }

    public World getWorld() {
        return world;
    }

    public Array<Body> getCurrentLevelWorldBodies() {
        return currentLevelWorldBodies;
    }

    public void setCoinsToBeCreated(Coin coin) {
        coinsToBeCreated.add(coin);
    }

    public Array<Coin> getCoinsToBeCreated() {
        return coinsToBeCreated;
    }

    public Exit getExit() {
        return exit;
    }

    private void setCurrentLevel(Level currentLevel) {
        //set the current level
        this.currentLevel = currentLevel;
        //destroy the game world
        if (world != null) {
            world.dispose();
            createWorld();
        }
        //EARTH-------------------------------------------------------------------------------------
        //create the world bodies of the current map level earth objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("earth")) {
            BodyFactory.createBody(world, mapObject, currentLevelWorldBodies, true);
        }
        //SLOPES------------------------------------------------------------------------------------
        //create the world bodies of the current map level slope objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("slope")) {
            BodyFactory.createBody(world, mapObject, currentLevelWorldBodies, true);
        }
        //ENEMIES-----------------------------------------------------------------------------------
        //create the world bodies of the current map level enemy objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("enemies")) {
            //BLOCKER
            if (mapObject.getName().contains("enemy_blocker")) {
                //if this blocker is a jumper
                if (mapObject.getName().matches("enemy_blocker_j")) {
                    Blocker blocker = new Blocker(world, mapObject,
                            game, false);
                    blocker.setType('J');
                    this.currentLevel.getEnemies().add(blocker);
                }
                //if this blocker is a mixed one (a walker and a jumper)
                else if (mapObject.getName().matches("enemy_blocker_m")) {
                    Blocker blocker = new Blocker(world, mapObject,
                            game, false);
                    blocker.setType('M');
                    this.currentLevel.getEnemies().add(blocker);
                }
                //if this blocker is a boss (a big guy)
                //   tip: blocker bosses can move and jump
                else if (mapObject.getName().matches("enemy_blocker_b")) {
                    Blocker blocker = new Blocker(world, mapObject,
                            game, true);
                    blocker.setType('B');
                    this.currentLevel.getEnemies().add(blocker);
                } else {
                    Blocker blocker = new Blocker(world, mapObject,
                            game, false);
                    //blocker.setType('B');
                    this.currentLevel.getEnemies().add(blocker);
                }
            }
            //SLIME
            else if (mapObject.getName().contains("enemy_slime")) {
                //this slime is blue
                if (mapObject.getName().matches("enemy_slime_b")) {
                    this.currentLevel.getEnemies().add(new Slime(world, mapObject,
                            game, 'b'));
                }
                //this is the default slime
                else {
                    this.currentLevel.getEnemies().add(new Slime(world, mapObject,
                            game, 'd'));
                }
            }
            //SNAIL
            else if (mapObject.getName().contains("enemy_snail")) {
                this.currentLevel.getEnemies().add(new Snail(world, mapObject, game));
            }
        }
        //TILES-------------------------------------------------------------------------------------
        //create the world bodies of the current map level crate objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("crates")) {
            Crate crate = new Crate(world, mapObject, this.currentLevel.getMap(), hud, screen, game);
            currentLevelWorldBodies.add(crate.getBody());
        }
        //create the world bodies of the current map level coin objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("coins")) {
            Coin coin = new Coin(world, mapObject, this.currentLevel.getMap(), hud, game);
            currentLevelWorldBodies.add(coin.getBody());
        }
        //create the world bodies of the current map level candy objects
        for (MapObject mapObject : this.currentLevel.getMapObjects("candys")) {
            Candy candy = new Candy(world, mapObject, this.currentLevel.getMap(), hud, game);
            currentLevelWorldBodies.add(candy.getBody());
        }
        //create the world body of the current map level exit object
        for (MapObject mapObject : this.currentLevel.getMapObjects("exit")) {
            if (mapObject.getProperties().get("type") != null && mapObject.getName().matches("sign_exit")) {
                Exit exit = new Exit(world, mapObject, hud);
                currentLevelWorldBodies.add(exit.getBody());
                this.exit = exit;
            }
        }
        //create the song for the level
        song = Gdx.audio.newMusic(Gdx.files.internal(this.currentLevel.getSongName()));
        song.setLooping(true);
        song.setVolume(1.0f);
        song.play();
    }

    /**
     * Custom object default initialization.
     */
    private void init() {
        currentLevelIndex = -1;
        currentLevelWorldBodies = new Array<>();
        coinsToBeCreated = new Array<>();
    }

    /**
     * Adds a collection of levels to the level manager.
     *
     * @param levels levels
     */
    public void addLevels(Level[] levels) {
        this.levels = levels;
    }

    /**
     * Creates the game world; usually at the start of the game or at a new level.
     */
    public void createWorld() {
        world = new World(new Vector2(0, -9.8f), true);
        world.setContactListener(new CollisionDetector());
    }

    //destroy the game world
    public void destroyWorld() {
        world.dispose();
    }

    public void gotoNextLevel(int index) {
        setCurrentLevel(levels[index]);
    }
}
