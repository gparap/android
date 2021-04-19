/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import gparap.games.endless_runner.screens.Menu;
import gparap.games.endless_runner.utils.Globals;

public class EndlessRunner extends Game {
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;
    private TextureAtlas atlas;

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        assetManager = new AssetManager();
        atlas = new TextureAtlas(Gdx.files.internal(Globals.TEXTURE_ATLAS));
        loadSFX();
        setScreen(new Menu(this, false));
    }

    private void loadSFX() {
        assetManager.load(Globals.SFX_HIT_PLAYER_LOSE, Sound.class);
        assetManager.load(Globals.SFX_JUMP_PLAYER, Sound.class);
        assetManager.load(Globals.SFX_PICKUP_BONUS, Sound.class);
        assetManager.finishLoading();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        atlas.dispose();
    }
}
