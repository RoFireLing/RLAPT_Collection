package Mutants;


/**
 * describe:
 *
 * @author phantom
 * @date 2019/04/18
 */
public class Mutant {

    //    @Setter
//    @Getter
    private String fullName;

    public Mutant(String fullName) {
        setFullName(fullName);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
