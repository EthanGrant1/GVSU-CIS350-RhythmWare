import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math.*;


/**************************************************************************
 * Class is currently in testing phases. Blocks will render to the screen,
 * but are not continuous, nor do they render at proper y positions due to
 * moving the intended first position.
 *
 * TODO:
 *  Dynamically create timing for every block so that they can be rendered
 *  in different time signatures (4th, 8th, 16th, etc).
 **************************************************************************/
public class Graphics extends JFrame {

    // An array list to keep track of the active blocks
    ArrayList<Block> blockArrayList = new ArrayList<>();

    // An arbitrary size for our block count
    int size = 1000;

    // An array of BeatKeepers which holds information about the blocks
    BeatKeeper[] beats = new BeatKeeper[size];

    // How graphics will be drawn to the screen
    Draw d = new Draw();

    // Variable to keep track of index in the array
    int current_i = 0;

    // y-values of active blocks
    // ArrayList<Integer> y_val = new ArrayList<>;

    // Float to keep track of the current time
    float time = 0f;

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

            // Assign block to red lane
            if (randInt == 0) {
                beats[i] = new BeatKeeper(offset * i, "red"); }

            // Assign block to green lane
            if (randInt == 1) {
                beats[i] = new BeatKeeper(offset * i, "green"); }

            // Assign block to yellow lane
            if (randInt == 2) {
                beats[i] = new BeatKeeper(offset * i, "yellow"); }

            // Assign block to blue lane
            if (randInt == 3) {
                beats[i] = new BeatKeeper(offset * i, "blue"); } }
    }

    // A prototype of the graphical rendering
    public Graphics() {
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

                time += 0.01666666666f;

                // For all active blocks' y-values...
                for (int i = 0; i < blockArrayList.size(); i++) {
                    /* Remove them from the list if they are
                       past the screen. */
                    if (blockArrayList.get(i).getY() > 820) {
                        blockArrayList.remove(blockArrayList.get(i));

                        // Reset the combo counter
                        game.setCombo(0);

                        // Ensure that there is not an IndexOutOfBounds error
                        i++; }

                    // Else, move it a set amount down the screen
                    else {
                        blockArrayList.get(i).setY(blockArrayList.get(i).getY() + 3);
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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true); }

    /**********************************************
     * This main class is just used for testing the
     * graphics.
     **********************************************/
    public static void main (String [] args) {
        Graphics g = new Graphics();
        g.makeRandomBeatKeeper();
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
         */
        public void paint(java.awt.Graphics g) {
            super.paintComponents(g);

            // Render a black background
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1920, 1080);

            // Draw lanes with 110 pixel gaps so blocks aren't flush
            g.setColor(Color.WHITE);
            g.drawLine(540, 0, 540, 1080);
            g.drawLine(650, 0, 650, 1080);
            g.drawLine(760, 0, 760, 1080);
            g.drawLine(870, 0, 870, 1080);
            g.drawLine(980, 0, 980, 1080);

            // Constants for block x-positions
            int redBlockPosX = 545;
            int greenBlockPosX = 655;
            int yellowBlockPosX = 765;
            int blueBlockPosX = 875;

            // Constant for goal y-position
            int goalPosY = 720;

            // If there are blocks still in the list
            if (current_i < beats.length) {
                // Make a new instance of a block
                Block b = new Block(beats[current_i].getBlockType());

                // If the current block's time is equal to real time
                if (beats[current_i].getTime() == (int)time) {
                    // Add it to the currently active blocks
                    blockArrayList.add(b);

                    // Add 1 to current_i
                    current_i++;
                }
            }

            // Else, blocks have finished falling and we can now exit the game.
            else { System.exit(0); }

            // The block that is going to be considered for judgement
            Block candidateBlock = new Block("red");

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

                if ((block.getY() > (goalPosY - 99)) && (block.getY() < (goalPosY + 99))) {
                    if (block.getY() > candidateBlock.getY()) {
                        candidateBlock = block;
                    }
                }
            }

            /* Dynamically draws the goal points such that they dynamically
                fill when the keys are pressed. This gives visual
                feedback to the player. Also draws game variables like
                score and combo to the screen.

               Determine the candidateBlock's position for judgement.
               Uses the absolute value of the blocks position relative
               to the goal point (at 720 pixels y-position) to determine
               the accuracy level. For judgements that are too early, the
               block's y-position will be within 620-719 pixels. For
               judgements that are too late the block's y-position will
               be within 721-820 pixels. The judgement areas are subdivided
               into 5 categories, making each zone 20 pixels -- 10 for
               early and 10 for late either side. The only exception is
               Perfect, which is 1 pixel.
             */

            if (candidateBlock.getY() == 720) {
                game.setJudgement("Perfect!!!");
            }

            else if (Math.abs(candidateBlock.getY() - 720) <= 10) {
                game.setJudgement("Excellent");
            }


            else if (Math.abs(candidateBlock.getY() - 720) <= 20) {
                game.setJudgement("Great");
            }


            else if (Math.abs(candidateBlock.getY() - 720) <= 30) {
                game.setJudgement("Good");
            }

            else if (Math.abs(candidateBlock.getY() - 720) <= 40) {
                game.setJudgement("OK");
            }

            else if (Math.abs(candidateBlock.getY() - 720) <= 50) {
                game.setJudgement("Bad");
            }

            g.setColor(Color.WHITE);

            // W is not pressed
            if (!game.iswPressed()) {
                g.drawRect(redBlockPosX, goalPosY,100,100);
            }

            // W is pressed
            else if (game.iswPressed()) {
                // Fill the goal point
                g.fillRect(redBlockPosX, goalPosY,100,100);

                // Check the color of the first block in the list
                if (candidateBlock.getBlockType().equals("red")) {
                    //Remove the block
                    blockArrayList.remove(candidateBlock);

                    // Add one to the score and combo
                    game.setScore(game.getScore() + 1);
                    game.setCombo(game.getCombo() + 1);
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
                    //Remove the block
                    blockArrayList.remove(candidateBlock);

                    // Add one to the score and combo
                    game.setScore(game.getScore() + 1);
                    game.setCombo(game.getCombo() + 1);
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
                    //Remove the block
                    blockArrayList.remove(candidateBlock);

                    // Add one to the score and combo
                    game.setScore(game.getScore() + 1);
                    game.setCombo(game.getCombo() + 1);
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
                    //Remove the block
                    blockArrayList.remove(candidateBlock);

                    // Add one to the score and combo
                    game.setScore(game.getScore() + 1);
                    game.setCombo(game.getCombo() + 1);
                }
            }


            /* TODO: Currently the values update every frame to match the
                current position / times the button was pressed and not
                when the button was pressed *successfully*. The values instead
                need to be consistent with the last successful judgement and
                score. */

            // Set the font and the font size
            g.setFont(new Font ("TimesRoman", Font.BOLD, 14));
            // Draw combo and last judgement to the screen
            g.drawString("Combo: " + game.getCombo(), 1080, 450);
            g.drawString("Last Judgment: " + game.getJudgement(), 1080, 500);
        }

        // Default dimension is 1920x1080
        public Dimension getPreferredSize() {
            return new Dimension(1920, 1080); } } }