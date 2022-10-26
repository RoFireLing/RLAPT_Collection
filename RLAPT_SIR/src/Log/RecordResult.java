package Log;

import Constant.constant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.io.File.separator;

/**
 * @author RoFire
 * @date 2020/9/24
 **/
public class RecordResult {
    public static void recordResult(String txt_name, int id, double FAverage, double F2Average, double TAverage, double T2Average) {
        String path = constant.result_path + separator + txt_name;
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
            printWriter.write("id: " + String.valueOf(id) + "\n");
            printWriter.write("Fmeasure: " + String.valueOf(FAverage) + ";");
            printWriter.write("F2measure: " + String.valueOf(F2Average) + "\n");
            printWriter.write("Tmeasure: " + String.valueOf(TAverage) + ";");
            printWriter.write("T2measure: " + String.valueOf(T2Average) + "\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SpecificResult(String txt_name, int id, List F, List F2, List T, List T2) {
        String path = constant.result_path + separator + txt_name;
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

    public static void ParamResult(String txt_name, double gamma, int id, double FAverage, double F2Average, double TAverage, double T2Average) {
        String path = constant.param_result_path + separator + txt_name;
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
            printWriter.write("gamma-" + gamma + "\t");
            printWriter.write("id: " + String.valueOf(id) + "\n");
            printWriter.write("Fmeasure: " + String.valueOf(FAverage) + ";");
            printWriter.write("F2measure: " + String.valueOf(F2Average) + "\n");
            printWriter.write("Tmeasure: " + String.valueOf(TAverage) + ";");
            printWriter.write("T2measure: " + String.valueOf(T2Average) + "\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ParamSpecificResult(String txt_name, double gamma, int id, List F, List F2, List T, List T2) {
        String path = constant.param_result_path + separator + txt_name;
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
            printWriter.write("id " + id + " gamma=" + gamma + "\n");
            printWriter.write("F" + F.toString() + "\n");
            printWriter.write("F2" + F2.toString() + "\n");
            printWriter.write("T" + T.toString() + "\n");
            printWriter.write("T2" + T2.toString() + "\n\n");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
