package Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class MeasureRecorder {
    private List<Integer> FmeasureArray;

    private List<Integer> F2measureArray;


    public MeasureRecorder() {
        initial();
    }

    private void initial() {
        FmeasureArray = new ArrayList<>();
        F2measureArray = new ArrayList<>();
    }


    /**
     * add element to list
     **/

    public void addFMeasure(int value) {
        FmeasureArray.add(value);
    }

    public void addF2Measure(int value) {
        F2measureArray.add(value);
    }


    /**
     * get list
     */
    public List<Integer> getFmeasureArray() {
        return FmeasureArray;
    }

    public List<Integer> getF2measureArray() {
        return F2measureArray;
    }


    /**
     * get average and variance
     */

    public double getAverageFmeasure() {
        return calculateAverage(FmeasureArray);
    }

    public double getAverageF2measure() {
        return calculateAverage(F2measureArray);
    }


    public double getVarianceFmeasure() {
        return calculateVariance(FmeasureArray);
    }

    public double getVarianceF2measure() {
        return calculateVariance(F2measureArray);
    }


    /**
     * calculate average
     */

    private double calculateAverage(List<Integer> list) {
        int sum = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return Double.parseDouble(decimalFormat.format(sum / list.size()));
    }


    /**
     * calculate variance
     **/

    private double calculateVariance(List<Integer> list) {
        //get average
        double average = calculateAverage(list);

        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += Math.pow(list.get(i) - average, 2);
        }
        return sum / (list.size() - 1);
    }
}
