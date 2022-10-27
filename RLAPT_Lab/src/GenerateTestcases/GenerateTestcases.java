package GenerateTestcases;

import TestCase.*;
import TestFrame.GetFrame;
import _Constant.Constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

import static java.io.File.separator;

/**
 * @author RoFire
 * @date 2021/1/25
 **/
public class GenerateTestcases {
    public static CompleteTC4ACMS[] generate4ACMS(int tcnum) {
        Random random = new Random(0);
        int[] frameset = new int[tcnum];
        for (int i = 0; i < frameset.length; i++) {
            frameset[i] = random.nextInt(Constant.numberofacmsframes);
        }
        TestCase4ACMS[] tc = createTestSuite4ACMS(tcnum, frameset);
        CompleteTC4ACMS[] finaltc = new CompleteTC4ACMS[tcnum];
        int partitionindex = -1;
        int partition[] = new int[Constant.getPartitionNumber("ACMS")];
        for (int i = 0; i < finaltc.length; i++) {
            partitionindex = new Partition().partition4ACMS(tc[i]);
            partition[partitionindex]++;
            finaltc[i] = new CompleteTC4ACMS(i, partitionindex, tc[i], frameset[i]);
        }
        writePartitionStat("ACMS", tcnum, Arrays.toString(partition));
        return finaltc;
    }

    public static CompleteTC4CUBS[] generate4CUBS(int tcnum) {
        Random random = new Random(0);
        int[] frameset = new int[tcnum];
        for (int i = 0; i < frameset.length; i++) {
            frameset[i] = random.nextInt(Constant.numberofcubsframes);
        }
        TestCase4CUBS[] tc = createTestSuite4CUBS(tcnum, frameset);
        CompleteTC4CUBS[] finaltc = new CompleteTC4CUBS[tcnum];
        int partitionindex = -1;
        int partition[] = new int[Constant.getPartitionNumber("CUBS")];
        for (int i = 0; i < finaltc.length; i++) {
            partitionindex = new Partition().partition4CUBS(tc[i]);
            partition[partitionindex]++;
            finaltc[i] = new CompleteTC4CUBS(i, partitionindex, tc[i], frameset[i]);
        }
        writePartitionStat("CUBS", tcnum, Arrays.toString(partition));
        return finaltc;
    }

    public static CompleteTC4ERS[] generate4ERS(int tcnum) {
        Random random = new Random(0);
        int[] frameset = new int[tcnum];
        for (int i = 0; i < frameset.length; i++) {
            frameset[i] = random.nextInt(Constant.numberofersframes);
        }
        TestCase4ERS[] tc = createTestSuite4ERS(tcnum, frameset);
        CompleteTC4ERS[] finaltc = new CompleteTC4ERS[tcnum];
        int partitionindex = -1;
        int partition[] = new int[Constant.getPartitionNumber("ERS")];
        for (int i = 0; i < finaltc.length; i++) {
            partitionindex = new Partition().partition4ERS(tc[i]);
            partition[partitionindex]++;
            finaltc[i] = new CompleteTC4ERS(i, partitionindex, tc[i], frameset[i]);
        }
        writePartitionStat("ERS", tcnum, Arrays.toString(partition));
        return finaltc;
    }

    public static TestCase4ACMS[] createTestSuite4ACMS(int tcnum, int[] frameset) {
        TestCase4ACMS[] testcases = new TestCase4ACMS[tcnum];
        Random random = new Random(0);
//        Boolean[] ISSTUDENT = {true, false};
//        for (int i = 0; i < tcnum; i++) {
//            boolean isStudent = ISSTUDENT[random.nextInt(2)];
//            int airClass = 0;
//            if (isStudent) {
//                airClass = 2;
//            } else {
//                airClass = random.nextInt(4);
//            }
//            int area = random.nextInt(2);
//
//            double luggage = random.nextDouble() * 80;
//            double economicfee = random.nextDouble() * 5000 + 500;
//            TestCase4ACMS tc = new TestCase4ACMS(airClass, area, isStudent, luggage, economicfee);
//            testcases[i] = tc;
//        }
        GetFrame frame = new GetFrame();
        for (int i = 0; i < testcases.length; i++) {
            testcases[i] = new GenerateTestcases().acmsgenerateTestCase(frame.getframe("ACMS", frameset[i]));
        }
        return testcases;
    }

