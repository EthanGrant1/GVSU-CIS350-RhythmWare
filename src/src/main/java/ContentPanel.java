import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/*****************************************
 * The main panel for the GUI.
 *****************************************/
public class ContentPanel extends JPanel {
    // Int for tracking which song to display
    int directoryPos = 0;

    // Buttons within the GUI
    // Main Menu
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

    // Size of screen for proper scaling
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Generates different GUI based on which menu is chosen
    public void addContentPanel(String menu) {

        // We will be using the BorderLayout for the GUI
        setLayout(new BorderLayout());

        // Change the menu to the main menu
        if (menu.equals("Main Menu")) {

            // Create a sub panel with BorderLayout
            JPanel subPanel = new JPanel();
            subPanel.setLayout(new BorderLayout());

            // Add the game title
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

            // Adding sub panel to center of main menu panel
            add(subPanel, BorderLayout.CENTER);

            // Register the action listeners
            playButton.addActionListener(new ButtonListener());
            optionButton.addActionListener(new ButtonListener());
            quitButton.addActionListener(new ButtonListener()); }

        // Change the menu to the song select menu
        if (menu.equals("Song Select")) {

            // Previous and next buttons to choose a song
            previousButton = new JButton("<");
            add(previousButton, BorderLayout.WEST);

            nextButton = new JButton(">");
            add(nextButton, BorderLayout.EAST);

            // Create a sub panel for the difficulties
            difficultyPanel = new JPanel();

            // Easy button
            easyButton = new JButton("Easy");
            easyButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(easyButton, BorderLayout.WEST);

            // Normal button
            normalButton = new JButton("Normal");
            normalButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(normalButton, BorderLayout.CENTER);

            // Hard button
            hardButton = new JButton("Hard");
            hardButton.setPreferredSize(new Dimension(600, 25));
            difficultyPanel.add(hardButton, BorderLayout.EAST);

            // Panel that contain BG and song information
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(BorderFactory.createEtchedBorder());
            panel.add(getBG(), BorderLayout.EAST);

            // Adding song title and artist to screen
            title = new JLabel(getSongInformation()[1] + " - " + getSongInformation()[0]);
            title.setFont(new Font("Verdana", Font.BOLD, 40));
            add(title, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            panel.add(difficultyPanel, BorderLayout.SOUTH);

            // Panel that displays scores
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
            hardButton.addActionListener(new ButtonListener()); }

        // Change the menu to the Options menu
        if (menu.equals("Options")) {

            /* The options menu contains options to change
               keybindings, but the are not functional because of
               the class structure within KeyListener */
            options = new JLabel("Keybindings");
            options.setPreferredSize(new Dimension(370, 200));
            options.setFont(new Font("Arial", Font.BOLD, 40));

            // Key 1: W Key
            key1 = new JTextField("W");
            TitledBorder key1Border = BorderFactory.createTitledBorder("Key 1");
            key1.setBorder(key1Border);
            key1.setPreferredSize(new Dimension(60, 40));

            // Key 2: E Key
            key2 = new JTextField("E");
            TitledBorder key2Border = BorderFactory.createTitledBorder("Key 2");
            key2.setBorder(key2Border);
            key2.setPreferredSize(new Dimension(60, 40));

            // Key 3: I Key
            key3 = new JTextField("I");
            TitledBorder key3Border = BorderFactory.createTitledBorder("Key 3");
            key3.setBorder(key3Border);
            key3.setPreferredSize(new Dimension(60, 40));

            // Key 4: O Key
            key4 = new JTextField("O");
            TitledBorder key4Border = BorderFactory.createTitledBorder("Key 4");
            key4.setBorder(key4Border);
            key4.setPreferredSize(new Dimension(60, 40));

            // Create a panel with BorderLayout to place the options
            optionsPanel = new JPanel();
            optionsPanel.setLayout(new BorderLayout());
            add(options, BorderLayout.NORTH);

            // Add 3 keys to the options panel
            optionsPanel.add(key1, BorderLayout.NORTH);
            optionsPanel.add(key2, BorderLayout.CENTER);
            optionsPanel.add(key3, BorderLayout.SOUTH);

            // Add an extra panel because BorderLayout is 3x3
            JPanel extra = new JPanel();
            extra.setLayout(new BorderLayout());

            // Add the final key to the extra panel
            extra.add(key4, BorderLayout.CENTER);
            add(extra, BorderLayout.SOUTH);

            // Add a back button to return to main menu
            back = new JButton("Back");
            extra.add(back, BorderLayout.SOUTH);

            // Add the options panel
            add(optionsPanel, BorderLayout.CENTER);

            // Register an action listener to the back button
            back.addActionListener(new ButtonListener()); } }

    // Method for switching between menus
    public void changeMenu(String e) {
        // Remove all previous menu parts and create new panel
        this.removeAll();
        this.addContentPanel(e);
        repaint();
        revalidate(); }

    // Method for getting menu background for the song
    public JLabel getBG() {

        // Use image buffering to display the image
        BufferedImage background = null;

        // Try getting the image from the Songs directory
        try {
            background = ImageIO.read(new File("src/main/Assets/Songs/" +
                                                         getCurrentSong(directoryPos) + "/BG.jpg")); }

        // Otherwise, throw an error
        catch (IOException e) { e.printStackTrace(); }

        // Scale the image to a good size
        assert background != null;
        Image scaled = background.getScaledInstance((int) (screenSize.width * 0.833),
                                                    (int) (screenSize.height * 0.833), Image.SCALE_SMOOTH);

        // The image is returned as a JLabel
        return new JLabel(new ImageIcon(scaled)); }

    // Method for playing the song in the song select menu
    public void playSong() {

        // Stream the music
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream;
        AdvancedPlayer player;

        // Try getting the MP3 from the Songs folder and play it
        try {
            fileInputStream = new FileInputStream("src/main/Assets/Songs/"
                                                  + getCurrentSong(directoryPos) + "/Song.mp3");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new AdvancedPlayer(bufferedInputStream);
            player.play(); }

        // Otherwise, throw an error
        catch (JavaLayerException | IOException e) {
            e.printStackTrace(); } }

    // Plays song on separate thread to maintain GUI functionality
    public void start() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                playSong();
                return null; } };

        worker.execute(); }

    // Reads SongInfo file
    public String[] getSongInformation() {

        // There are 3 pieces of song information
        String[] info = new String[3];

        // Try getting the file from the Songs directory
        File file = new File("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/SongInfo.txt");
        Scanner scanner = null;

        try { scanner = new Scanner(file); }

        // Otherwise, throw an error
        catch (FileNotFoundException e) { e.printStackTrace(); }

        // Line number from the text file
        int lineNum = 0;

        // Scan the text file
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine();
            lineNum++;

            switch (lineNum) {
                case 1:
                    info[0] = line;
                    break;
                case 2:
                    info[1] = line;
                case 3:
                    info[2] = line; } }

        return info; }

    // Gets the name of the song in directory
    public String getCurrentSong(int directoryPos) {
        String name;
        File songFolder = new File("src/main/Assets/Songs/");
        File[] listOfFiles = songFolder.listFiles();

        assert listOfFiles != null;
        name = listOfFiles[directoryPos].getName();
        return name; }

    // Get the player's scores
    public JList<String> getScores() {

        // Try to read in the scores file
        ArrayList<String> scores = new ArrayList<>();
        BufferedReader reader;
        File file = new File("src/main/Assets/Songs/" + getCurrentSong(directoryPos) + "/Scores.txt");
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                scores.add(line);
                // read next line
                line = reader.readLine(); }

            reader.close(); }

        // Otherwise, throw an error
        catch (IOException e) { e.printStackTrace(); }

        // Return the list of high scores
        JList<String> highScores = new JList<>(scores.toArray(new String[scores.size()]));
        return highScores; }

    // Action listener for all of the GUI buttons
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // MAIN MENU BUTTONS

            // Play button. Go to the song select menu.
            if (event.getSource() == playButton) { changeMenu("Song Select"); }

            // Options button. Go to the options menu.
            if (event.getSource() == optionButton) { changeMenu("Options"); }

            // Quit button. Exit the game.
            if (event.getSource() == quitButton) { System.exit(1); }

            // SONG SELECTION MENU BUTTONS
            // Updates directory position and updates the menu
            if (event.getSource() == previousButton) {
                if (directoryPos > 0) {
                    directoryPos -= 1;
                    getCurrentSong(directoryPos);
                    changeMenu("Song Select"); } }

            if (event.getSource() == nextButton) {
                //updates directory position and updates the menu
                if (directoryPos < Objects.requireNonNull(new File("src/main/Assets/Songs/").list()).length) {
                    directoryPos += 1;
                    getCurrentSong(directoryPos);
                    changeMenu("Song Select"); } }

            if (event.getSource() == easyButton) {
                // Not functional
            }

            // Normal button. Play the chart.
            if (event.getSource() == normalButton) {

                // Try to create a Graphics instance and start the music
                try {
                    new Graphics("Pandora Palace");
                    start(); }

                // Otherwise, throw an error
                catch (IOException e) { e.printStackTrace(); } }

            if (event.getSource() == hardButton) {
                // Not functional
            }

            // Back button. Go back to the main menu.
            if (event.getSource() == back){ changeMenu("Main Menu"); } } } }