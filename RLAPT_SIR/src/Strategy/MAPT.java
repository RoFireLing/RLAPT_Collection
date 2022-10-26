package Strategy;

import java.util.Random;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class MAPT {
    /**
     * the test profile of MAPT
     */
    private double[][] MAPT;

    private double MAPT_gamma = 0.1;

    private double MAPT_tau = 0.1;

    /**
     * initialize the test profile of MAPT
     *
     * @param numberOfPartitions the number of partitions
     */
    public void initializeMAPT(int numberOfPartitions) {
        MAPT = new double[numberOfPartitions][numberOfPartitions];
        for (int i = 0; i < numberOfPartitions; i++) {
            for (int j = 0; j < numberOfPartitions; j++) {
                MAPT[i][j] = 1.0 / numberOfPartitions;
            }
        }
    }

    /**
     * get a index of partition
     * Note that the first number of partitions is 0
     *
     * @return the index
     */
    public int nextPartition4MAPT(int formerPartitionNumber) {
        double[] tempArray = new double[MAPT.length];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = MAPT[formerPartitionNumber][i];
        }
        int index = -1;
        double randomNumber = new Random().nextDouble();
        double sum = 0;
        do {
            index++;
            sum += tempArray[index];
        } while (randomNumber >= sum && index < tempArray.length - 1);
        return index;
    }

    /**
     * adjust the test profile for MAPT testing
     *
     * @param formerSourcePartitionIndex
     * @param isKilledMutans
     */
    public void adjustMAPT(int formerSourcePartitionIndex, boolean isKilledMutans) {
        double old_i = MAPT[formerSourcePartitionIndex][formerSourcePartitionIndex];
        // the test case killed a mutant
        if (isKilledMutans) { //same partition and killed a mutant
            double sum = 0;
            double threshold = MAPT_gamma * old_i / (MAPT.length - 1);
            for (int i = 0; i < MAPT.length; i++) {
                if (i != formerSourcePartitionIndex) {
                    if (MAPT[formerSourcePartitionIndex][i] > threshold) {
                        MAPT[formerSourcePartitionIndex][i] -= threshold;
                    }
                    sum += MAPT[formerSourcePartitionIndex][i];
                }
//                sum += MAPT[formerSourcePartitionIndex][i];
            }
            MAPT[formerSourcePartitionIndex][formerSourcePartitionIndex] = 1 - sum;
        } else {
            //do not kill a mutant
            double threshod = MAPT_tau * (1 - old_i) / (MAPT.length - 1);
            for (int i = 0; i < MAPT.length; i++) {
                if (i != formerSourcePartitionIndex) {
                    if (MAPT[formerSourcePartitionIndex][formerSourcePartitionIndex] > threshod) {
                        MAPT[formerSourcePartitionIndex][i] +=
                                MAPT_tau * MAPT[formerSourcePartitionIndex][i] / (MAPT.length - 1);
                    }
                } else {
                    if (MAPT[formerSourcePartitionIndex][formerSourcePartitionIndex] > threshod) {
                        MAPT[i][i] -= MAPT_tau * (1 - MAPT[i][i]) / (MAPT.length - 1);
                    }
                }
            }
        }
    }
}
