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

public class Slime extends Enemy {
    char type;  //'b' blue, 'd' default

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Slime(World world, MapObject mapObject, Platformer game, char type) {
        super(world, mapObject, game);
        this.type = type;
        createAnimation();
        init();
        createBody();
        createSensorUp(0, 0);
    }

    @Override
    protected void init() {
        super.init();
        velocity.x = -1.0f;
        maxSpeed = 1.25f;
        width = 50;
        height = 34;
        //dead sprite
        switch (type) {
            case 'b':
                spriteDead = game.getAtlas().findRegion("slime_blue_squashed");
                break;
            case 'd':
                spriteDead = game.getAtlas().findRegion("slime_squashed");
                break;
        }
        spriteDead.setRegionHeight(12); //stamped and in the ground
        isAnimated = true;
        frameDuration = 0.1f;
        stateTime = 0.0f;
        currentState = State.WALK;
    }

    /**
     * Creates the slime sprite animation.
     */
    private void createAnimation() {
        //create the animation frames based on type
        animationFrames = new Array<>();
        switch (type) {
            case 'b':
                animationFrames.add(game.getAtlas().findRegion("slime_blue"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_blue_walk"));
                break;
            case 'd':
                animationFrames.add(game.getAtlas().findRegion("slime"));
                animationFrames.add(game.getAtlas().findRegion("slime"));
                animationFrames.add(game.getAtlas().findRegion("slime"));
                animationFrames.add(game.getAtlas().findRegion("slime"));
                animationFrames.add(game.getAtlas().findRegion("slime"));
                animationFrames.add(game.getAtlas().findRegion("slime_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_walk"));
                animationFrames.add(game.getAtlas().findRegion("slime_walk"));
                break;
        }
        //create the animation
        animationWalk = new Animation<>(frameDuration, animationFrames);
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
     * Updates the slime.
     *
     * @param delta delta
     */
    @Override
    public void update(float delta) {
        super.update(delta);
        move();
        resetAnimation();
    }

    /**
     * Renders the slime on the screen.
     *
     * @param batch batch
     */
    @Override
    public void render(Batch batch) {
        super.render(batch);
    }
}
