/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Road implements Disposable {
    private final Racing game;
    private TextureRegion textureRegionClear, textureRegion, textureRegionParallax;
    private float x, y, y1, y2;
    private float speed;

    public Road(Racing game) {
        this.game = game;
        init();
    }

    private void init() {
        textureRegionClear = game.getTextureAtlas().findRegion(Globals.ROAD);
        textureRegion = game.getTextureAtlas().findRegion(Globals.ROAD);
        textureRegionParallax = game.getTextureAtlas().findRegion(Globals.ROAD);
        speed = 10 * Racing.speed_factor;

        //starting positions
        x = 0;
        y = 0;
        y1 = 0;
        y2 = game.getHeight();
    }

    public void update(float delta) {
        //move downwards
        y1 -= speed - delta; //- Racing.fps_factor;
        y2 -= speed - delta; //- Racing.fps_factor;

        //reset starting positions
        if (y1 + game.getHeight() < 0) {
            y1 = game.getHeight();
        }
        if (y2 + game.getHeight() < 0) {
            y2 = game.getHeight();
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(textureRegionClear, x, y, game.getWidth(), game.getHeight());
        game.getSpriteBatch().draw(textureRegion, x, y1, game.getWidth(), game.getHeight());
        game.getSpriteBatch().draw(textureRegionParallax, x, y2, game.getWidth(), game.getHeight());
    }

    @Override
    public void dispose() {
        textureRegionClear.getTexture().dispose();
        textureRegion.getTexture().dispose();
        textureRegionParallax.getTexture().dispose();
    }
}
