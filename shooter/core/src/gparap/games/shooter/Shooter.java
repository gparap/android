/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gparap.games.shooter.screens.Menu;

public class Shooter extends Game {
    private SpriteBatch spriteBatch;
    private TextureAtlas textureAtlas;
    private AssetManager assetManager;
    private int width, height;
    private float scaleFactor;

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    @Override
    public void create() {
        init();
        setScreen(new Menu(this, false));
    }

    private void init() {
        spriteBatch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Globals.TEXTURE_ATLAS);

        //get device dimensions
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        //compute scale factor based on design height
        scaleFactor = height / 480f;

        //prepare sfx
        assetManager = new AssetManager();
        assetManager.load(Globals.SFX_HIT_PLAYER, Sound.class);
        assetManager.load(Globals.SFX_HIT_ENEMY, Sound.class);
        assetManager.load(Globals.SFX_LASER_PLAYER, Sound.class);
        assetManager.load(Globals.SFX_LASER_ENEMY, Sound.class);
        assetManager.load(Globals.SFX_SHIELD, Sound.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        super.dispose();

        //release resources
        textureAtlas.dispose();
        assetManager.dispose();
    }
}