    public static TestCase4CUBS[] createTestSuite4CUBS(int tcnum, int[] frameset) {
        TestCase4CUBS[] testcases = new TestCase4CUBS[tcnum];
//        Random random = new Random(0);
//        String[] types = {"A", "B", "a", "b"};
//        int[] Achoices = {46, 96, 286, 886};
//        int[] Bchoices = {46, 96, 126, 186};
//        for (int i = 0; i < tcnum; i++) {
//            String planType = types[random.nextInt(4)];
//            int planFee = 0;
//            if (planType == "A" || planType == "a") {
//                planFee = Achoices[random.nextInt(4)];
//            } else {
//                planFee = Bchoices[random.nextInt(4)];
//            }
//            int talkTime = random.nextInt(4000);
//            int flow = random.nextInt(4000);
//
//            TestCase4CUBS tc = new TestCase4CUBS(planType, planFee, talkTime, flow);
//            testcases[i] = tc;
//        }
        GetFrame frame = new GetFrame();
        for (int i = 0; i < testcases.length; i++) {
            testcases[i] = new GenerateTestcases().cubsgenerateTestCase(frame.getframe("CUBS", frameset[i]));
        }
        return testcases;
    }

    public static TestCase4ERS[] createTestSuite4ERS(int tcnum, int[] frameset) {
        TestCase4ERS[] testcases = new TestCase4ERS[tcnum];
        Random random = new Random(0);
//        String[] levels = {"seniormanager", "manager", "supervisor"};
//        for (int i = 0; i < tcnum; i++) {
//            String stafflevel = levels[random.nextInt(3)];
//            double actualmonthlymileage = random.nextDouble() * 8000;
//            double monthlysalesamount = random.nextDouble() * 150000;
//            double airfareamount = random.nextDouble() * 10000;
//            double otherexpensesamount = random.nextDouble() * 10000;
//            TestCase4ERS tc = new TestCase4ERS(stafflevel, actualmonthlymileage,
//                    monthlysalesamount, airfareamount, otherexpensesamount);
//            testcases[i] = tc;
//        }
        GetFrame frame = new GetFrame();
        for (int i = 0; i < testcases.length; i++) {
            testcases[i] = new GenerateTestcases().ersgenerateTestCase(frame.getframe("ERS", frameset[i]));
        }
        return testcases;
    }

    public static void writeInfo(String Objectname, int num, String content) {
        String content_path = Constant.testcaseinfo + separator + Objectname + "#" + String.valueOf(num);

        File file = new File(content_path);

        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.write(content + "\n");
        printWriter.close();
    }

    public static void writePartitionStat(String Objectname, int num, String stat) {
        String partition_path = Constant.testcaseinfo + separator + "_PartitionInfo";

        File file = new File(partition_path);

        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        printWriter.write(Objectname + "#" + String.valueOf(num) + "\t\t");
        printWriter.write(stat + "\n");
        printWriter.close();
    }

    public static void main(String[] args) {
        int tcnum = 500;

        CompleteTC4ACMS[] tc = generate4ACMS(tcnum);
        for (CompleteTC4ACMS t : tc) {
            writeInfo("ACMS", tcnum, t.toString());
        }
        CompleteTC4CUBS[] tc2 = generate4CUBS(tcnum);
        for (CompleteTC4CUBS t : tc2) {
            writeInfo("CUBS", tcnum, t.toString());
        }
        CompleteTC4ERS[] tc3 = generate4ERS(tcnum);
        for (CompleteTC4ERS t : tc3) {
            writeInfo("ERS", tcnum, t.toString());
        }
    }
}
