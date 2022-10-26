package GenerateTestcases;

import TestCase.TestCase4ACMS;
import TestCase.TestCase4CUBS;
import TestCase.TestCase4ERS;

/**
 * @author RoFire
 * @date 2021/1/25
 **/
public class Partition {
    public static int partition4ACMS(TestCase4ACMS tc) {
        int partitionindex = -1;
        if (tc.getAirClass() == 0) {
            if (tc.getArea() == 0) partitionindex = 0;
            else partitionindex = 1;
        } else if (tc.getAirClass() == 1) {
            if (tc.getArea() == 0) partitionindex = 2;
            else partitionindex = 3;
        } else if (tc.getAirClass() == 2) {
            if (tc.getArea() == 0) partitionindex = 4;
            else partitionindex = 5;
        } else if (tc.getAirClass() == 3) {
            if (tc.getArea() == 0) partitionindex = 6;
            else partitionindex = 7;
        }
        return partitionindex;
    }

    public static int partition4CUBS(TestCase4CUBS tc) {
        String planType = tc.getPlanType();
        int planFee = tc.getPlanFee();
        int talkTimeBench = 0;
        int flowBench = 0;
        if (planType.equalsIgnoreCase("A") && planFee == 46) {
            talkTimeBench = 50;
            flowBench = 150;
        } else if (planType.equalsIgnoreCase("A") && planFee == 96) {
            talkTimeBench = 96;
            flowBench = 240;
        } else if (planType.equalsIgnoreCase("A") && planFee == 286) {
            talkTimeBench = 286;
            flowBench = 900;
        } else if (planType.equalsIgnoreCase("A") && planFee == 886) {
            talkTimeBench = 3000;
            flowBench = 3000;
        } else if (planType.equalsIgnoreCase("B") && planFee == 46) {
            talkTimeBench = 120;
            flowBench = 40;
        } else if (planType.equalsIgnoreCase("B") && planFee == 96) {
            talkTimeBench = 450;
            flowBench = 80;
        } else if (planType.equalsIgnoreCase("B") && planFee == 126) {
            talkTimeBench = 680;
            flowBench = 100;
        } else if (planType.equalsIgnoreCase("B") && planFee == 186) {
            talkTimeBench = 1180;
            flowBench = 150;
        }

        int partitionindex = -1;
        if (planType.equalsIgnoreCase("A")) {
            if (tc.getTalkTime() < talkTimeBench) partitionindex = 0;
            else partitionindex = 1;
        } else if (planType.equalsIgnoreCase("B")) {
            if (tc.getTalkTime() < talkTimeBench) partitionindex = 2;
            else partitionindex = 3;
        }
        return partitionindex;
    }

    public static int partition4ERS(TestCase4ERS tc) {
        int partitionindex = -1;
        String stafflevel = tc.getStafflevel();
        double sale = tc.getMonthlysalesamount();
        if (stafflevel == "seniormanager") {
            if (sale <= 50000) partitionindex = 0;
            else if (sale <= 80000) partitionindex = 1;
            else if (sale <= 100000) partitionindex = 2;
            else partitionindex = 3;
        } else if (stafflevel == "manager") {
            if (sale <= 50000) partitionindex = 4;
            else if (sale <= 80000) partitionindex = 5;
            else if (sale <= 100000) partitionindex = 6;
            else partitionindex = 7;
        } else if (stafflevel == "supervisor") {
            if (sale <= 50000) partitionindex = 8;
            else if (sale <= 80000) partitionindex = 9;
            else if (sale <= 100000) partitionindex = 10;
            else partitionindex = 11;
        }
        return partitionindex;
    }
}
