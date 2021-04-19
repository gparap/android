/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import static gparap.games.endless_runner.utils.Globals.BACKGROUND;
import static gparap.games.endless_runner.utils.Globals.BACKGROUND_PARALLAX;
import static gparap.games.endless_runner.utils.Globals.SCALE_FACTOR;

public class Background implements Disposable {
    private final EndlessRunner game;
    private Texture textureRegion1, textureRegion2;
    private float x1, x2, y, width, height;
    private float speed;

    public Background(EndlessRunner game) {
        this.game = game;
        init();
    }

    private void init() {
        textureRegion1 = new Texture(BACKGROUND);           //scrolling
        textureRegion2 = new Texture(BACKGROUND_PARALLAX);  //parallax scrolling
        speed = 1;

        //starting positions
        width = textureRegion1.getWidth() * SCALE_FACTOR;
        height = textureRegion1.getHeight() * SCALE_FACTOR;
        x1 = 0;
        x2 = width;
        y = 0;
    }

    public void update(float delta) {
        //parallax scrolling
        x1 -= speed * delta;
        x2 -= speed * delta;

        //reset starting positions
        if (x1 + width < 0) {
            x1 = x2 + width;
        }
        if (x2 + width < 0) {
            x2 = x1 + width;
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(textureRegion1, x1, y, width, height);
        game.getSpriteBatch().draw(textureRegion2, x2, y, width, height);
    }

    @Override
    public void dispose() {
        textureRegion1.dispose();
        textureRegion2.dispose();
    }
}
