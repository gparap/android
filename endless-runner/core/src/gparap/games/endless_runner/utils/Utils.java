/* ***************** *
 * created by gparap *
 * ***************** */
package gparap.games.endless_runner.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import gparap.games.endless_runner.EndlessRunner;

public class Utils {
    /**
     * Sets up the animation frames for a specific texture.
     *
     * @param name the name of the textures' animation
     * @param game game
     * @return animation frames
     */
    public static Array<TextureRegion> addAnimationFrames(String name, EndlessRunner game) {
        Array<TextureRegion> frames = new Array<>();
        switch (name) {

            //NON-FLIERS
            case "barnacle":
                frames.add(game.getAtlas().findRegion("barnacle"));
                frames.add(game.getAtlas().findRegion("barnacle"));
                frames.add(game.getAtlas().findRegion("barnacle"));
                frames.add(game.getAtlas().findRegion("barnacle"));
                frames.add(game.getAtlas().findRegion("barnacle"));
                frames.add(game.getAtlas().findRegion("barnacle_bite"));
                frames.add(game.getAtlas().findRegion("barnacle_bite"));
                frames.add(game.getAtlas().findRegion("barnacle_bite"));
                frames.add(game.getAtlas().findRegion("barnacle_bite"));
                frames.add(game.getAtlas().findRegion("barnacle_bite"));
                break;
            case "lady-bug":
                frames.add(game.getAtlas().findRegion("lady-bug"));
                frames.add(game.getAtlas().findRegion("lady-bug"));
                frames.add(game.getAtlas().findRegion("lady-bug"));
                frames.add(game.getAtlas().findRegion("lady-bug"));
                frames.add(game.getAtlas().findRegion("lady-bug"));
                frames.add(game.getAtlas().findRegion("lady-bug_walk"));
                frames.add(game.getAtlas().findRegion("lady-bug_walk"));
                frames.add(game.getAtlas().findRegion("lady-bug_walk"));
                frames.add(game.getAtlas().findRegion("lady-bug_walk"));
                frames.add(game.getAtlas().findRegion("lady-bug_walk"));
                break;
            case "slime":
                frames.add(game.getAtlas().findRegion("slime"));
                frames.add(game.getAtlas().findRegion("slime"));
                frames.add(game.getAtlas().findRegion("slime"));
                frames.add(game.getAtlas().findRegion("slime"));
                frames.add(game.getAtlas().findRegion("slime"));
                frames.add(game.getAtlas().findRegion("slime_walk"));
                frames.add(game.getAtlas().findRegion("slime_walk"));
                frames.add(game.getAtlas().findRegion("slime_walk"));
                frames.add(game.getAtlas().findRegion("slime_walk"));
                frames.add(game.getAtlas().findRegion("slime_walk"));
                break;
            case "slime-blue":
                frames.add(game.getAtlas().findRegion("slime-blue"));
                frames.add(game.getAtlas().findRegion("slime-blue"));
                frames.add(game.getAtlas().findRegion("slime-blue"));
                frames.add(game.getAtlas().findRegion("slime-blue"));
                frames.add(game.getAtlas().findRegion("slime-blue"));
                frames.add(game.getAtlas().findRegion("slime-blue_walk"));
                frames.add(game.getAtlas().findRegion("slime-blue_walk"));
                frames.add(game.getAtlas().findRegion("slime-blue_walk"));
                frames.add(game.getAtlas().findRegion("slime-blue_walk"));
                frames.add(game.getAtlas().findRegion("slime-blue_walk"));
                break;
            case "snail":
                frames.add(game.getAtlas().findRegion("snail"));
                frames.add(game.getAtlas().findRegion("snail"));
                frames.add(game.getAtlas().findRegion("snail"));
                frames.add(game.getAtlas().findRegion("snail"));
                frames.add(game.getAtlas().findRegion("snail"));
                frames.add(game.getAtlas().findRegion("snail_walk"));
                frames.add(game.getAtlas().findRegion("snail_walk"));
                frames.add(game.getAtlas().findRegion("snail_walk"));
                frames.add(game.getAtlas().findRegion("snail_walk"));
                frames.add(game.getAtlas().findRegion("snail_walk"));
                break;
            case "snake":
                frames.add(game.getAtlas().findRegion("snake"));
                frames.add(game.getAtlas().findRegion("snake"));
                frames.add(game.getAtlas().findRegion("snake"));
                frames.add(game.getAtlas().findRegion("snake"));
                frames.add(game.getAtlas().findRegion("snake"));
                frames.add(game.getAtlas().findRegion("snake_walk"));
                frames.add(game.getAtlas().findRegion("snake_walk"));
                frames.add(game.getAtlas().findRegion("snake_walk"));
                frames.add(game.getAtlas().findRegion("snake_walk"));
                frames.add(game.getAtlas().findRegion("snake_walk"));
                break;
            case "snake-slime":
                frames.add(game.getAtlas().findRegion("snake-slime"));
                frames.add(game.getAtlas().findRegion("snake-slime"));
                frames.add(game.getAtlas().findRegion("snake-slime"));
                frames.add(game.getAtlas().findRegion("snake-slime"));
                frames.add(game.getAtlas().findRegion("snake-slime"));
                frames.add(game.getAtlas().findRegion("snake-slime_move"));
                frames.add(game.getAtlas().findRegion("snake-slime_move"));
                frames.add(game.getAtlas().findRegion("snake-slime_move"));
                frames.add(game.getAtlas().findRegion("snake-slime_move"));
                frames.add(game.getAtlas().findRegion("snake-slime_move"));
                break;
            case "spider":
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider_walk1"));
                frames.add(game.getAtlas().findRegion("spider_walk1"));
                frames.add(game.getAtlas().findRegion("spider_walk1"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider"));
                frames.add(game.getAtlas().findRegion("spider_walk2"));
                frames.add(game.getAtlas().findRegion("spider_walk2"));
                frames.add(game.getAtlas().findRegion("spider_walk2"));
                break;
            case "worm":
                frames.add(game.getAtlas().findRegion("worm"));
                frames.add(game.getAtlas().findRegion("worm"));
                frames.add(game.getAtlas().findRegion("worm"));
                frames.add(game.getAtlas().findRegion("worm"));
                frames.add(game.getAtlas().findRegion("worm"));
                frames.add(game.getAtlas().findRegion("worm_walk"));
                frames.add(game.getAtlas().findRegion("worm_walk"));
                frames.add(game.getAtlas().findRegion("worm_walk"));
                frames.add(game.getAtlas().findRegion("worm_walk"));
                frames.add(game.getAtlas().findRegion("worm_walk"));
                break;

            //FLIERS
            case "bat":
                frames.add(game.getAtlas().findRegion("bat"));
                frames.add(game.getAtlas().findRegion("bat"));
                frames.add(game.getAtlas().findRegion("bat"));
                frames.add(game.getAtlas().findRegion("bat"));
                frames.add(game.getAtlas().findRegion("bat"));
                frames.add(game.getAtlas().findRegion("bat_fly"));
                frames.add(game.getAtlas().findRegion("bat_fly"));
                frames.add(game.getAtlas().findRegion("bat_fly"));
                frames.add(game.getAtlas().findRegion("bat_fly"));
                frames.add(game.getAtlas().findRegion("bat_fly"));
                break;
            case "bee":
                frames.add(game.getAtlas().findRegion("bee"));
                frames.add(game.getAtlas().findRegion("bee"));
                frames.add(game.getAtlas().findRegion("bee"));
                frames.add(game.getAtlas().findRegion("bee"));
                frames.add(game.getAtlas().findRegion("bee"));
                frames.add(game.getAtlas().findRegion("bee_fly"));
                frames.add(game.getAtlas().findRegion("bee_fly"));
                frames.add(game.getAtlas().findRegion("bee_fly"));
                frames.add(game.getAtlas().findRegion("bee_fly"));
                frames.add(game.getAtlas().findRegion("bee_fly"));
                break;
            case "fly":
                frames.add(game.getAtlas().findRegion("fly"));
                frames.add(game.getAtlas().findRegion("fly"));
                frames.add(game.getAtlas().findRegion("fly"));
                frames.add(game.getAtlas().findRegion("fly"));
                frames.add(game.getAtlas().findRegion("fly"));
                frames.add(game.getAtlas().findRegion("fly_fly"));
                frames.add(game.getAtlas().findRegion("fly_fly"));
                frames.add(game.getAtlas().findRegion("fly_fly"));
                frames.add(game.getAtlas().findRegion("fly_fly"));
                frames.add(game.getAtlas().findRegion("fly_fly"));
                break;
        }
        return frames;
    }
}
