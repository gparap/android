/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.Locale;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;

import static java.lang.String.format;

public class Menu extends Screen {
    private Image buttonPlay, buttonAlien, buttonCredits, buttonExit;
    private Label labelGameOver, labelNewHighScore, labelPlayerScore, labelDistance;
    private boolean isPressedPlay, isPressedAlien, isPressedCredits, isPressedExit;
    private final StringBuilder userInfoText;
    private final boolean isGameOver;

    public Menu(EndlessRunner game, boolean isGameOver) {
        super(game);
        this.isGameOver = isGameOver;
        isPressedPlay = false;
        isPressedAlien = false;
        isPressedCredits = false;
        isPressedExit = false;
        userInfoText = new StringBuilder();
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        createFont(16, Color.VIOLET, false);
        createInfoText();
        createLabels(preferences);
        createButtons();
        stage.addActor(createTable());
        Gdx.input.setInputProcessor(stage); //receive all touch events
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    public void render(float delta) {
        super.render(delta);

        //start game
        if (isPressedPlay) {
            this.dispose();
            game.setScreen(new Play(game));
        }
        //choose alien
        if (isPressedAlien) {
            this.dispose();
            game.setScreen(new Player(game));
        }
        //view credits
        if (isPressedCredits) {
            this.dispose();
            game.setScreen(new Credits(game));
        }
        //exit game
        if (isPressedExit) {
            this.dispose();
            game.dispose();
            Gdx.app.exit();
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        super.dispose();
    }

    private void createLabels(Preferences preferences) {
        labelGameOver = new Label(userInfoText,
                new Label.LabelStyle(font, Color.RED));
        labelNewHighScore = new Label(format(Locale.getDefault(), "%s",
                Globals.LABEL_NEW_HIGH_SCORE + preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE) + "!!!"),
                new Label.LabelStyle(font, Color.WHITE));
        labelPlayerScore = new Label(Globals.LABEL_YOUR_SCORE + preferences.getInteger(Globals.PREFERENCES_SCORE),
                new Label.LabelStyle(font, Color.WHITE));
        labelDistance = new Label(Globals.LABEL_DISTANCE + preferences.getInteger(Globals.PREFERENCES_DISTANCE) + " m",
                new Label.LabelStyle(font, Color.WHITE));
    }

    private Table createTable() {
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        float PAD = 2;
        //display text only for game over
        if (isGameOver) {
            table.add(labelGameOver).center();
            table.row();
            //check if there is a new high score
            if (preferences.getInteger(Globals.PREFERENCES_SCORE) > preferences.getInteger(Globals.PREFERENCES_PREVIOUS_HIGH_SCORE)) {
                table.add(labelNewHighScore).center().padBottom(PAD);
            } else {
                table.row();
                table.add(labelPlayerScore).center();
            }
            table.row();
            table.add(labelDistance).center();
            table.row();
        }
        table.add(buttonPlay).size(buttonPlay.getWidth(), buttonPlay.getHeight()).pad(PAD);
        table.row();
        table.add(buttonAlien).size(buttonAlien.getWidth(), buttonAlien.getHeight()).pad(PAD);
        table.row();
        table.add(buttonCredits).size(buttonCredits.getWidth(), buttonCredits.getHeight()).pad(PAD);
        table.row();
        table.add(buttonExit).size(buttonExit.getWidth(), buttonExit.getHeight()).pad(PAD);
        table.row();
        return table;
    }

    /**
     * Creates the text that will inform the alien (if has won or lost the game).
     */
    private void createInfoText() {
        userInfoText.delete(0, userInfoText.length);//clean up string builder
        userInfoText.append(Globals.TEXT_GAME_OVER);//create user information
    }

    /**
     * Creates the menu buttons.
     */
    private void createButtons() {
        //create play button
        buttonPlay = new Image(game.getAtlas().findRegion(Globals.BUTTON_PLAY));
        buttonPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPlay = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPlay = false;
            }
        });

        //create alien button
        buttonAlien = new Image(game.getAtlas().findRegion(Globals.BUTTON_ALIEN));
        buttonAlien.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedAlien = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPlay = false;
            }
        });

        //create go to credits button
        buttonCredits = new Image(game.getAtlas().findRegion(Globals.BUTTON_CREDITS));
        buttonCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedCredits = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedCredits = false;
            }
        });

        //create exit button
        buttonExit = new Image(game.getAtlas().findRegion(Globals.BUTTON_EXIT));
        buttonExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedExit = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedExit = false;
            }
        });
    }
}
