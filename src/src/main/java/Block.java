import java.awt.*;
import javax.swing.*;

/****************************************************************************************************
 * This class is interpreted from DynamicBeat's Note.java which can be found here:
 * https://github.com/JiwooL0920/DynamicBeat/blob/master/Dynamic%20Beat/src/dynamic_beat_17/Note.java
 ***************************************************************************************************/

public class Block extends Thread {

    private int x = 0;
    private int y = 0;
    public int noteSpeed;

    private boolean activeNote = true;

    private String noteType;

    public String getNoteType() { return noteType; }

    public boolean isActiveNote() { return activeNote; }

    public void deactivate() { activeNote = false; }

    public Block(String noteType) {
        int redBlockPosX = 760;
        int greenBlockPosX = 860;
        int yellowBlockPosX = 960;
        int blueBlockPosX = 1060;

        switch (noteType) {
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
        this.noteType = noteType; }

    public void drawNote(Graphics2D graphics2D) {
        graphics2D.fillRect(x, y, 100,100);
    }

    public void moveBlock() {
        y += getNoteSpeed();

        if (y > 1080) {
            deactivate();
        }
    }

    @Override
    public void run() { // Running the Thread
        try {
            while (true) {
                moveBlock();
                if (activeNote) { Thread.sleep(16); }

                else {
                    interrupt();
                    break;
                } } }

        catch(Exception e) { System.err.println(e.getMessage()); } }

    public int getNoteSpeed() {
        return noteSpeed;
    }

    public void setNoteSpeed(int noteSpeed) {
        this.noteSpeed = noteSpeed;
    }

    public int getY() {
        return y;
    }
}
