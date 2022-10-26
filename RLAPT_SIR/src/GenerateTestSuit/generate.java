package GenerateTestSuit;

import Constant.constant;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class generate {
    public static testcase[] generate(String programe_name) {
        /**
         * get tc_path ans tc_num
         */
        String txt_name = "";
        int tc_nums = 0;
        if (programe_name == "Grep") {
            txt_name = constant.grep_tc_path;
            tc_nums = constant.grep_tc_num;
        } else if (programe_name == "Gzip") {
            txt_name = constant.gzip_tc_path;
            tc_nums = constant.gzip_tc_num;
        } else if (programe_name == "Make") {
            txt_name = constant.make_tc_path;
            tc_nums = constant.make_tc_num;
        }

        /**
         * generate tc
         */
        testcase[] tc = new testcase[tc_nums];
        int cnt = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt_name));
            String s = null;
            while ((s = br.readLine()) != null) {
                tc[cnt] = new testcase(cnt, s, 0, null);
                cnt++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tc;
    }
}
