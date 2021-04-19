/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer;

import com.badlogic.gdx.Gdx;
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

import static com.badlogic.gdx.utils.Align.right;
import static com.badlogic.gdx.utils.Align.left;
import static java.lang.String.format;

public class HUD implements Disposable {
    private final Stage stage;
    private int level, life, key, score, time, levelTime;
    private float timer;
    private Label labelLevelL,  //displays the label "LEVEL: "
                  labelLevelR,  //displays the actual level
                  labelScoreL,  //displays the label "SCORE:
                  labelScoreR,  //displays the actual score
                  labelLifeL,   //displays the label "LIFE: "
                  labelLifeR,   //displays the actual life
                  labelTimeL,   //displays the label "TIME: "
                  labelTimeR;   //displays the actual time
    private BitmapFont font;

    public HUD(SpriteBatch spriteBatch) {
        Viewport viewport = new FitViewport(Platformer.WIDTH, Platformer.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);
        font = new BitmapFont(Gdx.files.internal("fonts/kenney_pixel.fnt"));
        init();
        createFonts();
        createLabels();
        stage.addActor(createTable());
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Initializes all heads up display's variables
     */
    private void init() {
        level = 1;
        score = 0;
        key = 0;
        life = 4;
        time = levelTime = 90;
        timer = 0;
    }

    /**
     * Creates all the fonts that will be shown in the heads up display
     */
    private void createFonts() {
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenney_pixel.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        //check for lower resolutions and adjust the size
        float tempWidth = Gdx.graphics.getWidth();
        float tempHeight = Gdx.graphics.getHeight();
        if ((tempWidth < Platformer.WIDTH || tempWidth < Platformer.HEIGHT) ||
                (tempHeight < Platformer.WIDTH || tempHeight < Platformer.HEIGHT)) {
            parameter.size = 32;
        }
        parameter.color = Color.GOLD;
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = 1;
        parameter.shadowOffsetY = 1;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    /**
     * Creates all the labels that will be shown in the heads up display
     */
    private void createLabels() {
        labelLevelL = new Label(format(Locale.getDefault(), "%s", "LEVEL: "),
                new Label.LabelStyle(font, Color.GOLD));
        labelLevelR = new Label(format(Locale.getDefault(), "%3d", level),
                new Label.LabelStyle(font, Color.GOLD));
        labelScoreL = new Label(format(Locale.getDefault(), "%s", "SCORE: "),
                new Label.LabelStyle(font, Color.GOLD));
        labelScoreR = new Label(format(Locale.getDefault(), "%3d", score),
                new Label.LabelStyle(font, Color.GOLD));
        labelLifeL = new Label(format(Locale.getDefault(), "%s", "LIFE: "),
                new Label.LabelStyle(font, Color.GOLD));
        labelLifeR = new Label(format(Locale.getDefault(), "%3d", life),
                new Label.LabelStyle(font, Color.GOLD));
        labelTimeL = new Label(format(Locale.getDefault(), "%s", "TIME: "),
                new Label.LabelStyle(font, Color.GOLD));
        labelTimeR = new Label(format(Locale.getDefault(), "%3d", time),
                new Label.LabelStyle(font, Color.GOLD));
    }

    /**
     * Create the table that will hold all labels that will be shown in the heads up display
     *
     * @return table
     */
    private Table createTable() {
        Table table = new Table();
        table.left().top();
        table.setFillParent(true);
        table.add(labelLevelL).align(right);
        table.add(labelLevelR).align(left);
        table.row();
        table.add(labelScoreL).align(right);
        table.add(labelScoreR).align(left);
        table.row();
        table.add(labelLifeL).align(right);
        table.add(labelLifeR).align(left);
        table.row();
        table.add(labelTimeL).align(right);
        table.add(labelTimeR).align(left);
        return table;
    }

    public void update(float delta) {
        //decrease game time
        timer += delta;
        if (timer > 1) {
            time -= 1;
            timer = 0;
        }
        //update display
        labelLevelR.setText(format(Locale.getDefault(), "%3d", level));
        labelScoreR.setText(format(Locale.getDefault(), "%3d", score));
        labelLifeR.setText(format(Locale.getDefault(), "%3d", life));
        labelTimeR.setText(format(Locale.getDefault(), "%3d", time));
        //change time font to red when time is almost up
        if (time < 10) {
            labelTimeL.setColor(Color.RED);
            labelTimeR.setColor(Color.RED);
        }
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

    public void addScore(int score) {
        this.score += score;
    }

    public void removeKey() {
        key = 0;
    }

    public void removeLife() {
        if (life > 0)
            life -= 1;
    }

    public void restartTime() {
        time = levelTime;
        labelTimeL.setColor(Color.GOLD);
        labelTimeR.setColor(Color.GOLD);
    }

    public int getTimeElapsed(int timer) {
        return (levelTime - timer);
    }

    public int getLevelDefault() {
        level = 1;
        return level;
    }

    public int getScoreDefault() {
        score = 0;
        return score;
    }

    public int getKeyDefault() {
        key = 0;
        return key;
    }

    public int getLifeDefault() {
        life = 4;
        return life;
    }
}
