/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.endless_runner.utils.Globals;
import gparap.games.endless_runner.objects.GameObject;
import gparap.games.endless_runner.objects.Platform;

public class PlatformManager implements Disposable {
    private Sprite generator;
    private Array<gparap.games.endless_runner.objects.GameObject> platforms;
    private final World world;
    private float speed;
    private gparap.games.endless_runner.objects.GameObject lastGeneratedPlatform;

    public gparap.games.endless_runner.objects.GameObject getLastGeneratedPlatform() {
        return lastGeneratedPlatform;
    }

    public PlatformManager(World world) {
        this.world = world;
        init();
    }

    private void init() {
        platforms = new Array<>();

        //create platform generator point
        generator = new Sprite(new Texture("platform/generator.png"));
        generator.setSize(generator.getTexture().getWidth() * Globals.SCALE_FACTOR,
                         generator.getTexture().getHeight() * Globals.SCALE_FACTOR);

        //create platforms
        gparap.games.endless_runner.objects.GameObject platform1 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform.png", Globals.PLATFORM_INIT_XY_FIRST, Globals.PLATFORM_INIT_XY_FIRST);
        platform1.setActive(true);
        gparap.games.endless_runner.objects.GameObject platform2 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform-half.png", Globals.PLATFORM_INIT_XY, Globals.PLATFORM_INIT_XY);
        platform2.setActive(false);
        gparap.games.endless_runner.objects.GameObject platform3 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform-half_minus.png", Globals.PLATFORM_INIT_XY, Globals.PLATFORM_INIT_XY);
        platform3.setActive(false);
        gparap.games.endless_runner.objects.GameObject platform4 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform-half.png", Globals.PLATFORM_INIT_XY, Globals.PLATFORM_INIT_XY);
        platform4.setActive(false);
        gparap.games.endless_runner.objects.GameObject platform5 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform-half_plus.png", Globals.PLATFORM_INIT_XY, Globals.PLATFORM_INIT_XY);
        platform5.setActive(false);
        gparap.games.endless_runner.objects.GameObject platform6 = new gparap.games.endless_runner.objects.Platform(world, "platform/platform-quarter.png", Globals.PLATFORM_INIT_XY, Globals.PLATFORM_INIT_XY);
        platform6.setActive(false);

        //get update speed from one of the platforms
        //noinspection CastCanBeRemovedNarrowingVariableType
        speed = ((Platform)platform1).getSpeed();

        //add active and inactive platforms to list
        platforms.add(platform1, platform2, platform3);
        platforms.add(platform4, platform5, platform6);

        //platform generator starts at the end of the last active
        lastGeneratedPlatform = platform1;
        generator.setPosition(platform1.getX() + platform1.getWidth() * Globals.SCALE_FACTOR, 0 * Globals.SCALE_FACTOR);
    }

    public void update(float delta) {
        //scroll platforms
        for (gparap.games.endless_runner.objects.GameObject platform : platforms) {
            platform.update(delta);
        }

        //scroll generator
        generator.setX(generator.getX() - speed * delta * Globals.SCALE_FACTOR);

        //check platform generator if should generate
        if (generator.getX() < Globals.WIDTH * Globals.SCALE_FACTOR) {
            //take the first random platform form the pool
            boolean isGenerated = false;
            int random = -1;
            while (!isGenerated) {
                for (int i = 0; i < platforms.size; i++) {
                    //randomize
                    RandomXS128 randomXS128 = new RandomXS128();
                    random = randomXS128.nextInt(platforms.size - 1);
                    if (!platforms.get(random).isActive()) {
                        isGenerated = true;

                        //update platform position
                        platforms.get(random).setActive(true);
                        ((gparap.games.endless_runner.objects.Platform) platforms.get(random)).resetPositionX();
                        ((Platform) platforms.get(random)).resetPositionY(generator.getY());

                        //update the reference of the last platform generated
                        lastGeneratedPlatform = platforms.get(random);

                        //update generator position
                        generator.setY(platforms.get(random).getY());
                        break;
                    }
                }
            }
            //set generator X to be in the end of the platform
            generator.setX(platforms.get(random).getX() + platforms.get(random).getWidth() * Globals.SCALE_FACTOR);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        for (gparap.games.endless_runner.objects.GameObject ground : platforms) {
            ground.draw(spriteBatch);
        }
        generator.draw(spriteBatch);
    }

    @Override
    public void dispose() {
        for (GameObject ground : platforms) {
            ground.dispose();
        }
        generator.getTexture().dispose();
    }
}
