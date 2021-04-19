/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.Locale;

import dmn.games.platformer.Background;
import dmn.games.platformer.Platformer;

public class GameOver extends Screen {
    private final int currentLevelIndex;
    private Table tableButton;
    private Image buttonPlayAgain, buttonGotoMenu;
    private Label labelLevelL, labelLevelR,
                  labelCoinsTotalL, labelCoinsTotalR,
                  labelCandysTotalL, labelCandysTotalR,
                  labelScoreL, labelScoreR,
                  labelEnemiesStampedTotalL, labelEnemiesStampedTotalR,
                  labelEmpty; //placeholder
    private StringBuilder userInfoText;
    private boolean isPressedPlayAgain, isPressedMainMenu;
    @SuppressWarnings("FieldCanBeLocal")
    private final float BUTTON_WIDTH = 128,
                        BUTTON_HEIGHT = 64,
                        BUTTON_PAD = 64,
                        LABEL_PAD = 32;

    public GameOver(Platformer game, int currentLevelIndex, Background background) {
        super(game, background, ViewportType.FILL);
        this.currentLevelIndex = currentLevelIndex;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        isPressedPlayAgain = false;
        isPressedMainMenu = false;
        userInfoText = new StringBuilder();
        createFont();
        createLabels(Gdx.app.getPreferences("platformer_preferences"));
        //create play again button
        buttonPlayAgain = new Image(game.getAtlas().findRegion("button_play_again"));
        buttonPlayAgain.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonPlayAgain.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPlayAgain = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPlayAgain = false;
            }
        });
        //create go to main menu button
        buttonGotoMenu = new Image(game.getAtlas().findRegion("button_goto_menu"));
        buttonGotoMenu.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonGotoMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedMainMenu = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedMainMenu = false;
            }
        });
        //create user information about the game
        createInfoText();
        //add tables to stage
        stage.addActor(createTableLabelInfo());
        stage.addActor(createTablePlayAgain());
        stage.addActor(createTableGotoMenu());
        stage.addActor(createTableGameOver());
        //receive all touch events
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    public void render(float delta) {
        super.render(delta);
        //play again
        if (isPressedPlayAgain) {
            this.dispose();
            game.setScreen(new Play(game, -1));
        }
        //go to main menu
        if (isPressedMainMenu) {
            this.dispose();
            game.setScreen(new Menu(game, background));
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        super.dispose();
    }

    private void createLabels(Preferences preferences) {
        //check if 1st level is not completed
        int level = currentLevelIndex == 0 ? 0 : preferences.getInteger(Platformer.LEVEL);
        //level
        labelLevelL = new Label("Levels Completed: ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
        labelLevelR = new Label(String.format(Locale.getDefault(),"%d", level),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //score
        labelScoreL = new Label("Score: ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
        labelScoreR = new Label(String.format(Locale.getDefault(),"%d", preferences.getInteger(Platformer.SCORE)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //coins (total)
        labelCoinsTotalL = new Label("Total Coins Collected: ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
        labelCoinsTotalR = new Label(String.format(Locale.getDefault(),"%d", preferences.getInteger(Platformer.COINS_TOTAL)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //candys (total)
        labelCandysTotalL = new Label("Total Candys Collected: ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
        labelCandysTotalR = new Label(String.format(Locale.getDefault(),"%d", preferences.getInteger(Platformer.CANDYS_TOTAL)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //enemies
        labelEnemiesStampedTotalL = new Label("Total Enemies Stamped: ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
        labelEnemiesStampedTotalR = new Label(String.format(Locale.getDefault(),"%d", preferences.getInteger(Platformer.ENEMIES_STAMPED_TOTAL)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //(empty label placeholder)
        labelEmpty = new Label(" ",
                new Label.LabelStyle(font, Color.DARK_GRAY));
    }

    /**
     * Create a table actor in the center with game level info.
     */
    private Table createTableLabelInfo() {
        Table tableInfo = new Table();
        tableInfo.add(labelLevelL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelLevelR).right();
        tableInfo.row();
        tableInfo.add(labelScoreL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelScoreR).right();
        tableInfo.row();
        tableInfo.add(labelCoinsTotalL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelCoinsTotalR).right();
        tableInfo.row();
        tableInfo.add(labelCandysTotalL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelCandysTotalR).right();
        tableInfo.row();
        tableInfo.add(labelEnemiesStampedTotalL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelEnemiesStampedTotalR).right();
        tableInfo.row();
        tableInfo.setFillParent(true);
        tableInfo.center();
        return tableInfo;
    }

    /**
     * Create a table actor on the right side with a button to play again.
     */
    private Table createTablePlayAgain() {
        tableButton = new Table();
        tableButton.add(buttonPlayAgain).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD).center();
        tableButton.setFillParent(true);
        tableButton.bottom().right();
        return tableButton;
    }

    /**
     * Create a table actor on the left side with a button to go to the game main menu.
     */
    private Table createTableGotoMenu() {
        tableButton = new Table();
        tableButton.add(buttonGotoMenu).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD).center();
        tableButton.setFillParent(true);
        tableButton.bottom().left();
        return tableButton;
    }

    /**
     * Create a table actor at the top displaying that the game is over.
     */
    private Actor createTableGameOver() {
        Label labelGameOver = new Label(userInfoText, new Label.LabelStyle(font, Color.DARK_GRAY));
        tableButton = new Table();
        tableButton.add(labelGameOver).padTop(LABEL_PAD).center();
        tableButton.setFillParent(true);
        tableButton.top();
        return tableButton;
    }

    /**
     * Create the text that will inform the player (if has won or lost the game).
     */
    private void createInfoText() {
        //clean up string builder
        userInfoText.delete(0, userInfoText.length);
        //create user information
        if (currentLevelIndex == Platformer.LEVELS_TOTAL) {
            userInfoText.append("YOU WON THE GAME!");
        } else {
            userInfoText.append("GAME OVER!");
        }
    }
}
