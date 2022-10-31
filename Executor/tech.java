package Executor;

public class tech implements test {
    @Override
    public void executeTestCase(String program_name, int repeatTimes) {

        // Time Recorder
        TimeRecorder timeRecorder = new TimeRecorder();

        // Measure Recorder
        MeasureRecorder measureRecorder = new MeasureRecorder();

        for (int i = 0; i < repeat_num; i++) {
            System.out.println(program_name + " use TECH ; now testing " + String.valueOf(i + 1));

            // initialize current technique
            Strategy curr_tech = new tech_in_strategy();
            curr_tech.initialize();

            // get Mutant
            List<Integer> used_mutant;

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

            // get/generate test cases and obtain its corresponding partition
            testcase[] tc;
            tc.get_partition();

            for (int j = 0; j < max_tc_num; j++) {
                // counter increment
                counter++;

                /**
                 * select partition and test case
                 */
                long start = System.nanoTime();
                // select partition
                if (counter == 1) {
                    partitionIndex = new Random().nextInt(partition_num_of_program);
                } else {
                    partitionIndex = nextPartitionIndex;
                }

                // select the next partition
                nextPartitionIndex = curr_tech.nextPartition();
                long end = System.nanoTime();

                // Record the time required -> select
                if (used_mutant.size() == mutant_num) {
                    onceTimeRecord.firstSelectionTimePlus(end - start);
                } else if (used_mutant.size() == mutant_num - 1) {
                    onceTimeRecord.secondSelectionTimePlus(end - start);
                }

                // select test case
                testcase testcase_now = tc.get_from_partition(partitionIndex);

                // Flag: indicates whether the test case kills the mutant
                boolean isKilledMutants = testcase_now.execute();

                if (isKilledMutants) {
                    used_mutant.remove_curr_mut();
                }

                // Record the measure
                if (isKilledMutants) {
                    if (used_mutant.size() == mutant_num - 1) {
                        onceMeasureRecord.FmeasurePlus(counter);
                    } else if (used_mutant.size() == mutant_num - 2) {
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
                curr_tech.adjust();
                long end2 = System.nanoTime();

                // Record the time required -> adjust
                if (used_mutant.size() == mutant_num) {
                    onceTimeRecord.firstExecutingTime(end2 - start2);
                } else if (used_mutant.size() == mutant_num - 1) {
                    onceTimeRecord.secondExecutingTime(end2 - start2);
                }
            }
            if ((mutant_num == 1 && onceMeasureRecord.getFmeasure() != 0) || onceMeasureRecord.getF2measure() != 0) {
                measureRecorder.addFMeasure(onceMeasureRecord.getFmeasure());
                measureRecorder.addF2Measure(onceMeasureRecord.getF2measure());

                timeRecorder.addFirstSelectTestCase(onceTimeRecord.getFirstSelectingTime());
                timeRecorder.addFirstGenerateTestCase(onceTimeRecord.getFirstGeneratingTime());
                timeRecorder.addFirstExecuteTestCase(onceTimeRecord.getFirstExecutingTime());
                timeRecorder.addSecondSelectTestCase(onceTimeRecord.getSecondSelectingTime());
                timeRecorder.addSecondGenerateTestCase(onceTimeRecord.getSecondGeneratingTime());
                timeRecorder.addSecondExecuteTestCase(onceTimeRecord.getSecondExecutingTime());
            }
            if (measureRecorder.getFmeasureArray().size() < 30 && i == repeat_num - 1) {
                i--;
            }
        }

        // record result in txt
        String txtLogName = "TECH_" + program_name + ".txt";
        recordResult(txtLogName, repeatTimes, measureRecorder.getAverageFmeasure(), measureRecorder.getAverageF2measure(), timeRecorder.getAverageSelectFirstTestCaseTime() + timeRecorder.getAverageGenerateFirstTestCaseTime() + timeRecorder.getAverageExecuteFirstTestCaseTime(), timeRecorder.getAverageSelectSecondTestCaseTime() + timeRecorder.getAverageGenerateSecondTestCaseTime() + timeRecorder.getAverageExecuteSecondTestCaseTime());
        String txtSpecificName = "TECH_" + program_name + "_Content.txt";
        recordSpecificResult(txtSpecificName, repeatTimes, measureRecorder.getFmeasureArray(), measureRecorder.getF2measureArray(), timeRecorder.getFirstTotalArray(), timeRecorder.getSecondTotalArray());
    }
}
