/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.vehicles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.racing.Racing;

public class Vehicle implements Disposable {
    protected final Racing game;
    protected TextureRegion textureRegion;
    protected Vector2 position;
    protected float width, height, speed;

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    protected Vehicle(Racing game) {
        this.game = game;
    }

    /**
     * Initializes vehicle's texture and dimensions.
     *
     * @param name texture region name in atlas
     */
    protected void initSprite(String name) {
        textureRegion = game.getTextureAtlas().findRegion(name);
        width = textureRegion.getRegionWidth();
        height = textureRegion.getRegionHeight();
    }

    /**
     * Returns the rectangle that surrounds the vehicle.
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
