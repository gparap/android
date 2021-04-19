/* ***************** *
 * created by gparap *
 * ***************** */
package dmn.games.platformer.touch;

import dmn.games.platformer.Player;

public class TouchManager {
    private final TouchPad touchPad;
    private Player player;

    public TouchManager(TouchPad touchPad, Player player) {
        this.touchPad = touchPad;
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void update() {
        if (touchPad.isRightPressed() && !touchPad.isLeftPressed()) {
            player.moveRight();
        }
        if (touchPad.isLeftPressed() && !touchPad.isRightPressed()) {
            player.moveLeft();
        }
        if ((!touchPad.isRightPressed() && !touchPad.isLeftPressed()) ||                //special
             (touchPad.isRightPressed() && touchPad.isLeftPressed())) {                 //cases
            player.getBody().setLinearVelocity(0f, player.getBody().getLinearVelocity().y);
        }
        if (touchPad.isJumpPressed() && player.getState() != Player.State.JUMP) {
            player.jump();
            touchPad.setJumpPressed(false); //auto jumping disabled
        }
        if (touchPad.isJumpPressed() && player.canJump()) {
            player.jump();
            player.setCanJump(false);
            touchPad.setJumpPressed(false); //auto jumping disabled
        }
    }
}
