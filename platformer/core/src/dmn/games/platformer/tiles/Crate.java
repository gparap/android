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
import dmn.games.platformer.screens.Play;

public class Crate extends Tile {
    @SuppressWarnings("FieldCanBeLocal")
    private final int TILE_BOX_CRATE_DOUBLE_ID = 5,
            TILE_BOX_COIN_DISABLED_ID = 8,
            SPAWNED_COIN_OFFSET_TOP = 64;

    public Crate(World world, MapObject mapObject, TiledMap map, HUD hud, Play screen, Platformer game) {
        super(world, mapObject, hud);
        this.map = map;
        this.screen = screen;
        this.game = game;
        createBody(false, Platformer.CATEGORY_CRATE,
                (short) (Platformer.CATEGORY_DEFAULT | Platformer.CATEGORY_PLAYER | Platformer.CATEGORY_ENEMY),
                null);
    }

    public void hitBySensor() {
        //if a yellow crate is hit then it is transformed into a brown one
        if (mapObject.getProperties().get("type") != null && mapObject.getName().matches("yellow")) {
            transformTile(mapObject, "brown", "tiles",
                    TILE_BOX_CRATE_DOUBLE_ID);  //change texture
            hud.addScore(5);
        }
        //if a brown crate is hit then it is removed from game
        else if (mapObject.getName().matches("brown")) {
            removeTile(mapObject);                      //make the texture disappear
            setCategoryBits(Platformer.CATEGORY_NULL);  //no more collisions with this object
        }
        //if a yellow_coin crate is hit then it is transformed into a gold coin
        else if (mapObject.getProperties().get("type") != null &&
                mapObject.getName().matches("yellow_coin")) {
            //spawn a coin above the crate
            if (mapObject.getName().matches("yellow_coin")) {
                Coin tileCoin = new Coin(screen,
                                         new Vector2(((RectangleMapObject) mapObject).getRectangle().x,
                                                 ((RectangleMapObject) mapObject).getRectangle().y + SPAWNED_COIN_OFFSET_TOP),
                                         hud, game);
                screen.getLevelManager().setCoinsToBeCreated(tileCoin);
            }
            transformTile(mapObject, "brown", "tiles",
                    TILE_BOX_COIN_DISABLED_ID);  //change texture
            hud.addScore(5);
        }
        //play sfx
        sfx = game.getAssetManager().get("sfx/hit_crate.wav");
        sfx.play(0.75f);
    }
}
