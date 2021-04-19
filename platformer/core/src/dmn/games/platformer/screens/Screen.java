package dmn.games.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import dmn.games.platformer.Background;
import dmn.games.platformer.Platformer;

public class Screen implements com.badlogic.gdx.Screen {
    protected Platformer game;
    protected Background background;
    protected Stage stage;
    protected BitmapFont font;

    public enum ViewportType {STRETCH, FILL}

    protected Screen(Platformer game, Background background, ViewportType viewportType) {
        this.game = game;
        this.background = background;

        //create background on startup
        if (background == null){
            createBackground();
        }

        //create viewport
        Viewport viewport = null;
        if (viewportType == ViewportType.STRETCH) {
            viewport = new StretchViewport(Platformer.WIDTH, Platformer.HEIGHT, new OrthographicCamera());
        } else if (viewportType == ViewportType.FILL) {
            viewport = new FillViewport(Platformer.WIDTH, Platformer.HEIGHT, new OrthographicCamera());
        }
        //create stage
        assert viewport != null;
        stage = new Stage(viewport);
    }

    /**
     * Creates the background for screens.
     */
    private void createBackground() {
        //get the user device dimensions in logical pixels (aspect ratio = height / width)
        float deviceWidth = Gdx.graphics.getWidth();
        float deviceHeight = Gdx.graphics.getHeight();
        //create the camera
        //how many units (map tiles) we want to display
        OrthographicCamera camera = new OrthographicCamera(15f, //how many units (map tiles) we want to display
                                                           15f * deviceHeight / deviceWidth);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        //create the background
        background = new Background(game,
                "background/colored_land.png",
                "background/colored_grass.png", camera);    //this is ignored (for convenience only)
    }

    protected void createFont() {
        font = new BitmapFont(Gdx.files.internal("fonts/kenney_pixel.fnt"));
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenney_pixel.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render screen
        background.draw();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
