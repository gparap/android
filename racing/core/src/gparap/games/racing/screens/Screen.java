package gparap.games.racing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.racing.Globals;
import gparap.games.racing.Racing;

public class Screen implements com.badlogic.gdx.Screen {
    protected final Racing game;
    protected final TextureRegion background;
    protected Stage stage;
    protected Camera camera;
    protected BitmapFont font;
    protected Preferences preferences;

    protected Screen(Racing game) {
        this.game = game;
        background = game.getTextureAtlas().findRegion(Globals.ROAD);
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        Viewport viewport = new FillViewport(game.getWidth(), game.getHeight(), camera);
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
        game.getSpriteBatch().draw(background, 0, 0, game.getWidth(), game.getHeight());
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

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }

    /**
     * Creates the bitmap font for the screen.
     *
     * @param color        primary font color
     * @param mustCheckDIP if device's density independent pixels should be checked
     */
    protected void createFont(Color color, boolean mustCheckDIP) {
        font = new BitmapFont(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_FNT));
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_OTF));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;

        //check DIP resolution and adjust the size
        if (mustCheckDIP) {
            if (Gdx.graphics.getDensity() > 1.0f) {
                parameter.size = 36;
            } else if (Gdx.graphics.getDensity() > 2.0f) {
                parameter.size = 48;
            }
        }

        parameter.color = color;
        parameter.shadowColor = Color.WHITE;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        font = generator.generateFont(parameter);
        generator.dispose();
    }
}
