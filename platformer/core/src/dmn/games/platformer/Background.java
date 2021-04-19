/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Background implements Disposable {
    private final Platformer game;
    private final Texture texture1;
    private final Texture texture2;
    public OrthographicCamera camera;
    private float x1, x2;
    @SuppressWarnings("FieldCanBeLocal")
    private final float MOVEMENT_THRESHOLD_X_MIN = 7.5f,  //background must move when camera moves...
                        MOVEMENT_THRESHOLD_X_MAX = 192.5f;//...between minimum and maximum thresholds

    //background construction for the main game
    public Background(Platformer game, String path1, String path2, OrthographicCamera camera) {
        this.camera = camera;
        this.game = game;
        this.texture1 = new Texture(Gdx.files.internal(path1));
        this.texture2 = new Texture(Gdx.files.internal(path2));
        init();
    }

    private void init() {
        reset();
    }

    public void update(float delta, char direction, float velocity, OrthographicCamera camera) {
        //scroll backgrounds only when camera moves
        if (camera.position.x > MOVEMENT_THRESHOLD_X_MIN &&
                camera.position.x < MOVEMENT_THRESHOLD_X_MAX) {
            //scroll left
            if (direction == 'L') {
                x1 += delta * Math.abs(velocity / 2f);
                x2 += delta * Math.abs(velocity / 2f);
                //reset position
                if ((x1) > (camera.position.x + (texture1.getWidth() / 2f * Platformer.SCALE_FACTOR))) {
                    if (x1 > x2) {
                        x1 = x2 - (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    } else {
                        x2 = x1 - (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    }
                } else if ((x2) > (camera.position.x + (texture1.getWidth() / 2f * Platformer.SCALE_FACTOR))) {
                    if (x2 > x1) {
                        x2 = x1 - (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    } else {
                        x1 = x2 - (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    }
                }
            }
            //scroll right
            else if (direction == 'R') {
                x1 -= delta * velocity / 2f;
                x2 -= delta * velocity / 2f;
                //reset position
                if ((x1 + (texture1.getWidth() * Platformer.SCALE_FACTOR)) <
                        (camera.position.x - (texture1.getWidth() / 2f * Platformer.SCALE_FACTOR))) {
                    if (x1 < x2) {
                        x1 = x2 + (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    } else {
                        x2 = x1 + (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    }
                } else if ((x2 + (texture1.getWidth() * Platformer.SCALE_FACTOR)) <
                        (camera.position.x - (texture1.getWidth() / 2f * Platformer.SCALE_FACTOR))) {
                    if (x2 < x1) {
                        x2 = x1 + (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    } else {
                        x1 = x2 + (texture1.getWidth() * Platformer.SCALE_FACTOR);
                    }
                }
            }
        }
    }

    public void draw() {
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(texture1, x1, 0, texture1.getWidth() * Platformer.SCALE_FACTOR,
                texture1.getHeight() * Platformer.SCALE_FACTOR);
        game.getSpriteBatch().draw(texture2, x2, 0, texture2.getWidth() * Platformer.SCALE_FACTOR,
                texture2.getHeight() * Platformer.SCALE_FACTOR);
        game.getSpriteBatch().end();
    }

    /**
     * Initializes backgrounds positions
     */
    public void reset() {
        x1 = 0;
        x2 = x1 + (texture1.getWidth() * Platformer.SCALE_FACTOR);
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        texture1.dispose();
        texture2.dispose();
    }
}
