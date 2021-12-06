import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

/**************************************************************************
 * Graphics class that renders objects to the screen.
 **************************************************************************/
public class Graphics extends JFrame {

    // An array list to keep track of the active blocks
    ArrayList<Block> blockArrayList = new ArrayList<>();

    // An arbitrary size for our block count
    int size = 1000;

    // An array of BeatKeepers which holds information about the blocks
    Block[] beats;

    // How graphics will be drawn to the screen
    Draw d = new Draw();

    // Variable to keep track of index in the array
    int current_i = 0;

    // y-values of active blocks
    // ArrayList<Integer> y_val = new ArrayList<>;

    // Float to keep track of the current time
    float time = 0f;

    // The judgement for the timing of a user's key press
    String judgement = "";

    // A constant value for perfect score
    double perfect;

    // How we track scores
    File f = new File("src/main/Assets/Songs/Pandora Palace/Scores.txt");
    FileWriter fw;
    BufferedWriter buff;
    boolean isScoreWritten = false;

    // How we will keep track of all game variables
    public static Game game = new Game();

    public void makeRandomBeatKeeper() {
        // Offset in seconds.
        int offset = 1;

        // Randomly assign a block to a lane
        Random rand = new Random();
        for (int i = 0; i < beats.length; i++) {
            // Random number between 0 and 3 inclusive
            int randInt = rand.nextInt(4);

            switch (randInt) {
                case 0:
                    beats[i] = new Block("red");
                    break;

                case 1:
                    beats[i] = new Block("green");
                    break;

                case 2:
                    beats[i] = new Block("yellow");
                    break;

                case 3:
                    beats[i] = new Block("blue");
                    break;
            }
            // Time to spawn the block
            beats[i].setTime(offset*i);

            // Amount of pixels that the blocks are going to move per frame.
            beats[i].setBlockSpeed(15);
        }
    }

