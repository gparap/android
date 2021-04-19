/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects.spawnables;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import gparap.games.endless_runner.physics.BodyFactory;
import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;
import gparap.games.endless_runner.objects.GameObject;

import static gparap.games.endless_runner.utils.Globals.CATEGORY_BONUS;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_DEFAULT;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_ENEMY;
import static gparap.games.endless_runner.utils.Globals.CATEGORY_PLAYER;
import static gparap.games.endless_runner.utils.Globals.HEIGHT;
import static gparap.games.endless_runner.utils.Globals.SCALE_FACTOR;

/**
 * Parent class for game spawnable objects.
 */
public class GameSpawnable extends GameObject {
    protected EndlessRunner game;
    protected boolean canFly;
    protected float speed;
    private final float positionX, positionY;
    private float availableX, availableY;           //limits of where the spawnable...
    private float availableWidth, availableHeight;  //...can be instantiated
    private int difficultyBonus, difficultyEnemy;
    //animation
    protected Animation<TextureRegion> animationWalk;
    protected float stateTime, frameDuration;
    private TextureRegion textureRegion;

    public void setDifficultyBonus(int difficultyBonus) {
        this.difficultyBonus = difficultyBonus;
    }

    public void setDifficultyEnemy(int difficultyEnemy) {
        this.difficultyEnemy = difficultyEnemy;
    }

    public void setAvailableX(float availableX) {
        this.availableX = availableX;
    }

    public void setAvailableY(float availableY) {
        this.availableY = availableY;
    }

    public void setAvailableWidth(float availableWidth) {
        this.availableWidth = availableWidth;
    }

    public void setAvailableHeight(float availableHeight) {
        this.availableHeight = availableHeight;
    }

    public GameSpawnable(EndlessRunner game, World world, float positionX, float positionY, Type type, boolean canFly) {
        super(world);
        this.game = game;
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
        this.canFly = canFly;
    }

    protected void init(String textureRegionName) {
        isActive = false;
        speed = 480f;
        difficultyBonus = 0;
        difficultyEnemy = 0;

        //create sprite
        sprite = new Sprite(game.getAtlas().findRegion((textureRegionName)));
        sprite.setSize(sprite.getWidth() * SCALE_FACTOR, sprite.getHeight() * SCALE_FACTOR);
        sprite.setOrigin(sprite.getWidth() / 2f, sprite.getHeight() / 2f);
        sprite.setPosition(positionX * SCALE_FACTOR, positionY * SCALE_FACTOR);

        //create box2d body
        short categoryBits = 0;
        short maskBits = 0;
        if (type == Type.BONUS) {
            categoryBits = CATEGORY_BONUS;
            maskBits = CATEGORY_DEFAULT | CATEGORY_PLAYER | CATEGORY_ENEMY;
        } else if (type == Type.ENEMY) {
            categoryBits = CATEGORY_ENEMY;
            maskBits = CATEGORY_DEFAULT | CATEGORY_PLAYER | CATEGORY_ENEMY;
        }
        body = BodyFactory.createBody(world, sprite, BodyDef.BodyType.DynamicBody, true, categoryBits, maskBits, 0, 0);
        body.setUserData(this);
    }

    @Override
    public void update(float delta) {
        if (!isActive) {
            //update starting position
            sprite.setX(availableX);
            sprite.setY(availableY);

            //randomize bonus activation and position
            int randomActivate;
            if (type == Type.BONUS) {
                randomActivate = new RandomXS128().nextInt(difficultyBonus);
                if (randomActivate == 0) {
                    setActive(true);
                    sprite.setX(availableX + getRandomSpace(availableWidth - sprite.getWidth()));
                    sprite.setY(availableY + availableHeight * SCALE_FACTOR);
                    randomizeTexture();
                }
            } else if (type == Type.ENEMY) {
                randomActivate = new RandomXS128().nextInt(difficultyEnemy);
                if (randomActivate == 0) {
                    setActive(true);
                    sprite.setX(availableX + availableWidth / 2f * SCALE_FACTOR + getRandomSpace(availableWidth / 2f - sprite.getWidth()));
                    sprite.setY(availableY + availableHeight * SCALE_FACTOR);
                    if (canFly) {
                        sprite.setY(availableY + availableHeight * SCALE_FACTOR + getRandomSpace(HEIGHT) - sprite.getHeight());
                    }
                }
            }
        }
        if (isActive) {
            //enable endless scrolling
            sprite.setX(sprite.getX() - speed * delta * SCALE_FACTOR);
            body.setTransform(sprite.getX(), sprite.getY(), 0);

            //check if out of screen
            if ((sprite.getX() + sprite.getWidth()) < 0) {
                setActive(false);

                //set out of sight
                sprite.setX(Globals.ENEMY_HIDDEN_X);
                body.setTransform(sprite.getX(), sprite.getY(), 0);
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (isActive) {
            if (type == Type.BONUS) {
                sprite.draw(spriteBatch);

            } else if (type == Type.ENEMY) {
                spriteBatch.draw(getAnimationFrame(), getX(), getY(),
                        sprite.getRegionWidth() * SCALE_FACTOR,
                        sprite.getRegionHeight() * SCALE_FACTOR);
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        textureRegion.getTexture().dispose();
    }

    protected float getRandomSpace(float value) {
        float random = new RandomXS128().nextInt((int) value);
        random *= SCALE_FACTOR;
        return random;
    }

    protected void randomizeTexture() {
    }

    /**
     * Returns the current frame of the animation.
     */
    TextureRegion getAnimationFrame() {
        textureRegion = animationWalk.getKeyFrame(stateTime, true);
        stateTime += frameDuration;
        return textureRegion;
    }

    public void hide() {
        sprite.setX(Globals.ENEMY_HIDDEN_X);
    }
}
