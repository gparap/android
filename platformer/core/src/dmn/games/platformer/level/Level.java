/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

import dmn.games.platformer.Platformer;
import dmn.games.platformer.tiles.Coin;
import dmn.games.platformer.enemies.Enemy;

public class Level {
    private final String mapFileName;
    private TiledMap map;
    private final SpriteBatch spriteBatch;
    private OrthogonalTiledMapRenderer mapRenderer;
    private int width, height;
    private HashMap<String, int[]> mapLayers;
    private Array<Enemy> enemies;
    private String songName;
    @SuppressWarnings("FieldCanBeLocal")
    private final int TILE_COIN_GOLD_ID = 15;

    protected Level(String mapFileName, SpriteBatch spriteBatch) {
        this.mapFileName = mapFileName;
        this.spriteBatch = spriteBatch;
        create();
    }

    public TiledMap getMap() {
        return map;
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return mapRenderer;
    }

    /**
     * Gets the level map width in pixels.
     *
     * @return map width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the level map height in pixels.
     *
     * @return map height
     */
    public int getHeight() {
        return height;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    protected String getSongName() {
        return songName;
    }

    protected void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Creates the level map.
     */
    private void create() {
        //create the map
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load(String.valueOf(Gdx.files.internal(mapFileName)));
        mapRenderer = new OrthogonalTiledMapRenderer(map, Platformer.SCALE_FACTOR, spriteBatch);
        //create the map layers
        mapLayers = new HashMap<>();
        mapLayers.put("background", new int[]{0});
        mapLayers.put("foreground", new int[]{1});
        mapLayers.put("earth", new int[]{2});
        mapLayers.put("slope", new int[]{3});
        mapLayers.put("exit", new int[]{4});
        mapLayers.put("crates", new int[]{5});
        mapLayers.put("coins", new int[]{6});
        mapLayers.put("candys", new int[]{7});
        mapLayers.put("enemies", new int[]{8});
        //create the map properties
        width = map.getProperties().get("width", Integer.class) *       //total tiles
                map.getProperties().get("tilewidth", Integer.class);    //single tile
        height = map.getProperties().get("height", Integer.class) *     //total tiles
                map.getProperties().get("tileheight", Integer.class);   //single tile
        //initialize the map level enemies
        enemies = new Array<>();
    }

    /**
     * Gets level map layer as array. (useful for map renderer)
     *
     * @param layerName map layer name
     * @return map layer
     */
    public int[] getMapLayerAsArray(String layerName) {
        return mapLayers.get(layerName);
    }

    /**
     * Gets level map layer objects.
     *
     * @param layerName map layer name
     * @return map layer objects
     */
    protected MapObjects getMapObjects(String layerName) {
        return map.getLayers().get(layerName).getObjects();
    }

    /**
     * Removes enemies from world.
     *
     * @param world world
     */
    public void removeEnemies(World world) {
        for (Enemy enemy : enemies) {
            if (enemy.isJustStamped()) {
                enemy.getBody().setLinearVelocity(0, enemy.getBody().getLinearVelocity().y);
            }
            //remove enemy object from (level) enemies list and enemy body from world
            if (enemy.isToBeRemoved()) {
                //the enemy body should be smoothly deleted (not instantly)
                enemy.getBody().setLinearVelocity(0, enemy.getBody().getLinearVelocity().y);
                if (enemy.getStateTimeBody() < 0) {
                    enemies.removeValue(enemy, true);   //remove enemy from level
                    world.destroyBody(enemy.getBody());              //destroy enemy body
                }
            }
        }
    }

    public void createCoins(Array<Coin> coins, LevelManager levelManager) {
        for (Coin coin : coins) {
            if (coin.getBody() == null) {
                coin.createBody(coin.getPosition());
                levelManager.getCurrentLevelWorldBodies().add((coin.getBody()));
                coin.transformTile(coin.getRectangleMapObject(), "",
                        "tiles", TILE_COIN_GOLD_ID);
                coins.removeValue(coin, true);
            }
        }
    }
}
