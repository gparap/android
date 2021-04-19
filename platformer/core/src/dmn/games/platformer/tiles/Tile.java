/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import dmn.games.platformer.HUD;
import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;
import dmn.games.platformer.screens.Play;

public class Tile {
    protected Platformer game;
    protected World world;
    protected Body body;
    protected Fixture fixture;
    protected MapObject mapObject;
    protected Play screen;
    protected HUD hud;
    protected Sound sfx;
    private final Filter filter;
    protected TiledMap map;
    private Vector2 position;

    public Body getBody() {
        return body;
    }

    public Vector2 getPosition() {
        return position;
    }

    //default tile constructor
    protected Tile(World world, MapObject mapObject, HUD hud) {
        this.world = world;
        this.mapObject = mapObject;
        this.hud = hud;
        filter = new Filter();
    }

    //spawned coin constructor
    protected Tile(Play screen, Vector2 position, HUD hud, Platformer game) {
        this.screen = screen;
        this.world = screen.getLevelManager().getWorld();
        this.position = position;
        this.map = screen.getLevelManager().getCurrentLevel().getMap();
        this.hud = hud;
        this.game = game;
        filter = new Filter();
    }

    /**
     * Creates the body of a tile.
     *
     * @param isSensor     if body is a sensor or not
     * @param categoryBits collision category bits
     * @param maskBits     collision mask bits
     * @param offset       offset from default map position
     */
    protected void createBody(boolean isSensor, short categoryBits, short maskBits, Vector2 offset) {
        //create and add the body to the game world
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.type = BodyDef.BodyType.StaticBody;
        if (offset == null) {
            //position based on map
            bodyDefinition.position.set(
                    ((RectangleMapObject) mapObject).getRectangle().x * Platformer.SCALE_FACTOR,
                    ((RectangleMapObject) mapObject).getRectangle().y * Platformer.SCALE_FACTOR);
        } else {
            //position based on offset
            bodyDefinition.position.set(offset.x * Platformer.SCALE_FACTOR, offset.y * Platformer.SCALE_FACTOR);
        }
        body = world.createBody(bodyDefinition);
        //create the shape of the fixture of the body
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
                ((RectangleMapObject) mapObject).getRectangle().width / 2f * Platformer.SCALE_FACTOR,
                ((RectangleMapObject) mapObject).getRectangle().height / 2f * Platformer.SCALE_FACTOR,
                new Vector2(
                        ((RectangleMapObject) mapObject).getRectangle().width / 2f * Platformer.SCALE_FACTOR,
                        ((RectangleMapObject) mapObject).getRectangle().height / 2f * Platformer.SCALE_FACTOR),
                0);
        //create the fixture of the body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        if (isSensor) {
            fixtureDefinition.isSensor = true;                      //don't collide with the body, just sense it
        }
        if (categoryBits != 0) {
            fixtureDefinition.filter.categoryBits = categoryBits;   //set the collision category bits
        }
        if (maskBits != 0) {
            fixtureDefinition.filter.maskBits = maskBits;           //set the collision mask bits
        }
        fixture = body.createFixture(fixtureDefinition);
        fixture.setUserData(this);                                  //reference the body in the game world
        shape.dispose();                                            //don't need the shape anymore
    }

    /**
     * Acts when hit with the upper player sensor.
     */
    public void hitBySensor() {

    }

    /**
     * Acts when hit with the player body.
     */
    public void hitByBody(Player player) {

    }

    /**
     * Sets the collision category bits.
     */
    @SuppressWarnings("SameParameterValue")
    protected void setCategoryBits(short categoryBits) {
        filter.categoryBits = categoryBits;
        fixture.setFilterData(filter);
    }

    /**
     * Removes the tile (texture) that is inside the map object.
     *
     * @param mapObject mapObject
     */
    protected void removeTile(MapObject mapObject) {
        ((TiledMapTileLayer) map.getLayers().get(1)).setCell(
                ((int) (((RectangleMapObject) mapObject).getRectangle().x * Platformer.SCALE_FACTOR)),
                (int) (((RectangleMapObject) mapObject).getRectangle().y * Platformer.SCALE_FACTOR),
                null);
    }

    /**
     * Transforms the tile (changes the texture) that is inside the map object.
     *
     * @param mapObject        mapObject
     * @param mapObjectNewName an optional new map object property name
     * @param tileSetName      tileSetName
     * @param tileId           tileId
     */
    public void transformTile(MapObject mapObject, String mapObjectNewName, String tileSetName, int tileId) {
        if (!(mapObjectNewName.isEmpty()))
            mapObject.setName(mapObjectNewName);
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) map.getLayers().get(1)).getCell(
                ((int) (((RectangleMapObject) mapObject).getRectangle().x * Platformer.SCALE_FACTOR)),
                (int) (((RectangleMapObject) mapObject).getRectangle().y * Platformer.SCALE_FACTOR));
        if (cell != null)   //necessary evil - sometime crashes here
            cell.setTile(getTileFromTileSet(tileSetName, tileId));
    }

    /**
     * Gets a specific tile from a specific map tile set.
     *
     * @param tileSetName tileSetName
     * @param tileId      tileId
     * @return tile
     */
    private TiledMapTile getTileFromTileSet(String tileSetName, int tileId) {
        TiledMapTileSet tileSet = map.getTileSets().getTileSet(tileSetName);
        return tileSet.getTile(tileId + 1);
    }
}
