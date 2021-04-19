/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.endless_runner.EndlessRunner;
import gparap.games.endless_runner.utils.Globals;
import gparap.games.endless_runner.objects.spawnables.Enemy;
import gparap.games.endless_runner.objects.spawnables.GameSpawnable;

public class EnemyManager implements Disposable {
    private Array<GameSpawnable> enemiesBasic;
    private final EndlessRunner game;
    private final World world;
    private int activeEnemyIndex;
    private int difficulty;

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean hasActiveEnemy() {
        for (GameSpawnable enemy : enemiesBasic) {
            if (enemy.isActive()) {
                return true;
            }
        }
        return false;
    }

    public EnemyManager(EndlessRunner game, World world) {
        this.game = game;
        this.world = world;
        init();
    }

    private void init() {
        enemiesBasic = new Array<>();

        //create enemy groups
        int randomGroup = new RandomXS128().nextInt(3);
        switch (randomGroup) {
            case 0:
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "barnacle", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "lady-bug", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "snake-slime", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "bat", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, true));
                break;
            case 1:
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "slime", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "spider", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "worm", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "bee", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, true));
                break;
            case 2:
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "slime-blue", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "snail", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "snake", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, false));
                enemiesBasic.add(new gparap.games.endless_runner.objects.spawnables.Enemy(game, world, "fly", Globals.ENEMY_INIT_XY, Globals.ENEMY_INIT_XY, true));
                break;
        }
    }

    public void update(float delta) {
        gparap.games.endless_runner.objects.spawnables.Enemy enemy = (Enemy) enemiesBasic.get(activeEnemyIndex);
        enemy.setDifficultyEnemy(difficulty);
        enemy.update(delta);
    }

    public void draw(SpriteBatch spriteBatch) {
        for (GameSpawnable enemy : enemiesBasic) {
            enemy.draw(spriteBatch);
        }
    }

    @Override
    public void dispose() {
        for (GameSpawnable enemy : enemiesBasic) {
            enemy.dispose();
        }
    }

    public GameSpawnable getRandomEnemy() {
        boolean isActive = false;
        RandomXS128 randomXS128 = new RandomXS128();
        int random = 0;

        //loop until you get an inactive enemy
        while (!isActive) {
            random = randomXS128.nextInt(enemiesBasic.size);

            //check if enemy is active or not
            if (!enemiesBasic.get(random).isActive()) {
                isActive = true;
                activeEnemyIndex = random;
            }
        }
        return enemiesBasic.get(random);
    }
}
