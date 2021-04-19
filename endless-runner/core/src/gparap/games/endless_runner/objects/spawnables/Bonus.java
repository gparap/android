/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects.spawnables;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;

public class Bonus extends GameSpawnable {
    public Bonus(EndlessRunner game, World world, float positionX, float positionY) {
        super(game, world, positionX, positionY, Type.BONUS, false);
        super.init(Globals.GEM_BLUE);
    }

    protected void randomizeTexture() {
        String name = "";
        switch (new RandomXS128().nextInt(4)) {
            case 0:
                name = Globals.GEM_BLUE;
                break;
            case 1:
                name = Globals.GEM_GREEN;
                break;
            case 2:
                name = Globals.GEM_RED;
                break;
            case 3:
                name = Globals.GEM_YELLOW;
                break;
        }
        sprite.setRegion(game.getAtlas().findRegion(name));
    }
}
