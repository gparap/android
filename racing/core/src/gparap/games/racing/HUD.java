/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

import static java.lang.String.format;

public class HUD implements Disposable {
    private final Stage stage;
    private int distance;
    private Label labelDistance;
    private BitmapFont font;

    public HUD(Racing game) {
        Viewport viewport = new FitViewport(game.getWidth(), game.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getSpriteBatch());
        font = new BitmapFont(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_FNT));
        init();
        createFonts();
        createLabels();
        stage.addActor(createTableDistance());
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Initializes all heads up display's variables.
     */
    private void init() {
        distance = 0;
    }

    /**
     * Creates all the fonts that will be shown in the heads up display.
     */
    private void createFonts() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_OTF));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.color = Color.DARK_GRAY;
        parameter.shadowColor = Color.WHITE;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Creates all the labels that will be shown in the heads up display.
     */
    private void createLabels() {
        labelDistance = new Label("0 m", new Label.LabelStyle(font, Color.WHITE));
    }

    /**
     * Creates stage actor for distance.
     *
     * @return table
     */
    private Table createTableDistance() {
        Table table = new Table();
        table.center().top();
        table.setFillParent(true);
        table.add(labelDistance).center();
        table.row();
        return table;
    }

    public void update() {
        float km = distance;

        //handle km or m
        if (km > 1000) {
            km /= 1000;
            labelDistance.setText(format(Locale.getDefault(), "%3.3f km", km));
        } else {
            labelDistance.setText(format(Locale.getDefault(), "%3d m", distance));
        }
    }

    public void render() {
        stage.draw();
    }

    public Camera getCamera() {
        return stage.getCamera();
    }

    /**
     * Releases all resources of the head's up display.
     */
    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
