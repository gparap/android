/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dmn.games.platformer.Background;
import dmn.games.platformer.Platformer;

public class Menu extends Screen {
    private boolean isStartPressed, isCreditsPressed, isExitPressed;
    @SuppressWarnings("FieldCanBeLocal")
    private final float BUTTON_WIDTH = 128,
                        BUTTON_HEIGHT = 64,
                        BUTTON_PAD = 8;

    public Menu(Platformer game, Background background) {
        super(game, background, ViewportType.FILL);
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
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
        //process input and redirect accordingly to target screen
        if (isStartPressed) {
            this.dispose();
            background.dispose();   //it's ok, Play always creates the background
            this.hide();
            game.setScreen(new Play(game, -1));
        } else if (isCreditsPressed) {
            this.dispose();
            this.hide();
            game.setScreen(new Credits(game, background));
        } else if (isExitPressed) {
            background.dispose();
            Gdx.app.exit();
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        stage.dispose();
    }

    private Table createTable() {
        Image buttonStart = new Image(game.getAtlas().findRegion("button_start"));
        buttonStart.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonStart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isStartPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isStartPressed = false;
            }
        });
        Image buttonCredits = new Image(game.getAtlas().findRegion("button_credits"));
        buttonCredits.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isCreditsPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isCreditsPressed = false;
            }
        });
        Image buttonExit = new Image(game.getAtlas().findRegion("button_exit"));
        buttonExit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isExitPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isExitPressed = false;
            }
        });
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(buttonStart).size(buttonStart.getWidth(), buttonStart.getHeight()).pad(BUTTON_PAD);
        table.row();
        table.add(buttonCredits).size(buttonCredits.getWidth(), buttonCredits.getHeight()).pad(BUTTON_PAD);
        table.row();
        table.add(buttonExit).size(buttonExit.getWidth(), buttonExit.getHeight()).pad(BUTTON_PAD);
        return table;
    }
}
