package Util;

import Constant.constant;
import GenerateTestSuit.generate;
import GenerateTestSuit.get_fault_matrix;
import GenerateTestSuit.get_partition;
import GenerateTestSuit.testcase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.io.File.separator;

/**
 * @author RoFire
 * @date 2020/11/17
 **/
public class CalculateFailureRate {
    public static void get_failure_rate(String program_name, String version) throws IOException {
        /**
         * get tc
         */
        testcase[] tc = generate.generate(program_name);
        get_partition gp = new get_partition();
        gp.partiton_tc(tc, program_name);
        get_fault_matrix gfm = new get_fault_matrix();
        gfm.get_fm(tc, program_name, version);

        String path = System.getProperty("user.dir") + separator + "src" + separator + "Testcase_Info" + separator + "Failure Rate stat";
        File file = new File(path);
        PrintWriter printWriter = null;

        /**
         * record txt
         */
        // failure rate stat.
        int[] mutant_stat = new int[constant.get_num_of_partition(program_name)];
        int[] tcnum = new int[constant.get_num_of_partition(program_name)];
        List<Integer> used_mutant = Arrays.stream(constant.get_mutant(program_name, version)).boxed().collect(Collectors.toList());
        for (testcase t : tc) {
            tcnum[t.getPartition()]++;
            List<Integer> killable_mutant = t.getKillableMutants();
            killable_mutant.retainAll(used_mutant);
            if (killable_mutant.size() != 0) {
                mutant_stat[t.getPartition()]++;
            }
        }

        printWriter = new PrintWriter(new FileWriter(file, true));
        printWriter.write(program_name + "_" + version + "\n");
        for (int i = 0; i < mutant_stat.length; i++) {
            printWriter.write("Partition" + String.valueOf(i) + "\nThe number of test cases in this partition: " + String.valueOf(tcnum[i]) + "\nFailure Rate: " + String.valueOf((double) mutant_stat[i] / (double) tcnum[i]) + "\n\n");
        }
        printWriter.close();
    }

    public static void main(String[] args) throws IOException {
        String[] program_name = {"Grep", "Gzip", "Make"};
        for (String s : program_name) {
            if (s == "Grep") {
                String[] version = {"v1", "v2", "v3", "v4"};
                for (String v : version) {
                    get_failure_rate(s, v);
                }
            } else if (s == "Gzip") {
                String[] version = {"v1", "v2", "v4", "v5"};
                for (String v : version) {
                    get_failure_rate(s, v);
                }
            } else {
                String[] version = {"v1", "v2"};
                for (String v : version) {
                    get_failure_rate(s, v);
                }
            }
        }
    }
}
