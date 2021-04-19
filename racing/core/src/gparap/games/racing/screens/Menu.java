/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.screens;

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

import gparap.games.racing.Globals;
import gparap.games.racing.Racing;

import static java.lang.String.format;

public class Menu extends Screen {
    private Image buttonRace, buttonDriver, buttonCredits, buttonExit;
    private Label labelGameOver, labelNewRecord, labelRecord, labelDistance;
    private boolean isPressedRace, isPressedDriver, isPressedCredits, isPressedExit;
    private final StringBuilder userInfoText;
    private final boolean isGameOver;

    public Menu(Racing game, boolean isGameOver) {
        super(game);
        this.isGameOver = isGameOver;
        isPressedRace = false;
        isPressedDriver = false;
        isPressedCredits = false;
        isPressedExit = false;
        userInfoText = new StringBuilder();
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        super.createFont(Color.DARK_GRAY, false);
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

        //start race
        if (isPressedRace) {
            this.dispose();
            game.setScreen(new Race(game));
        }
        //choose driver
        if (isPressedDriver) {
            this.dispose();
            game.setScreen(new Driver(game));
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
        //game over label
        labelGameOver = new Label(userInfoText, new Label.LabelStyle(font, Color.RED));

        //new record label
        String distance = convertDistance(preferences.getInteger(Globals.PREFERENCES_RECORD));
        labelNewRecord = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_NEW_RECORD + distance),
                         new Label.LabelStyle(font, Color.WHITE));

        //(current) record label
        distance = convertDistance(preferences.getInteger(Globals.PREFERENCES_RECORD));
        labelRecord = new Label(Globals.LABEL_RECORD + distance, new Label.LabelStyle(font, Color.WHITE));

        //race distance label
        distance = convertDistance(preferences.getInteger(Globals.PREFERENCES_DISTANCE));
        labelDistance = new Label(Globals.LABEL_RACED_MENU + distance, new Label.LabelStyle(font, Color.WHITE));
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
            if (preferences.getInteger(Globals.PREFERENCES_DISTANCE) > preferences.getInteger(Globals.PREFERENCES_PREVIOUS_RECORD)) {
                table.add(labelNewRecord).center().padBottom(PAD);
            } else {
                table.add(labelRecord).right();
                table.row();
                table.add(labelDistance).right();
            }
            table.row();
        }
        table.add(buttonRace).size(buttonRace.getWidth(), buttonRace.getHeight()).pad(PAD);
        table.row();
        table.add(buttonDriver).size(buttonDriver.getWidth(), buttonDriver.getHeight()).pad(PAD);
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
        //create race button
        buttonRace = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_RACE));
        buttonRace.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedRace = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedRace = false;
            }
        });
        //create driver button
        buttonDriver = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_DRIVER));
        buttonDriver.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedDriver = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedDriver = false;
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

    /**
     * Converts label of distance to kilometers or meters
     *
     * @param distance distance in meters
     * @return conversion output
     */
    private String convertDistance(float distance) {
        String output;
        if (distance > 1000) {
            distance /= 1000;
            output = distance + " km";
        } else {
            output = (int) distance + " m";
        }
        return output;
    }
}
