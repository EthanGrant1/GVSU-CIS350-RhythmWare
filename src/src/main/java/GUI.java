import javax.swing.*;


/*******************************************************************
 * public class GUI
 * Will include an interactable interface used to navigate the game.
 * This is what the Game() class is going to be run in.
 *******************************************************************/
public class GUI {
    public static void main(String args[]) {
        JFrame frame;
        // Setting up the main frame
        frame = new JFrame("RhythmWare Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Adds the menu bar
        JMenuBar mb = new JMenuBar();

        // Menus for File and Options
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Options");
        mb.add(m1);
        mb.add(m2);

        // Items to be added to the menus
        JMenuItem openMenuButton = new JMenuItem("Open");
        JMenuItem saveMenuButton = new JMenuItem("Save");
        JMenuItem saveAsMenuButton = new JMenuItem("Save As");
        JMenuItem volumeMenuButton = new JMenuItem("Change Volume Settings");
        JMenuItem resetMenuButton = new JMenuItem("Reset Game");
        JMenuItem restartMenuButton = new JMenuItem("Restart Current Song");
        JMenuItem quitMenuButton = new JMenuItem("Quit");

        m1.add(openMenuButton);
        m1.add(saveMenuButton);
        m1.add(saveAsMenuButton);
        m2.add(volumeMenuButton);
        m2.add(resetMenuButton);
        m2.add(restartMenuButton);
        m2.add(quitMenuButton);

        // Adds the main content panel
        ContentPanelMain panel = new ContentPanelMain(openMenuButton, saveMenuButton, saveAsMenuButton,
                volumeMenuButton, resetMenuButton, restartMenuButton,
                quitMenuButton);
        frame.getContentPane().add(panel);

        // Places the menu toolbar at the top of the screen
        frame.setJMenuBar(mb);

        // Displays the frame
        frame.setVisible(true);
    }
}


