/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.objects;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import gparap.games.shooter.Globals;
import gparap.games.shooter.Shooter;

public class Shield extends GameObject {
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Shield(Shooter game) {
        super(game);
        init();
    }

    private void init() {
        initSprite(Globals.SHIELD);
        position = new Vector2(randomizePositionX(width), game.getHeight() - height);
        speed = randomizeSpeed();
        isActive = false;
    }

    public void update(float delta) {
        //update movement
        if (isActive) {
            //move downwards
            position.y -= (speed + delta);
            if (position.y + height < 0) {
                position.y = game.getHeight();
            }
        }
    }

    public void draw() {
        if (isActive) {
            game.getSpriteBatch().draw(textureRegion, position.x, position.y);
        }
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(((width * 10 / 100) / 2), ((height * 10 / 100) / 2));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void remove() {
        setActive(false);
        position = new Vector2(randomizePositionX(width), game.getHeight() - height);
        speed = randomizeSpeed();
    }

    private float randomizePositionX(float width) {
        int random = new RandomXS128().nextInt((int) (game.getWidth() - width));
        return (float) random;
    }

    public float randomizeSpeed() {
        return new RandomXS128().nextFloat() + 0.33f * game.getScaleFactor();
    }
}
