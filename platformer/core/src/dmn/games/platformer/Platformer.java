/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dmn.games.platformer.screens.Menu;

public class Platformer extends Game {
    //PUBLIC
    public static final float SCALE_FACTOR         = 1 / 64f;              //1 unit in game world equals 64 pixels in tiled map
    public static final float WIDTH                = 800,                  //we need them for
                              HEIGHT               = 480;                  // TouchPad and HUD stages
    public static final int   LEVELS_TOTAL         = 7;
    //category bits for collision
    public static final short CATEGORY_DEFAULT     = 0b0000000000000001;    //  1
    public static final short CATEGORY_PLAYER      = 0b0000000000000010;    //  2
    public static final short CATEGORY_CRATE       = 0b0000000000000100;    //  4
    public static final short CATEGORY_PICKUP      = 0b0000000000001000;    //  8
    public static final short CATEGORY_KEY         = 0b0000000000010000;    // 16
    public static final short CATEGORY_ENEMY       = 0b0000000000100000;    // 32
    public static final short CATEGORY_ENEMY_TOP   = 0b0000000001000000;    // 64
    public static final short CATEGORY_ENEMY_HURT  = 0b0000000010000000;    //128
    public static final short CATEGORY_NULL        = 0b0000000100000000;    //no more categories
    //preferences
    public static final String LEVEL                   = "level";
    public static final String SCORE                   = "score";
    public static final String KEY                     = "key";
    public static final String LIFE                    = "life";
    public static final String COINS_LEVEL             = "coins_level";
    public static final String COINS_GOLD              = "coins_gold";
    public static final String COINS_SILVER            = "coins_silver";
    public static final String COINS_BROWN             = "coins_brown";
    public static final String COINS_TOTAL             = "coins_total";
    public static final String COINS_TOTAL_GOLD        = "coins_total_gold";
    public static final String COINS_TOTAL_SILVER      = "coins_total_silver";
    public static final String COINS_TOTAL_BROWN       = "coins_total_brown";
    public static final String CANDYS_LEVEL            = "candys_level";
    public static final String CANDYS_GUMMY_GREEN      = "candys_gummy_green";
    public static final String CANDYS_GUMMY_RED        = "candys_gummy_red";
    public static final String CANDYS_WAFFLE_CHOCO     = "candys_waffle_choco";
    public static final String CANDYS_WAFFLE_WHITE     = "candys_waffle_white";
    public static final String CANDYS_TOTAL            = "candys_total";
    public static final String ENEMIES_STAMPED_LEVEL   = "enemies_stamped_level";
    public static final String ENEMIES_STAMPED_TOTAL   = "enemies_stamped_total";
    public static final String TIME_ELAPSED            = "time_elapsed";
    //PRIVATE
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

    /**
     * Called when the application is first created.
     */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("atlas_platformer.pack"));
        assetManager = new AssetManager();
        //MUSIC
        assetManager = new AssetManager();
        //SFX
        assetManager.load("sfx/hit_crate.wav", Sound.class);
        assetManager.load("sfx/hit_enemy_head.wav", Sound.class);
        assetManager.load("sfx/hit_player_lose.wav", Sound.class);
        assetManager.load("sfx/hit_wall.wav", Sound.class);
        assetManager.load("sfx/jump_player.wav", Sound.class);
        assetManager.load("sfx/pickup.wav", Sound.class);
        assetManager.finishLoading();
        setScreen(new Menu(this, null));
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        assetManager.dispose();
        atlas.dispose();
        getScreen().dispose();
    }
}
