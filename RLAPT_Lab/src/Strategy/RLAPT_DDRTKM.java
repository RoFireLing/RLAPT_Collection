package Strategy;

import java.util.Random;

public class RLAPT_DDRTKM {
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
        if (objectName.equals("ACMS")) {
            double[][] Mat = {{0.0, 987.0282211410389, 1492.4198361437113, 686.4363918779407, 612.6363870582136, 2097.5311593883002, 317.94165701208254, 382.406122797989}, {987.0282211410389, 0.0, 505.8148616492049, 1673.3601202885482, 375.4410868205619, 1110.638787108128, 1304.820081389722, 604.9200110366314}, {1492.4198361437113, 505.8148616492049, 0.0, 2178.847375271164, 881.2446679466563, 605.175660638925, 1810.3161645996486, 1110.6016575111848}, {686.4363918779407, 1673.3601202885482, 2178.847375271164, 0.0, 1298.5190104436497, 2783.937498298799, 368.55119729093684, 1068.4946985822414}, {612.6363870582136, 375.4410868205619, 881.2446679466563, 1298.5190104436497, 0.0, 1485.8944040179101, 930.0461833252901, 230.24543031376066}, {2097.5311593883002, 1110.638787108128, 605.175660638925, 2783.937498298799, 1485.8944040179101, 0.0, 2415.396909094702, 1715.5543277402624}, {317.94165701208254, 1304.820081389722, 1810.3161645996486, 368.55119729093684, 930.0461833252901, 2415.396909094702, 0.0, 699.9687085943932}, {382.406122797989, 604.9200110366314, 1110.6016575111848, 1068.4946985822414, 230.24543031376066, 1715.5543277402624, 699.9687085943932, 0.0}};
            setDRT_Mat(Mat);
        } else if (objectName.equals("CUBS")) {
            double[][] Mat = {{0.0, 622.1945033508413, 818.8052271450152, 4179.853825195326}, {622.1945033508413, 0.0, 906.8836750101966, 3794.2975634496565}, {818.8052271450152, 906.8836750101966, 0.0, 3486.410761800738}, {4179.853825195326, 3794.2975634496565, 3486.410761800738, 0.0}};
            setDRT_Mat(Mat);
        } else if (objectName.equals("ERS")) {
            double[][] Mat = {{0.0, 15213.369471198703, 68661.6180779289, 35583.5637723748, 47842.746889149465, 56008.383158174984, 73501.80760061876, 13100.507111945579, 27743.977473171402, 67326.8615840959, 73867.4668910164, 25632.626745377343}, {15213.369471198703, 0.0, 82472.84562136003, 49481.14037247872, 61830.77931909546, 69935.24951781987, 87364.35541621044, 26197.672605358537, 14040.179524662013, 81446.01454952455, 88100.2447489137, 39189.83020595835}, {68661.6180779289, 82472.84562136003, 0.0, 33490.32011202817, 21623.610521946448, 13326.554307115191, 4954.09803619012, 56489.24407823668, 96121.12907089165, 5604.910116454525, 8067.998810620821, 43358.0852804802}, {35583.5637723748, 49481.14037247872, 33490.32011202817, 0.0, 12357.766067548158, 20583.947110356494, 38256.564342529426, 23507.41233453687, 62951.62199523269, 32024.256351929464, 39169.16062712458, 10526.8164602437}, {47842.746889149465, 61830.77931909546, 21623.610521946448, 12357.766067548158, 0.0, 8435.913624483324, 26224.819382029553, 35848.7355825093, 75279.79830196848, 19748.454646396236, 27277.14657579741, 22764.74902982808}, {56008.383158174984, 69935.24951781987, 13326.554307115191, 20583.947110356494, 8435.913624483324, 0.0, 17892.253394568594, 43969.03048500093, 83470.8445998427, 11727.26485940722, 19234.858929496502, 30754.962502868275}, {73501.80760061876, 87364.35541621044, 4954.09803619012, 38256.564342529426, 26224.819382029553, 17892.253394568594, 0.0, 61350.14550047984, 100989.25423030778, 7703.884286443104, 5852.180465524319, 48223.83381281099}, {13100.507111945579, 26197.672605358537, 56489.24407823668, 23507.41233453687, 35848.7355825093, 43969.03048500093, 61350.14550047984, 0.0, 39757.609356272456, 55385.626373080304, 62145.14349778352, 13401.912944649095}, {27743.977473171402, 14040.179524662013, 96121.12907089165, 62951.62199523269, 75279.79830196848, 83470.8445998427, 100989.25423030778, 39757.609356272456, 0.0, 94896.31185438264, 101536.38785696992, 52813.82472504329}, {67326.8615840959, 81446.01454952455, 5604.910116454525, 32024.256351929464, 19748.454646396236, 11727.26485940722, 7703.884286443104, 55385.626373080304, 94896.31185438264, 0.0, 8846.179466907863, 42299.29585400691}, {73867.4668910164, 88100.2447489137, 8067.998810620821, 39169.16062712458, 27277.14657579741, 19234.858929496502, 5852.180465524319, 62145.14349778352, 101536.38785696992, 8846.179466907863, 0.0, 49099.99977347275}, {25632.626745377343, 39189.83020595835, 43358.0852804802, 10526.8164602437, 22764.74902982808, 30754.962502868275, 48223.83381281099, 13401.912944649095, 52813.82472504329, 42299.29585400691, 49099.99977347275, 0.0}};
            setDRT_Mat(Mat);
        } else {
            double[][] Mat = {};
            setDRT_Mat(Mat);
        }
    }
}
