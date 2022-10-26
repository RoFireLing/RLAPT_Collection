package Util;

/**
 * @author RoFire
 * @date 2020/11/17
 **/
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

    public static void main(String[] args) {
        double[] max = {0.0091324200913242, 0.091324200913242, 0.0410958904109589, 0.0045662100456621, 0.75, 0.06403940886699508, 0.2, 0.75, 0.03686635944700461, 0.009216589861751152};
        double[] max2 = {0.0040650406504065045, 0.07317073170731707, 0.016260162601626018, 0.0040650406504065045, 0.2, 0, 0.0049261083743842365, 0.009852216748768473, 0, 0};
        for (int i = 0; i < max.length; i++) {
            System.out.println(cal_delta(max[i], max2[i]));
        }
    }

    /**
     * Delta Result
     * Grep_1 4.0947992100065836E-4
     * Grep_2 0.004809574186723089
     * Grep_3 0.001879574970484061
     * Grep_4 2.2430256506272232E-4
     * Gzip_1 0.12250000000000001
     * Gzip_2 0.0027368421052631586
     * Gzip_4 0.010049504950495051
     * Gzip_5 0.12009950248756222
     * Make_1 0.0015311004784688996
     * Make_2 3.7209302325581393E-4
     */
}
