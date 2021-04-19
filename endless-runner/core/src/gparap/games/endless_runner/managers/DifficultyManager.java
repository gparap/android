/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.managers;

public class DifficultyManager {
    private int difficultyBonus, difficultyEnemy;

    public int getDifficultyBonus() {
        return difficultyBonus;
    }

    public int getDifficultyEnemy() {
        return difficultyEnemy;
    }

    public DifficultyManager() {
        //start with easiest difficulty
        difficultyEnemy = 6000;
        difficultyBonus = 6;
    }

    public void update(int score) {
        if (score >= 1000 && score < 2000) {
            //proceed with medium difficulty
            difficultyEnemy = 180;
            difficultyBonus = 60;
        } else if (score >= 2000) {
            //end with hard difficulty
            difficultyEnemy = 60;
            difficultyBonus = 90;
        }
    }
}
