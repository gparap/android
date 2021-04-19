/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;

public class Snail extends Enemy {
    public Snail(World world, MapObject mapObject, Platformer game) {
        super(world, mapObject, game);
        init();
        createAnimation();
        createBody();
        createSensorUp(0,0);
    }

    @Override
    protected void init() {
        super.init();
        width = 50;
        height = 30;
        velocity.x = -0.25f;
        forceMove = 0.75f;
        maxSpeed = 1f;
        spriteDead = game.getAtlas().findRegion("snailShell_upsidedown");
        spriteHurt = game.getAtlas().findRegion("snailShell");
        isAnimated = true;
        frameDuration = 0.1f;
        stateTime = 0.0f;
        currentState = State.WALK;
    }

    /**
     * Creates the snail sprite animation.
     */
    private void createAnimation() {
        //create the animation frames
        animationFrames = new Array<>();
        animationFrames.add(game.getAtlas().findRegion("snailWalk1"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk1"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk1"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk1"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk1"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk2"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk2"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk2"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk2"));
        animationFrames.add(game.getAtlas().findRegion("snailWalk2"));
        //create the animation
        animationWalk = new Animation<>(frameDuration, animationFrames);
    }

    public void stamped() {
        if (!isHurt) {
            setCategoryBits(Platformer.CATEGORY_ENEMY_HURT);
            setHurt();
        }
    }

    public void stamped(Player player) {
        //player must be on its way down to kill the snail
        if (player.getBody().getLinearVelocity().y < 0) {
            setCategoryBits(Platformer.CATEGORY_NULL);
            isJustStamped = true;
            isHurt = false;
            setDead();
        }
    }

    /**
     * Updates the snail.
     *
     * @param delta delta
     */
    @Override
    public void update(float delta) {
        if (!isHurt) {
            //if enemy was hurt reset category filter to a normal enemy body category
            if (filter.categoryBits == Platformer.CATEGORY_ENEMY_HURT) {
                setCategoryBits(Platformer.CATEGORY_ENEMY);
            }
            super.update(delta);
            move();
            resetAnimation();
        }
    }

    /**
     * Renders the snail on the screen.
     *
     * @param batch batch
     */
    @Override
    public void render(Batch batch) {
        super.render(batch);
    }
}
