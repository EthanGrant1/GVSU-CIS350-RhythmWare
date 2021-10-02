/****************************************************************************************************
 * This class is interpreted from DynamicBeat's Beat.java which can be found here:
 * https://github.com/JiwooL0920/DynamicBeat/blob/master/Dynamic%20Beat/src/dynamic_beat_17/Note.java
 ***************************************************************************************************/

public class BeatKeeper {
    private int time;
    private String noteType;

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public String getNoteName() {
        return noteType;
    }
    public void setNoteName(String noteName) {
        this.noteType = noteName;
    }

    //Constructor
    public BeatKeeper(int time, String noteType) {
        super();
        this.time = time;
        this.noteType = noteType;
    }
}
