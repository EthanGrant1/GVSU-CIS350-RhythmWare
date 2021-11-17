import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/*****************************************
 * The main panel for the GUI.
 *****************************************/
public class ContentPanel extends JPanel {
    // Buttons within the GUI
    //Main Menu
    JButton playButton, scoreButton, optionButton, quitButton;

    // Song Menu
    JPanel panel1;
    JButton previousButton, nextButton, easyButton, normalButton, hardButton;

    public void addContentPanel(String menu) {

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

        if (menu.equals("Song Select")) {
            c.weightx = 0;
            c.weighty = 0;
            c.ipady = 1080;

            previousButton = new JButton("Previous");
            c.gridx = 0;
            c.anchor = GridBagConstraints.LINE_START;
            add(previousButton, c);

            nextButton = new JButton("Next");
            c.gridx = 2;
            c.anchor = GridBagConstraints.LINE_END;
            add(nextButton, c);

            panel1 = new JPanel();
            c.ipadx = 1740;
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            c.weighty = 1.0;
            c.gridx = 1;
            c.anchor = GridBagConstraints.CENTER;
            panel1.setBorder(BorderFactory.createEtchedBorder());
            panel1.add(getBG());
            add(panel1, c);
            start();
        }

        if (menu.equals("Options")) {
            // stuff goes in here
        }
    }

    //method for switching between menus
    public void changeMenu(String e) {
        // remove all previous menu parts and create new panel
        this.removeAll();
        this.addContentPanel(e);
        repaint();
        revalidate();
    }

    //method for getting menu background for the song
    public JLabel getBG() {
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("src/main/Assets/Songs/songname/bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = background.getScaledInstance(500, 281, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }
    
    //method for playing the song in the song select menu
    public static void playSong() {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        Player player;
        try {
            fileInputStream = new FileInputStream("src/main/Assets/Songs/songname/song.mp3");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new Player(bufferedInputStream);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    //plays song on separate thread to maintain gui functionality
    private void start(){
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                playSong();
                return null;
            }
        };
        worker.execute();
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
                changeMenu("Options");
            }

            if (event.getSource() == quitButton) {
                System.exit(1);
            }

            //SONG SELECTION MENU BUTTONS
            if (event.getSource() == previousButton) {
                getBG();
            }

            if (event.getSource() == nextButton) {
                getBG();
            }
            } } }