/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.racing.vehicles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import gparap.games.racing.Globals;
import gparap.games.racing.Racing;

public class Enemy extends Vehicle {
    private final float startPosition;
    private TextureRegion textureGreen, textureOrange, textureRed, textureWhite;

    public Enemy(Racing game, float startPosition) {
        super(game);
        this.startPosition = startPosition;
        init();
    }

    private void init() {
        textureGreen = game.getTextureAtlas().findRegion(Globals.CAR_GREEN);
        textureOrange = game.getTextureAtlas().findRegion(Globals.CAR_ORANGE);
        textureRed = game.getTextureAtlas().findRegion(Globals.CAR_RED);
        textureWhite = game.getTextureAtlas().findRegion(Globals.CAR_WHITE);
        width = textureGreen.getRegionWidth();
        height = textureGreen.getRegionHeight();
        randomizeTexture();
        position = new Vector2(game.getWidth() / 2f, startPosition);
        speed = 10f * Racing.speed_factor;
    }

    //randomize position X based on game width
    private float randomizePositionX(float width) {
        int random = new RandomXS128().nextInt((int) width);
        //check if position is inside screen
        if (random + this.width > game.getWidth()) {
            random = (int) (game.getWidth() - this.width);
        }
        return (float) random;
    }

    public void update(float delta) {
        //move downwards
        position.y -= speed + delta;
        if (position.y + height < 0) {
            reset();
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(super.getTextureRegion(), position.x, position.y);
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(((width * 10 / 100) / 2), ((height * 10 / 100) / 2));
    }

    public void reset() {
        position = new Vector2(randomizePositionX(game.getWidth()), startPosition);
        randomizeTexture();
    }

    private void randomizeTexture() {
        int random = new RandomXS128().nextInt(4);
        switch (random) {
            case 0:
                super.setTextureRegion(textureGreen);
                break;
            case 1:
                super.setTextureRegion(textureOrange);
                break;
            case 2:
                super.setTextureRegion(textureRed);
                break;
            case 3:
                super.setTextureRegion(textureWhite);
                break;
        }
    }

    @Override
    public void dispose() {
        textureGreen.getTexture().dispose();
        textureOrange.getTexture().dispose();
        textureRed.getTexture().dispose();
        textureWhite.getTexture().dispose();
    }
}
