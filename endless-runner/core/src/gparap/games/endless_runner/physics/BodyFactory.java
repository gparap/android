/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.physics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import gparap.games.endless_runner.utils.Globals;

public class BodyFactory {
    /**
     * Creates a body and adds it to the game world.
     *
     * @param world    game world
     * @param sprite   sprite
     * @param bodyType BodyDef type
     * @param isSensor shape only contacts but never collides
     * @return body
     */
    public static Body createBody(World world, Sprite sprite, BodyDef.BodyType bodyType, boolean isSensor, short categoryBits, short maskBits,
                                  float widthOffset, float heightOffset) {
        //create and add the body to the game world
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(sprite.getX() * gparap.games.endless_runner.utils.Globals.SCALE_FACTOR, sprite.getY() * Globals.SCALE_FACTOR);
        Body body = world.createBody(bodyDef);

        //create the shape of the fixture of the body
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2f - widthOffset,
                       sprite.getHeight() / 2f - heightOffset,
                new Vector2(sprite.getOriginX(),
                            sprite.getOriginY()),
                0);

        //create the fixture of the body and add to it a shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        if (isSensor) {
            fixtureDef.isSensor = true; //don't collide with body, just sense it
        }
        if (categoryBits != 0) {
            fixtureDef.filter.categoryBits = categoryBits;
        }
        if (maskBits != 0) {
            fixtureDef.filter.maskBits = maskBits;
        }
        body.createFixture(fixtureDef);
        shape.dispose(); //don't need the shape anymore

        return body;
    }
}
