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

public class Credits extends Screen {
    private Image buttonMenu;
    private Label labelCredits;
    private boolean isPressedMenu;

    public Credits(Racing game) {
        super(game);
        isPressedMenu = false;
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        super.show();
        super.createFont(Color.DARK_GRAY, true);
        createLabels();
        createMenuButton();
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

        //go back to menu
        if (isPressedMenu) {
            this.dispose();
            game.setScreen(new Menu(game, false));
        }
    }

    /**
     * Called when this screen should release all resources.
     */
    public void dispose() {
        super.dispose();
    }

    private void createLabels() {
        labelCredits = new Label(String.format("%s", "\n" +
                "Graphics/Font: Kenney \n" +
                "www.kenney.nl \n" +
                "\n" +
                "Game created with LibGDX \n" +
                "libgdx.badlogicgames.com\n" +
                "\n" +
                "LibGDX is under Apache 2.0 licence\n" +
                "see www.apache.org/licenses/LICENSE-2.0.txt\n"),
                new Label.LabelStyle(font, Color.WHITE));
    }

    private Table createTable() {
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        float PAD = 2;
        table.add(labelCredits).center();
        table.row();
        table.add(buttonMenu).size(buttonMenu.getWidth(), buttonMenu.getHeight()).pad(PAD);
        table.row();
        return table;
    }

    private void createMenuButton() {
        buttonMenu = new Image(game.getTextureAtlas().findRegion(Globals.BUTTON_MENU));
        buttonMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressedMenu = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressedMenu = false;
            }
        });
    }
}
