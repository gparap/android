/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

/**
 * Parent class for game objects.
 */
public class GameObject implements Disposable {
    public enum Type {
        PLATFORM, PLAYER, BONUS, ENEMY
    }

    protected Type type;
    protected Sprite sprite;
    protected World world;
    protected Body body;
    protected boolean isActive;

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getWidth() {
        return sprite.getTexture().getWidth();
    }

    public float getHeight() {
        return sprite.getTexture().getHeight();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        if (active) {
            body.setActive(true);
            body.setAwake(true);
        } else {
            body.setActive(false);
            body.setAwake(false);
        }
    }

    protected GameObject(World world) {
        this.world = world;
    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
    }
}
