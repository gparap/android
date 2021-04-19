/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import gparap.games.endless_runner.objects.Platform;
import gparap.games.endless_runner.objects.Player;
import gparap.games.endless_runner.objects.spawnables.Bonus;
import gparap.games.endless_runner.objects.spawnables.Enemy;
import gparap.games.endless_runner.objects.spawnables.GameSpawnable;

import static gparap.games.endless_runner.utils.Globals.CATEGORY_PLAYER;
import static gparap.games.endless_runner.utils.Globals.PLAYER_SENSOR_JUMP;
import static gparap.games.endless_runner.utils.Globals.SCORE_POINTS;

public class GameContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        //get the first two fixtures in the contact
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //alien contact with a spawnable
        if (fixtureA.getFilterData().categoryBits == CATEGORY_PLAYER || fixtureB.getFilterData().categoryBits == CATEGORY_PLAYER) {
            //fixture A is the alien
            if (gparap.games.endless_runner.objects.Player.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                //find what is fixture B
                if (gparap.games.endless_runner.objects.spawnables.Bonus.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                    //add score and remove bonus
                    ((gparap.games.endless_runner.objects.Player) fixtureA.getBody().getUserData()).addScore(SCORE_POINTS);
                    ((GameSpawnable) fixtureB.getBody().getUserData()).hide();
                } else if (gparap.games.endless_runner.objects.spawnables.Enemy.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                    //alien loses
                    ((gparap.games.endless_runner.objects.Player) fixtureA.getBody().getUserData()).removeLife();
                    ((GameSpawnable) fixtureB.getBody().getUserData()).hide();
                }
            }
            //fixture B is the alien
            else if (gparap.games.endless_runner.objects.Player.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                //find what is fixture A
                if (Bonus.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                    //add score and remove bonus
                    ((gparap.games.endless_runner.objects.Player) fixtureB.getBody().getUserData()).addScore(SCORE_POINTS);
                    ((GameSpawnable) fixtureA.getBody().getUserData()).hide();
                } else if (Enemy.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                    //alien loses
                    ((gparap.games.endless_runner.objects.Player) fixtureB.getBody().getUserData()).removeLife();
                    ((GameSpawnable) fixtureA.getBody().getUserData()).hide();
                }
            }
        }

        //alien sensor jump with platform
        if (fixtureA.getUserData() == PLAYER_SENSOR_JUMP) {
            if (gparap.games.endless_runner.objects.Platform.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                ((gparap.games.endless_runner.objects.Player) fixtureA.getBody().getUserData()).setCanJump(true);
            }
        }
        //alien sensor jump with platform
        if (fixtureB.getUserData() == PLAYER_SENSOR_JUMP) {
            if (gparap.games.endless_runner.objects.Platform.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                ((gparap.games.endless_runner.objects.Player) fixtureB.getBody().getUserData()).setCanJump(true);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        //get the first two fixtures in the contact
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //alien sensor jump with platform
        if (fixtureA.getUserData() == PLAYER_SENSOR_JUMP) {
            if (gparap.games.endless_runner.objects.Platform.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                ((gparap.games.endless_runner.objects.Player) fixtureA.getBody().getUserData()).setCanJump(false);
            }
        }
        //alien sensor jump with platform
        if (fixtureB.getUserData() == PLAYER_SENSOR_JUMP) {
            if (Platform.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                ((Player) fixtureB.getBody().getUserData()).setCanJump(false);
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
