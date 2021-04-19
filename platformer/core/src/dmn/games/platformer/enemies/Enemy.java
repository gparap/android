/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;

public class Enemy {
    protected Platformer game;
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width, height;
    private boolean isBoss;
    //MOVEMENT
    protected float forceMove;
    protected float forceJump;
    protected float maxSpeed;

    //ANIMATION
    public enum State {WALK, HURT}
    protected boolean isAnimated, isJustStamped, isHurt, isDead, isToBeRemoved;
    protected State currentState;
    private TextureRegion textureRegion;
    protected TextureRegion spriteIdle, spriteDead, spriteHurt;
    protected Animation<TextureRegion> animationWalk;
    protected float stateTime;    //used for animation
    protected float frameDuration;
    protected Array<TextureRegion> animationFrames;
    private char facing;
    //WORLD
    protected World world;
    protected Body body;
    protected Fixture fixture, fixtureUp;
    protected Filter filter;
    protected float stateTimeBody;                  //used for deleting body smoothly
    protected final float BODY_DELETE_TIME = 3.0f;  //default time to delete body

    private void setPosition(Vector2 position) {
        this.position = position;
    }

    public Body getBody() {
        return body;
    }

    public boolean isJustStamped() {
        return isJustStamped;
    }

    public boolean isToBeRemoved() {
        return isToBeRemoved;
    }

    public float getStateTimeBody() {
        return stateTimeBody;
    }

    public boolean isBoss() {
        return isBoss;
    }

    protected Enemy(World world, MapObject mapObject, Platformer game) {
        this.world = world;
        this.game = game;
        position = new Vector2(
                ((RectangleMapObject) mapObject).getRectangle().x * Platformer.SCALE_FACTOR,
                ((RectangleMapObject) mapObject).getRectangle().y * Platformer.SCALE_FACTOR);
        filter = new Filter();
        isJustStamped = false;
    }

    /**
     * Custom game object default initialization.
     */
    protected void init() {
        textureRegion = new TextureRegion();
        facing = 'L';           //default
        isAnimated = false;     //default
        isDead = false;         //default
        isJustStamped = false;  //default
        isToBeRemoved = false;  //default
        stateTime = 0.0f;
        velocity = new Vector2(-1f, 0f);
        isBoss = false;         //default
    }

    protected void init(boolean isBoss) {
        textureRegion = new TextureRegion();
        facing = 'L';           //default
        isAnimated = false;     //default
        isDead = false;         //default
        isJustStamped = false;  //default
        isToBeRemoved = false;  //default
        stateTime = 0.0f;
        velocity = new Vector2(-1f, 0f);
        this.isBoss = isBoss;
    }

