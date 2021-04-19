/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.screens;

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

import gparap.games.shooter.Globals;
import gparap.games.shooter.Shooter;

import static java.lang.String.format;

public class Menu extends Screen {
    private Image buttonPlay, buttonCredits, buttonExit;
    private Label labelGameOver, labelNewHighScore, labelHighScore, labelPlayerScore;
    private boolean isPressedPlay, isPressedCredits, isPressedExit;
    private final StringBuilder userInfoText;
    private final boolean isGameOver;

    public Menu(Shooter game, boolean isGameOver) {
        super(game);
        this.isGameOver = isGameOver;
        isPressedPlay = false;
        isPressedCredits = false;
        userInfoText = new StringBuilder();
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        createFont(16, false);
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

        //play (again)
        if (isPressedPlay) {
            this.dispose();
            game.setScreen(new Play(game));
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
        labelNewHighScore = new Label(format(Locale.getDefault(), "%s",
                Globals.LABEL_NEW_HIGH_SCORE + preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE) + "!!!"),
                new Label.LabelStyle(font, Color.FIREBRICK));
        labelGameOver = new Label(userInfoText,
                new Label.LabelStyle(font, Color.WHITE));
        labelHighScore = new Label(Globals.LABEL_HIGH_SCORE + preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE),
                new Label.LabelStyle(font, Color.GOLDENROD));
        labelPlayerScore = new Label(Globals.LABEL_YOUR_SCORE + preferences.getInteger(Globals.PREFERENCES_SCORE),
                new Label.LabelStyle(font, Color.GOLD));
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
                table.add(labelHighScore).center();
                table.row();
                table.add(labelPlayerScore).center();
            }
            table.row();
        }
        table.add(buttonPlay).size(buttonPlay.getWidth(), buttonPlay.getHeight()).pad(PAD);
        table.row();
        table.add(buttonCredits).size(buttonCredits.getWidth(), buttonCredits.getHeight()).pad(PAD);
        table.row();
        table.add(buttonExit).size(buttonExit.getWidth(), buttonExit.getHeight()).pad(PAD);
        table.row();
        return table;
    }

    /**
     * Creates the text that will inform the player (if has won or lost the game).
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
        buttonPlay = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_PLAY));
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

        //create credits button
        buttonCredits = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_CREDITS));
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
        buttonExit = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_EXIT));
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
