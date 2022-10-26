package GenerateTestcases;

import TestCase.*;
import _Constant.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RoFire
 * @date 2021/1/26
 **/
public class GetTestcases {
    public static List<CompleteTC4ACMS> gettestcases4ACMS(int num) {
        String path = Constant.getTcpath();
        if (num == 500) {
            path += "ACMS#500";
        } else if (num == 1000) {
            path += "ACMS#1000";
        } else if (num == 1500) {
            path += "ACMS#1500";
        } else if (num == 2000) {
            path += "ACMS#2000";
        } else path = "";

        File file = new File(path);

        List<CompleteTC4ACMS> tcs = new ArrayList<CompleteTC4ACMS>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String tempStr = "";
            int index = 0;
            while ((tempStr = bufferedReader.readLine()) != null) {
                int no = Integer.parseInt(tempStr.split(";")[0]);
                int partition = Integer.parseInt(tempStr.split(";")[1]);
                int airclass = Integer.parseInt(tempStr.split(";")[2]);
                int area = Integer.parseInt(tempStr.split(";")[3]);
                double luggage = Double.parseDouble(tempStr.split(";")[4]);
                double fee = Double.parseDouble(tempStr.split(";")[5]);
                String isStudent = tempStr.split(";")[6];
                int frameindex = Integer.parseInt(tempStr.split(";")[7]);

                TestCase4ACMS tc = new TestCase4ACMS(airclass, area, (isStudent == "true"), luggage, fee);
                CompleteTC4ACMS finaltc = new CompleteTC4ACMS(no, partition, tc, frameindex);
                tcs.add(finaltc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tcs;
    }

    public static List<CompleteTC4CUBS> gettestcases4CUBS(int num) {
        String path = Constant.getTcpath();
        if (num == 500) {
            path += "CUBS#500";
        } else if (num == 1000) {
            path += "CUBS#1000";
        } else if (num == 1500) {
            path += "CUBS#1500";
        } else if (num == 2000) {
            path += "CUBS#2000";
        } else path = "";

        File file = new File(path);

        List<CompleteTC4CUBS> tcs = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String tempStr = "";
            int index = 0;
            while ((tempStr = bufferedReader.readLine()) != null) {
                int no = Integer.parseInt(tempStr.split(";")[0]);
                int partition = Integer.parseInt(tempStr.split(";")[1]);
                String plantype = tempStr.split(";")[2];
                int planfee = Integer.parseInt(tempStr.split(";")[3]);
                int talktime = Integer.parseInt(tempStr.split(";")[4]);
                int flow = Integer.parseInt(tempStr.split(";")[5]);
                int frameindex = Integer.parseInt(tempStr.split(";")[6]);

                TestCase4CUBS tc = new TestCase4CUBS(plantype, planfee, talktime, flow);
                CompleteTC4CUBS finaltc = new CompleteTC4CUBS(no, partition, tc, frameindex);
                tcs.add(finaltc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tcs;
    }

    public static List<CompleteTC4ERS> gettestcases4ERS(int num) {
        String path = Constant.getTcpath();
        if (num == 500) {
            path += "ERS#500";
        } else if (num == 1000) {
            path += "ERS#1000";
        } else if (num == 1500) {
            path += "ERS#1500";
        } else if (num == 2000) {
            path += "ERS#2000";
        } else path = "";

        File file = new File(path);

        List<CompleteTC4ERS> tcs = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String tempStr = "";
            int index = 0;
            while ((tempStr = bufferedReader.readLine()) != null) {
                int no = Integer.parseInt(tempStr.split(";")[0]);
                int partition = Integer.parseInt(tempStr.split(";")[1]);
                String stafflevel = tempStr.split(";")[2];
                double act = Double.parseDouble(tempStr.split(";")[3]);
                double mon = Double.parseDouble(tempStr.split(";")[4]);
                double air = Double.parseDouble(tempStr.split(";")[5]);
                double oth = Double.parseDouble(tempStr.split(";")[6]);
                int frameindex = Integer.parseInt(tempStr.split(";")[7]);

                TestCase4ERS tc = new TestCase4ERS(stafflevel, act, mon, air, oth);
                CompleteTC4ERS finaltc = new CompleteTC4ERS(no, partition, tc, frameindex);
                tcs.add(finaltc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tcs;
    }
}