    /**
     * Creates the box2D body of the enemy.
     */
    protected void createBody() {
        //create and add the (deactivated) body to the game world
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(position);
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDefinition);
        body.setActive(false);
        //create the shape of the fixture of the body
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f * Platformer.SCALE_FACTOR, height / 2f * Platformer.SCALE_FACTOR,
                new Vector2(width / 2f * Platformer.SCALE_FACTOR, height / 2f * Platformer.SCALE_FACTOR), 0);
        //create the fixture of the body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        //create the filter for collisions (only the body)
        fixtureDefinition.filter.categoryBits = Platformer.CATEGORY_ENEMY;
        fixtureDefinition.filter.maskBits = Platformer.CATEGORY_DEFAULT | Platformer.CATEGORY_PLAYER | Platformer.CATEGORY_CRATE;
        fixture = body.createFixture(fixtureDefinition);
        body.setUserData(this); //reference the enemy body in the game world
        shape.dispose();        //don't need the shape anymore
    }

    /**
     * Creates a box2D sensor at the top of the body.
     *
     * @param offsetX width offset
     * @param offsetY height offset
     */
    protected void createSensorUp(float offsetX, float offsetY) {
        //create the shape of the fixture of the sensor
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((width / 2f) + offsetX) * Platformer.SCALE_FACTOR,
                        height / 4f * Platformer.SCALE_FACTOR,
                new Vector2(body.getLocalCenter().x + offsetY,
                        body.getLocalCenter().y + height * Platformer.SCALE_FACTOR),
                        0);
        //create the fixture of the enemy body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        fixtureDefinition.isSensor = true;
        //create the filter for collisions (only this sensor)
        fixtureDefinition.filter.categoryBits = Platformer.CATEGORY_ENEMY_TOP;
        fixtureDefinition.filter.maskBits = Platformer.CATEGORY_DEFAULT | Platformer.CATEGORY_PLAYER;
        fixtureUp = body.createFixture(fixtureDefinition);
        fixtureUp.setUserData("enemySensorUp"); //reference the sensor of the enemy body in the game world
        shape.dispose();                        //don't need the shape anymore
    }

    /**
     * Sets the collision category bits.
     *
     * @param categoryBits categoryBits
     */
    protected void setCategoryBits(short categoryBits) {
        filter.categoryBits = categoryBits;
        fixture.setFilterData(filter);      //body
        fixtureUp.setFilterData(filter);    //sensor (up)
    }

    /**
     * Returns the current state of the animation
     */
    private State getAnimationState() {
        if (isHurt) {
            currentState = State.HURT;
        } else {
            currentState = State.WALK;
        }
        return currentState;
    }

    /**
     * Returns the current frame of the animation
     */
    private TextureRegion getAnimationFrame() {
        currentState = getAnimationState();
        //HURT
        if (currentState == State.HURT) {
            textureRegion = spriteHurt;
        }
        //WALK
        else {
            textureRegion = animationWalk.getKeyFrame(stateTime, true);
            stateTime += frameDuration;
            //reset animation
            if (stateTime > animationFrames.size * frameDuration) {
                stateTime = 0.0f;
            }
            //FACING
            if ((facing == 'R' || body.getLinearVelocity().x > 0) && !textureRegion.isFlipX()) {
                textureRegion.flip(true, false);
                facing = 'R';
            } else if ((facing == 'L' || body.getLinearVelocity().x < 0) && textureRegion.isFlipX()) {
                textureRegion.flip(true, false);
                facing = 'L';
            }
        }
        return textureRegion;
    }

    protected void resetAnimation() {
        if (stateTime > animationFrames.size * frameDuration) {
            stateTime = 0.0f;
        }
    }

    protected void move() {
        //don't move if not on the ground
        if (body.getLinearVelocity().y >= 0)
            body.applyLinearImpulse(velocity.x, 0, position.x, position.y, true);
        //control movement speed
        if (body.getLinearVelocity().x > maxSpeed)
            body.setLinearVelocity(maxSpeed, body.getLinearVelocity().y);
        if (body.getLinearVelocity().x < -maxSpeed)
            body.setLinearVelocity(-maxSpeed, body.getLinearVelocity().y);
    }

    public void moveReverse() {
        velocity.x = -velocity.x;
    }

    public void stamped() {
    }

    public void stamped(Player player) {
    }

    protected void setHurt() {
        isHurt = true;
        isAnimated = false;
        stateTimeBody = BODY_DELETE_TIME;
    }

    protected void setDead() {
        isDead = true;
        isAnimated = false;
        stateTime = 0;
    }

    protected void playStampedSFX() {
        Sound sfx = game.getAssetManager().get("sfx/hit_enemy_head.wav");
        sfx.play(0.5f);
    }

    /**
     * Updates the game object.
     *
     * @param delta delta
     */
    public void update(float delta) {
        setPosition(body.getPosition());
    }

    /**
     * Renders the game object on the screen.
     *
     * @param batch batch
     */
    public void render(Batch batch) {
        if (isAnimated) {
            batch.draw(getAnimationFrame(), position.x, position.y,
                    getAnimationFrame().getRegionWidth() * Platformer.SCALE_FACTOR,
                    getAnimationFrame().getRegionHeight() * Platformer.SCALE_FACTOR);

        } else if (isHurt) {
            if (stateTimeBody > 0) {
                stateTimeBody -= Gdx.graphics.getDeltaTime();
                batch.draw(spriteHurt, position.x, position.y,
                        spriteHurt.getRegionWidth() * Platformer.SCALE_FACTOR,
                        spriteHurt.getRegionHeight() * Platformer.SCALE_FACTOR);
            } else {
                isHurt = false;
                isAnimated = true;
            }
        } else if (isDead) {
            if (stateTimeBody > 0) {
                stateTimeBody -= Gdx.graphics.getDeltaTime();
                batch.draw(spriteDead, position.x, position.y,
                        spriteDead.getRegionWidth() * Platformer.SCALE_FACTOR,
                        spriteDead.getRegionHeight() * Platformer.SCALE_FACTOR);
            } else {
                isToBeRemoved = true;
            }
        } else {
            batch.draw(spriteIdle, position.x, position.y,
                    spriteIdle.getRegionWidth() * Platformer.SCALE_FACTOR,
                    spriteIdle.getRegionHeight() * Platformer.SCALE_FACTOR);
        }
    }
}
