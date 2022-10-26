package Executor;

import Constant.constant;
import GenerateTestSuit.generate;
import GenerateTestSuit.get_fault_matrix;
import GenerateTestSuit.get_partition;
import GenerateTestSuit.testcase;
import Log.RecordResult;
import Strategy.RLAPT_S;
import Util.MeasureRecorder;
import Util.OnceMeasureRecord;
import Util.OnceTimeRecord;
import Util.TimeRecorder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class rlapt_s implements test {
//    public static void main(String[] args) {
//        rlapt_s test = new rlapt_s();
//        for (int repeatTime = 0; repeatTime < 10; repeatTime++) {
//            test.executeTestCase("Make", "v1", repeatTime);
//        }
//    }

    @Override
    public void executeTestCase(String program_name, String version, int repeatTimes) {
// Time Recorder
        TimeRecorder timeRecorder = new TimeRecorder();

        // Measure Recorder
        MeasureRecorder measureRecorder = new MeasureRecorder();

        for (int i = 0; i < constant.repeatnum; i++) {
            System.out.println(program_name + " " + version + " use RL-APT_S ; now testing " + String.valueOf(i + 1));

            // initialize RL-APT_S
            RLAPT_S rlapts = new RLAPT_S();
            rlapts.initializeRLAPT(constant.get_num_of_partition(program_name));

            // get Mutant List
            List<Integer> used_mutant = Arrays.stream(constant.get_mutant(program_name, version)).boxed().collect(Collectors.toList());

            // Initialize an object that records the number of test cases executed
            int counter = 0;

            // Initialize an object that records metric values
            OnceMeasureRecord onceMeasureRecord = new OnceMeasureRecord();

            // Initialize an object that records time
            OnceTimeRecord onceTimeRecord = new OnceTimeRecord();

            // an object that records the partition_index
            int partitionIndex = 0;
            // an object that records the next_partition_index
            int nextPartitionIndex = 0;
            // an object that records the next_next_partition_index
            int nextnextpartitionIndex = 0;

            // generate test case set
            testcase[] tc = generate.generate(program_name);
            get_partition.partiton_tc(tc, program_name);
            get_fault_matrix.get_fm(tc, program_name, version);


            // generate test seq
//            int[] testseq = new int[constant.testcasenum];
//            for (int s = 0; s < testseq.length; s++) {
//                testseq[s] = new Random().nextInt(constant.get_tc_num(program_name));
//            }
            int[] testseq = constant.getTestseq();
            for (int s = 0; s < testseq.length; s++) {
                testseq[s] = testseq[s] % constant.get_tc_num(program_name);
            }

            for (int j = 0; j < constant.testcasenum; j++) {
                // counter increment
                counter++;

                /**
                 * select partition and test case
                 */
                long start = System.nanoTime();
                // select partition
                if (counter == 1) {
                    partitionIndex = new Random().
                            nextInt(constant.get_num_of_partition(program_name));
                    // select the next partition
                    nextPartitionIndex = rlapts.nextPartition4RLAPT(program_name, partitionIndex, counter);
                } else {
                    partitionIndex = nextPartitionIndex;
                    nextPartitionIndex = nextnextpartitionIndex;
                }

                // select the next next partition
                nextnextpartitionIndex = rlapts.nextPartition4RLAPT(program_name, nextPartitionIndex, counter);
                long end = System.nanoTime();

                // Record the time required -> select
                if (used_mutant.size() == constant.get_mutant_num(program_name, version)) {
                    onceTimeRecord.firstSelectionTimePlus(end - start);
                } else if (used_mutant.size() == constant.get_mutant_num(program_name, version) - 1) {
                    onceTimeRecord.secondSelectionTimePlus(end - start);
                }

                // select test case
                while (tc[testseq[j]].getPartition() != partitionIndex && j < constant.testcasenum - 1) {
                    j++;
                }
                testcase testcasenow = tc[testseq[j]];

                // Flag: indicates whether the test case kills the mutant
                boolean isKilledMutants = false;

                int pre_mutant_num = used_mutant.size();
                used_mutant.removeAll(testcasenow.getKillableMutants());
                int now_mutant_num = used_mutant.size();
                if (now_mutant_num < pre_mutant_num) {
                    isKilledMutants = true;
                }

                // Record the measure
                if (isKilledMutants) {
                    if (used_mutant.size() == constant.get_mutant_num(program_name, version) - 1) {
                        onceMeasureRecord.FmeasurePlus(counter);
                    } else if (used_mutant.size() == constant.get_mutant_num(program_name, version) - 2) {
                        if (onceMeasureRecord.getFmeasure() == 0) {
                            onceMeasureRecord.FmeasurePlus(counter);
                        } else {
                            onceMeasureRecord.F2measurePlus(counter - onceMeasureRecord.getFmeasure());
                        }
                    }
                }

                // All mutants are killed and the test is over
                if (used_mutant.size() == 0) {
                    break;
                }

                long start2 = System.nanoTime();
                // Adjust the test profile according to the test result
                rlapts.adjustRLAPT_S(counter, partitionIndex, nextPartitionIndex, nextnextpartitionIndex, isKilledMutants);
                long end2 = System.nanoTime();

                // Record the time required -> adjust
                if (used_mutant.size() == constant.get_mutant_num(program_name, version)) {
                    onceTimeRecord.firstExecutingTime(end2 - start2);
                } else if (used_mutant.size() == constant.get_mutant_num(program_name, version) - 1) {
                    onceTimeRecord.secondExecutingTime(end2 - start2);
                }
            }
            if ((constant.get_mutant_num(program_name, version) == 1 && onceMeasureRecord.getFmeasure() != 0) || onceMeasureRecord.getF2measure() != 0) {
                measureRecorder.addFMeasure(onceMeasureRecord.getFmeasure());
                measureRecorder.addF2Measure(onceMeasureRecord.getF2measure());

                timeRecorder.addFirstSelectTestCase(onceTimeRecord.getFirstSelectingTime());
                timeRecorder.addFirstGenerateTestCase(onceTimeRecord.getFirstGeneratingTime());
                timeRecorder.addFirstExecuteTestCase(onceTimeRecord.getFirstExecutingTime());
                timeRecorder.addSecondSelectTestCase(onceTimeRecord.getSecondSelectingTime());
                timeRecorder.addSecondGenerateTestCase(onceTimeRecord.getSecondGeneratingTime());
                timeRecorder.addSecondExecuteTestCase(onceTimeRecord.getSecondExecutingTime());
            }
            if (measureRecorder.getFmeasureArray().size() < 30 && i == constant.repeatnum - 1) {
                i--;
            }
        }

        // record result in txt
        String txtLogName = "RL-APT_S_" + program_name + "_" + version + ".txt";
        RecordResult.recordResult(txtLogName, repeatTimes, measureRecorder.getAverageFmeasure(), measureRecorder.getAverageF2measure(), timeRecorder.getAverageSelectFirstTestCaseTime() + timeRecorder.getAverageGenerateFirstTestCaseTime() + timeRecorder.getAverageExecuteFirstTestCaseTime(), timeRecorder.getAverageSelectSecondTestCaseTime() + timeRecorder.getAverageGenerateSecondTestCaseTime() + timeRecorder.getAverageExecuteSecondTestCaseTime());
        String txtSpecificName = "RL-APT_S_" + program_name + "_" + version + "_Content.txt";
        RecordResult.SpecificResult(txtSpecificName, repeatTimes, measureRecorder.getFmeasureArray(), measureRecorder.getF2measureArray(), timeRecorder.getFirstTotalArray(), timeRecorder.getSecondTotalArray());
    }
}
