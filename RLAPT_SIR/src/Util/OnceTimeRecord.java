package Util;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class OnceTimeRecord {
    /**
     * Initialize the object that records the time
     */
    private long firstSelectingTime;
    private long firstGeneratingTime;
    private long firstExecutingTime;
    private long secondSelectingTime;
    private long secondGeneratingTime;
    private long secondExecutingTime;


    public OnceTimeRecord() {
        initializeTimeRecorders();
    }

    private void initializeTimeRecorders() {
        firstSelectingTime = 0;
        firstGeneratingTime = 0;
        firstExecutingTime = 0;
        secondSelectingTime = 0;
        secondGeneratingTime = 0;
        secondExecutingTime = 0;
    }

    public void firstSelectionTimePlus(long time) {
        firstSelectingTime += time;
    }

    public void firstGeneratingTimePlus(long time) {
        firstGeneratingTime += time;
    }

    public void firstExecutingTime(long time) {
        firstExecutingTime += time;
    }

    public void secondSelectionTimePlus(long time) {
        secondSelectingTime += time;
    }

    public void secondGeneratingTimePlus(long time) {
        secondGeneratingTime += time;
    }

    public void secondExecutingTime(long time) {
        secondExecutingTime += time;
    }

    public long getFirstSelectingTime() {
        return firstSelectingTime;
    }

    public long getFirstGeneratingTime() {
        return firstGeneratingTime;
    }

    public long getFirstExecutingTime() {
        return firstExecutingTime;
    }

    public long getSecondSelectingTime() {
        return secondSelectingTime;
    }

    public long getSecondGeneratingTime() {
        return secondGeneratingTime;
    }

    public long getSecondExecutingTime() {
        return secondExecutingTime;
    }
}
