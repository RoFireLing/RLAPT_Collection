package GenerateTestSuit;

import Constant.constant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class get_fault_matrix {
    /**
     * Get the killable mutant index of the test case
     *
     * @param tc
     * @param program_name
     * @param version
     */
    public static void get_fm(testcase[] tc, String program_name, String version) {
        String txt_name = "";
        try {
            Class global = Class.forName("Constant.constant");
            Constructor c = global.getConstructor();
            Object g = c.newInstance();
            Method name = global.getDeclaredMethod("get" + program_name + "_fm_" + version + "_path", null);
            txt_name = (String) name.invoke(g);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        int cnt = 0;
        int all_mutant_num = constant.get_all_mutant_num(program_name, version);
        try {
            BufferedReader br = new BufferedReader(new FileReader(txt_name));
            String s = null;
            while ((s = br.readLine()) != null) {
                int v = 1;
                List<Integer> mutant = new ArrayList<>();
                while (v <= all_mutant_num) {
                    String vno = br.readLine();
                    String check = br.readLine();
                    if (check.indexOf("1") != -1) {
                        mutant.add(Integer.valueOf(vno.substring(1, vno.length() - 1)));
                    }
                    v++;
                }
                tc[cnt].setKillableMutants(mutant);
                cnt++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
