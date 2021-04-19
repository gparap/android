/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import dmn.games.platformer.Platformer;
import dmn.games.platformer.Player;
import dmn.games.platformer.tiles.Tile;
import dmn.games.platformer.enemies.Enemy;

public class CollisionDetector implements com.badlogic.gdx.physics.box2d.ContactListener {
    @SuppressWarnings("FieldCanBeLocal")
    private boolean isEnemyStamped = false;  //corrects the stamp when velocityY is BIG

    /**
     * Called when two fixtures begin to touch.
     *
     * @param contact a contact object
     */
    @Override
    public void beginContact(Contact contact) {
        isEnemyStamped = false;

        //get the first two fixtures in the contact
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        //player sensor up contact with a body
        if (fixtureA.getUserData() != null && fixtureA.getUserData() == "sensorUp" ||
                fixtureB.getUserData() != null && fixtureB.getUserData() == "sensorUp") {
            //find which one is the non-player fixture
            Fixture fixtureObject = (fixtureA.getUserData() == "sensorUp") ? fixtureB : fixtureA;
            //get the fixture object type
            if (fixtureObject.getUserData() != null &&
                    Tile.class.isAssignableFrom(fixtureObject.getUserData().getClass())) {
                ((Tile) fixtureObject.getUserData()).hitBySensor();
            }
        }
        //player sensor down contact with an enemy sensor up
        else if ((fixtureA.getUserData() != null && fixtureA.getUserData() == "sensorDown" ||
                fixtureB.getUserData() != null && fixtureB.getUserData() == "sensorDown")
                &&
                (fixtureA.getFilterData().categoryBits != Platformer.CATEGORY_ENEMY_HURT &&
                        fixtureB.getFilterData().categoryBits != Platformer.CATEGORY_ENEMY_HURT)) {
            //get the enemy sensor up fixture
            Fixture fixtureObject = (fixtureA.getUserData() == "sensorDown") ? fixtureB : fixtureA;
            //assert that it is indeed an enemy object
            if (fixtureObject.getBody().getUserData() != null &&
                    Enemy.class.isAssignableFrom(fixtureObject.getBody().getUserData().getClass())) {
                //if it is not a boss enemy
                if (!((Enemy) fixtureObject.getBody().getUserData()).isBoss()) {
                    ((Enemy) fixtureObject.getBody().getUserData()).stamped();
                    //get the player sensor down fixture
                    Fixture fixturePlayer = (fixtureA.getUserData() == "sensorDown") ? fixtureA : fixtureB;
                    if (fixturePlayer.getBody().getLinearVelocity().y < 0) {
                        ((Player) fixturePlayer.getBody().getUserData()).getBody().setLinearVelocity(
                                ((Player) fixturePlayer.getBody().getUserData()).getBody().getLinearVelocity().x,
                                //player can jump according the the restitution force (sort of)
                                Math.abs(
                                        ((Player) fixturePlayer.getBody().getUserData()).getBody().getLinearVelocity().y) -
                                        ((Player) fixturePlayer.getBody().getUserData()).getForceJump());
                        ((Player) fixturePlayer.getBody().getUserData()).setCanJump(true);
                        ((Player) fixturePlayer.getBody().getUserData()).addStamp();
                    }
                }
            }
            isEnemyStamped = true;   //now if player hits also the body is ok
        }
        //player sensor down contact with a hurt enemy sensor up
        else if ((fixtureA.getUserData() != null && fixtureA.getFilterData().categoryBits == Platformer.CATEGORY_ENEMY_HURT ||
                fixtureA.getUserData() != null && fixtureB.getFilterData().categoryBits == Platformer.CATEGORY_ENEMY_HURT)) {
            //get the enemy sensor up fixture and the player sensor down fixture
            Fixture fixtureObject = (fixtureA.getUserData() == "sensorDown") ? fixtureB : fixtureA;
            Fixture fixturePlayer = (fixtureA.getUserData() == "sensorDown") ? fixtureA : fixtureB;
            //assert that it is indeed an enemy object and pass it the player object
            if (fixtureObject.getBody().getUserData() != null &&
                    Enemy.class.isAssignableFrom(fixtureObject.getBody().getUserData().getClass())) {
                ((Enemy) fixtureObject.getBody().getUserData()).stamped(
                        (Player) (fixturePlayer.getBody().getUserData())); //we need player velocity
            }
        }
        //enemy body contact with other bodies
        else if (fixtureA.getFilterData().categoryBits == Platformer.CATEGORY_ENEMY ||
                fixtureB.getFilterData().categoryBits == Platformer.CATEGORY_ENEMY) {
            //if fixtureA is the enemy
            if (fixtureA.getBody().getUserData() != null &&
                    Enemy.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                //if the other fixture is NOT the ground
                if (fixtureB.getUserData() != null &&
                        fixtureB.getUserData() != "ground") {
                    ((Enemy) fixtureA.getBody().getUserData()).moveReverse();
                }
                //if the other body is the player body
                if (fixtureB.getBody().getUserData() != null && !isEnemyStamped &&
                        Player.class.isAssignableFrom(fixtureB.getBody().getUserData().getClass())) {
                    ((Player) fixtureB.getBody().getUserData()).removeLife();
                }
            }
            //if fixtureB is the enemy
            else {
                //if the other fixture is NOT the ground
                if (fixtureA.getUserData() != null &&
                        fixtureA.getUserData() != "ground") {
                    ((Enemy) fixtureB.getBody().getUserData()).moveReverse();
                }
                //if the other body is the player body
                if (fixtureA.getBody().getUserData() != null && !isEnemyStamped &&
                        Player.class.isAssignableFrom(fixtureA.getBody().getUserData().getClass())) {
                    ((Player) fixtureA.getBody().getUserData()).removeLife();
                }
            }
        }
        //player body contact with tiles
        else if (fixtureA.getFilterData().categoryBits == Platformer.CATEGORY_PLAYER ||
                fixtureB.getFilterData().categoryBits == Platformer.CATEGORY_PLAYER) {
            //if one fixture in the contact is a tile
            if (fixtureA.getUserData() != null &&
                    Tile.class.isAssignableFrom(fixtureA.getUserData().getClass())) {
                ((Tile) fixtureA.getUserData()).hitByBody(((Player) fixtureB.getBody().getUserData()));//we need player
            } else if (fixtureB.getUserData() != null &&
                    Tile.class.isAssignableFrom(fixtureB.getUserData().getClass())) {
                ((Tile) fixtureB.getUserData()).hitByBody(((Player) fixtureA.getBody().getUserData()));//we need player
            }
        }

        //player body with slope
        if (fixtureA.getFilterData().categoryBits == Platformer.CATEGORY_PLAYER ||
                fixtureB.getFilterData().categoryBits == Platformer.CATEGORY_PLAYER) {
            //if one fixture in the contact is a slope
            if (fixtureA.getUserData() != null && fixtureA.getUserData() == "slope") {
                ((Player) fixtureB.getBody().getUserData()).setOnSlope(true);
            } else if (fixtureB.getUserData() != null && fixtureB.getUserData() == "slope") {
                ((Player) fixtureB.getBody().getUserData()).setOnSlope(true);
            }
        }
    }

    /**
     * Called when two fixtures cease to touch.
     *
     * @param contact a contact object
     */
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
