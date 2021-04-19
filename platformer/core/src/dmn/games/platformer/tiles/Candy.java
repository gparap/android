/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.tiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import dmn.games.platformer.HUD;
import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;

public class Candy extends Tile {
    public Candy(World world, MapObject mapObject, TiledMap map, HUD hud, Platformer game) {
        super(world, mapObject, hud);
        this.map = map;
        this.game = game;
        createBody(true, Platformer.CATEGORY_PICKUP, (short) 0, null);
    }

    public void hitByBody(Player player) {
        if (mapObject != null) {
            if (mapObject.getName().matches("gummy_red")) {
                hud.addScore(4);
                player.addCandy('R');   //better here than in contact listener (performance)
            } else if (mapObject.getName().matches("gummy_green")) {
                hud.addScore(2);
                player.addCandy('G');
            } else if (mapObject.getName().matches("waffle_white")) {
                hud.addScore(4);
                player.addCandy('W');
            } else if (mapObject.getName().matches("waffle_choco")) {
                hud.addScore(2);
                player.addCandy('C');
            }
            setCategoryBits(Platformer.CATEGORY_NULL);  //no more collisions with this object
            removeTile(mapObject);                      //make the texture disappear

            //play sfx
            sfx = game.getAssetManager().get("sfx/pickup.wav");
            sfx.play(0.75f);
        }
    }
}
