package Util;

/**
 * @Description:
 * @auther phantom
 * @create 2019-09-18 下午4:28
 */
public class OnceMeasureRecord {

    /***初始化记录度量标准值的对象*/
    private int Fmeasure;
    private int F2measure;

    public OnceMeasureRecord() {
        initializeMeasureRecorders();
    }

    /****
     * 初始化记录度量标准值的对象
     */
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
