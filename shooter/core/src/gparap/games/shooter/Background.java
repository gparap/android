/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Background implements Disposable {
    private final Shooter game;
    private TextureRegion textureRegion, textureRegion1, textureRegion2;
    private float x, y, y1, y2;
    private float speed;

    public Background(Shooter game) {
        this.game = game;
        init();
    }

    private void init() {
        textureRegion = game.getTextureAtlas().findRegion(Globals.BACKGROUND_STARS);     //for glitches
        textureRegion1 = game.getTextureAtlas().findRegion(Globals.BACKGROUND_STARS);    //for scrolling
        textureRegion2 = game.getTextureAtlas().findRegion(Globals.BACKGROUND_STARS);    //for scrolling
        speed = 1f * game.getScaleFactor();
        //starting positions
        x = 0;
        y = 0;
        y1 = 0;
        y2 = game.getHeight();
    }

    public void update(float delta) {
        //scrolling downwards
        y1 -= speed - delta;
        y2 -= speed - delta;

        //reset starting positions
        if (y1 + game.getHeight() < 0) {
            y1 = game.getHeight();
        }
        if (y2 + game.getHeight() < 0) {
            y2 = game.getHeight();
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(textureRegion, x, y, game.getWidth(), game.getHeight());
        game.getSpriteBatch().draw(textureRegion1, x, y1, game.getWidth(), game.getHeight());
        game.getSpriteBatch().draw(textureRegion2, x, y2, game.getWidth(), game.getHeight());
    }

    @Override
    public void dispose() {
        textureRegion.getTexture().dispose();
        textureRegion1.getTexture().dispose();
        textureRegion2.getTexture().dispose();
    }
}
