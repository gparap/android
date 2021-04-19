/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.level;

import dmn.games.platformer.Platformer;

public class LevelFactory {
    private final Platformer game;
    private final Level[] levels;

    public LevelFactory(Platformer game) {
        this.game = game;
        levels = new Level[Platformer.LEVELS_TOTAL];
        createLevels();
    }

    public Level[] getLevels() {
        return levels;
    }

    /**
     * Creates all the levels and adds them to the level manager.
     */
    private void createLevels() {
        levels[0] = new Level("levels/level01.tmx", game.getSpriteBatch());
        levels[0].setSongName("music/platformer_01.mp3");
        levels[1] = new Level("levels/level02.tmx", game.getSpriteBatch());
        levels[1].setSongName("music/platformer_02.mp3");
        levels[2] = new Level("levels/level03.tmx", game.getSpriteBatch());
        levels[2].setSongName("music/platformer_03.mp3");
        levels[3] = new Level("levels/level04.tmx", game.getSpriteBatch());
        levels[3].setSongName("music/platformer_04.mp3");
        levels[4] = new Level("levels/level05.tmx", game.getSpriteBatch());
        levels[4].setSongName("music/platformer_05.mp3");
        levels[5] = new Level("levels/level06.tmx", game.getSpriteBatch());
        levels[5].setSongName("music/platformer_06.mp3");
        levels[6] = new Level("levels/level07.tmx", game.getSpriteBatch());
        levels[6].setSongName("music/platformer_07.mp3");
    }
}
