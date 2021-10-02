import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Thread {
    private boolean playerActive = false;
    private int score = 0;
    private int combo = 0;

    ArrayList<Block> blockStack = new ArrayList<>();

    public Game(boolean playerActive) {
        this.playerActive = playerActive;
    }

    public void drawGraphics(Graphics2D graphics) {

        // Drawing lanes
        graphics.setColor(Color.BLACK);
        graphics.drawLine(760, 0, 760, 1080);
        graphics.drawLine(860, 0, 860, 1080);
        graphics.drawLine(960, 0, 960, 1080);
        graphics.drawLine(1060, 0, 1060, 1080);

        for (int i = 0; i < blockStack.size(); i++) {
            Block b = blockStack.get(i);

            if (b.getY() > 1080) {
                combo = 0;
            }

            if (!b.isActiveNote()) {
                blockStack.remove(i);
                i--;
            }

            else {
                switch (b.getNoteType()) {
                    case "red":
                        graphics.setColor(Color.RED);
                        break;
                    case "green":
                        graphics.setColor(Color.GREEN);
                        break;
                    case "yellow":
                        graphics.setColor(Color.YELLOW);
                        break;
                    case "blue":
                        graphics.setColor(Color.BLUE);
                        break;
                }
                b.drawNote(graphics); } }
    }

    @Override
    public void run() {
        dropBlocks();
    }

    public void dropBlocks() {
        BeatKeeper[] beats = null;

        // Song length
        int time = 5500;

        // Beats per minute
        int bpm = 120;

        // Randomize the blocks being dropped in the lanes
        // TODO: Add custom charts
        Random rand = new Random();
        int bound = 4;

        // 100 notes
        beats = new BeatKeeper[100];

        // Populate the chart with random notes
        for (int i = 0; i < 100; i++) {
            int randInt = rand.nextInt(bound);

            if (randInt == 0) {
                beats[i] = new BeatKeeper(time + (bpm * (i * 4)), "red");
            }

            if (randInt == 1) {
                beats[i] = new BeatKeeper(time + (bpm * (i * 4)), "green");
            }

            if (randInt == 2) {
                beats[i] = new BeatKeeper(time + (bpm * (i * 4)), "yellow");
            }

            if (randInt == 3) {
                beats[i] = new BeatKeeper(time + (bpm * (i * 4)), "blue");
            }
        }

        int noteCount = 0;
        float songTime = 0.0f;

        while (noteCount < beats.length && !isInterrupted()) {
            boolean isActiveNote = false;
            songTime += 0.001f;

            if (beats[noteCount].getTime() <= songTime) {
                Block b = new Block(beats[noteCount].getNoteName());
                b.start();
                blockStack.add(b);
                noteCount++;
                isActiveNote = true;
            }

            if (!isActiveNote) {
                try {
                    Thread.sleep(5);
                }
                catch (Exception e) {
                    e.printStackTrace(); } } } }

    public boolean isPlayerActive() {
        return playerActive;
    }

    public void setPlayerActive(boolean playerActive) {
        this.playerActive = playerActive;
    }
}
