package GenerateTestSuit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class get_partition {
    /**
     * Partition for test cases
     *
     * @param tc
     * @param program_name
     */
    public static void partiton_tc(testcase[] tc, String program_name) {
        if (program_name == "Grep") {
            for (testcase t : tc) {
                String content = t.getContent();
                if (content.indexOf("-E") != -1) {
                    // -E
                    t.setPartition(0);
                } else if (content.indexOf("-F") != -1) {
                    // -F
                    t.setPartition(1);
                } else {
                    // -G or none
                    t.setPartition(2);
                }
            }
        } else if (program_name == "Gzip") {
            for (testcase t : tc) {
                String content = t.getContent();
                if (content.indexOf("subdir") != -1) {
                    // dir
                    t.setPartition(1);
                } else if (content.indexOf("zipfile") != -1 || content.indexOf("tarfile") != -1 || content.indexOf("packfile") != -1) {
                    // pack
                    t.setPartition(2);
                } else if (content.indexOf("binaryfile") != -1) {
                    // binary
                    t.setPartition(3);
                } else {
                    // normal
                    t.setPartition(0);
                }
            }
        } else if (program_name == "Make") {
            for (testcase t : tc) {
                String content = t.getContent();

                // Extract the main part
                String pattern = "-P \\[[^\\]]{0,}\\]{1}";
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(content);
                while (m.find()) {
                    content = m.group();
                }

                if (content.indexOf("-w") != -1) {
                    if (content.indexOf("-s") != -1) {
                        // -w -s
                        t.setPartition(0);
                    } else {
                        // -w
                        t.setPartition(1);
                    }
                } else {
                    if (content.indexOf("-s") != -1) {
                        // -s
                        t.setPartition(2);
                    } else {
                        // none
                        t.setPartition(3);
                    }
                }
            }
        }
    }
}