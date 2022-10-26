package Executor;

/**
 * @author RoFire
 * @date 2020/10/6
 **/
public class test_factory {
    public test getTest(String testName) {
        if (testName == null) return null;
        if (testName.equalsIgnoreCase("DRT")) return new drt();
        else if (testName.equalsIgnoreCase("RPT")) return new rpt();
        else if (testName.equalsIgnoreCase("MAPT")) return new mapt();
        else if (testName.equalsIgnoreCase("RAPT")) return new rapt();
        else if (testName.equalsIgnoreCase("RLAPT_Q")) return new rlapt_q();
        else if (testName.equalsIgnoreCase("RLAPT_S")) return new rlapt_s();
        return null;
    }
}
