


/***********************************
 * Game logic (score, combo, button
 * presses, etc) are found here.
 ***********************************/
public class Game {

    // The total score
    int score;

    // The current combo
    int combo;

    // Accuracy judgement
    String judgement;

    // Boolean values to keep track of key presses

    /* TODO: Allow for the user to change keys to
        whatever keys they want via a setting. */
    boolean wPressed;
    boolean ePressed;
    boolean oPressed;
    boolean pPressed;

    public Game() {
        score = 0;
        combo = 0;
        wPressed = false;
        ePressed = false;
        oPressed = false;
        pPressed = false;
        judgement = "Bad";
    }

    /* Change the boolean value based on whether or not
       the key is pressed or released. Allows for dynamic
       actions to occur on press and release. */
    public void pressW() {
        wPressed = true;
    }

    public void releaseW() {
        wPressed = false;
    }

    public void pressE() {
        ePressed = true;
    }

    public void releaseE() {
        ePressed = false;
    }

    public void pressO() {
        oPressed = true;
    }

    public void releaseO() {
        oPressed = false;
    }

    public void pressP() {
        pPressed = true;
    }

    public void releaseP() {
        pPressed = false;
    }

    // Various getters and setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCombo() { return combo; }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public boolean iswPressed() {
        return wPressed;
    }

    public boolean isePressed() {
        return ePressed;
    }

    public boolean isoPressed() {
        return oPressed;
    }

    public boolean ispPressed() {
        return pPressed;
    }
}
