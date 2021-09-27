import javax.swing.*;
import java.awt.*;


/*******************************************************************
 * public class GUI
 * Will include an interactable interface used to navigate the game.
 * This is what the Game() class is going to be run in.
 *******************************************************************/
public class GUI {

    /*********************************************
     *
     * @param cont:
     * Refers to a content pane that will inhabit
     * the main frame.
     */
    public static void addButtons(Container cont) {
        // For menu buttons
        JButton button;

        // We will be using the GridBagLayout for the GUI
        cont.setLayout(new GridBagLayout());

        // GridBagConstraints so we can set the area in which am
        // element can and cannot inhabit
        GridBagConstraints c = new GridBagConstraints();

        // Fills horizontal space
        c.fill = GridBagConstraints.HORIZONTAL;
        // No need for padding on the x and y axis
        c.weightx = 0;
        c.weighty = 0;
        // Place buttons in the center of the screen
        c.gridx = GridBagConstraints.CENTER;
        // Size of the grid x-axis
        c.gridwidth = 1;
        // Button height
        c.ipady = 40;
        // Button width
        c.ipadx = 500;

        button = new JButton("Play Game");
        c.gridy = 1;
        cont.add(button, c);

        button = new JButton("High Scores");
        c.gridy = 2;
        cont.add(button, c);

        button = new JButton("Options");
        c.gridy = 3;
        cont.add(button, c);

        button = new JButton("Quit Game");
        c.gridy = 4;
        cont.add(button, c);
    }

    public static void main(String args[]){

        // Setting up the main frame
        JFrame frame = new JFrame("RhythmWare Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        // Adds the menu
        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Options");
        mb.add(m1);
        mb.add(m2);

        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem volume = new JMenuItem("Change Volume Settings");
        JMenuItem reset = new JMenuItem("Reset Game");
        JMenuItem restart = new JMenuItem("Restart Current Song");
        JMenuItem quit = new JMenuItem("Quit");

        m1.add(open);
        m1.add(save);
        m1.add(saveAs);
        m2.add(volume);
        m2.add(reset);
        m2.add(restart);
        m2.add(quit);

        frame.getContentPane().add(BorderLayout.NORTH, mb);

        // Adds the GUI buttons
        JPanel buttonPanel = new JPanel();
        addButtons(buttonPanel);
        frame.getContentPane().add(buttonPanel);

        // Displays the frame
        frame.setVisible(true);
    }
}
