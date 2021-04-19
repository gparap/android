/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.tiles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;

import dmn.games.platformer.HUD;
import dmn.games.platformer.Player;

public class Exit extends Tile {
    private boolean canExit;

    public Exit(World world, MapObject mapObject, HUD hud) {
        super(world, mapObject, hud);
        createBody(true, (short) 0, (short) 0, null);
        canExit = false;
    }

    public boolean getCanExit() {
        return canExit;
    }

    public void hitByBody(Player player) {
        if (mapObject.getProperties().get("type") != null && mapObject.getName().matches("sign_exit")) {
            hud.removeKey();
            canExit = true;
        }
    }
}
