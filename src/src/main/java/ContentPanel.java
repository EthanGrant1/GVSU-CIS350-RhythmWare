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
    // Buttons within the GUI
    //Main Menu
    JButton playButton, scoreButton, optionButton, quitButton;

    // Song Menu
    JPanel panel1, difficultyPanel, scores;
    JLabel title, artist, bpm;
    JButton previousButton, nextButton, easyButton, normalButton, hardButton;
    TitledBorder border = BorderFactory.createTitledBorder("Scores");


    public void addContentPanel(String menu) {

        // We will be using the BorderLayout for the GUI
        setLayout(new BorderLayout());

        if (menu.equals("Main Menu")) {

            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());

            // Add the buttons to the sub panel
            playButton = new JButton("Play Game");
            subPanel.add(playButton, BorderLayout.NORTH);

            optionButton = new JButton("Options");
            subPanel.add(optionButton, BorderLayout.CENTER);

            quitButton = new JButton("Quit Game");
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
            panel1 = new JPanel();
            panel1.setLayout(new BorderLayout());
            panel1.setBorder(BorderFactory.createEtchedBorder());
            panel1.add(getBG(), BorderLayout.EAST);
            title = new JLabel(getSongInformation()[0]);
            title.setFont(new Font("Verdana",1,40));
//            artist = new JLabel(getSongInformation()[1]);
//            artist.setFont(new Font("Verdana",1,15));
//            bpm = new JLabel(getSongInformation()[2]);
//            bpm.setFont(new Font("Verdana",1,15));
            add(title, BorderLayout.NORTH);
//            panel1.add(artist, BorderLayout.NORTH);
//            panel1.add(bpm, BorderLayout.EAST);
            add(panel1, BorderLayout.CENTER);
            panel1.add(difficultyPanel, BorderLayout.SOUTH);
            scores = new JPanel();
            scores.setPreferredSize(new Dimension(220, 400));
            scores.setBorder(border);
            panel1.add(scores, BorderLayout.WEST);
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
    public JLabel getBG(){
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("src/main/Assets/Songs/PandoraPalace/DeltaruneBG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = background.getScaledInstance(1600, 900, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }
    //method for playing the song in the song select menu
    public static void playSong() {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        Player player;
        try {
            fileInputStream = new FileInputStream("src/main/Assets/Songs/PandoraPalace/PandoraPalace.mp3");
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
    public String[] getSongInformation(){
        String[] info = new String[3];
        File file = new File("src/main/Assets/Songs/PandoraPalace/SongInfo.txt");
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