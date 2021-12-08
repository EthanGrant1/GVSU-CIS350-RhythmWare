import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/***********************************************
 * This class is responsible for keeping track
 * of whether or not the input keys are being
 * pressed by the user.
 ***********************************************/
class KeyListener extends KeyAdapter {

    /*********************************************
     * keyPressed (KeyEvent e):
     *
     * Uses the KeyEvent to track which keyboard
     * buttons are being pressed by the user. Only
     * responds if a specific set of buttons are
     * being pressed, and ignores the rest.
     *
     * @param e is the event associated with a
     *          particular keyboard key.
     *********************************************/
    @Override
    public void keyPressed (KeyEvent e) {

        /* Do not invoke if keys are pressed and
        the Graphics class is not active. */
        if (Graphics.game == null) {
            return;
        }

        // If the W key is pressed
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Graphics.game.pressW();
        }

        // If the E key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            Graphics.game.pressE();
        }

        // If the O key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_O) {
            Graphics.game.pressO();
        }

        // If the P key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            Graphics.game.pressP();
        }

        // If the Escape key is pressed
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Graphics.game.pressEsc();
        }
    }

    /*********************************************
     * keyReleased (KeyEvent e):
     *
     * Essentially the same as keyPressed, except
     * this event triggers only after a key has
     * already been pressed, making it dependent
     * on the other method to work.
     *
     * @param e is the event associated with a
     *          particular keyboard key.
     *********************************************/
    @Override
    public void keyReleased (KeyEvent e) {

        /* Do not invoke if keys are released and
        the Graphics class is not active. */
        if (Graphics.game == null) {
            return;
        }

        // If the W key is released
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Graphics.game.releaseW();
        }

        // If the E key is released
        else if (e.getKeyCode() == KeyEvent.VK_E) {
            Graphics.game.releaseE();
        }

        // If the O key is released
        else if (e.getKeyCode() == KeyEvent.VK_O) {
            Graphics.game.releaseO();
        }

        // If the P key is released
        else if (e.getKeyCode() == KeyEvent.VK_P) {
            Graphics.game.releaseP();
        }
    }
}
