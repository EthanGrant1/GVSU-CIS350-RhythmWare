import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/*****************************************
 * The main panel for the GUI.
 *****************************************/
public class ContentPanel extends JPanel {
    //int for tracking which song to display
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

    // Options Menu
    JPanel optionsPanel;
    JLabel options;
    JButton back;
    JTextField key1, key2, key3, key4;
    //size of screen for proper scaling
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //generates different GUI based on which menu is chosen
    public void addContentPanel(String menu) {

        // We will be using the BorderLayout for the GUI
        setLayout(new BorderLayout());


        if (menu.equals("Main Menu")) {
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());

            gameName = new JLabel("RhythmWare Mania");
            gameName.setPreferredSize(new Dimension(370, 400));
            gameName.setFont(new Font("Arial", Font.BOLD, 40));
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
            title.setFont(new Font("Verdana", Font.BOLD, 40));
            add(title, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            panel.add(difficultyPanel, BorderLayout.SOUTH);

            //panel that displays scores
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBorder(border);
            scrollPane.setPreferredSize(new Dimension(220, 400));
            scrollPane.setViewportView(getScores());
            getScores().setLayoutOrientation(JList.VERTICAL_WRAP);
            panel.add(scrollPane, BorderLayout.WEST);


            // Register the action listeners
            previousButton.addActionListener(new ButtonListener());
            nextButton.addActionListener(new ButtonListener());
            easyButton.addActionListener(new ButtonListener());
            normalButton.addActionListener(new ButtonListener());
            hardButton.addActionListener(new ButtonListener());

        }

        if (menu.equals("Options")) {
            // stuff goes in here
            options = new JLabel("Keybindings");
            options.setPreferredSize(new Dimension(370, 200));
            options.setFont(new Font("Arial", Font.BOLD, 40));

            key1 = new JTextField("W");
            TitledBorder key1Border = BorderFactory.createTitledBorder("Key 1");
            key1.setBorder(key1Border);
            key1.setPreferredSize(new Dimension(60, 40));
            key2 = new JTextField("E");
            TitledBorder key2Border = BorderFactory.createTitledBorder("Key 2");
            key2.setBorder(key2Border);
            key2.setPreferredSize(new Dimension(60, 40));
            key3 = new JTextField("I");
            TitledBorder key3Border = BorderFactory.createTitledBorder("Key 3");
            key3.setBorder(key3Border);
            key3.setPreferredSize(new Dimension(60, 40));
            key4 = new JTextField("O");
            TitledBorder key4Border = BorderFactory.createTitledBorder("Key 4");
            key4.setBorder(key4Border);
            key4.setPreferredSize(new Dimension(60, 40));

            optionsPanel = new JPanel();
            optionsPanel.setLayout(new BorderLayout());
            add(options, BorderLayout.NORTH);

            optionsPanel.add(key1, BorderLayout.NORTH);
            optionsPanel.add(key2, BorderLayout.CENTER);
            optionsPanel.add(key3, BorderLayout.SOUTH);

            JPanel extra = new JPanel();
            extra.setLayout(new BorderLayout());
            extra.add(key4, BorderLayout.CENTER);
            add(extra, BorderLayout.SOUTH);

            back = new JButton("Back");
            extra.add(back, BorderLayout.SOUTH);

            add(optionsPanel, BorderLayout.CENTER);

            back.addActionListener(new ButtonListener());
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
            background = ImageIO.read(new File("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/BG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = background.getScaledInstance((int) (screenSize.width * 0.833), (int) (screenSize.height * 0.833), Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }

    //method for playing the song in the song select menu
    public void playSong() {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        AdvancedPlayer player;
        try {
            fileInputStream = new FileInputStream("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/Song.mp3");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new AdvancedPlayer(bufferedInputStream);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    //plays song on separate thread to maintain gui functionality
    public void start() {
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
    public String[] getSongInformation() {
        String[] info = new String[3];
        File file = new File("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/SongInfo.txt");
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
    public String getCurrentSong(int e) {
        String name;
        File songFolder = new File("src/main/Assets/Songs/");
        File[] listOfFiles = songFolder.listFiles();

        name = listOfFiles[directoryPos].getName();
        return name;
    }

    public JList<String> getScores() {
        ArrayList<String> scores = new ArrayList<>();
        BufferedReader reader;
        File file = new File("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/Scores.txt");
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                scores.add(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JList<String> highScores = new JList<String>(scores.toArray(new String[scores.size()]));
        return highScores;
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
            //updates directory position and updates the menu
            if (event.getSource() == previousButton) {
                if (directoryPos > 0) {
                    directoryPos -= 1;
                    getCurrentSong(directoryPos);
                    changeMenu("Song Select");
                }
            }
            if (event.getSource() == nextButton) {
                //updates directory position and updates the menu
                if (directoryPos < new File("src/main/Assets/Songs/").list().length) {
                    directoryPos += 1;
                    getCurrentSong(directoryPos);
                    changeMenu("Song Select");
                }
            }
            if (event.getSource() == easyButton) {

            }
            if (event.getSource() == normalButton) {
                try {
                    new Graphics("Pandora Palace");
                    start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (event.getSource() == hardButton) {
            }
            if (event.getSource() == back){
                changeMenu("Main Menu");
            }
        }
    }
}