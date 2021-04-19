/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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

import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static java.lang.String.format;

public class HUD implements Disposable {
    private final Stage stage;
    private int life, score, highScore;
    private Label labelHighScoreL,    //displays the label "HIGH SCORE":
                  labelHighScoreR,    //displays the actual high score
                  labelScoreL,        //displays the label "SCORE:
                  labelScoreR,        //displays the actual score
                  labelLifeL,         //displays the label "LIFE: "
                  labelLifeR;         //displays the actual life
    private BitmapFont font;
    private Preferences preferences;

    public HUD(Shooter game) {
        Viewport viewport = new FitViewport(game.getWidth(), game.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.getSpriteBatch());
        font = new BitmapFont(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_FNT));
        init();
        createFonts();
        createLabels(preferences);
        stage.addActor(createTableLeft());
        stage.addActor(createTableCenter());
        stage.addActor(createTableRight());
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Initializes all heads up display's variables
     */
    private void init() {
        preferences = Gdx.app.getPreferences(Globals.PREFERENCES_SHOOTER);
        score = 0;
        life = 5;
        highScore = preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE);
    }

    /**
     * Creates all the fonts that will be shown in the heads up display
     */
    private void createFonts() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal(Globals.FONTS_KENNEY_PIXEL_OTF));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.color = Color.PURPLE;
        parameter.shadowColor = Color.GOLD;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Creates all the labels that will be shown in the heads up display
     *
     * @param preferences permanent game values
     */
    private void createLabels(Preferences preferences) {
        labelHighScoreL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_HIGH_SCORE_CAPS),
                new Label.LabelStyle(font, Color.GOLD));
        labelHighScoreR = new Label(format(Locale.getDefault(), "%3d", preferences.getInteger(Globals.PREFERENCES_HIGH_SCORE)),
                new Label.LabelStyle(font, Color.GOLD));
        labelScoreL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_SCORE),
                new Label.LabelStyle(font, Color.GOLD));
        labelScoreR = new Label(format(Locale.getDefault(), "%3d", preferences.getInteger(Globals.PREFERENCES_SCORE)),
                new Label.LabelStyle(font, Color.GOLD));
        labelLifeL = new Label(format(Locale.getDefault(), "%s", Globals.LABEL_SHIELD),
                new Label.LabelStyle(font, Color.GOLD));
        labelLifeR = new Label(format(Locale.getDefault(), "%3d", life),
                new Label.LabelStyle(font, Color.GOLD));
    }

    //Table for score
    private Table createTableLeft() {
        Table table = new Table();
        table.left().top();
        table.setFillParent(true);
        table.add(labelScoreL).align(right);
        table.add(labelScoreR).align(left);
        table.row();
        return table;
    }

    //Table for high score
    private Table createTableCenter() {
        Table table = new Table();
        table.center().top();
        table.setFillParent(true);
        table.add(labelHighScoreL).align(right);
        table.add(labelHighScoreR).align(left);
        table.row();
        return table;
    }

    //Table for life
    private Table createTableRight() {
        Table table = new Table();
        table.right().top();
        table.setFillParent(true);
        table.add(labelLifeR).align(right);
        table.add(labelLifeL).align(left);
        table.row();
        return table;
    }

    public void update() {
        //update display
        labelScoreR.setText(format(Locale.getDefault(), "%3d", score));
        labelLifeR.setText(format(Locale.getDefault(), "%3d", life));
        labelHighScoreR.setText(format(Locale.getDefault(), "%3d", highScore));
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
        font.dispose();
    }

    public Camera getCamera() {
        return stage.getCamera();
    }
}
