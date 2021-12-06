import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/*****************************************
 * The main panel for the GUI.
 *****************************************/
public class ContentPanel extends JPanel {
    int directoryPos = 0;
    // Buttons within the GUI
    //Main Menu
    JButton playButton, optionButton, quitButton;
    JLabel gameName;

    // Song Menu
    JPanel panel, difficultyPanel, scores;
    JLabel title;
    JButton previousButton, nextButton, easyButton, normalButton, hardButton;
    TitledBorder border = BorderFactory.createTitledBorder("Scores");

    //generates different GUI based on which menu is chosen
    public void addContentPanel(String menu) {

        // We will be using the BorderLayout for the GUI
        setLayout(new BorderLayout());

        if (menu.equals("Main Menu")) {
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());

            gameName = new JLabel("RhythmWare Mania");
            gameName.setPreferredSize(new Dimension(370, 400));
            gameName.setFont(new Font("Arial", Font.BOLD,40));
            add(gameName, BorderLayout.NORTH);


            // Add the buttons to the sub panel
            playButton = new JButton("Play Game");
            playButton.setPreferredSize(new Dimension(120, 40));
            subPanel.add(playButton, BorderLayout.NORTH);

            optionButton = new JButton("Options");
            optionButton.setPreferredSize(new Dimension(120, 40));
            subPanel.add(optionButton, BorderLayout.CENTER);

            quitButton = new JButton("Quit Game");
            quitButton.setPreferredSize(new Dimension(120, 40));
            subPanel.add(quitButton, BorderLayout.SOUTH);

            //adding sub panel to center of main menu panel
            add(subPanel, BorderLayout.CENTER);

            // Register the action listeners
            playButton.addActionListener(new ButtonListener());
            optionButton.addActionListener(new ButtonListener());
            quitButton.addActionListener(new ButtonListener());
        }

        if (menu.equals("Song Select")) {

            //Default buttons
            previousButton = new JButton("<");
            add(previousButton, BorderLayout.WEST);

            nextButton = new JButton(">");
            add(nextButton, BorderLayout.EAST);

            difficultyPanel = new JPanel();
            easyButton = new JButton("Easy");
            easyButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(easyButton, BorderLayout.WEST);

            normalButton = new JButton("Normal");
            normalButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(normalButton, BorderLayout.CENTER);

            hardButton = new JButton("Hard");
            hardButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(hardButton, BorderLayout.EAST);


            //Panel That Contains BG and Song Information
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.add(getBG(), BorderLayout.EAST);

            //adding song title and artist to screen
            title = new JLabel(getSongInformation()[1] + " - " + getSongInformation()[0]);
            title.setFont(new Font("Verdana", Font.BOLD,40));
            add(title, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            panel.add(difficultyPanel, BorderLayout.SOUTH);

            //panel that displays scores
            scores = new JPanel();
            scores.setPreferredSize(new Dimension(220, 400));
            scores.setBorder(border);
            panel.add(scores, BorderLayout.WEST);

            //play song audio
            start();

            // Register the action listeners
            previousButton.addActionListener(new ButtonListener());
            nextButton.addActionListener(new ButtonListener());
            easyButton.addActionListener(new ButtonListener());
            normalButton.addActionListener(new ButtonListener());
            hardButton.addActionListener(new ButtonListener());
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
    public JLabel getBG(){
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("src/main/Assets/Songs/" + getCurrentSong() + "/BG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = background.getScaledInstance(1600, 900, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }

    //method for playing the song in the song select menu
    public void playSong() {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        Player player;
        try {
            fileInputStream = new FileInputStream("src/main/Assets/Songs/" + getCurrentSong() + "/Song.mp3");
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
    //reads songInfo file
    public String[] getSongInformation(){
        String[] info = new String[3];
        File file = new File("src/main/Assets/Songs/" + getCurrentSong() + "/SongInfo.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineNum++;
            if (lineNum == 1) {
                info[0] = line;
            }
            if (lineNum == 2) {
                info[1] = line;
            }
            if (lineNum == 3) {
                info[2] = line;
            }
        }
        return info;
    }
    //gets the name of the song in directory
    public String getCurrentSong(){
        return "Pandora Palace";
    }
    private class ButtonListener implements ActionListener {

        // TODO: Add all events to this ActionEvent list.

        @Override
        public void actionPerformed(ActionEvent event) {
            //MAIN MENU BUTTONS
            if (event.getSource() == playButton) {
                changeMenu("Song Select");
            }

            if (event.getSource() == optionButton) {
                changeMenu("Options");
            }

            if (event.getSource() == quitButton) {
                System.exit(1);
            }

            //SONG SELECTION MENU BUTTONS
            if (event.getSource() == previousButton) {
                if(directoryPos > 0){
                    directoryPos -= 1;
                }
            }
            if (event.getSource() == nextButton) {
                if (directoryPos < new File("src/main/Assets/Songs/").list().length) {
                    directoryPos += 1;
                }
            }
            if(event.getSource() == easyButton){

            }
            if (event.getSource() == normalButton){
                try {
                    new Graphics("Pandora Palace");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (event.getSource() == hardButton){

            }
        }
    }
}