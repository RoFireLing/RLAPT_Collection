package Util;

import _Constant.Constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.io.File.separator;

/**
 * describe:
 *
 * @author phantom
 * @date 2019/04/30
 */
public class RecordResult {

    public static void recordResult(String objectName, int idVersion,
                                    double FAverage, double F2Average) {

        String path = Constant.resultPath + separator + objectName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.write("id-version: " + String.valueOf(idVersion) + "\n");
            printWriter.write("Fmeasure: " + String.valueOf(FAverage) + "\n");
            printWriter.write("F2measure: " + String.valueOf(F2Average) + "\n");
//            printWriter.write("Ftime: " + String.valueOf(TAverage) + "\t");
//            printWriter.write("F2time: " + String.valueOf(T2Average) + "\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void recordResult2(String objectName, int idVersion,
                                     double FAverage, double F2Average,
                                     double TAverage, double T2Average) {

        String path = Constant.resultPath + separator + objectName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.write("id-version: " + String.valueOf(idVersion) + "\n");
            printWriter.write("Fmeasure: " + String.valueOf(FAverage) + "\t");
            printWriter.write("F2measure: " + String.valueOf(F2Average) + "\n");
            printWriter.write("Ftime: " + String.valueOf(TAverage) + "\t");
            printWriter.write("F2time: " + String.valueOf(T2Average) + "\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SpecificResult(String objectName, int id, List F, List F2, List T, List T2) {
        String path = Constant.resultPath + separator + objectName;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.write("id " + id + "\n");
            printWriter.write("F = " + F.toString() + "\n");
            printWriter.write("F2 = " + F2.toString() + "\n");
            printWriter.write("T = " + T.toString() + "\n");
            printWriter.write("T2 = " + T2.toString() + "\n\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
