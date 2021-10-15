import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*****************************************
 * The main panel for the GUI.
 *****************************************/
public class ContentPanel extends JPanel {

    // Buttons within the GUI
    //Main Menu
    JButton playButton, scoreButton, optionButton, quitButton;

    // Song Menu
    JButton previousButton, nextButton, backButton, playSongButton;

    public ContentPanel(String menu) {
        // We will be using the GridBagLayout for the GUI
        setLayout(new GridBagLayout());

        // GridBagConstraints so we can set the area in which am
        // element can and cannot inhabit
        GridBagConstraints c = new GridBagConstraints();

        if (menu.equals("Main Menu")) {
            // Fills horizontal space
            c.fill = GridBagConstraints.HORIZONTAL;
            // No need for padding on the x and y axis
            c.weightx = 0;
            c.weighty = 0;
            // Place buttons in the center of the screen
            c.gridx = GridBagConstraints.CENTER;
            c.gridy = GridBagConstraints.CENTER;
            // Size of the grid x-axis
            c.gridwidth = 1;
            // Button height
            c.ipady = 40;
            // Button width
            c.ipadx = 500;

            // Add the buttons to the GUI
            playButton = new JButton("Play Game");
            c.gridy = 1;
            add(playButton, c);

            scoreButton = new JButton("High Scores");
            c.gridy = 2;
            add(scoreButton, c);

            optionButton = new JButton("Options");
            c.gridy = 3;
            add(optionButton, c);

            quitButton = new JButton("Quit Game");
            c.gridy = 4;
            add(quitButton, c);

            // Register the action listeners
            playButton.addActionListener(new ButtonListener());
            scoreButton.addActionListener(new ButtonListener());
            optionButton.addActionListener(new ButtonListener());
            quitButton.addActionListener(new ButtonListener());
        }
        //TODO: format buttons correctly
        if(menu.equals("Song Select")){
            // Fills horizontal space
            c.fill = GridBagConstraints.HORIZONTAL;
            // No need for padding on the x and y axis
            c.weightx = 0;
            c.weighty = 0;
            // Place buttons in the center of the screen
            c.gridx = GridBagConstraints.CENTER;
            c.gridy = GridBagConstraints.CENTER;
            // Size of the grid x-axis
            c.gridwidth = 1;
            // Button height
            c.ipady = 40;
            // Button width
            c.ipadx = 500;

            // Add the buttons to the GUI
            previousButton = new JButton("Previous Song");
            c.gridy = 1;
            add(previousButton, c);

            nextButton = new JButton("Next Song");
            c.gridy = 2;
            add(nextButton, c);

            backButton = new JButton("Back");
            c.gridy = 3;
            add(backButton, c);

            playSongButton = new JButton("Play");
            c.gridy = 4;
            add(playSongButton, c);

            // Register the action listeners
            previousButton.addActionListener(new ButtonListener());
            nextButton.addActionListener(new ButtonListener());
            backButton.addActionListener(new ButtonListener());
            playSongButton.addActionListener(new ButtonListener());
        }
    }
    //method for switching between menus
    public void changeMenu(String e) {
        // remove all previous menu parts and create new panel
        removeAll();
        JPanel newPanel = new JPanel();
        newPanel.add(new ContentPanel(e));
        add(newPanel);
        repaint();
        revalidate();
    }

    private class ButtonListener implements ActionListener {

        // TODO: Add all events to this ActionEvent list.

        @Override
        public void actionPerformed(ActionEvent event) {
            //MAIN MENU BUTTONS
            if (event.getSource() == playButton) {
                changeMenu("Song Select");
            }

            if (event.getSource() == scoreButton) {

            }

            if (event.getSource() == optionButton) {

            }

            if (event.getSource() == quitButton) {
                System.exit(1);
            }

            //SONG SELECTION MENU BUTTONS
            if (event.getSource() == previousButton) {

            }

            if (event.getSource() == nextButton) {

            }
            if (event.getSource() == backButton) {
                changeMenu("Main Menu");
            }
            if (event.getSource() == playSongButton) {

            }
            } } }