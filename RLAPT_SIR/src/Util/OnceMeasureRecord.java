package Util;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class OnceMeasureRecord {
    /**
     * Initialize the object that records the metric value
     */
    private int Fmeasure;
    private int F2measure;

    public OnceMeasureRecord() {
        initializeMeasureRecorders();
    }

    private void initializeMeasureRecorders() {
        Fmeasure = 0;
        F2measure = 0;
    }

    public void FmeasurePlus(int measure) {
        Fmeasure += measure;
    }

    public void F2measurePlus(int measure) {
        F2measure += measure;
    }

    public int getFmeasure() {
        return Fmeasure;
    }

    public int getF2measure() {
        return F2measure;
    }
}
