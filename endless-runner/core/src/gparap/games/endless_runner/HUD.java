/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

import gparap.games.endless_runner.utils.Globals;

import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static java.lang.String.format;

public class HUD implements Disposable {
    private final Stage stage;
    private int distance, score, highScore;
    private Label labelHighScoreL,    //displays the label "HIGH SCORE":
                  labelHighScoreR,    //displays the actual high score
                  labelScoreL,        //displays the label "SCORE:
                  labelScoreR,        //displays the actual score
                  labelDistanceL,     //displays the label "DISTANCE: "
                  labelDistanceR;     //displays the actual distance in meters
    private BitmapFont font;
    private Preferences preferences;

    public HUD(SpriteBatch spriteBatch) {
        Viewport viewport = new FitViewport(Globals.WIDTH, Globals.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        font = new BitmapFont(Gdx.files.internal(Globals.FONT_KENNEY_PIXEL_FNT));
        init();
        createFonts();
        createLabels(preferences);
        stage.addActor(createTable());
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Initializes all heads up display's variables.
     */
    private void init() {
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
        score = 0;
        distance = 0;
        highScore = preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE);
    }

    /**
     * Creates all the fonts that will be shown in the heads up display.
     */
    private void createFonts() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(Globals.FONT_KENNEY_PIXEL_OTF));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.VIOLET;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Creates all the labels that will be shown in the heads up display.
     *
     * @param preferences permanent game values
     */
    private void createLabels(Preferences preferences) {
        labelHighScoreL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_HIGH_SCORE_CAPS),
                new Label.LabelStyle(font, Color.WHITE));
        labelHighScoreR = new Label(format(Locale.getDefault(), "%3d", preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE)),
                new Label.LabelStyle(font, Color.WHITE));
        labelScoreL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_SCORE),
                new Label.LabelStyle(font, Color.WHITE));
        labelScoreR = new Label(format(Locale.getDefault(), "%3d", preferences.getInteger(Globals.PREFERENCES_SCORE)),
                new Label.LabelStyle(font, Color.WHITE));
        labelDistanceL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_DISTANCE),
                new Label.LabelStyle(font, Color.WHITE));
        labelDistanceR = new Label(format(Locale.getDefault(), "%3d%s", preferences.getInteger(Globals.PREFERENCES_DISTANCE), " m"),
                new Label.LabelStyle(font, Color.WHITE));
    }

    /**
     * Create the table that will hold all labels that will be shown in the heads up display.
     *
     * @return table
     */
    private Table createTable() {
        Table table = new Table();
        table.center().top();
        table.setFillParent(true);
        table.add(labelHighScoreL).align(right);
        table.add(labelHighScoreR).align(left);
        table.row();
        table.add(labelScoreL).align(right);
        table.add(labelScoreR).align(left);
        table.row();
        table.add(labelDistanceL).align(right);
        table.add(labelDistanceR).align(left);
        return table;
    }

    public void update() {
        //update display
        labelScoreR.setText(format(Locale.getDefault(), "%3d", score));
        labelDistanceR.setText(format(Locale.getDefault(), "%3d m", distance));
        labelHighScoreR.setText(format(Locale.getDefault(), "%3d", highScore));
    }

    public void render() {
        stage.draw();
    }

    /**
     * Releases all resources of the heads up display.
     */
    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }

    public Camera getCamera() {
        return stage.getCamera();
    }
}
