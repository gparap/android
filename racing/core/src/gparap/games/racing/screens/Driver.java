/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import gparap.games.racing.Globals;
import gparap.games.racing.Racing;

public class Driver extends Screen {
    private Image buttonBlack, buttonBlue, buttonGreen, buttonRed, buttonYellow;
    private Label labelTapBlack, labelTapBlue, labelTapGreen, labelTapRed, labelTapYellow;
    private boolean isPressedBlack, isPressedBlue, isPressedGreen, isPressedRed, isPressedYellow;
    float PAD = 2;

    public Driver(Racing game) {
        super(game);
        isPressedBlack = false;
        isPressedBlue = false;
        isPressedGreen = false;
        isPressedRed = false;
        isPressedYellow = false;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        super.createFont(Color.WHITE, false);
        createLabels();
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

        //select driver and return to main menu
        if (isPressedBlack) {
            selectDriver(0);
        }
        if (isPressedBlue) {
            selectDriver(1);
        }
        if (isPressedGreen) {
            selectDriver(2);
        }
        if (isPressedRed) {
            selectDriver(3);
        }
        if (isPressedYellow) {
            selectDriver(4);
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        super.dispose();
    }

    private void createLabels() {
        labelTapBlack = new Label(Globals.TEXT_TAP, new Label.LabelStyle(font, Color.BLACK));
        labelTapBlue = new Label(Globals.TEXT_TAP, new Label.LabelStyle(font, Color.BLUE));
        labelTapGreen = new Label(Globals.TEXT_TAP, new Label.LabelStyle(font, Color.GREEN));
        labelTapRed = new Label(Globals.TEXT_TAP, new Label.LabelStyle(font, Color.RED));
        labelTapYellow = new Label(Globals.TEXT_TAP, new Label.LabelStyle(font, Color.YELLOW));
    }

    private Table createTable() {
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(buttonBlack).size(buttonBlack.getWidth(), buttonBlack.getHeight()).pad(PAD);
        table.add(buttonBlue).size(buttonBlue.getWidth(), buttonBlue.getHeight()).pad(PAD);
        table.add(buttonGreen).size(buttonGreen.getWidth(), buttonGreen.getHeight()).pad(PAD);
        table.add(buttonRed).size(buttonRed.getWidth(), buttonRed.getHeight()).pad(PAD);
        table.add(buttonYellow).size(buttonYellow.getWidth(), buttonYellow.getHeight()).pad(PAD);
        table.row();
        table.add(labelTapBlack).pad(PAD);
        table.add(labelTapBlue).pad(PAD);
        table.add(labelTapGreen).pad(PAD);
        table.add(labelTapRed).pad(PAD);
        table.add(labelTapYellow).pad(PAD);
        return table;
    }

    /**
     * Creates the driver buttons.
     */
    private void createButtons() {
        //black bike
        buttonBlack = new Image(game.getTextureAtlas().findRegion(Globals.BIKE_BLACK));
        buttonBlack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedBlack = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedBlack = false;
            }
        });

        //blue bike
        buttonBlue = new Image(game.getTextureAtlas().findRegion(Globals.BIKE_BLUE));
        buttonBlue.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedBlue = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedBlue = false;
            }
        });

        //green bike
        buttonGreen = new Image(game.getTextureAtlas().findRegion(Globals.BIKE_GREEN));
        buttonGreen.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedGreen = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedGreen = false;
            }
        });

        //red bike
        buttonRed = new Image(game.getTextureAtlas().findRegion(Globals.BIKE_RED));
        buttonRed.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedRed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedRed = false;
            }
        });

        //yellow bike
        buttonYellow = new Image(game.getTextureAtlas().findRegion(Globals.BIKE_YELLOW));
        buttonYellow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedYellow = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedYellow = false;
            }
        });
    }

    /**
     * Selects driver and returns to main menu.
     *
     * @param driver driver
     */
    private void selectDriver(int driver) {
        //save preferences
        preferences.putInteger(Globals.PREFERENCES_DRIVER, driver);
        preferences.flush();

        //goto menu
        this.dispose();
        game.setScreen(new Menu(game, false));
    }
}
