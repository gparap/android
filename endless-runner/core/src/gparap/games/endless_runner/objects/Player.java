/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import gparap.games.endless_runner.physics.BodyFactory;
import gparap.games.endless_runner.utils.Globals;

import static gparap.games.endless_runner.utils.Globals.CATEGORY_BONUS;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_DEFAULT;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_ENEMY;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_PLAYER;
import static gparap.games.endless_runner.utils.Globals.SCALE_FACTOR;

public class Player extends GameObject {
    public enum State {IDLE, WALK, JUMP, OFF_GROUND}

    private Texture spritesheet;
    private State currentState;
    private TextureRegion textureRegion;
    private TextureRegion spriteJump, spriteIdle;
    private Animation<TextureRegion> animationWalk;
    private boolean isIdle, canJump = true;
    private float forceJump;
    private float stateTime;
    private float frameDuration;
    private int score;
    private int life;
    private final AssetManager assetManager;
    private Sound sfx;
    private int player;

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;

        //play sfx
        sfx = assetManager.get(Globals.SFX_PICKUP_BONUS);
        sfx.play(0.5f);
    }

    public int getLife() {
        return life;
    }

    public void removeLife() {
        life = 0;
        sfxGameOVer();
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public Player(World world, AssetManager assetManager) {
        super(world);
        this.assetManager = assetManager;
        init();
    }

    protected void init() {
        type = Type.PLAYER;

        //get alien from preferences
        Preferences preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
        player = preferences.getInteger(Globals.PREFERENCES_PLAYER);
        switch (player) {
            case 1:
                spritesheet = new Texture(Globals.ALIEN_1_SPRITESHEET);
                break;
            case 2:
                spritesheet = new Texture(Globals.ALIEN_2_SPRITESHEET);
                break;
            case 3:
                spritesheet = new Texture(Globals.ALIEN_3_SPRITESHEET);
                break;
        }
        forceJump = 600 * SCALE_FACTOR;
        isIdle = true;
        score = 0;
        life = 1;
        canJump = false;

        //create basic sprite
        sprite = new Sprite(new Texture(Globals.ALIEN_STAND));
        sprite.setSize(sprite.getWidth() * SCALE_FACTOR, sprite.getHeight() * SCALE_FACTOR);
        sprite.setOrigin(sprite.getWidth() / 2f, sprite.getHeight() / 2f);
        sprite.setPosition(10 * SCALE_FACTOR, 32 * SCALE_FACTOR);   //starting position

        //create box2d body
        //noinspection UnnecessaryLocalVariable
        short categoryBits = CATEGORY_PLAYER;
        short maskBits = CATEGORY_DEFAULT | CATEGORY_BONUS | CATEGORY_ENEMY;
        body = BodyFactory.createBody(world, sprite, BodyDef.BodyType.DynamicBody, false, categoryBits, maskBits, 15f * SCALE_FACTOR, 10f * SCALE_FACTOR);
        setActive(true);
        body.setUserData(this);

        //create animation
        stateTime = 0.0f;
        switch (player) {
            case 1:
            case 3:
                spriteJump = new TextureRegion(spritesheet, 438, 93, 67, 94);
                break;
            case 2:
                spriteJump = new TextureRegion(spritesheet, 423, 95, 66, 94);
                break;
        }

        switch (player) {
            case 1:
            case 3:
                spriteIdle = new TextureRegion(spritesheet, 67, 196, 66, 92);
                break;
            case 2:
                spriteIdle = new TextureRegion(spritesheet, 67, 190, 66, 92);
                break;
        }

        frameDuration = 0.1f;
        createAnimation();
        createSensorJump();
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.justTouched()) {
            jump();
        }

        //update sprite position based on body
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(getAnimationFrame(), getX(), getY(),
                sprite.getRegionWidth() * SCALE_FACTOR,
                sprite.getRegionHeight() * SCALE_FACTOR);
    }

    private void jump() {
        if (canJump) {
            body.applyLinearImpulse(0, forceJump, getX(), getY(), true);

            //play sfx
            sfx = assetManager.get(Globals.SFX_JUMP_PLAYER);
            sfx.play(0.5f);
        }
    }

    /**
     * Creates a box2d sensor on top of the alien body.
     */
    private void createSensorJump() {
        //create the shape of the fixture of the sensor
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((getWidth() / 2f)) * SCALE_FACTOR - 15f * SCALE_FACTOR,
                getHeight() / 8f * SCALE_FACTOR,
                new Vector2(body.getLocalCenter().x + getWidth() / 2f * SCALE_FACTOR,
                        body.getLocalCenter().y),
                0);
        //create the fixture of the alien body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        fixtureDefinition.isSensor = true;
        fixtureDefinition.filter.categoryBits = CATEGORY_PLAYER;
        fixtureDefinition.filter.maskBits = CATEGORY_DEFAULT;
        Fixture fixtureDown = body.createFixture(fixtureDefinition);
        fixtureDown.setUserData(Globals.PLAYER_SENSOR_JUMP);    //reference the sensor of the alien body in the game world
        shape.dispose();    //don't need the shape anymore
    }

    /**
     * Creates the alien sprite animation.
     */
    private void createAnimation() {
        //create the animation frames
        Array<TextureRegion> animationFrames = new Array<>();

        switch (player) {
            case 1:
            case 3:
                animationFrames.add(new TextureRegion(spritesheet, 0, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 73, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 146, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 0, 98, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 73, 98, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 146, 98, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 219, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 292, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 219, 98, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 365, 0, 72, 97));
                animationFrames.add(new TextureRegion(spritesheet, 292, 98, 72, 97));
                break;
            case 2:
                animationFrames.add(new TextureRegion(spritesheet, 0, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 71, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 142, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 0, 95, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 71, 95, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 142, 95, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 213, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 284, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 213, 95, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 355, 0, 70, 94));
                animationFrames.add(new TextureRegion(spritesheet, 284, 95, 70, 94));
                break;
        }
        //create the animation
        animationWalk = new Animation<>(frameDuration, animationFrames);
    }

    /**
     * Returns the current state of the animation.
     */
    private Player.State getAnimationState() {
        if ((body.getLinearVelocity().y > 0) || (body.getLinearVelocity().y < 0)) {
            currentState = State.JUMP;
        } else if (isIdle) {
            currentState = State.IDLE;
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
        if (currentState == State.JUMP || currentState == State.OFF_GROUND) {
            textureRegion = spriteJump;
        } else if (currentState == State.IDLE) {
            textureRegion = spriteIdle;
        } else if (currentState == State.WALK) {
            textureRegion = animationWalk.getKeyFrame(stateTime, true);
            stateTime += frameDuration;
        }
        return textureRegion;
    }

    public void sfxGameOVer() {
        sfx = assetManager.get(Globals.SFX_HIT_PLAYER_LOSE);
        sfx.play(0.5f);
    }

    /**
     * Releases all resources of the alien's class.
     */
    @Override
    public void dispose() {
        super.dispose();
        spritesheet.dispose();
        spriteJump.getTexture().dispose();
    }
}
