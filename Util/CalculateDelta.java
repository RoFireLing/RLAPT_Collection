package Util;

public class CalculateDelta {
    /**
     * calculate delta
     *
     * @param max  largest failure rate
     * @param max2 second largest failure rate
     * @return
     */
    public static double cal_delta(double max, double max2) {
        double epsilon = 0.05;
        double delta = ((max * 0.05 / (1 - max)) - (max2 * 0.05 / (1 - max2))) *
                0.8 + (max2 * 0.05 / (1 - max2));
        return delta;
    }
}
