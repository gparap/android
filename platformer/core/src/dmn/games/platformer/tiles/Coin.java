/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.tiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import dmn.games.platformer.HUD;
import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;
import dmn.games.platformer.screens.Play;

public class Coin extends Tile {
    public boolean isToBeCreated, isToBeDisappeared;
    RectangleMapObject rectangleMapObject;

    public Coin(World world, MapObject mapObject, TiledMap map, HUD hud, Platformer game) {
        super(world, mapObject, hud);
        this.map = map;
        this.game = game;
        createBody(true, Platformer.CATEGORY_PICKUP, (short) 0, null);
    }

    protected Coin(Play screen, Vector2 position, HUD hud, Platformer game) {
        super(screen, position, hud, game);
        isToBeCreated = true;
        isToBeDisappeared = true;
        //create the rectangle for debug renderer
        rectangleMapObject = new RectangleMapObject();
        rectangleMapObject.getRectangle().setX(position.x);
        rectangleMapObject.getRectangle().setY(position.y);
        rectangleMapObject.setName("coin_gold");
        rectangleMapObject.getProperties().put("type", "coin_gold");
        rectangleMapObject.getRectangle().setWidth(32f);
        rectangleMapObject.getRectangle().setHeight(32f);
        this.mapObject = rectangleMapObject;
    }

    /**
     * Creates the body of a spawned coin.
     *
     * @param offset spawned offset x,y
     */
    public void createBody(Vector2 offset) {
        super.createBody(true, Platformer.CATEGORY_PICKUP, (short) 0, offset);
    }

    public RectangleMapObject getRectangleMapObject() {
        return rectangleMapObject;
    }

    public void hitByBody(Player player) {
        if (mapObject != null) {
            if (mapObject.getProperties().get("type") != null &&
                    mapObject.getName().matches("coin_gold")) {
                hud.addScore(5);
                player.addCoin('G');   //better here than in contact listener (performance)
            } else if (mapObject.getName().matches("coin_silver")) {
                hud.addScore(3);
                player.addCoin('S');
            } else if (mapObject.getName().matches("coin_brown")) {
                hud.addScore(1);
                player.addCoin('B');
            }
            setCategoryBits(Platformer.CATEGORY_NULL);  //no more collisions with this object
            removeTile(mapObject);                      //make the texture disappear

            //play sfx
            sfx = game.getAssetManager().get("sfx/pickup.wav");
            sfx.play(0.75f);
        }
    }
}
