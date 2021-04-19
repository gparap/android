/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects.spawnables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Utils;

public class Enemy extends GameSpawnable {
    public Enemy(EndlessRunner game, World world, String textureRegionName, float positionX, float positionY, boolean canFly) {
        super(game, world, positionX, positionY, Type.ENEMY, canFly);
        super.init(textureRegionName);
        this.canFly = canFly;
        initAnimation(textureRegionName);
        initSpeed(textureRegionName);
    }

    void initAnimation(String textureName) {
        //create animation
        stateTime = 0.0f;
        frameDuration = 0.1f;
        createAnimation(textureName);
    }

    /**
     * Creates the enemy sprite animation.
     */
    void createAnimation(String textureRegionName) {
        //create the animation frames
        Array<TextureRegion> animationFrames = Utils.addAnimationFrames(textureRegionName, game);

        //create the animation
        animationWalk = new Animation<>(frameDuration, animationFrames);
    }

    /**
     * Give different speed to different enemies.
     *
     * @param name enemy
     */
    private void initSpeed(String name) {
        if (name.equals("barnacle")) {
            speed = 480f;
        }
        //noinspection IfCanBeSwitch
        if (name.equals("slime") || name.equals("slime-blue") || name.equals("snail")) {
            speed = 490f;
        } else if (name.equals("snake") || name.equals("worm") || name.equals("snake-slime") || name.equals("lady-bug")) {
            speed = 500f;
        } else if (name.equals("mouse") || name.equals("spider")) {
            speed = 520f;
        }
        //fliers have maximum speed
        if (name.equals("bat") || name.equals("bee") || name.equals("fly")) {
            speed = 580.0f;
        }
    }
}
