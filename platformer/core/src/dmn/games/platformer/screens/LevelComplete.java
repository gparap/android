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

import java.util.Locale;

import dmn.games.platformer.Background;
import dmn.games.platformer.Platformer;

public class LevelComplete extends Screen {
    private final int currentLevelIndex;
    private Table tableButton;
    private Image buttonNextLevel, buttonGotoMenu;
    private Label labelLabel,
                  labelLevelL,                  labelLevelR,
                  labelLifeL,                   labelLifeR,
                  labelCoinsTotalL,             labelCoinsTotalR,
                  labelCoinsGoldL,              labelCoinsGoldR,
                  labelCoinsSilverL,            labelCoinsSilverR,
                  labelCoinsBrownL,             labelCoinsBrownR,
                  labelScoreL,                  labelScoreR,
                  labelEnemiesStampedTotalL,    labelEnemiesStampedTotalR,
                  labelTimeL,                   labelTimeR,
                  labelCandysTotalL,            labelCandysTotalR,
                  labelCandysGummyGreenL,       labelCandysGummyGreenR,
                  labelCandysGummyRedL,         labelCandysGummyRedR,
                  labelEmpty; //placeholder
    private boolean isPressedNextLevel, isPressedMainMenu;
    @SuppressWarnings("FieldCanBeLocal")
    private final float BUTTON_WIDTH = 128,
                        BUTTON_HEIGHT = 64,
                        BUTTON_PAD = 64,
                        LABEL_PAD = 32;

    public LevelComplete(Platformer game, int currentLevelIndex, Background background) {
        super(game, background, ViewportType.FILL);
        this.currentLevelIndex = currentLevelIndex;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        isPressedNextLevel = false;
        isPressedMainMenu = false;
        createFont();
        createLabels(Gdx.app.getPreferences("platformer_preferences"));
        //create go to next level button
        buttonNextLevel = new Image(game.getAtlas().findRegion("button_next_level"));
        buttonNextLevel.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonNextLevel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedNextLevel = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedNextLevel = false;
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
        //add tables to stage
        stage.addActor(createTableLevel());
        stage.addActor(createTableInfo(currentLevelIndex));  //add table with completed level info
        stage.addActor(createTableNextLevel());//add table with button for next level
        stage.addActor(createTableGotoMenu());  //add table with button for main menu
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
        //go to next level
        if (isPressedNextLevel) {
            this.dispose();
            background.dispose();
            game.setScreen(new Play(game, currentLevelIndex));
        }
        if (isPressedMainMenu) {
            this.dispose();
            game.setScreen(new Menu(game, background));
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {
        super.dispose();
    }

    private void createLabels(Preferences preferences) {
        //placeholder
        labelEmpty = new Label(" ", new Label.LabelStyle(font, Color.DARK_GRAY));
        //user info text
        labelLabel = new Label("LEVEL COMPLETED!", new Label.LabelStyle(font, Color.DARK_GRAY));
        //level
        labelLevelL = new Label("Level: ", new Label.LabelStyle(font, Color.DARK_GRAY));
        labelLevelR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.LEVEL)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //life
        labelLifeL = new Label("Life: ", new Label.LabelStyle(font, Color.DARK_GRAY));
        labelLifeR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.LIFE)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //score
        labelScoreL = new Label("Score: ", new Label.LabelStyle(font, Color.DARK_GRAY));
        labelScoreR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.SCORE)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //default land (levels 1-5)
        if (currentLevelIndex < 5) {
            //coins
            labelCoinsTotalL = new Label("Coins Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsTotalR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.COINS_LEVEL)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsGoldL = new Label("Gold Coins Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsGoldR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.COINS_GOLD)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsSilverL = new Label("Silver Coins Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsSilverR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.COINS_SILVER)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsBrownL = new Label("Brown Coins Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCoinsBrownR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.COINS_BROWN)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
        }
        //candy land
        else {
            //candys
            labelCandysTotalL = new Label("Candys Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCandysTotalR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.CANDYS_LEVEL)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCandysGummyGreenL = new Label("Green Gummies Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCandysGummyGreenR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.CANDYS_GUMMY_GREEN)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCandysGummyRedL = new Label("Red Gummies Collected: ", new Label.LabelStyle(font, Color.DARK_GRAY));
            labelCandysGummyRedR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.CANDYS_GUMMY_RED)),
                    new Label.LabelStyle(font, Color.DARK_GRAY));
        }
        //enemies
        labelEnemiesStampedTotalL = new Label("Enemies Stamped: ", new Label.LabelStyle(font, Color.DARK_GRAY));
        labelEnemiesStampedTotalR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.ENEMIES_STAMPED_LEVEL)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
        //time
        labelTimeL = new Label("Time Completed (sec): ", new Label.LabelStyle(font, Color.DARK_GRAY));
        labelTimeR = new Label(String.format(Locale.getDefault(), "%d", preferences.getInteger(Platformer.TIME_ELAPSED)),
                new Label.LabelStyle(font, Color.DARK_GRAY));
    }

    private Actor createTableLevel() {
        Table tableLabel = new Table();
        tableLabel.add(labelLabel).padTop(LABEL_PAD).center();
        tableLabel.setFillParent(true);
        tableLabel.top();
        return tableLabel;
    }

    private Table createTableInfo(int currentLevelIndex) {
        Table tableInfo = new Table();
        tableInfo.add(labelLevelL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelLevelR).right();
        tableInfo.row();
        tableInfo.add(labelLifeL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelLifeR).right();
        tableInfo.row();
        tableInfo.add(labelScoreL).right();
        tableInfo.add(labelEmpty).center();
        tableInfo.add(labelScoreR).right();
        tableInfo.row();
        //default land (levels 1-5)
        if (currentLevelIndex < 5) {
            tableInfo.add(labelCoinsTotalL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCoinsTotalR).right();
            tableInfo.row();
            tableInfo.add(labelCoinsGoldL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCoinsGoldR).right();
            tableInfo.row();
            tableInfo.add(labelCoinsSilverL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCoinsSilverR).right();
            tableInfo.row();
            tableInfo.add(labelCoinsBrownL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCoinsBrownR).right();
            tableInfo.row();
            tableInfo.add(labelEnemiesStampedTotalL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelEnemiesStampedTotalR).right();
            tableInfo.row();
            tableInfo.add(labelTimeL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelTimeR).right();
        }
        //candy land
        else {
            tableInfo.add(labelCandysTotalL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCandysTotalR).right();
            tableInfo.row();
            tableInfo.add(labelCandysGummyGreenL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCandysGummyGreenR).right();
            tableInfo.row();
            tableInfo.add(labelCandysGummyRedL).right();
            tableInfo.add(labelEmpty).center();
            tableInfo.add(labelCandysGummyRedR).right();
        }
        tableInfo.row();
        tableInfo.setFillParent(true);
        tableInfo.center();
        return tableInfo;
    }

    private Table createTableNextLevel() {
        tableButton = new Table();
        tableButton.add(buttonNextLevel).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD).center();
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
}
