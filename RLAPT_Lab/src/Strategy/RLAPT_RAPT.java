package Strategy;

import _Constant.Constant;

import java.util.Random;

public class RLAPT_RAPT {
    private static final int[] pun4ACMS_500 = Constant.getAcms500();
    private static final int[] pun4ACMS_1000 = Constant.getAcms1000();
    private static final int[] pun4ACMS_1500 = Constant.getAcms1500();
    private static final int[] pun4ACMS_2000 = Constant.getAcms2000();
    private static final int[] pun4CUBS_500 = Constant.getCubs500();
    private static final int[] pun4CUBS_1000 = Constant.getCubs1000();
    private static final int[] pun4CUBS_1500 = Constant.getCubs1500();
    private static final int[] pun4CUBS_2000 = Constant.getCubs2000();
    private static final int[] pun4ERS_500 = Constant.getErs500();
    private static final int[] pun4ERS_1000 = Constant.getErs1000();
    private static final int[] pun4ERS_1500 = Constant.getErs1500();
    private static final int[] pun4ERS_2000 = Constant.getErs2000();

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

    public static int[] getBOU(int[] num) {
        int[] bou = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            bou[i] = (int) Math.round(num[i] * 0.7);
        }
        return bou;
    }

    private void setRAPT_delta(double RAPT_delta) {
        this.RAPT_delta = RAPT_delta;
    }

    private void setBou_RAPT(String objectName, int[] punArray) {
        if (objectName.equals("ACMS")) {
            rew = new int[8];
            pun = new int[8];
            bou = new int[8];
            for (int i = 0; i < punArray.length; i++) {
                bou[i] = punArray[i];
            }
        } else if (objectName.equals("CUBS")) {
            rew = new int[4];
            pun = new int[4];
            bou = new int[4];
            for (int i = 0; i < punArray.length; i++) {
                bou[i] = punArray[i];
            }
        } else if (objectName.equals("ERS")) {
            rew = new int[12];
            pun = new int[12];
            bou = new int[12];
            for (int i = 0; i < punArray.length; i++) {
                bou[i] = punArray[i];
            }
        }
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
    public void adjustRAPT(int formersourcePartitionIndex,
                           boolean isKilledMutant) {
        double old_i = RAPT[formersourcePartitionIndex];
        if (isKilledMutant) {
//            double sum = 0;
//            for (int i = 0; i < RAPT.length; i++) {
//                if (i != formersourcePartitionIndex) {
//                    RAPT[i] -= (1 + Math.log(rew[formersourcePartitionIndex]))
//                            * RAPT_epsilon / (RAPT.length - 1);
//                    if (RAPT[i] < 0) {
//                        RAPT[i] = 0;
//                    }
//                }
//                sum += RAPT[i];
//            }
//            RAPT[formersourcePartitionIndex] = 1 - sum;
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
//                    sum += RAPT[i];
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

    public void setParameters4RAPT(String objectName, int tcnum) {
        if (objectName.equals("ACMS")) {
            if (tcnum == 500) {
                setRAPT_delta(0.030804597701149433);
                setBou_RAPT(objectName, getBOU(pun4ACMS_500));
            } else if (tcnum == 1000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ACMS_1000));
            } else if (tcnum == 1500) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ACMS_1500));
            } else if (tcnum == 2000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ACMS_2000));
            } else {
                setRAPT_delta(0);
            }
        } else if (objectName.equals("CUBS")) {
            if (tcnum == 500) {
                setRAPT_delta(0.0022377622377622378);
                setBou_RAPT(objectName, getBOU(pun4CUBS_500));
            } else if (tcnum == 1000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4CUBS_1000));
            } else if (tcnum == 1500) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4CUBS_1500));
            } else if (tcnum == 2000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4CUBS_2000));
            } else {
                setRAPT_delta(0);
            }
        } else if (objectName.equals("ERS")) {
            if (tcnum == 500) {
                setRAPT_delta(0.05000000000000002);
                setBou_RAPT(objectName, getBOU(pun4ERS_500));
            } else if (tcnum == 1000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ERS_1000));
            } else if (tcnum == 1500) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ERS_1500));
            } else if (tcnum == 2000) {
                setRAPT_delta(0);
                setBou_RAPT(objectName, getBOU(pun4ERS_2000));
            } else {
                setRAPT_delta(0);
            }
        } else {
            setRAPT_delta(0.05);
        }
    }
}
