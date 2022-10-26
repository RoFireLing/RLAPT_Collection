package Executor;

import Constant.constant;

import java.util.Random;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class executor {
    /**
     * initialize test sequence
     */
    public static void genearate_testseq() {
        int[] testseq = new int[constant.testcasenum];
        for (int s = 0; s < testseq.length; s++) {
            testseq[s] = new Random().nextInt(1000);
        }
        constant.setTestseq(testseq);
    }

    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {
        genearate_testseq();
        String program_name = "Make";
        String version = "v1";

        // Design Pattern-Factory Pattern
//        test_factory tcf = new test_factory();
//        String[] strategy = {"RPT", "DRT", "MAPT", "RAPT", "RLAPT_Q", "RLAPT_S"};
//        for (int repeatTime = 0; repeatTime < 1; repeatTime++) {
//            for (String s : strategy) {
//                test executor = tcf.getTest(s);
//                executor.executeTestCase(program_name, version, repeatTime);
//            }
//        }

//        rlapt_q qtest = new rlapt_q();
//        for (int repeatTime = 0; repeatTime < 50; repeatTime++) {
//            qtest.executeTestCase(program_name, version, repeatTime);
//        }
//        rlapt_s stest = new rlapt_s();
//        for (int repeatTime = 0; repeatTime < 1; repeatTime++) {
//            stest.executeTestCase(program_name, version, repeatTime);
//        }
//        mapt mtest = new mapt();
//        for (int repeatTime = 0; repeatTime < 1; repeatTime++) {
//            mtest.executeTestCase(program_name, version, repeatTime);
//        }
//        rapt rtest = new rapt();
//        for (int repeatTime = 0; repeatTime < 1; repeatTime++) {
//            rtest.executeTestCase(program_name, version, repeatTime);
//        }
//        drt dtest = new drt();
//        for (int repeatTime = 0; repeatTime < 50; repeatTime++) {
//            dtest.executeTestCase(program_name, version, repeatTime);
//        }
//        rpt rptest = new rpt();
//        for (int repeatTime = 0; repeatTime < 1; repeatTime++) {
//            rptest.executeTestCase(program_name, version, repeatTime);
//        }
        ddtrkm ddtest = new ddtrkm();
        for (int repeatTime = 0; repeatTime < 50; repeatTime++) {
            ddtest.executeTestCase(program_name, version, repeatTime);
        }
    }
}
