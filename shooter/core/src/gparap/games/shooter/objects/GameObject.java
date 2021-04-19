/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.shooter.Shooter;

public class GameObject implements Disposable {
    protected final Shooter game;
    protected TextureRegion textureRegion;
    protected Vector2 position;
    protected float width, height, speed;

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    protected GameObject(Shooter game) {
        this.game = game;
    }

    /**
     * Initializes object's texture and dimensions.
     *
     * @param name texture region name in atlas
     */
    protected void initSprite(String name) {
        textureRegion = game.getTextureAtlas().findRegion(name);
        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();
    }

    /**
     * Returns the rectangle that surrounds the game object.
     *
     * @param offsetWidth  helper offset for width
     * @param offsetHeight helper offset for height
     * @return rectangle
     */
    protected Rectangle getCollisionRectangle(float offsetWidth, float offsetHeight) {
        Rectangle rectangle = new Rectangle();
        rectangle.x = position.x;
        rectangle.y = position.y;
        rectangle.width = (position.x + width) - offsetWidth;
        rectangle.height = (position.y + height) - offsetHeight;
        return rectangle;
    }

    @Override
    public void dispose() {
        textureRegion.getTexture().dispose();
    }
}
