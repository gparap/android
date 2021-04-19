/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gparap.games.shooter.Globals;
import gparap.games.shooter.Shooter;

public class Enemy extends GameObject {
    private Vector2 velocity;
    private float speedX, speedY, fireTimer, fireTimerStart;
    private List<Bullet> bullets;
    private boolean isVisible, isActive, canFire;
    private Enemy.TYPE type;
    private Sound sfxFire, sfxHit;

    public List<Bullet> getBullets() {
        return bullets;
    }

    private enum TYPE {
        SHIP, UFO, METEOR
    }

    public void setActive(boolean active) {
        isActive = active;

        //for cumulative difficulty
        if (type == TYPE.SHIP || type == TYPE.UFO) {
            createBullet();
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Enemy(Shooter game) {
        super(game);
        init();
    }

    private void init() {
        bullets = new ArrayList<>();
        randomizeSprite();
        velocity = new Vector2(randomizeVelocity(), 0);
        speedX = randomizeSpeedX();
        speedY = randomizeSpeedY();
        fireTimer = fireTimerStart = 5.0f;
        canFire = true;
        isActive = false;
        isVisible = false;
        sfxFire = game.getAssetManager().get(Globals.SFX_LASER_ENEMY);
        sfxHit = game.getAssetManager().get(Globals.SFX_HIT_ENEMY);
    }

    public void update(float delta) {
        //update movement
        if (isActive) {
            //check screen Y visibility for better collision detection
            isVisible = position.y < game.getHeight();

            //update bullets
            if (isVisible) {
                for (Bullet bullet : bullets) {
                    if (canFire) {
                        if (!bullet.isFired()) {
                            sfxFire.play(0.33f);
                            bullet.setFired(true);
                            bullet.setPosition(new Vector2(position.x + width / 2 - bullet.getWidth() / 2, position.y + height / 2));
                            canFire = false;
                            fireTimer += delta;
                        }
                    } else {
                        if (fireTimer < 0.0f) {
                            fireTimer = fireTimerStart;
                            canFire = true;
                            fireTimer += delta;
                        }
                    }
                    fireTimer -= delta;
                }
            }

            //move right
            if (velocity.x == 1f) {
                position.x += (speedX + delta) * velocity.x;
                //keep inside screen and change velocity
                if (position.x + textureRegion.getRegionWidth() > game.getWidth()) {
                    velocity.x *= -1f;
                }
            }
            //move left
            else {
                position.x += (speedX + delta) * velocity.x;
                //keep inside screen and change velocity
                if (position.x < 0) {
                    velocity.x *= -1f;
                }
            }
            //move downwards
            position.y -= (speedY + delta);
            if (position.y + height < 0) {
                position.y = game.getHeight();
            }
        }
    }

    public void draw() {
        if (isVisible) {
            //draw bullets
            for (Bullet bullet : bullets) {
                if (bullet.isFired()) {
                    bullet.draw();
                }
            }
            //draw enemy
            if (isActive) {
                game.getSpriteBatch().draw(textureRegion, position.x, position.y);
            }
        }
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(((width * 10 / 100) / 2), ((height * 10 / 100) / 2));
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void remove() {
        sfxHit.play(0.5f);
        setActive(false);
        position = new Vector2(randomizePositionX(width), game.getHeight() + height);
        speedX = randomizeSpeedX();
        speedY = randomizeSpeedY();
    }

    private float randomizeVelocity() {
        int random = new Random().nextInt() + 1;
        if (random == 0) {
            return -1.0f;   //left
        } else {
            return +1.0f;   //right
        }
    }

    private float randomizePositionX(float width) {
        int random = new RandomXS128().nextInt((int) (game.getWidth() - width));
        return (float) random;
    }

    public float randomizeSpeedX() {
        return new RandomXS128().nextFloat() + 0.33f * game.getScaleFactor();
    }

    public float randomizeSpeedY() {
        return new RandomXS128().nextFloat() + 0.5f * game.getScaleFactor();
    }

    public void createBullet() {
        if (bullets.size() < Globals.ENEMY_BULLET_MAX) {
            Bullet bullet = new Bullet(game, Bullet.Type.ENEMY);
            bullet.setPosition(new Vector2(position.x + width / 2 - bullet.getWidth() / 2, position.y + height / 2));
            bullets.add(bullet);
        }
    }

    private void randomizeSprite() {
        int random = new RandomXS128().nextInt(12);
        switch (random) {
            case 0:
                initSprite(Globals.ENEMY_SHIP_BLACK);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.SHIP;
                createBullet();
                break;
            case 1:
                initSprite(Globals.ENEMY_SHIP_BLUE);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.SHIP;
                createBullet();
                break;
            case 2:
                initSprite(Globals.ENEMY_SHIP_GREEN);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.SHIP;
                createBullet();
                break;
            case 3:
                initSprite(Globals.ENEMY_SHIP_RED);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.SHIP;
                createBullet();
                break;
            case 4:
                initSprite(Globals.ENEMY_UFO_BLUE);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.UFO;
                createBullet();
                break;
            case 5:
                initSprite(Globals.ENEMY_UFO_GREEN);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.UFO;
                createBullet();
                break;
            case 6:
                initSprite(Globals.ENEMY_UFO_RED);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.UFO;
                createBullet();
                break;
            case 7:
                initSprite(Globals.ENEMY_UFO_YELLOW);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.UFO;
                createBullet();
                break;
            case 8:
                initSprite(Globals.ENEMY_METEOR_BROWN_BIG);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.METEOR;
                break;
            case 9:
                initSprite(Globals.ENEMY_METEOR_BROWN_SMALL);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.METEOR;
                break;
            case 10:
                initSprite(Globals.ENEMY_METEOR_GREY_BIG);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.METEOR;
                break;
            case 11:
                initSprite(Globals.ENEMY_METEOR_GREY_SMALL);
                position = new Vector2(randomizePositionX(width), game.getHeight() + height);
                type = TYPE.METEOR;
                break;
        }
    }
}
