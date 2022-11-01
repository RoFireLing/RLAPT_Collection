package Strategy;

import java.util.Random;

public class RAPT {
    // 70% testcases in each partition
    private static final int[] pun = {};

    /**
     * the test profile of RAPT
     */
    private double[] RAPT;

    private double RAPT_epsilon = 0.05;

    private double RAPT_delta;

    /**
     * the factor of reward
     */
    private int[] rew;

    /**
     * the factor of punishment
     */
    private int[] bou;

    private int[] pun;

    private void setRAPT_delta(double RAPT_delta) {
        this.RAPT_delta = RAPT_delta;
    }

    private void setBou_RAPT(int[] punArray) {
        bou = punArray;
    }

    /**
     * initialize the test profile of RAPT
     *
     * @param numberOfPartitions the number of partitions
     */
    public void initializeRAPT(int numberOfPartitions) {
        RAPT = new double[numberOfPartitions];
        for (int i = 0; i < RAPT.length; i++) {
            RAPT[i] = 1.0 / numberOfPartitions;
            pun[i] = 0;
        }
    }

    /**
     * get a index of partition
     * Note that the first number of partitions is 0
     *
     * @return the index
     */
    public int nextPartition4RAPT() {
        boolean flag = false;
        int partitionindex = 0;
        for (int i = 0; i < rew.length; i++) {
            if (rew[i] > 0) {
                flag = true;
                partitionindex = i;
                break;
            }
        }
        if (flag) {
            return partitionindex;
        } else {
            int index = -1;
            double randomNumber = new Random().nextDouble();
            double sum = 0;
            do {
                index++;
                sum += RAPT[index];
            } while (randomNumber >= sum && index < RAPT.length - 1);
            return index;
        }
    }


    /**
     * adjust the test profile for RAPT testing
     *
     * @param
     * @param isKilledMutant
     */
    public void adjustRAPT(int formersourcePartitionIndex, boolean isKilledMutant) {
        double old_i = RAPT[formersourcePartitionIndex];
        if (isKilledMutant) {
            rew[formersourcePartitionIndex]++;
            pun[formersourcePartitionIndex] = 0;
        } else {
            pun[formersourcePartitionIndex]++;
            if (rew[formersourcePartitionIndex] != 0) {
                double sum = 0;
                for (int i = 0; i < RAPT.length; i++) {
                    if (i != formersourcePartitionIndex) {
                        RAPT[i] -= (1 + Math.log(rew[formersourcePartitionIndex]))
                                * RAPT_epsilon / (RAPT.length - 1);
                        if (RAPT[i] < 0) {
                            RAPT[i] = 0;
                        }
                        sum += RAPT[i];
                    }
                }
                RAPT[formersourcePartitionIndex] = 1 - sum;
                rew[formersourcePartitionIndex] = 0;
            } else {
                for (int i = 0; i < RAPT.length; i++) {
                    if (i == formersourcePartitionIndex) {
                        if (old_i >= RAPT_delta) {
                            RAPT[i] -= RAPT_delta;
                        }
                        if (old_i < RAPT_delta || bou[i] == pun[i]) {
                            RAPT[i] = 0;
                        }
                    } else {
                        if (old_i >= RAPT_delta) {
                            RAPT[i] += RAPT_delta / (RAPT.length - 1);
                        }
                        if (old_i < RAPT_delta || bou[i] == pun[i]) {
                            RAPT[i] += old_i / (RAPT.length - 1);
                        }
                    }
                }
            }
        }
    }


    public void setParameters4RAPT() {
        setRAPT_delta(0);
        setBou_RAPT(pun);
    }
}