    // A prototype of the graphical rendering
    public Graphics(String songToUse) throws IOException {

        switch (songToUse) {
            case "random":
                beats = new Block[size];
                this.makeRandomBeatKeeper();
                break;

            case "Pandora Palace":
                beats = Block.makePandora();
                perfect = beats.length * game.getPERFECT_SCORE();
                break;

            // For testing
            case "empty":
                beats = new Block[0];
                break;

            default:
                System.out.println("Not a valid song.");
                System.exit(1);
        }

        /* An ActionListener which is responsible for
           event handling and timing. Will be used by
           the Timer class. */
        ActionListener listen = new AbstractAction() {
            /**********************************************
             * What gets performed during every Timer tick.
             *
             * @param e is what event is currently being
             * executed. Goes unused in this context
             **********************************************/
            @Override
            public void actionPerformed(ActionEvent e) {

                time += (1f/60f);

                // For all active blocks' y-values...
                for (int i = 0; i < blockArrayList.size(); i++) {

                    Block temp = blockArrayList.get(i);

                    /* Remove them from the list if they are
                       past the screen. */
                    if (temp.getY() > 840) {
                        blockArrayList.remove(temp);

                        // Reset the combo counter
                        game.setCombo(0);

                        // Ensure that there is not an IndexOutOfBounds error
                        i++; }

                    // Else, move it a set amount down the screen
                    else {
                        temp.setY(temp.getY() + temp.getBlockSpeed());
                    } }

                // Redraw the graphics with updated positions
                d.repaint(); } };

        // Timer to keep track of event handling. Utilizes our ActionListener.
        Timer timer = new Timer(16, listen);
        timer.start();

        // Add the Draw to the JFrame
        add(d);

        // Add the key listener
        addKeyListener(new KeyListener());

        // Window is 1920x1080
        setSize(d.getPreferredSize());

        // Setting defaults and displaying the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true); }

    /**********************************************
     * This main class is just used for testing the
     * graphics.
     **********************************************/
    public static void main (String [] args) throws IOException {
        new Graphics("empty");
    }

    /********************************************
     * How we draw the graphics to the screen. It
     * is a JPanel that can be added to a JFrame,
     * so that it can be displayed.
     ********************************************/
    private class Draw extends JPanel {

        /******************************************
         * paint() is invoked automatically whenever
         * an instance of the Draw() class is
         * displayed to the screen.
         *
         * @param g is the Graphics instance that is
         * called whenever the Container which holds
         * it is rendered to the screen.
         ******************************************/
        public void paint(java.awt.Graphics g) {
            super.paintComponents(g);

            // If there are blocks still in the list
            if (current_i < beats.length) {
                // Render a black background
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, (int)d.getPreferredSize().getWidth(), (int)d.getPreferredSize().getHeight());

                // Draw lanes with 110 pixel gaps so blocks aren't flush
                g.setColor(Color.WHITE);
                g.drawLine(540, 0, 540, (int)d.getPreferredSize().getHeight());
                g.drawLine(650, 0, 650, (int)d.getPreferredSize().getHeight());
                g.drawLine(760, 0, 760, (int)d.getPreferredSize().getHeight());
                g.drawLine(870, 0, 870, (int)d.getPreferredSize().getHeight());
                g.drawLine(980, 0, 980, (int)d.getPreferredSize().getHeight());

                // Constants for block x-positions
                int redBlockPosX = 545;
                int greenBlockPosX = 655;
                int yellowBlockPosX = 765;
                int blueBlockPosX = 875;

                // Constant for goal y-position
                int goalPosY = 720;

                // Make a new instance of a block
                Block b = beats[current_i];

                // If the current block's time is approximates real time
                if (Math.abs(beats[current_i].getTime() - time) < 0.001f) {
                    // Add it to the currently active blocks
                    blockArrayList.add(b);

                    // Add 1 to current_i
                    current_i++;
                }
                // The block that is going to be considered for judgement
                Block candidateBlock = new Block("");

                // For all currently active blocks...
                for (Block block : blockArrayList) {

                    // Set the appropriate color
                    switch (block.getBlockType()) {
                        case "red":
                            g.setColor(Color.RED);
                            break;
                        case "green":
                            g.setColor(Color.GREEN);
                            break;
                        case "yellow":
                            g.setColor(Color.YELLOW);
                            break;
                        case "blue":
                            g.setColor(Color.BLUE);
                            break;
                    }

                    // Draw the block at the intended position
                    g.fillRect(block.getX(), block.getY(), 100, 100);

                    if ((block.getY() > (goalPosY - 100)) && (block.getY() < (goalPosY + 100))) {
                        if (block.getY() > candidateBlock.getY()) {
                            candidateBlock = block;
                        }
                    }
                }

                /* Dynamically draws the goal points such that they dynamically
                fill when the keys are pressed. This gives visual
                feedback to the player. Also draws game variables like
                score and combo to the screen.
                */

                g.setColor(Color.WHITE);

                // W is not pressed
                if (!game.iswPressed()) {
                    g.drawRect(redBlockPosX, goalPosY,100,100);
                }

                // W is pressed
                else if (game.iswPressed()) {
                    g.fillRect(redBlockPosX, goalPosY,100,100);
                    // Check the color of the first block in the list
                    if (candidateBlock.getBlockType().equals("red")) {
                        Graphics.this.remove(candidateBlock);
                    }
                }

                // E is not pressed
                if (!game.isePressed()) {
                    g.drawRect(greenBlockPosX, goalPosY,100,100);
                }

                // E is pressed
                else if (game.isePressed()) {
                    g.fillRect(greenBlockPosX, goalPosY,100,100);
                    // Check the color of the first block in the list
                    if (candidateBlock.getBlockType().equals("green")) {
                        Graphics.this.remove(candidateBlock);
                    }
                }

                // O is not pressed
                if (!game.isoPressed()) {
                    g.drawRect(yellowBlockPosX, goalPosY,100,100);
                }

                // O is pressed
                else if (game.isoPressed()) {
                    g.fillRect(yellowBlockPosX, goalPosY,100,100);
                    // Check the color of the first block in the list
                    if (candidateBlock.getBlockType().equals("yellow")) {
                        Graphics.this.remove(candidateBlock);
                    }
                }

                // P is not pressed
                if (!game.ispPressed()) {
                    g.drawRect(blueBlockPosX, goalPosY,100,100);
                }

                // P is pressed
                else if (game.ispPressed()) {
                    g.fillRect(blueBlockPosX, goalPosY,100,100);
                    // Check the color of the first block in the list
                    if (candidateBlock.getBlockType().equals("blue")) {
                        Graphics.this.remove(candidateBlock);
                    }
                }

                // Esc is pressed. Close the window.
                if (game.isEscPressed()) {
                    Graphics.this.dispatchEvent(new WindowEvent(Graphics.this, WindowEvent.WINDOW_CLOSING));
                }

                // Set the font and the font size
                g.setFont(new Font ("TimesRoman", Font.BOLD, 14));
                // Draw score values to the screen
                g.drawString("Score: " + (game.getScore() / perfect)*100 + "%", 1080, 200);
                g.drawString("Combo: " + game.getCombo(), 1080, 250);
                g.drawString("Last Judgment: " + judgement, 1080, 300);
                g.drawString("Number of Perfects: " + game.getNumPerfects(), 1080, 350);
                g.drawString("Number of Excellents: " + game.getNumExcellents(), 1080, 400);
                g.drawString("Number of Greats: " + game.getNumGreats(), 1080, 450);
                g.drawString("Number of Goods: " + game.getNumGoods(), 1080, 500);
                g.drawString("Number of OKs: " + game.getNumOKs(), 1080, 550);
                g.drawString("Number of Bads: " + game.getNumBads(), 1080, 600);
            }

            // Else, blocks have finished falling Display the end screen.
            else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 1920, 1080);

                // Set the font and the font size
                g.setColor(Color.WHITE);
                g.setFont(new Font ("TimesRoman", Font.BOLD, 40));
                g.drawString("Congrats!!! Here are your results...", 470, 200);
                g.drawString("------------------------------------------------", 470, 220);

                g.setFont(new Font ("TimesRoman", Font.BOLD, 25));
                // Draw score values to the screen
                g.drawString("Score: " + (game.getScore() / perfect)*100 + "%", 650, 250);
                g.drawString("Best Combo: " + game.getBest_combo(), 650, 300);
                g.drawString("Number of Perfects: " + game.getNumPerfects(), 650, 350);
                g.drawString("Number of Excellents: " + game.getNumExcellents(), 650, 400);
                g.drawString("Number of Greats: " + game.getNumGreats(), 650, 450);
                g.drawString("Number of Goods: " + game.getNumGoods(), 650, 500);
                g.drawString("Number of OKs: " + game.getNumOKs(), 650, 550);
                g.drawString("Number of Bads: " + game.getNumBads(), 650, 600);

                if (!isScoreWritten) {
                    try {
                        writeScores();
                        closeWriter();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    isScoreWritten = true;
                }
            }
        }

