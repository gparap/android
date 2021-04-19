/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.physics;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import dmn.games.platformer.Platformer;

public class BodyFactory {
    public static void createBody(World world, MapObject mapObject, Array<Body> worldBodies, boolean isCurrentLevelWorldBody) {
        //if it is an object of type "wall" (rectangle)
        if (mapObject.getProperties().get("type").equals("platform")) {
            //create and add the body to the game world
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.StaticBody;
            bodyDefinition.position.set(((RectangleMapObject) mapObject).getRectangle().x * Platformer.SCALE_FACTOR,
                                        ((RectangleMapObject) mapObject).getRectangle().y * Platformer.SCALE_FACTOR);
            Body body = world.createBody(bodyDefinition);
            //create the shape of the fixture of the body
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(((RectangleMapObject) mapObject).getRectangle().width / 2f * Platformer.SCALE_FACTOR,
                           ((RectangleMapObject) mapObject).getRectangle().height / 2f * Platformer.SCALE_FACTOR,
                    new Vector2(((RectangleMapObject) mapObject).getRectangle().width / 2f * Platformer.SCALE_FACTOR,
                                ((RectangleMapObject) mapObject).getRectangle().height / 2f * Platformer.SCALE_FACTOR),
                    0);
            //create the fixture of the body and add to it a shape
            FixtureDef fixtureDefinition = new FixtureDef();
            fixtureDefinition.shape = shape;
            body.createFixture(fixtureDefinition);
            shape.dispose(); //don't need the shape anymore
            //add body to current level world bodies
            if (isCurrentLevelWorldBody) {
                worldBodies.add(body);
            }
        }
        //if it is an object of type "ground" or "slope" (polyline)
        else if (mapObject.getProperties().get("type").equals("ground") ||
                 mapObject.getProperties().get("type").equals("slope")) {
            //create and add the body to the game world
            BodyDef bodyDefinition = new BodyDef();
            bodyDefinition.type = BodyDef.BodyType.StaticBody;
            bodyDefinition.position.set(
                    ((PolylineMapObject) mapObject).getPolyline().getX() * Platformer.SCALE_FACTOR,
                    ((PolylineMapObject) mapObject).getPolyline().getY() * Platformer.SCALE_FACTOR);
            Body body = world.createBody(bodyDefinition);
            //create the shape of the fixture of the body
            ChainShape shape = new ChainShape();
            //scale the vertices of the shape
            int scaledVerticesLength = ((PolylineMapObject) mapObject).getPolyline().getVertices().length;
            float[] scaledVertices = new float[scaledVerticesLength];
            for (int i = 0; i < scaledVerticesLength; i++) {
                scaledVertices[i] =
                        ((PolylineMapObject) mapObject).getPolyline().getVertices()[i] * Platformer.SCALE_FACTOR;
            }
            shape.createChain(scaledVertices);
            //create the fixture of the body and add to it a shape
            FixtureDef fixtureDefinition = new FixtureDef();
            fixtureDefinition.shape = shape;
            Fixture fixture = body.createFixture(fixtureDefinition);
            if (mapObject.getProperties().get("type").equals("ground")) {
                fixture.setUserData("ground");
            } else if (mapObject.getProperties().get("type").equals("slope")) {
                fixture.setUserData("slope");
            }
            shape.dispose(); //don't need the shape anymore
            //add body to current level world bodies
            if (isCurrentLevelWorldBody) {
                worldBodies.add(body);
            }
        }
    }
}
