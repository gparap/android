/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;

public class Player extends Screen {
    private Image buttonGreen, buttonBlue, buttonPink;
    private Label labelTapGreen, labelTapBlue, labelTapPink;
    private boolean isPressedGreen, isPressedBlue, isPressedPink;

    public Player(EndlessRunner game) {
        super(game);
        isPressedGreen = false;
        isPressedBlue = false;
        isPressedPink = false;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        createFont(16, Color.WHITE, true);
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

        //choose an alien
        if (isPressedGreen) {
            selectAlien(1);
        }
        if (isPressedBlue) {
            selectAlien(2);
        }
        if (isPressedPink) {
            selectAlien(3);
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        super.dispose();
    }

    private void createLabels() {
        labelTapGreen = new Label("Tap!", new Label.LabelStyle(font, Color.GREEN));
        labelTapBlue = new Label("Tap!", new Label.LabelStyle(font, Color.BLUE));
        labelTapPink = new Label("Tap!", new Label.LabelStyle(font, Color.PINK));
    }

    private Table createTable() {
        float PAD = 2;

        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(buttonGreen).size(buttonGreen.getWidth(), buttonGreen.getHeight()).pad(PAD);
        table.add(buttonBlue).size(buttonBlue.getWidth(), buttonBlue.getHeight()).pad(PAD);
        table.add(buttonPink).size(buttonPink.getWidth(), buttonPink.getHeight()).pad(PAD);
        table.row();
        table.add(labelTapGreen).pad(PAD);
        table.add(labelTapBlue).pad(PAD);
        table.add(labelTapPink).pad(PAD);

        return table;
    }

    /**
     * Creates the alien buttons.
     */
    private void createButtons() {
        //create green alien button
        buttonGreen = new Image(game.getAtlas().findRegion(Globals.ALIEN_1));
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

        //create blue alien button
        buttonBlue = new Image(game.getAtlas().findRegion(Globals.ALIEN_2));
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

        //create pink alien button
        buttonPink = new Image(game.getAtlas().findRegion(Globals.ALIEN_3));
        buttonPink.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPink = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedPink = false;
            }
        });
    }

    /**
     * Selects alien and returns to main menu.
     *
     * @param alien alien
     */
    private void selectAlien(int alien) {
        //save preferences
        preferences.putInteger(Globals.PREFERENCES_PLAYER, alien);
        preferences.flush();

        //goto menu
        this.dispose();
        game.setScreen(new Menu(game, false));
    }
}
