import java.io.*;
import java.util.Random;

/****************************************************************************************************
 * This class is interpreted from DynamicBeat's Note.java which can be found here:
 * https://github.com/JiwooL0920/DynamicBeat/blob/master/Dynamic%20Beat/src/dynamic_beat_17/Note.java
 ***************************************************************************************************/

public class Block {

    // The values for the position of the blocks
    private int x;
    private int y;

    // How fast the block moves down the screen
    public int blockSpeed;

    // Whether or not the block is currently "in play" or not
    private boolean activeBlock;

    // What lane the block occupies
    private String blockType;

    private float time;

    // The block is no longer in play
    public void deactivate() { activeBlock = false; }

    /*********************************
     * Constructor for Block
     *
     * @param blockType is the lane
     * in which the block will occupy.
     **********************************/
    public Block(String blockType) {
        // Constants for lane x-axis positions
        int redBlockPosX = 545;
        int greenBlockPosX = 655;
        int yellowBlockPosX = 765;
        int blueBlockPosX = 875;

        activeBlock = true;

        /* Determine what lane the block occupies,
           and set the x value to the constant position */
        switch (blockType) {
            case "red":
                this.x = redBlockPosX;
                break;
            case "green":
                this.x = greenBlockPosX;
                break;
            case "yellow":
                this.x = yellowBlockPosX;
                break;
            case "blue":
                this.x = blueBlockPosX;
                break;
            default:
                this.x = 0;
                break; }
        this.blockType = blockType;
        this.y = 0;
        this.time = 0f;
    }

    // Various getters and setters
    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getBlockSpeed() { return blockSpeed; }

    public void setBlockSpeed(int blockSpeed) { this.blockSpeed = blockSpeed; }

    public boolean isActiveBlock() { return activeBlock; }

    public void setActiveNote(boolean activeBlock) { this.activeBlock = activeBlock; }

    public String getBlockType() { return blockType; }

    public void setBlockType(String blockType) { this.blockType = blockType; }

    public float getTime() { return time; }

    public void setTime(float time) { this.time = time; }

    // Hardcoded chart for Pandora Palace
    public static Block[] makePandora() throws IOException {
        // Initialize the size of the chart
        Block[] pandora = new Block[525];

        /* Chart was timed in 30FPS, so we need
           to multiply the frames by 2 */
        float offset = 1f/60f;

        // Read the note times from a file
        File f = new File("src/main/Assets/Songs/Pandora Palace/NoteTimes.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));

        // Randomly assign lanes
        Random rand = new Random();
        for (int i = 0; i < pandora.length-1; i++) {
            int randInt = rand.nextInt(4);
            switch (randInt) {
                case 0:
                    pandora[i] = new Block("red");
                    break;

                case 1:
                    pandora[i] = new Block("green");
                    break;

                case 2:
                    pandora[i] = new Block("yellow");
                    break;

                case 3:
                    pandora[i] = new Block("blue");
                    break;
            }

            // Blocks move 15 pixels / frame
            pandora[i].setBlockSpeed(15);
        }

        String read;
        int j = 0;
        while ((read = b.readLine()) != null && j < pandora.length-1) {
            int frame = Integer.parseInt(read)*2;
            pandora[j].setTime((float)frame * offset);
            j++;
        }
        return pandora;
    }
}
