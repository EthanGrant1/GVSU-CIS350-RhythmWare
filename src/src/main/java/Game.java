/***********************************
 * Game logic (score, combo, button
 * presses, etc) are found here.
 ***********************************/
public class Game {

    // The total score
    double score;

    // Constant values for scores
    final int PERFECT_SCORE = 300;
    final int EXCELLENT_SCORE = 250;
    final int GREAT_SCORE = 200;
    final int GOOD_SCORE = 150;
    final int OK_SCORE = 100;
    final int BAD_SCORE = 50;


    // The current and best combo so far
    int combo;
    int best_combo;

    // Accuracy judgements and number of judgements
    String judgement;
    int numPerfects = 0;
    int numExcellents = 0;
    int numGreats = 0;
    int numGoods = 0;
    int numOKs = 0;
    int numBads = 0;

    // Boolean values to keep track of key presses

    /* TODO: Allow for the user to change keys to
        whatever keys they want via a setting. */
    boolean wPressed;
    boolean ePressed;
    boolean oPressed;
    boolean pPressed;
    boolean escPressed;

    public Game() {
        score = 0;
        combo = 0;
        wPressed = false;
        ePressed = false;
        oPressed = false;
        pPressed = false;
        escPressed = false;
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

    public void releaseP() { pPressed = false; }

    public void pressEsc() { escPressed = true; }

    // Various getters and setters
    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }

    public int getCombo() { return combo; }

    public void setCombo(int combo) { this.combo = combo; }

    public int getBest_combo() { return best_combo; }

    public void setBest_combo(int best_combo) { this.best_combo = best_combo;}

    public String getJudgement() { return judgement; }

    public void setJudgement(String judgement) { this.judgement = judgement; }

    public boolean iswPressed() { return wPressed; }

    public boolean isePressed() { return ePressed; }

    public boolean isoPressed() { return oPressed; }

    public boolean ispPressed() { return pPressed; }

    public boolean isEscPressed() { return escPressed; }

    public int getNumPerfects() {
        return numPerfects;
    }

    public void setNumPerfects(int numPerfects) {
        this.numPerfects = numPerfects;
    }

    public int getNumExcellents() {
        return numExcellents;
    }

    public void setNumExcellents(int numExcellents) {
        this.numExcellents = numExcellents;
    }

    public int getNumGreats() {
        return numGreats;
    }

    public void setNumGreats(int numGreats) {
        this.numGreats = numGreats;
    }

    public int getNumGoods() {
        return numGoods;
    }

    public void setNumGoods(int numGoods) {
        this.numGoods = numGoods;
    }

    public int getNumOKs() {
        return numOKs;
    }

    public void setNumOKs(int numOKs) {
        this.numOKs = numOKs;
    }

    public int getNumBads() {
        return numBads;
    }

    public void setNumBads(int numBads) {
        this.numBads = numBads;
    }

    public int getPERFECT_SCORE() {
        return PERFECT_SCORE;
    }

    public int getEXCELLENT_SCORE() {
        return EXCELLENT_SCORE;
    }

    public int getGREAT_SCORE() {
        return GREAT_SCORE;
    }

    public int getGOOD_SCORE() {
        return GOOD_SCORE;
    }

    public int getOK_SCORE() {
        return OK_SCORE;
    }

    public int getBAD_SCORE() {
        return BAD_SCORE;
    }
}
