import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/***********************************************
 * This class is responsible for keeping track
 * of whether or not the input keys are being
 * pressed by the user.
 *
 * TODO: Allow for the user to change keys to
 *  whatever keys they want via a setting.
 ***********************************************/
class KeyListener extends KeyAdapter {
    @Override
    public void keyPressed (KeyEvent e) {

        /* Do not invoke if keys are pressed and
        the Graphics class is not active. */
        if (Graphics.game == null) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            Graphics.game.pressW();
        }

        else if (e.getKeyCode() == KeyEvent.VK_E) {
            Graphics.game.pressE();
        }

        else if (e.getKeyCode() == KeyEvent.VK_O) {
            Graphics.game.pressO();
        }

        else if (e.getKeyCode() == KeyEvent.VK_P) {
            Graphics.game.pressP();
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {

        /* Do not invoke if keys are released and
        the Graphics class is not active. */
        if (Graphics.game == null) {
            return;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            Graphics.game.releaseW();
        }

        else if (e.getKeyCode() == KeyEvent.VK_E) {
            Graphics.game.releaseE();
        }

        else if (e.getKeyCode() == KeyEvent.VK_O) {
            Graphics.game.releaseO();
        }

        else if (e.getKeyCode() == KeyEvent.VK_P) {
            Graphics.game.releaseP();
        }
    }
}
