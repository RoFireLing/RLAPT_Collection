package TestCase;

/**
 * describe:
 * test case
 *
 * @author phantom
 * @date 2019/04/17
 */

public class TestCase4ACMS {
    private int airClass;
    private int area;
    private boolean isStudent;
    private double luggage;
    private double economicfee;

    public TestCase4ACMS(int airClass, int area, boolean isStudent, double luggage, double economicfee) {
        setAirClass(airClass);
        setArea(area);
        setLuggage(luggage);
        setStudent(isStudent);
        setEconomicfee(economicfee);
    }

    public int getAirClass() {
        return airClass;
    }

    public void setAirClass(int airClass) {
        this.airClass = airClass;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public double getLuggage() {
        return luggage;
    }

    public void setLuggage(double luggage) {
        this.luggage = luggage;
    }

    public double getEconomicfee() {
        return economicfee;
    }

    public void setEconomicfee(double economicfee) {
        this.economicfee = economicfee;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("airClass =" + String.valueOf(getAirClass()) + " ");
//        stringBuffer.append("area =" + String.valueOf(getArea()) + " ");
//        stringBuffer.append("luggage =" + String.valueOf(luggage) + " ");
//        stringBuffer.append("economicfee =" + String.valueOf(economicfee) + " ");
//        stringBuffer.append("isStudent =" + String.valueOf(isStudent) + " ");
        stringBuffer.append(String.valueOf(getAirClass()) + ";");
        stringBuffer.append(String.valueOf(getArea()) + ";");
        stringBuffer.append(String.valueOf(luggage) + ";");
        stringBuffer.append(String.valueOf(economicfee) + ";");
        stringBuffer.append(String.valueOf(isStudent) + "");

        return stringBuffer.toString();
    }
}
