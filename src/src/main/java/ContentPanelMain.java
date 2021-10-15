
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*********************************************
 * ContentPanelMain performs all of the menu
 * actions as well as some behind the scenes
 * work for the GUI class.
 **********************************************/
public class ContentPanelMain extends JPanel {

    // Buttons for the menu toolbar
    private JMenuItem openMenuButton, saveMenuButton, saveAsMenuButton,
            volumeMenuButton, resetMenuButton, restartMenuButton, quitMenuButton;

    /*********************************************************************************************************
     * Does the behind the scenes work for the GUI and implements menu functionality.
     *
     * @param openMenuButton Opens a song file.
     * @param saveMenuButton Saves a song file as default file format.
     * @param saveAsMenuButton Saves a song file as selected file format.
     * @param volumeMenuButton Opens a menu to change volume settings.
     * @param resetMenuButton Resets the game.
     * @param restartMenuButton Restarts the current song from the beginning
     * @param quitMenuButton Quits the game.
     *********************************************************************************************************/
    public ContentPanelMain(JMenuItem openMenuButton, JMenuItem saveMenuButton, JMenuItem saveAsMenuButton,
                            JMenuItem volumeMenuButton, JMenuItem resetMenuButton, JMenuItem restartMenuButton,
                            JMenuItem quitMenuButton) {

        // Create the main content panel
        JPanel panel = new JPanel();
        panel.add(new ContentPanel("Main Menu"));
        add(panel);

        // All of the menu buttons
        this.openMenuButton = openMenuButton;
        this.saveMenuButton = saveMenuButton;
        this.saveAsMenuButton = saveAsMenuButton;
        this.volumeMenuButton = volumeMenuButton;
        this.resetMenuButton = resetMenuButton;
        this.restartMenuButton = restartMenuButton;
        this.quitMenuButton = quitMenuButton;

        // Register the action listeners
        openMenuButton.addActionListener(new MenuListener());
        saveMenuButton.addActionListener(new MenuListener());
        saveAsMenuButton.addActionListener(new MenuListener());
        volumeMenuButton.addActionListener(new MenuListener());
        resetMenuButton.addActionListener(new MenuListener());
        restartMenuButton.addActionListener(new MenuListener());
        quitMenuButton.addActionListener(new MenuListener());
    }

    /*****************************************************
     * Does an action upon clicking the menu buttons.
     *****************************************************/
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {

            // Button events are currently blank
            // TODO: Give functionality to all menu buttons
            if (event.getSource() == openMenuButton) {

            }

            if (event.getSource() == saveMenuButton) {

            }

            if (event.getSource() == saveAsMenuButton) {

            }

            if (event.getSource() == volumeMenuButton) {

            }

            if (event.getSource() == resetMenuButton) {

            }

            if (event.getSource() == restartMenuButton) {

            }

            if (event.getSource() == quitMenuButton) {
                System.exit(1);
            } } } }