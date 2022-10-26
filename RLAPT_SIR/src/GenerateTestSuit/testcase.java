package GenerateTestSuit;

import java.util.List;

/**
 * @author RoFire
 * @date 2020/9/21
 **/
public class testcase {
    private int test_no;
    private String content;
    private int partition;
    private List<Integer> killableMutants;

    public testcase(int test_no, String content, int partition, List<Integer> KillableMutants) {
        this.test_no = test_no;
        this.content = content;
        this.partition = partition;
        this.killableMutants = KillableMutants;
    }

    @Override
    public String toString() {
        return "testcase{" +
                "test_no=" + test_no +
                ", content='" + content + '\'' +
                ", partition=" + partition +
                ", killableMutants=" + killableMutants +
                '}';
    }

    public int getTest_no() {
        return test_no;
    }

    public void setTest_no(int test_no) {
        this.test_no = test_no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public List<Integer> getKillableMutants() {
        return killableMutants;
    }

    public void setKillableMutants(List<Integer> killableMutants) {
        this.killableMutants = killableMutants;
    }
}
