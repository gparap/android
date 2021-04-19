package gparap.games.endless_runner.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;

public class Screen implements com.badlogic.gdx.Screen {
    protected EndlessRunner game;
    protected Texture background;
    protected Stage stage;
    protected Camera camera;
    protected BitmapFont font;
    protected Preferences preferences;

    protected Screen(EndlessRunner game) {
        this.game = game;
        background = new Texture(Globals.BACKGROUND);
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        Viewport viewport = new FillViewport(Globals.WIDTH, Globals.HEIGHT, camera);
        stage = new Stage(viewport);
    }

    @Override
    public void render(float delta) {
        //clear screen
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw screen
        game.getSpriteBatch().setProjectionMatrix(camera.combined);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(background, 0, 0, Globals.WIDTH, Globals.HEIGHT);
        game.getSpriteBatch().end();
        game.getSpriteBatch().begin();
        stage.draw();
        game.getSpriteBatch().end();
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

    /**
     * Releases all screen's resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }

    /**
     * Creates the bitmap font for the screen.
     *
     * @param size      font's size
     * @param color     font's primary color
     * @param hasShadow font's shadow
     */
    protected void createFont(int size, Color color, boolean hasShadow) {
        font = new BitmapFont(Gdx.files.internal(Globals.FONT_KENNEY_PIXEL_FNT));
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(Globals.FONT_KENNEY_PIXEL_OTF));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;

        //font shadow
        if (hasShadow) {
            parameter.shadowColor = Color.WHITE;
            parameter.shadowOffsetX = 1;
            parameter.shadowOffsetY = 1;
        }

        font = generator.generateFont(parameter);
        generator.dispose();
    }
}
