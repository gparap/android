/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Player {
    private final Platformer game;
    private final int width, height;
    private final HUD hud;
    private boolean isSpawn, isDead, isHurt, isOffGround, isMoving = false, canJump, isOnSlope,
                    isRespawn;          //used by level manager for music management
    private int stamps;                 //how many enemies the player has stamped
    private int coinsGold;              //how many gold coins the player has collected
    private int coinsSilver;            //how many silver coins the player has collected
    private int coinsBrown;             //how many brown coins the player has collected
    private int candyGummyGreen;        //how many green gummy worms the player has collected
    private int candyGummyRed;          //how many red gummy worms the player has collected
    private int candyWaffleChoco;       //how many choco waffles the player has collected
    private int candyWaffleWhite;       //how many white waffles the player has collected
    //MOVEMENT
    private Vector2 position;
    private float forceMove, forceJump;
    private float maxSpeed, maxSpeedAir;
    //ANIMATION
    public enum State {IDLE, WALK, JUMP, HURT, OFF_GROUND}
    private State currentState;
    private char facing;
    private TextureRegion textureRegion;
    private TextureRegion spriteIdle, spriteJump, spriteHurt;
    private Animation<TextureRegion> animationWalk;
    private float stateTime, frameDuration;
    //WORLD
    private final World world;
    private Body body;
    //SFX
    private Sound sfx;

    public Player(World world, Vector2 position, int width, int height, HUD hud, Platformer game) {
        this.world = world;
        this.position = position;
        this.width = width;
        this.height = height;
        this.hud = hud;
        this.game = game;
        init();
        createAnimation();
        createBody();
        createSensors();
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getForceJump() {
        return forceJump;
    }

    public State getState() {
        return currentState;
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isRespawn() {
        return isRespawn;
    }

    public void setRespawn(boolean value) {
        isRespawn = value;
    }

    public boolean canJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public void setOnSlope(boolean value) {
        isOnSlope = value;
    }

    public int getStamps() {
        return stamps;
    }

    public void addStamp() {
        stamps += 1;
    }

    public void resetStamps() {
        stamps = 0;
    }

    public int getCoinsTotal() {
        return (coinsGold + coinsSilver + coinsBrown);
    }

    public int getCoinsGold() {
        return coinsGold;
    }

    public int getCoinsSilver() {
        return coinsSilver;
    }

    public int getCoinsBrown() {
        return coinsBrown;
    }

    public void addCoin(char coin) {
        switch (coin) {
            case 'G':
                coinsGold += 1;
                break;
            case 'S':
                coinsSilver += 1;
                break;
            case 'B':
                coinsBrown += 1;
                break;
        }
    }

    public void resetCoins() {
        coinsGold = 0;
        coinsSilver = 0;
        coinsBrown = 0;
    }

    public int getCandysTotal() {
        return (candyGummyGreen + candyGummyRed + candyWaffleChoco + candyWaffleWhite);
    }

    public int getGummyGreen() {
        return candyGummyGreen;
    }

    public int getGummyRed() {
        return candyGummyRed;
    }

    public int getWaffleChoco() {
        return candyWaffleChoco;
    }

    public int getWaffleWhite() {
        return candyWaffleWhite;
    }

    public void addCandy(char type) {
        //G=gummy green
        //R=gummy red
        //C=waffle choco
        //W=waffle white
        switch (type) {
            case 'G':
                candyGummyGreen += 1;
                break;
            case 'R':
                candyGummyRed += 1;
                break;
            case 'C':
                candyWaffleChoco += 1;
                break;
            case 'W':
                candyWaffleWhite += 1;
                break;
        }
    }

    public Body getBody() {
        return body;
    }

    private void init() {
        currentState = State.IDLE;
        textureRegion = new TextureRegion();
        stateTime = 0.0f;
        forceMove = 0.75f;
        forceJump = 8.5f;
        maxSpeed = 5.55f;
        maxSpeedAir = maxSpeed - maxSpeed / 10.0f;
        spriteIdle = game.getAtlas().findRegion("p1_stand");
        spriteJump = game.getAtlas().findRegion("p1_jump");
        spriteHurt = game.getAtlas().findRegion("p1_hurt");
        frameDuration = 0.1f;
        isSpawn = false;
        isDead = false;
        isHurt = false;
        isRespawn = false;
        isOffGround = false;
        isOnSlope = false;
        canJump = false;
        stamps = 0;
        coinsGold = 0;
        coinsSilver = 0;
        coinsBrown = 0;
    }

    /**
     * Creates the player box2D body
     */
    private void createBody() {
        //create and add the player body to the game world
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(position);
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDefinition);
        //create the shape of the fixture of the player body
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2f * Platformer.SCALE_FACTOR, height / 2f * Platformer.SCALE_FACTOR,
                new Vector2(width / 2f * Platformer.SCALE_FACTOR,
                        height / 2f * Platformer.SCALE_FACTOR), 0);
        //create the fixture of the player body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        createCollisionFilters(fixtureDefinition);
        body.createFixture(fixtureDefinition);
        body.setUserData(this); //reference the player body in the game world
        shape.dispose();        //don't need the shape anymore
    }

    /**
     * Creates a player box2D sensor.
     */
    private void createSensors() {
        createSensorUp();
        createSensorDown();
    }

    /**
     * Creates the player sprite animation.
     */
    private void createAnimation() {
        //create the animation frames
        Array<TextureRegion> animationFrames = new Array<>();
        animationFrames.add(game.getAtlas().findRegion("p1_walk01"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk02"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk03"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk04"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk05"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk06"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk07"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk08"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk09"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk10"));
        animationFrames.add(game.getAtlas().findRegion("p1_walk11"));

        //create the animation
        animationWalk = new Animation<>(frameDuration, animationFrames);
    }

    /**
     * Returns the current state of the animation
     */
    private State getAnimationState() {
        if (((body.getLinearVelocity().y > 0) || (body.getLinearVelocity().y < 0)) && !isOnSlope) {
            currentState = State.JUMP;
        } else if (body.getLinearVelocity().x != 0) {
            if (!isHurt)
                currentState = State.WALK;
        } else if (!isDead && isHurt) {
            currentState = State.HURT;
        } else if (isOffGround) {
            currentState = State.OFF_GROUND;
        } else {
            currentState = State.IDLE;
        }
        return currentState;
    }

    /**
     * Returns the current frame of the animation
     */
    private TextureRegion getAnimationFrame() {
        currentState = getAnimationState();
        //IDLE
        if (currentState == State.IDLE) {
            //FACING
            if (facing == 'R' && spriteIdle.isFlipX()) {
                spriteIdle.flip(true, false);
                facing = 'R';
            } else if (facing == 'L' && !spriteIdle.isFlipX()) {
                spriteIdle.flip(true, false);
                facing = 'L';
            }
            textureRegion = spriteIdle;
            stateTime = 0;
        }
        //HURT
        if (currentState == State.HURT) {
            //FACING
            if (facing == 'R' && spriteHurt.isFlipX()) {
                spriteHurt.flip(true, false);
                facing = 'R';
            } else if (facing == 'L' && !spriteHurt.isFlipX()) {
                spriteHurt.flip(true, false);
                facing = 'L';
            }
            textureRegion = spriteHurt;
        }
        //JUMP
        else if (currentState == State.JUMP || currentState == State.OFF_GROUND) {
            //FACING
            if ((facing == 'R' || body.getLinearVelocity().x > 0) && spriteJump.isFlipX()) {
                spriteJump.flip(true, false);
                facing = 'R';
            } else if ((facing == 'L' || body.getLinearVelocity().x < 0) && !spriteJump.isFlipX()) {
                spriteJump.flip(true, false);
                facing = 'L';
            }
            textureRegion = spriteJump;
        }
        //WALK
        else if (currentState == State.WALK) {
            textureRegion = animationWalk.getKeyFrame(stateTime, true);
            stateTime += frameDuration;
            //FACING
            if ((facing == 'R' || body.getLinearVelocity().x > 0) && textureRegion.isFlipX()) {
                textureRegion.flip(true, false);
                facing = 'R';
            } else if ((facing == 'L' || body.getLinearVelocity().x < 0) && !textureRegion.isFlipX()) {
                textureRegion.flip(true, false);
                facing = 'L';
            }
        }
        return textureRegion;
    }

    public void moveRight() {
        if (!isHurt)
            body.applyLinearImpulse(forceMove, 0, position.x, position.y, true);
        //control speed X
        if (body.getLinearVelocity().x > maxSpeed) {
            body.setLinearVelocity(maxSpeed, body.getLinearVelocity().y);
        }
        //control speed X when player is in the air
        if (Math.abs(body.getLinearVelocity().y) > 0 &&//.55f &&
                body.getLinearVelocity().x > maxSpeedAir) {
            body.setLinearVelocity(maxSpeedAir, body.getLinearVelocity().y);
        }
        //fixing for the box2d "stuck" bug: rotate the body (right)
        if (!isMoving && body.getLinearVelocity().x == 0.75f) {
            body.setAngularVelocity(0.1f);
        }
    }

    public void moveLeft() {
        if (!isHurt)
            body.applyLinearImpulse(-forceMove, 0, position.x, position.y, true);
        //control speed X
        if (body.getLinearVelocity().x < -maxSpeed) {
            body.setLinearVelocity(-maxSpeed, body.getLinearVelocity().y);
        }
        //control speed X when player is in the air
        if (Math.abs(body.getLinearVelocity().y) > 0 &&//.55f &&
                body.getLinearVelocity().x < -maxSpeedAir) {
            body.setLinearVelocity(-maxSpeedAir, body.getLinearVelocity().y);
        }
        //fixing for the box2d "stuck" bug: rotate the body (left)
        if (!isMoving && body.getLinearVelocity().x == -0.75f) {
            body.setAngularVelocity(-0.1f);
        }
    }

    public void jump() {
        if (isOnSlope) {
            body.applyLinearImpulse(0, forceJump / 2f, position.x, position.y, true);
        } else {
            body.applyLinearImpulse(0, forceJump, position.x, position.y, true);
        }

        sfx = game.getAssetManager().get("sfx/jump_player.wav");
        sfx.play(0.5f);
        isOnSlope = false;


    }

    private void createSensorUp() {
        //create the shape of the fixture of the sensor
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2((body.getLocalCenter().x + 5) * Platformer.SCALE_FACTOR,
                        (body.getLocalCenter().y + 5 + height) * Platformer.SCALE_FACTOR),
                new Vector2((body.getLocalCenter().x - 5 + width) * Platformer.SCALE_FACTOR,
                        (body.getLocalCenter().y + 5 + height) * Platformer.SCALE_FACTOR));
        //create the fixture of the player body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        fixtureDefinition.isSensor = true;
        //create the filter for collisions (only this sensor)
        createCollisionFilters(fixtureDefinition);
        Fixture fixtureUp = body.createFixture(fixtureDefinition);
        fixtureUp.setUserData("sensorUp");  //reference the sensor of the player body in the game world
        shape.dispose();                    //don't need the shape anymore
    }

    private void createSensorDown() {
        //create the shape of the fixture of the sensor
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((width / 2f) - 9f) * Platformer.SCALE_FACTOR,
                height / 8f * Platformer.SCALE_FACTOR,   //DON'T change this number! (0.435f)
                new Vector2(body.getLocalCenter().x + 0.435f, body.getLocalCenter().y),
                0);
        //create the fixture of the player body and add to it a shape
        FixtureDef fixtureDefinition = new FixtureDef();
        fixtureDefinition.shape = shape;
        fixtureDefinition.isSensor = true;
        //create the filter for collisions (only this sensor)
        fixtureDefinition.filter.categoryBits = Platformer.CATEGORY_PLAYER;
        fixtureDefinition.filter.maskBits = Platformer.CATEGORY_DEFAULT |
                Platformer.CATEGORY_ENEMY |
                Platformer.CATEGORY_ENEMY_TOP |
                Platformer.CATEGORY_ENEMY_HURT |
                Platformer.CATEGORY_PICKUP |
                Platformer.CATEGORY_CRATE |
                Platformer.CATEGORY_KEY;
        Fixture fixtureDown = body.createFixture(fixtureDefinition);
        fixtureDown.setUserData("sensorDown");  //reference the sensor of the player body in the game world
        shape.dispose();                        //don't need the shape anymore
    }

    /**
     * Creates the filters for collisions.
     *
     * @param fixtureDefinition fixtureDefinition
     */
    private void createCollisionFilters(FixtureDef fixtureDefinition) {
        fixtureDefinition.filter.categoryBits = Platformer.CATEGORY_PLAYER;
        fixtureDefinition.filter.maskBits = Platformer.CATEGORY_DEFAULT |
                Platformer.CATEGORY_ENEMY |
                Platformer.CATEGORY_ENEMY_TOP |
                Platformer.CATEGORY_PICKUP |
                Platformer.CATEGORY_CRATE |
                Platformer.CATEGORY_KEY;
    }

    public void removeLife() {
        if (!isHurt) {
            hud.removeLife();
            hud.restartTime();
        }
        if (hud.getLife() > 0) {
            isHurt = true;
            stateTime = 0;
        } else
            isDead = true;
    }

    /**
     * Spawns player to the starting position (usually after losing life).
     */
    private void spawnPosition() {
        //set player initial position
        position.x = 1f;
        position.y = 5f;
        isSpawn = true;
        isRespawn = true;
        isOffGround = false;
        isHurt = false;
        //try to clear velocity
        body.setActive(false);
        body.setLinearVelocity(0, 0);
        body.setActive(true);
    }

    /**
     * Updates the player.
     *
     * @param delta delta
     */
    public void update(float delta) {
        //fixing for the box2d "stuck" bug: stop angular velocity
        if (body.getAngularVelocity() > 0 || body.getAngularVelocity() < 0) {
            body.setAngularVelocity(0);
        }
        //fixing for the box2d "stuck" bug: flat the body after a specific angle
        if (body.getAngle() > 0.1 || body.getAngle() < -0.1) {
            body.setTransform(body.getPosition(), 0);
        }
        //fixing for the box2d "stuck" bug: find the previous position
        float prevPos = getPosition().x;

        if (isSpawn) {
            body.setTransform(3f, 5f, 0);
            isSpawn = false;
        } else {
            this.position = (body.getPosition());
            //fixing for the box2d "stuck" bug: find the next position (that should be)
            float nextPos = getPosition().x;
            //fixing for the box2d "stuck" bug: check if the player is stuck on the ground
            if (prevPos == nextPos) {
                isMoving = false;
            }
        }
        //control falling
        if (body.getLinearVelocity().y < -9.8f) {//
            body.setLinearVelocity(body.getLinearVelocity().x, -9.8f);//9.80665 gravity
        }
        //check if is hurt
        if (isHurt) {
            if (stateTime < 0.33f) {
                stateTime += delta;
                //apply a force
                if (body.getLinearVelocity().x > 0 || facing == 'R') {
                    body.applyLinearImpulse(new Vector2(-1f, 0.25f), body.getLocalCenter(), true);
                } else if (body.getLinearVelocity().x < 0 || facing == 'L') {
                    body.applyLinearImpulse(new Vector2(1f, 0.25f), body.getWorldCenter(), true);
                } else {
                    body.applyLinearImpulse(new Vector2(0f, 0f), body.getWorldCenter(), true);
                }
                //control force
                if (body.getLinearVelocity().y > 2.5f) {
                    body.setLinearVelocity(body.getLinearVelocity().x, 1.0f);
                }
            } else {
                isHurt = false;
                spawnPosition();
            }
            //play losing life sfx
            sfx = game.getAssetManager().get("sfx/hit_player_lose.wav");
            sfx.play(0.5f);
        }
        //check if level time is up
        if (hud.getTime() == 0) {
            removeLife();
            hud.restartTime();
        }
        //control can jump
        if (body.getLinearVelocity().y == 0) {
            canJump = false;
        }
        //check if falling off ground
        if (body.getPosition().y < -1.0149997) {    //necessary evil
            //play losing life sfx
            sfx = game.getAssetManager().get("sfx/hit_player_lose.wav");
            sfx.play(0.5f);
            isOffGround = true;
            removeLife();
            if (body.getPosition().y < -2.0299994)  //necessary evil
                spawnPosition();
        }
    }

    /**
     * Renders the player on the screen.
     *
     * @param batch batch
     */
    public void render(Batch batch) {
        batch.draw(getAnimationFrame(), position.x, position.y,
                spriteIdle.getRegionWidth() * Platformer.SCALE_FACTOR,
                spriteIdle.getRegionHeight() * Platformer.SCALE_FACTOR);
    }
}
