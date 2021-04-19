/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gparap.games.racing.screens.Menu;

public class Racing extends Game {
    public static float speed_factor;
    private int width, height;
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    @Override
    public void create() {
        init();

        //go to main menu
        setScreen(new Menu(this, false));
    }

    private void init() {
        spriteBatch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Globals.ATLAS);

        //get device dimensions
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //factorize speed by height scaling (design height 480)
        speed_factor = height / 480f;

        //load sfx
        assetManager = new AssetManager();
        assetManager.load(Globals.AUDIO_SFX_PATH, Sound.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();

        //release resources
        assetManager.dispose();
        textureAtlas.dispose();
    }
}
