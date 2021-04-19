/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.vehicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import gparap.games.racing.Globals;
import gparap.games.racing.Racing;

public class Driver extends Vehicle {
    private boolean isHit;
    private float distance;
    private final AssetManager assetManager;

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;

        //play sfx
        Sound sfxCrash = assetManager.get(Globals.AUDIO_SFX_PATH);
        sfxCrash.play(1.0f);
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Driver(Racing game) {
        super(game);
        this.assetManager = game.getAssetManager();
        init();
    }

    private void init() {
        position = new Vector2(0.0f, 0.0f);

        //find driver from preferences
        Preferences preferences = Gdx.app.getPreferences(Globals.PREFERENCES);
        int driver = preferences.getInteger(Globals.PREFERENCES_DRIVER);
        String driverName = "";
        switch (driver) {
            case 0:
                driverName = Globals.BIKE_BLACK;
                break;
            case 1:
                driverName = Globals.BIKE_BLUE;
                break;
            case 2:
                driverName = Globals.BIKE_GREEN;
                break;
            case 3:
                driverName = Globals.BIKE_RED;
                break;
            case 4:
                driverName = Globals.BIKE_YELLOW;
                break;
        }
        initSprite(driverName);
        speed = 10;
        distance = 0;
        isHit = false;
    }

    public void update(float delta) {
        //update movement
        if (Gdx.input.isTouched()) {
            //move right
            if (Gdx.input.getX() > position.x + textureRegion.getRegionWidth()) {
                position.x += speed + delta;
                //keep inside screen
                if (position.x + textureRegion.getRegionWidth() > game.getWidth()) {
                    position.x = game.getWidth() - textureRegion.getRegionWidth();
                }
            }
            //move left
            if (Gdx.input.getX() < position.x) {
                position.x -= speed + delta;
                //keep inside screen
                if (position.x < 0) {
                    position.x = 0;
                }
            }
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(textureRegion, position.x, position.y);
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(((width * 10 / 100) / 2), ((height * 10 / 100) / 2));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
