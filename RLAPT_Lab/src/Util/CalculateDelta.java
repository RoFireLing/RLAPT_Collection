package Util;

/**
 * @author RoFire
 * @date 2021/1/30
 **/
public class CalculateDelta {
    public static void calculate(double max, double max2) {
        double delta = ((max * 0.05 / (1 - max)) - (max2 * 0.05 / (1 - max2))) *
                0.8 + (max2 * 0.05 / (1 - max2));
        System.out.println(delta);
    }

    public static void main(String[] args) {
        double max1 = 0.5555555555555556;
        double max2 = 0;
        CalculateDelta.calculate(max1, max2);
    }

    /**
     * ACMS500      0.030804597701149433
     * ACMS1000
     * ACMS1500
     * ACMS2000
     *
     * CUBS500      0.0022377622377622378
     * CUBS1000
     * CUBS1500
     * CUBS2000
     *
     * ERS500       0.05000000000000002
     * ERS1000
     * ERS1500
     * ERS2000
     */
}
