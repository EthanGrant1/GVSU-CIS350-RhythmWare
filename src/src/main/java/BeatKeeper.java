/****************************************************************************************************
 * This class is interpreted from DynamicBeat's Beat.java which can be found here:
 * https://github.com/JiwooL0920/DynamicBeat/blob/master/Dynamic%20Beat/src/dynamic_beat_17/Note.java
 ***************************************************************************************************/

public class BeatKeeper {
    // Time in which the block will be played
    private int time;
    // The lane in which the block will occupy
    private String blockType;

    // A basic constructor
    public BeatKeeper(int time, String blockType) {
        super();
        this.time = time;
        this.blockType = blockType;
    }

    // Various getters and setters
    public int getTime() { return time; }

    public void setTime(int time) { this.time = time; }

    public String getBlockType() { return blockType; }

    public void setBlockType(String blockType) { this.blockType = blockType; } }