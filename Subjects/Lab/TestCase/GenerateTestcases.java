package TestCase;

public class GenerateTestcases {
    public static TestCase4ACMS[] createTestSuite4ACMS(int tcnum) {
        TestCase4ACMS[] testcases = new TestCase4ACMS[tcnum];
        Random random = new Random(0);
        Boolean[] ISSTUDENT = {true, false};
        for (int i = 0; i < tcnum; i++) {
            boolean isStudent = ISSTUDENT[random.nextInt(2)];
            int airClass = 0;
            if (isStudent) {
                airClass = 2;
            } else {
                airClass = random.nextInt(4);
            }
            int area = random.nextInt(2);

            double luggage = random.nextDouble() * 80;
            double economicfee = random.nextDouble() * 5000 + 500;
            TestCase4ACMS tc = new TestCase4ACMS(airClass, area, isStudent, luggage, economicfee);
            testcases[i] = tc;
        }
        return testcases;
    }

    public static TestCase4CUBS[] createTestSuite4CUBS(int tcnum) {
        TestCase4CUBS[] testcases = new TestCase4CUBS[tcnum];
        Random random = new Random(0);
        String[] types = {"A", "B", "a", "b"};
        int[] Achoices = {46, 96, 286, 886};
        int[] Bchoices = {46, 96, 126, 186};
        for (int i = 0; i < tcnum; i++) {
            String planType = types[random.nextInt(4)];
            int planFee = 0;
            if (planType == "A" || planType == "a") {
                planFee = Achoices[random.nextInt(4)];
            } else {
                planFee = Bchoices[random.nextInt(4)];
            }
            int talkTime = random.nextInt(4000);
            int flow = random.nextInt(4000);

            TestCase4CUBS tc = new TestCase4CUBS(planType, planFee, talkTime, flow);
            testcases[i] = tc;
        }
        return testcases;
    }

    public static TestCase4ERS[] createTestSuite4ERS(int tcnum) {
        TestCase4ERS[] testcases = new TestCase4ERS[tcnum];
        Random random = new Random(0);
        String[] levels = {"seniormanager", "manager", "supervisor"};
        for (int i = 0; i < tcnum; i++) {
            String stafflevel = levels[random.nextInt(3)];
            double actualmonthlymileage = random.nextDouble() * 8000;
            double monthlysalesamount = random.nextDouble() * 150000;
            double airfareamount = random.nextDouble() * 10000;
            double otherexpensesamount = random.nextDouble() * 10000;
            TestCase4ERS tc = new TestCase4ERS(stafflevel, actualmonthlymileage,
                    monthlysalesamount, airfareamount, otherexpensesamount);
            testcases[i] = tc;
        }
        return testcases;
    }
}