        // Default dimension is 1920x1080
        public Dimension getPreferredSize() {
            return Toolkit.getDefaultToolkit().getScreenSize();
        } }

    /***************************************************************
     * Determine the candidateBlock's position for judgement.
     * Uses the absolute value of the blocks position relative
     * to the goal point (at 720 pixels y-position) to determine
     * the accuracy level. For judgements that are too early, the
     * block's y-position will be within 620-719 pixels. For
     * judgements that are too late the block's y-position will
     * be within 721-820 pixels. The judgement areas are subdivided
     * into 5 categories, making each zone 20 pixels -- 10 for
     * early and 10 for late either side. The only exception is
     * Perfect, which is 1 pixel.
     *
     * @param candidateBlock is the block that is being judged.
     * @return is the String representation of the judgement.
     ***************************************************************/
    public String judge(Block candidateBlock) {

        int abs = Math.abs(candidateBlock.getY() - 720);

        if (candidateBlock.getY() == 720) {
            game.setNumPerfects(game.getNumPerfects() + 1);
            game.setScore(game.getScore() + game.getPERFECT_SCORE());
            return "Perfect!!!";
        }

        else if (abs <= 20) {
            game.setNumExcellents(game.getNumExcellents() + 1);
            game.setScore(game.getScore() + game.getEXCELLENT_SCORE());
            return "Excellent";
        }

        else if (abs <= 40) {
            game.setNumGreats(game.getNumGreats() + 1);
            game.setScore(game.getScore() + game.getGREAT_SCORE());
            return "Great";
        }

        else if (abs <= 60) {
            game.setNumGoods(game.getNumGoods() + 1);
            game.setScore(game.getScore() + game.getGOOD_SCORE());
            return "Good";
        }

        else if (abs <= 80) {
            game.setNumOKs(game.getNumOKs() + 1);
            game.setScore(game.getScore() + game.getOK_SCORE());
            return "OK";
        }

        else if (abs <= 100) {
            game.setNumBads(game.getNumBads() + 1);
            game.setScore(game.getScore() + game.getBAD_SCORE());
            return "Bad";
        }

        else { return "Error"; } }

    /* Removes a block from play, given a candidateBlock
       chosen from the blockArrayList */
    public void remove (Block candidateBlock) {
        // Judge and remove the block
        judgement = judge(candidateBlock);
        blockArrayList.remove(candidateBlock);

        // Add one to the current combo
        game.setCombo(game.getCombo() + 1);

        // Set the best combo so far
        if (game.best_combo < game.getCombo()) {
            game.setBest_combo(game.getCombo());
        }
    }

    public void writeScores() throws IOException {

        if (f.createNewFile()) {
            fw = new FileWriter(f);
        }
        else {
            fw = new FileWriter(f, true);
        }

        buff = new BufferedWriter(fw);
        buff.write("Player High Score - " + game.getScore() + "\n");
        buff.flush();

        fw.close();
    }

    public void closeWriter() throws IOException {
        buff.close();
    }

}