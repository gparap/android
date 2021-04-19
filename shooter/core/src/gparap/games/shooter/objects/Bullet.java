/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.objects;

import com.badlogic.gdx.math.Rectangle;

import gparap.games.shooter.Globals;
import gparap.games.shooter.Shooter;

public class Bullet extends GameObject {
    public enum Type {
        PLAYER, ENEMY
    }

    private final Enum<Type> type;
    private boolean isFired;

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public Bullet(Shooter game, Type type) {
        super(game);
        this.type = type;
        init();
    }

    private void init() {
        //init sprite
        if (type == Type.PLAYER) {
            initSprite(Globals.PLAYER_LASER_GREEN);
        } else if (type == Type.ENEMY) {
            initSprite(Globals.ENEMY_LASER_RED);
        }

        speed = 5f * game.getScaleFactor();
        isFired = false;
    }

    public void update(float delta) {
        if (isFired) {

            //if it is a player's bullet
            if (type == Type.PLAYER) {
                position.y += speed + delta;
                if ((position.y + height) > game.getHeight()) {
                    isFired = false;
                    setHiddenPosition();    //disappear while not fired
                }
            }

            //if it is an enemy's bullet
            else if (type == Type.ENEMY) {
                position.y -= speed + delta;
                if (position.y + height < 0) {
                    isFired = false;
                }
            }
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(textureRegion, position.x, position.y);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(0, 0);
    }

    public void setHiddenPosition() {
        position.set(-100, -100);
    }
}
