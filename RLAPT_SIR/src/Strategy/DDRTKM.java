package Strategy;

import java.util.Random;

public class DDRTKM {
    /**
     * the test profile of DRT
     */
    private double[] DRT;

    private double DRT_epsilon = 0.05;

    private double DRT_delta = 0.05;

    private double[][] DRT_Dis_Mat;

    private void setDRT_Mat(double[][] Mat) {
        this.DRT_Dis_Mat = Mat;
    }

    /**
     * initialize the test profile of DRT
     *
     * @param numberOfPartitions the number of partitions
     */
    public void initializeDRT(int numberOfPartitions) {
        DRT = new double[numberOfPartitions];
        for (int i = 0; i < numberOfPartitions; i++) {
            DRT[i] = 1.0 / numberOfPartitions;
        }
    }

    /**
     * get a index of partition
     * Note that the first number of partitions is 0
     *
     * @return the index
     */
    public int nextPartition4DRT() {
        double[] tempArray = DRT;
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
    public void adjustDRT(int formerSourcePartitionIndex,
                          boolean isKilledMutans) {
        //the source test case and follow-up test case belong to the same partition
        double sum_dis = 0.0;
        for (int i = 0; i < DRT.length; i++) {
            sum_dis += DRT_Dis_Mat[i][formerSourcePartitionIndex];
        }
        // the test case killed a mutant
        if (isKilledMutans) { //same partition and killed a mutant
            double sum = 0;
            double threshold;
            for (int i = 0; i < DRT.length; i++) {
                if (i != formerSourcePartitionIndex) {
                    threshold = DRT_epsilon * DRT_Dis_Mat[i][formerSourcePartitionIndex] / sum_dis;
                    if (DRT[i] > threshold) {
                        DRT[i] -= threshold;
                    } else {
                        DRT[i] = 0;
                    }
                    sum += DRT[i];
                }
            }
            DRT[formerSourcePartitionIndex] = 1 - sum;
        } else { // same partition and do not kill a mutant
            double threshod;
            if (DRT[formerSourcePartitionIndex] >= DRT_delta) {
                DRT[formerSourcePartitionIndex] -= DRT_delta;
            } else {
                DRT[formerSourcePartitionIndex] = 0;
            }
            for (int i = 0; i < DRT.length; i++) {
                if (DRT[formerSourcePartitionIndex] >= DRT_delta) {
                    threshod = DRT_delta * DRT_Dis_Mat[i][formerSourcePartitionIndex] / sum_dis;
                } else {
                    threshod = DRT[formerSourcePartitionIndex] * (DRT_delta * DRT_Dis_Mat[i][formerSourcePartitionIndex] / sum_dis) / DRT_delta;
                }
                if (i != formerSourcePartitionIndex) {
                    DRT[i] += threshod;
                }
            }
        }
    }

    public void setParameters4DRT(String objectName) {
        if (objectName.equals("Grep")) {
            double[][] Mat = {{0.0, 22.93468988235943, 4.0}, {22.93468988235943, 0.0, 21.494185260204677}, {4.0, 21.494185260204677, 0.0}};
            setDRT_Mat(Mat);
        } else if (objectName.equals("Gzip")) {
            double[][] Mat = {};
            setDRT_Mat(Mat);
        } else if (objectName.equals("Make")) {
            double[][] Mat = {{0.0, 3.0, 2.23606797749979, 3.0}, {3.0, 0.0, 2.8284271247461903, 2.0}, {2.23606797749979, 2.8284271247461903, 0.0, 2.449489742783178}, {3.0, 2.0, 2.449489742783178, 0.0}};
            setDRT_Mat(Mat);
        } else {
            double[][] Mat = {};
            setDRT_Mat(Mat);
        }
    }
}
