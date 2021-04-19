/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.touch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dmn.games.platformer.Platformer;

public class TouchPad implements Disposable {
    private final Platformer game;
    private final Stage stage;
    private boolean isRightPressed,
                    isLeftPressed,
                    isJumpPressed;
    private final float BUTTON_WIDTH = 200,
                        BUTTON_HEIGHT = 100;

    public TouchPad(Platformer game) {
        this.game = game;
        Viewport viewport = new FillViewport(Platformer.WIDTH, Platformer.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        stage.addActor(createTableRight());
        stage.addActor(createTableLeft());
        Gdx.input.setInputProcessor(stage); //receive all touch events
    }

    public boolean isRightPressed() {
        return isRightPressed;
    }

    public boolean isLeftPressed() {
        return isLeftPressed;
    }

    public boolean isJumpPressed() {
        return isJumpPressed;
    }

    public Camera getCamera() {
        return stage.getCamera();
    }

    private Table createTableRight() {
        Image buttonLeft = new Image(game.getAtlas().findRegion("button_left"));
        buttonLeft.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonLeft.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isLeftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isLeftPressed = false;
            }
        });
        Image buttonRight = new Image(game.getAtlas().findRegion("button_right"));
        buttonRight.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonRight.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isRightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isRightPressed = false;
            }
        });
        Table table = new Table();
        table.bottom().right();
        table.setFillParent(true);
        table.add();
        table.add(buttonLeft).size(buttonLeft.getWidth(), buttonLeft.getHeight());
        table.add(buttonRight).size(buttonRight.getWidth(), buttonRight.getHeight());
        return table;
    }

    private Table createTableLeft() {
        Image buttonJump = new Image(game.getAtlas().findRegion("button_jump"));
        buttonJump.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonJump.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isJumpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isJumpPressed = false;
            }
        });
        Table table = new Table();
        table.bottom().left();
        table.setFillParent(true);
        table.add(buttonJump).size(buttonJump.getWidth(), buttonJump.getHeight());
        return table;
    }

    public void render() {
        stage.draw();
    }

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    public void setJumpPressed(boolean jumpPressed) {
        isJumpPressed = jumpPressed;
    }
}
