package Read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class read_txt {
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
