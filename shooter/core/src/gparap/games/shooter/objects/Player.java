/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.shooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import gparap.games.shooter.Globals;
import gparap.games.shooter.Shooter;

public class Player extends GameObject {
    private final static float SHIELD_OFFSET = 8;   //fixer
    private TextureRegion textureRegionShield, textureRegionDamaged;
    private List<Bullet> bullets;
    private float fireTimer, fireTimerStart;
    private boolean canFire;
    private int life, score;
    private Sound sfxFire, sfxHit, sfxShield;

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score += 5;
    }

    public int getLife() {
        return life;
    }

    public void removeLife() {
        if (life >= 0) {
            sfxHit.play(0.5f);
            life -= 1;
        }
    }

    public void addLife() {
        life += 1;
        sfxShield.play(0.5f);
    }

    public Player(Shooter game) {
        super(game);
        init();
    }

    private void init() {
        textureRegionShield = game.getTextureAtlas().findRegion(Globals.PLAYER_SHIELD);
        textureRegionDamaged = game.getTextureAtlas().findRegion(Globals.PLAYER_DAMAGED);
        position = new Vector2(0.0f, 0.0f);
        initSprite(Globals.PLAYER_SHIP);
        speed = 10;
        bullets = new ArrayList<>();
        fireTimer = fireTimerStart = 3.0f;
        canFire = true;
        createBullets();
        score = 0;
        life = 5;
        sfxFire = game.getAssetManager().get(Globals.SFX_LASER_PLAYER);
        sfxHit = game.getAssetManager().get(Globals.SFX_HIT_PLAYER);
        sfxShield = game.getAssetManager().get(Globals.SFX_SHIELD);
    }

    public void createBullets() {
        for (int i = 0; i < 3; i++) {
            Bullet bullet = new Bullet(game, Bullet.Type.PLAYER);
            bullet.setPosition(new Vector2(position.x + width / 2 - bullet.getWidth() / 2, position.y + height / 2));
            bullets.add(bullet);
        }
    }

    public void update(float delta) {
        //update bullets
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
            bullet.update(delta);
        }

        //update player movement
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
        //draw bullets
        for (Bullet bullet : bullets) {
            if (bullet.isFired()) {
                bullet.draw();
            }
        }
        //draw player
        if (life > 0) {
            //with shield
            game.getSpriteBatch().draw(textureRegion, position.x, position.y);
            game.getSpriteBatch().draw(textureRegionShield, position.x - width / 3 + SHIELD_OFFSET, position.y);
        } else {
            //damaged
            game.getSpriteBatch().draw(textureRegionDamaged, position.x, position.y);
        }
    }

    public Rectangle getCollisionRectangle() {
        return super.getCollisionRectangle(((width * 10 / 100) / 2), ((height * 10 / 100) / 2));
    }

    @Override
    public void dispose() {
        super.dispose();
        textureRegionShield.getTexture().dispose();
        textureRegionDamaged.getTexture().dispose();
    }
}
