/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.utils;

public class Globals {
    public static final float SCALE_FACTOR                      = 1 / 100f;                         //pixels per meter
    public static final float WIDTH                             = 800;                              //game screen width
    public static final float HEIGHT                            = 480;                              //game screen width
    public static final float GRAVITY                           = 9.80665f;                         //earth's gravity
    public static final int SCORE_POINTS                        = 100;
    public static final int GET_READY_OFFSET_X                  = 10;

    //category bits for collision
    public static final short CATEGORY_DEFAULT                  = 0b0000000000000001;
    public static final short CATEGORY_PLAYER                   = 0b0000000000000010;
    public static final short CATEGORY_BONUS                    = 0b0000000000000100;
    public static final short CATEGORY_ENEMY                    = 0b0000000000001000;

    //texture atlas
    public static final String TEXTURE_ATLAS                   = "atlas_runner.pack";

    //backgrounds
    public static final String BACKGROUND                       = "background/blue_land.png";
    public static final String BACKGROUND_PARALLAX              = "background/blue_grass.png";

    //platform
    public static final float PLATFORM_SPEED                    = 480.0f;
    public static final int PLATFORM_INIT_XY_FIRST              = 0;
    public static final int PLATFORM_INIT_XY                    = -100;
    public static final int PLATFORM_HIDDEN_X                   = -100;

    //gems
    public static final String GEM_BLUE                         = "gem-blue";
    public static final String GEM_GREEN                        = "gem-green";
    public static final String GEM_RED                          = "gem-red";
    public static final String GEM_YELLOW                       = "gem-yellow";

    //aliens
    public static final String ALIEN_1                          = "p1_front";
    public static final String ALIEN_2                          = "p2_front";
    public static final String ALIEN_3                          = "p3_front";
    public static final String ALIEN_1_SPRITESHEET              = "alien/p1_spritesheet.png";
    public static final String ALIEN_2_SPRITESHEET              = "alien/p2_spritesheet.png";
    public static final String ALIEN_3_SPRITESHEET              = "alien/p3_spritesheet.png";
    public static final String ALIEN_STAND                      = "alien/p1_stand.png";

    //enemies
    public static final int ENEMY_INIT_XY                       = 1000000;
    public static final int ENEMY_HIDDEN_X                      = -100;

    //sfx and audio
    public static final String SFX_HIT_PLAYER_LOSE              = "audio/hit_player_lose.wav";
    public static final String SFX_JUMP_PLAYER                  = "audio/jump_player.wav";
    public static final String SFX_PICKUP_BONUS                 = "audio/pickup_bonus.wav";
    public static final String TEXT_MUSIC_PATH                  = "audio/endless_runner.mp3";

    //fonts
    public static final String FONT_KENNEY_PIXEL_FNT            = "font/kenney_pixel.fnt";
    public static final String FONT_KENNEY_PIXEL_OTF            = "font/kenney_pixel.otf";

    //labels
    public static final String LABEL_HIGH_SCORE_CAPS            = "HIGH SCORE: ";
    public static final String LABEL_SCORE                      = "SCORE: ";
    public static final String LABEL_DISTANCE                   = "Distance run: ";
    public static final String LABEL_NEW_HIGH_SCORE             = "NEW High score: ";
    public static final String LABEL_YOUR_SCORE                 = "Your score: ";

    //text
    public static final String TEXT_GAME_OVER                   = "GAME OVER!";
    public static final String TEXT_GET_READY                   = "Get Ready!";
    public static final String PLAYER_SENSOR_JUMP               = "player_sensor_jump";

    //buttons
    public static final String BUTTON_MENU                      = "button_menu";
    public static final String BUTTON_PLAY                      = "button_play";
    public static final String BUTTON_ALIEN                     = "button_alien";
    public static final String BUTTON_CREDITS                   = "button_credits";
    public static final String BUTTON_EXIT                      = "button_exit";

    //preferences
    public static final String PREFERENCES                      = "preferences";
    public static final String PREFERENCES_PLAYER               = "alien";
    public static final String PREFERENCES_HIGH_SCORE           = "high_score";
    public static final String PREFERENCES_SCORE                = "score";
    public static final String PREFERENCES_PREVIOUS_HIGH_SCORE  = "previous_high_score";
    public static final String PREFERENCES_DISTANCE             = "distance";
    public static final int PREFERENCES_DEFAULT                 = 0;
}
