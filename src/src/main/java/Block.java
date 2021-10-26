import java.awt.*;
import javax.swing.*;

/****************************************************************************************************
 * This class is interpreted from DynamicBeat's Note.java which can be found here:
 * https://github.com/JiwooL0920/DynamicBeat/blob/master/Dynamic%20Beat/src/dynamic_beat_17/Note.java
 ***************************************************************************************************/

public class Block extends Thread {

    // The values for the position of the blocks
    private int x = 0;
    private int y = 0;

    // How fast the block moves down the screen
    public int blockSpeed;

    // Whether or not the block is currently "in play" or not
    private boolean activeBlock = true;

    // What lane the block occupies
    private String blockType;

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

        // Determine what lane the block occupies,
        // and set the x value to the constant position
        switch (blockType) {
            case "red":
                x = redBlockPosX;
                break;
            case "green":
                x = greenBlockPosX;
                break;
            case "yellow":
                x = yellowBlockPosX;
                break;
            case "blue":
                x = blueBlockPosX;
                break; }
        this.blockType = blockType;

        y = 0;
    }

    /******************************************
     * Renders a block to the screen.
     *
     * @param g is the Graphics instance that is
     * called whenever the Container which holds
     * it is rendered to the screen.
     ******************************************/
    public void drawBlock(java.awt.Graphics g) {
        // Blocks are 100x100 pixels
        g.fillRect(x, y, 100, 100); }

    /************************
     * Moves a block down the
     * screen by a set number
     * of pixels.
     ************************/
    public void moveBlock() {
        /* Block speed is the number
           of pixels to move per frame
           cycle. */
        y += getBlockSpeed();

        /* If the block is past the
        screen, remove it from play */
        if (y > 1080) { deactivate(); } }

    /********************************************
     * Running the method from the Thread class.
     ********************************************/
    @Override
    public void run() {
        try {
            while (true) {
                moveBlock();
                if (activeBlock) { Thread.sleep(16); }

                else {
                    interrupt();
                    break; } } }

        catch(Exception e) { System.err.println(e.getMessage()); } }

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

    public void setBlockType(String blockType) { this.blockType = blockType; } }
