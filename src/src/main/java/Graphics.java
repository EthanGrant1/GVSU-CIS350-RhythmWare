import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.Stack;

/**************************************************************************
 * Class is currently in testing phases. Blocks will render to the screen,
 * but are not continuous, nor do they render at proper y positions due to
 * moving the intended first position.
 *
 * TODO: Render blocks continuously
 *
 * TODO: Debug y-positions to determine the cause for unintended movement.
 **************************************************************************/
public class Graphics extends JFrame {

    // An array list to keep track of the active blocks
    // TODO: Maybe use a Stack class?
    Stack<Block> blockStack = new Stack<>();

    // An arbitrary size for our block count
    int size = 1000;

    // An array of BeatKeepers which holds information about the blocks
    BeatKeeper[] beats = new BeatKeeper[size];

    // How graphics will be drawn to the screen
    Draw d = new Draw();

    // Various variables to keep track of coordinates and index in the arrays
    int current_x = 0;
    int current_y = 0;
    int current_i = 0;

    // y-values of active blocks
    // TODO: Maybe use a Stack class?
    Stack<Integer> y_val = new Stack<>();

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

                // For all active blocks' y-values...
                for (int i = 0; i < y_val.size(); i++) {
                    /* Remove them from the list if they are
                       past the screen. */
                    if (y_val.peek() >= 1080) {
                        y_val.pop();
                        // Ensure that there is not an IndexOutOfBounds error
                        i++; }

                    // Else, move it a set amount down the screen
                    else {
                        int temp = y_val.peek() + 1;
                        y_val.pop();
                        y_val.push(temp);
                    } }

                // Redraw the graphics with updated positions
                d.repaint(); } };

        // Timer to keep track of event handling. Utilizes our ActionListener.
        Timer timer = new Timer(16, listen);
        timer.start();

        // Add the Draw to the JFrame
        add(d);

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
       new Graphics();
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

            /* Offset in seconds, currently not in use.

               TODO: Dynamically create timing for every block
               so that they can be rendered in different time
               signatures (4th, 8th, 16th, etc). */
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

            // Constants for block x-positions
            int redBlockPosX = 545;
            int greenBlockPosX = 655;
            int yellowBlockPosX = 765;
            int blueBlockPosX = 875;

            // current_i starts moving through the beat list
            if (current_i < beats.length) {
                /* Get the block type of the current block,
                   set the x-value, and change the color of
                   the Graphics variable to the color of the
                   corresponding lane. */
                switch (beats[current_i].getBlockType()) {
                    case "red":
                        current_x = redBlockPosX;
                        g.setColor(Color.RED);
                        break;
                    case "green":
                        current_x = greenBlockPosX;
                        g.setColor(Color.GREEN);
                        break;
                    case "yellow":
                        current_x = yellowBlockPosX;
                        g.setColor(Color.YELLOW);
                        break;
                    case "blue":
                        current_x = blueBlockPosX;
                        g.setColor(Color.BLUE);
                        break; }

                // Make a new instance of a block
                Block b = new Block(beats[current_i].getBlockType());

                // Add it to the currently active blocks
                blockStack.push(b);

                // Add 1 to current_i
                current_i++; }

            // Else, blocks have finished falling and we can now exit the game.
            else { System.exit(0); }

            // For all currently active blocks...
            for (int j = 0; j < blockStack.size();  j++) {
                // Get their current y-value
                current_y = blockStack.peek().getY();
                // and add it to the y-value stack.
                y_val.add(current_y);

                // Draw the block at the intended position
                g.drawRect(current_x, y_val.get(j), 100, 100);

                // If the y-value is past the screen, remove the block from the stack.
                if (current_y >= 1080) { blockStack.pop(); } } }

        // Default dimension is 1920x1080
        public Dimension getPreferredSize() {
            return new Dimension(1920, 1080); } } }