/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import dmn.games.platformer.Background;
import dmn.games.platformer.Platformer;

public class Credits extends Screen {
    private Table tableButton;
    private Image buttonGotoMenu;
    private Label labelCredits;
    private boolean isPressedMainMenu;
    @SuppressWarnings("FieldCanBeLocal")
    private final float BUTTON_WIDTH = 128,
                        BUTTON_HEIGHT = 64,
                        BUTTON_PAD = 64,
                        LABEL_PAD = 2;

    public Credits(Platformer game, Background background) {
        super(game, background, ViewportType.STRETCH);
    }

    /**
     * Called when this screen becomes the current screen for a game.
     */
    public void show() {
        isPressedMainMenu = false;
        createFont();
        createLabels();
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
        stage.addActor(createTableGotoMenu());  //add table with button for main menu
        stage.addActor(createTableCredits());   //add table with the credits
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
        //go to main menu
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

    /**
     * Create a table actor on the left side with a button to go to the game main menu.
     */
    private Table createTableGotoMenu() {
        tableButton = new Table();
        tableButton.add(buttonGotoMenu).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(BUTTON_PAD).center();//table.add();
        tableButton.setFillParent(true);
        tableButton.bottom().left();
        return tableButton;
    }

    /**
     * Create a table actor at the top displaying the credits.
     */
    private Actor createTableCredits() {
        tableButton = new Table();
        tableButton.add(labelCredits).padTop(LABEL_PAD).center();
        tableButton.setFillParent(true);
        tableButton.top();
        return tableButton;
    }

    private void createLabels() {
        labelCredits = new Label(String.format("%s", "\n" +
                "Graphics/Font: Kenney\n" +
                "www.kenney.nl\n" +
                "\n" +
                "Game levels created with Tiled Map Editor\n" +
                "www.mapeditor.org\n" +
                "\n" +
                "Game created with LibGDX\n" +
                "libgdx.badlogicgames.com\n" +
                "\n" +
                "LibGDX is under Apache 2.0 licence\n" +
                "see www.apache.org/licenses/LICENSE-2.0.txt\n"),
                new Label.LabelStyle(font, Color.DARK_GRAY));
    }
}
