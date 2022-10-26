package Util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class TimeRecorder {
    /**
     * the time recorders
     */
    private List<Long> firstSelectTestCaseArray;
    private List<Long> secondSelectTestCaseArray;

    private List<Long> firstGenerateTestCaseArray;
    private List<Long> secondGenerateTestCaseArray;

    private List<Long> firstExecuteTestCaseArray;
    private List<Long> secondExecuteTestCaseArray;

    private List<Long> firstTotalArray;
    private List<Long> secondTotalArray;

    private DecimalFormat decimalFormat;

    public TimeRecorder() {
        initial();
    }

    private void initial() {
        firstSelectTestCaseArray = new ArrayList<>();
        firstGenerateTestCaseArray = new ArrayList<>();
        firstExecuteTestCaseArray = new ArrayList<>();

        secondSelectTestCaseArray = new ArrayList<>();
        secondGenerateTestCaseArray = new ArrayList<>();
        secondExecuteTestCaseArray = new ArrayList<>();

        decimalFormat = new DecimalFormat("0.00");
    }

    public void addFirstSelectTestCase(Long time) {
        firstSelectTestCaseArray.add(time);
    }

    public void addSecondSelectTestCase(Long time) {
        secondSelectTestCaseArray.add(time);
    }


    public void addFirstGenerateTestCase(Long time) {
        firstGenerateTestCaseArray.add(time);
    }

    public void addSecondGenerateTestCase(Long time) {
        secondGenerateTestCaseArray.add(time);
    }


    public void addFirstExecuteTestCase(Long time) {
        firstExecuteTestCaseArray.add(time);
    }

    public void addSecondExecuteTestCase(Long time) {
        secondExecuteTestCaseArray.add(time);
    }


    /**
     * get average value
     **/

    public double getAverageSelectFirstTestCaseTime() {
        return getAverageValue(firstSelectTestCaseArray);
    }

    public double getAverageSelectSecondTestCaseTime() {
        return getAverageValue(secondSelectTestCaseArray);
    }


    public double getAverageGenerateFirstTestCaseTime() {
        return getAverageValue(firstGenerateTestCaseArray);
    }

    public double getAverageGenerateSecondTestCaseTime() {
        return getAverageValue(secondGenerateTestCaseArray);
    }


    public double getAverageExecuteFirstTestCaseTime() {
        return getAverageValue(firstExecuteTestCaseArray);
    }

    public double getAverageExecuteSecondTestCaseTime() {
        return getAverageValue(secondExecuteTestCaseArray);
    }


    /**
     * get variance value
     **/

    public double getVarianceSelectFirstTestCaseTime() {
        return getVarianceValue(firstSelectTestCaseArray);
    }

    public double getVarianceSelectSecondTestCaseTime() {
        return getVarianceValue(secondSelectTestCaseArray);
    }


    public double getVarianceGenerateFirstTestCaseTime() {
        return getVarianceValue(firstGenerateTestCaseArray);
    }

    public double getVarianceGenerateSecondTestCaseTime() {
        return getVarianceValue(secondGenerateTestCaseArray);
    }


    public double getVarianceExecuteFirstTestCaseTime() {
        return getVarianceValue(firstExecuteTestCaseArray);
    }

    public double getVarianceExecuteSecondTestCaseTime() {
        return getVarianceValue(secondExecuteTestCaseArray);
    }


    private double getAverageValue(List<Long> timeArray) {
        double sum = 0;
        for (int i = 0; i < timeArray.size(); i++) {
            sum += timeArray.get(i);
        }
        return Double.parseDouble(decimalFormat.format(sum / timeArray.size()));
    }


    private double getVarianceValue(List<Long> list) {
        double average = getAverageValue(list);

        double sum = 0;

        for (int i = 0; i < list.size(); i++) {
            sum += Math.pow(list.get(i) - average, 2);
        }
        return Double.parseDouble(decimalFormat.format(sum / (list.size() - 1)));
    }


    /**
     * get list
     */

    public List<Long> getFirstSelectTestCaseArray() {
        return firstSelectTestCaseArray;
    }

    public List<Long> getSecondSelectTestCaseArray() {
        return secondSelectTestCaseArray;
    }


    public List<Long> getFirstGenerateTestCaseArray() {
        return firstGenerateTestCaseArray;
    }

    public List<Long> getSecondGenerateTestCaseArray() {
        return secondGenerateTestCaseArray;
    }


    public List<Long> getFirstExecuteTestCaseArray() {
        return firstExecuteTestCaseArray;
    }

    public List<Long> getSecondExecuteTestCaseArray() {
        return secondExecuteTestCaseArray;
    }

    /**
     * get T/T2 list
     */
    public List<Long> getFirstTotalArray() {
        List<Long> TArray = new ArrayList<>();
        for (int i = 0; i < firstSelectTestCaseArray.size(); i++) {
            TArray.add(firstSelectTestCaseArray.get(i) + firstGenerateTestCaseArray.get(i) + firstExecuteTestCaseArray.get(i));
        }
        return TArray;
    }

    public List<Long> getSecondTotalArray() {
        List<Long> T2Array = new ArrayList<>();
        for (int i = 0; i < secondSelectTestCaseArray.size(); i++) {
            T2Array.add(secondSelectTestCaseArray.get(i) + secondGenerateTestCaseArray.get(i) + secondExecuteTestCaseArray.get(i));
        }
        return T2Array;
    }
}
