/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.enemies;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;

import dmn.games.platformer.Platformer;

public class Blocker extends Enemy {
    private char type;                  // 'W' walker, 'J' jumper, 'M' mixed (walker and jumper)
    private RandomXS128 randomXS128;    //used for jump height randomization
    private float jumpTimer;
    @SuppressWarnings("FieldCanBeLocal")
    private final float MAX_JUMP_TIMER = 1.667f;

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Blocker(World world, MapObject mapObject, Platformer game, boolean isBoss) {
        super(world, mapObject, game);
        if (isBoss) {
            init(true);
            createBody();
        } else {
            init();
            createBody();
            createSensorUp(-5.0f, +0.5f);
        }
    }

    @Override
    protected void init(boolean isBoss) {
        super.init(isBoss);
        width = 128;
        height = 128;
        velocity.x = -1.0f;
        forceJump = 5.0f;
        maxSpeed = 2.5f;
        spriteIdle = game.getAtlas().findRegion("blocker_mad_boss");
        spriteDead = game.getAtlas().findRegion("blocker_body");
        spriteDead.setRegionHeight(12); //stamped and in the ground
        type = 'B'; //default
        randomXS128 = new RandomXS128();
        jumpTimer = 0.0f;
        isAnimated = false;
    }

    @Override
    protected void init() {
        super.init();
        width = 64;
        height = 64;
        velocity.x = -1.0f;
        forceJump = 5.0f;
        maxSpeed = 2.5f;
        spriteIdle = game.getAtlas().findRegion("blocker_mad");
        spriteDead = game.getAtlas().findRegion("blocker_body");
        spriteDead.setRegionHeight(12); //stamped and in the ground
        type = 'W'; //default
        randomXS128 = new RandomXS128();
        jumpTimer = 0.0f;
        isAnimated = false;
    }

    public void jump() {
        jumpTimer = 0.0f;
        if (body.getLinearVelocity().y == 0 && !isDead) {
            body.applyLinearImpulse(0, forceJump + getRandomJumpHeight(),
                    position.x, position.y, true);
        }
    }

    /**
     * Randomizes the extra height of an enemy blocker's jump.
     *
     * @return random height
     */
    private int getRandomJumpHeight() {
        return randomXS128.nextInt((int) forceJump);
    }

    public void stamped() {
        if (!isJustStamped) {
            setCategoryBits(Platformer.CATEGORY_NULL);  //no more collisions with this object
            isJustStamped = true;
            setDead();
            playStampedSFX();
            stateTimeBody = BODY_DELETE_TIME;   //start body delete timer
            //if enemy is stamped, it cannot go up anymore
            body.setLinearVelocity(0, -(Math.abs(body.getLinearVelocity().y)));
        }
    }

    /**
     * Updates the blocker.
     *
     * @param delta delta
     */
    @Override
    public void update(float delta) {
        super.update(delta);
        //blockers are 'W' walkers, 'J' jumpers or 'M' mixed (walkers and jumpers) and 'B' boss
        if (type == 'W') {
            move();
        } else if (type == 'J') {
            if (jumpTimer > MAX_JUMP_TIMER)
                jump();
            jumpTimer += delta;
        } else if (type == 'M' || type == 'B') {
            move();
            if (jumpTimer > MAX_JUMP_TIMER)
                jump();
            jumpTimer += delta;
        }
    }

    /**
     * Renders the blocker on the screen.
     *
     * @param batch batch
     */
    @Override
    public void render(Batch batch) {
        super.render(batch);
    }
}
