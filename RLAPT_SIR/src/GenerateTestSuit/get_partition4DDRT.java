package GenerateTestSuit;

import Constant.constant;

import java.io.BufferedReader;
import java.io.FileReader;

public class get_partition4DDRT {
    /**
     * Get partition for test cases in DDRT
     *
     * @param tc
     * @param program_name
     */
    public static void partiton_tc(testcase[] tc, String program_name) {
        // Get partition
        String txt_name = constant.tc_path4DDRT + program_name + "_tc.txt";
//        HashMap<String, Integer> partition_info = new HashMap<>();
        int[] patition_info = new int[constant.get_tc_num(program_name)];
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt_name));
            String s = null;
            int idx = 0;
            while ((s = br.readLine()) != null) {
//                partition_info.put(s.substring(2), (int) s.charAt(0) - 47);
                patition_info[idx] = Integer.parseInt(s.split(";")[0]);
                idx++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set partition
        for (int i = 0; i < tc.length; i++) {
            testcase t = tc[i];
            t.setPartition(patition_info[i]);
        }
    }
}
