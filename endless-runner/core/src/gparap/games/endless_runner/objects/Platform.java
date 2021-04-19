/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import gparap.games.endless_runner.physics.BodyFactory;
import gparap.games.endless_runner.utils.Globals;

import static gparap.games.endless_runner.utils.Globals.SCALE_FACTOR;
import static gparap.games.endless_runner.utils.Globals.WIDTH;
import static gparap.games.endless_runner.utils.Globals.HEIGHT;

public class Platform extends GameObject {
    private final String textureName;
    private final float positionX, positionY;
    private float speed;

    public Platform(World world, String textureName, float positionX, float positionY) {
        super(world);
        this.textureName = textureName;
        this.positionX = positionX;
        this.positionY = positionY;
        init();
    }

    protected void init() {
        type = Type.PLATFORM;
        isActive = false;
        speed = Globals.PLATFORM_SPEED;

        //create sprite
        sprite = new Sprite(new Texture(textureName));
        sprite.setSize(sprite.getWidth() * SCALE_FACTOR, sprite.getHeight() * SCALE_FACTOR);
        sprite.setOrigin(sprite.getWidth() / 2f, sprite.getHeight() / 2f);
        sprite.setPosition(positionX * SCALE_FACTOR, positionY * SCALE_FACTOR);

        //create body
        body = BodyFactory.createBody(world, sprite, BodyDef.BodyType.StaticBody, false, (short) 0, (short) 0, 0, 0);
        body.setUserData(this);
    }

    @Override
    public void update(float delta) {
        //enable endless scrolling
        sprite.setX(sprite.getX() - speed * delta * SCALE_FACTOR);
        body.setTransform(sprite.getX(), sprite.getY(), 0);

        //check if out of screen
        if ((sprite.getX() + sprite.getWidth()) < 0) {
            setActive(false);

            //set out of sight
            sprite.setX(Globals.PLATFORM_HIDDEN_X);
            body.setTransform(sprite.getX(), sprite.getY(), 0);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private float getRandomSpace(int value) {
        float random = new RandomXS128().nextInt(value);
        random *= SCALE_FACTOR;
        return random;
    }

    public void resetPositionX() {
        float randomSpace = getRandomSpace((int) ((int) WIDTH / 3f));

        //necessary fix for alien sensor to allow
        //  distinction between begin and end contact states
        if (randomSpace < 100f * SCALE_FACTOR) {
            randomSpace = 100f * SCALE_FACTOR;
        }
        sprite.setX(WIDTH * SCALE_FACTOR + randomSpace);
        body.setTransform(sprite.getX(), sprite.getY(), 0);
    }

    public void resetPositionY(float currentY) {
        //randomize if platform should go up or down
        int randomUpOrDown = new RandomXS128().nextInt(2);
        randomUpOrDown = randomUpOrDown == 0 ? +1 : -1;

        //randomize position with +- offset based of current Y
        float randomSpace = getRandomSpace(175);

        //set new position and check if is valid
        sprite.setY(currentY + randomSpace * randomUpOrDown);
        if (sprite.getY() < 0) {
            sprite.setY(0);
        }
        if (sprite.getY() > (HEIGHT - 160) * SCALE_FACTOR) {
            sprite.setY((HEIGHT - 160) * SCALE_FACTOR);
        }
        body.setTransform(sprite.getX(), sprite.getY(), 0);
    }

    public float getSpeed() {
        return speed;
    }
}
