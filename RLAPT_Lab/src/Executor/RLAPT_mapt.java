package Executor;

import ACMS.sourceCode.AirlinesBaggageBillingService;
import CUBS.sourceCode.BillCalculation;
import ERS.sourceCode.ExpenseReimbursementSystem;
import GenerateTestcases.GenerateTestcases;
import Method.Methods4Testing;
import Mutants.Mutant;
import Mutants.UsedMutantsSet;
import Strategy.RLAPT_MAPT;
import TestCase.TestCase4ACMS;
import TestCase.TestCase4CUBS;
import TestCase.TestCase4ERS;
import TestFrame.GetCompleteFrame;
import TestFrame._CompleteTestFrame;
import Util.*;
import _Constant.Constant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RLAPT_mapt implements NewStrategy {
//    public static void main(String[] args) {
//        RLAPT_mapt mapt = new RLAPT_mapt();
//        for (int i = 0; i < 10; i++) {
//            mapt.testing("CUBS", 500, i);
//        }
//    }

    @Override
    public void testing(String objectName, int tcnum, int repeatTimes) {

        //记录时间的对象
        TimeRecorder timeRecorder = new TimeRecorder();

        //记录metrics值的对象
        MeasureRecorder measureRecorder = new MeasureRecorder();

        //提取测试用例
//            List<CompleteTC4ACMS> tcs4acms = new ArrayList<>();
//            List<CompleteTC4CUBS> tcs4cubs = new ArrayList<>();
//            List<CompleteTC4ERS> tcs4ers = new ArrayList<>();
//            if (objectName.equalsIgnoreCase("ACMS")) {
//                tcs4acms = new GetTestcases().gettestcases4ACMS(tcnum);
//            } else if (objectName.equalsIgnoreCase("CUBS")) {
//                tcs4cubs = new GetTestcases().gettestcases4CUBS(tcnum);
//            } else if (objectName.equalsIgnoreCase("ERS")) {
//                tcs4ers = new GetTestcases().gettestcases4ERS(tcnum);
//            } else break;
        List<_CompleteTestFrame> tcs = new ArrayList<>();
        tcs = GetCompleteFrame.getcompleteframe(objectName, tcnum);

        //提取分区信息
        Map<Integer, List<String>> partitioncontent = new HashMap<>();
        for (int t = 0; t < Constant.getPartitionNumber(objectName); t++) {
            partitioncontent.put(t, new ArrayList<String>());
        }
//            if (objectName.equalsIgnoreCase("ACMS")) {
//                for (CompleteTC4ACMS t : tcs4acms) {
//                    partitioncontent.get(t.getPartition()).add(t.getNo());
//                }
//            } else if (objectName.equalsIgnoreCase("CUBS")) {
//                for (CompleteTC4CUBS t : tcs4cubs) {
//                    partitioncontent.get(t.getPartition()).add(t.getNo());
//                }
//            } else if (objectName.equalsIgnoreCase("ERS")) {
//                for (CompleteTC4ERS t : tcs4ers) {
//                    partitioncontent.get(t.getPartition()).add(t.getNo());
//                }
//            } else break;
        for (_CompleteTestFrame tc : tcs) {
            partitioncontent.get(tc.getPartition()).add(tc.getFrame());
        }


        for (int i = 0; i < Constant.repeatNumber; i++) {
            System.out.println("RL-APT4" + objectName + "使用mapt:" +
                    "执行第" + String.valueOf(i + 1) + "次测试：");

            //初始化MAPT
            RLAPT_MAPT mapt = new RLAPT_MAPT();

            //初始化测试剖面
            mapt.initializeMAPT(Constant.getPartitionNumber(objectName));

            //获得变异体集合
            UsedMutantsSet mutantsSet = new UsedMutantsSet(objectName);
            Map<String, Mutant> mutantMap = mutantsSet.getMutants();

            //初始化一个存放杀死的变异体的集合
            Set<String> killedMutants = new HashSet<>();

            //获得待测程序的待测方法名
            String methodName = new Methods4Testing(objectName).getMethodName();

            //初始化一个记录执行的测试用例数目的对象
            int counter = 0;

            //初始化记录时间的对象
            OnceTimeRecord onceTimeRecord = new OnceTimeRecord();

            //初始化记录度量标准值的对象
            OnceMeasureRecord onceMeasureRecord = new OnceMeasureRecord();


            //记录分区号的对象
            int partitionIndex = 0;


            for (int j = 0; j < Constant.number; j++) {
                //计数器自增
                counter++;

                /**开始选择分区和测试用例*/
                long startSelectTestCase = System.nanoTime();
                //选择分区
                if (counter == 1) {
                    partitionIndex = new Random().
                            nextInt(Constant.getPartitionNumber(objectName));
                } else {
                    partitionIndex = mapt.nextPartition4MAPT(partitionIndex);
                }
                long endSelectTestCase = System.nanoTime();

                //记录选择测试用例需要的时间
                if (killedMutants.size() == 0) {
                    onceTimeRecord.firstSelectionTimePlus(endSelectTestCase - startSelectTestCase);
                } else if (killedMutants.size() == 1) {
                    onceTimeRecord.secondSelectionTimePlus(endSelectTestCase - startSelectTestCase);
                }

                //选择测试用例
                TestCase4ACMS tc4acms = new TestCase4ACMS(0, 0, true, 0, 0);
                TestCase4CUBS tc4cubs = new TestCase4CUBS("a", 0, 0, 0);
                TestCase4ERS tc4ers = new TestCase4ERS("manager", 0, 0, 0, 0);
                if (objectName.equalsIgnoreCase("ACMS")) {
//                    tc4acms = tcs4acms.get(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size()))).getTc();
                    tc4acms = GenerateTestcases.acmsgenerateTestCase(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size())));
                } else if (objectName.equalsIgnoreCase("CUBS")) {
//                    tc4cubs = tcs4cubs.get(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size()))).getTc();
                    tc4cubs = GenerateTestcases.cubsgenerateTestCase(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size())));
                } else if (objectName.equalsIgnoreCase("ERS")) {
//                    tc4ers = tcs4ers.get(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size()))).getTc();
                    tc4ers = GenerateTestcases.ersgenerateTestCase(partitioncontent.get(partitionIndex).get(new Random().nextInt(partitioncontent.get(partitionIndex).size())));
                } else break;

                //标志位：表示测试用力是否杀死变异体
                boolean isKilledMutants = false;

                //遍历变异体
                for (Map.Entry<String, Mutant> entry : mutantMap.entrySet()) {
                    if (killedMutants.contains(entry.getKey())) {
                        continue;
                    }
                    Mutant mutant = entry.getValue();
                    Object mutantInstance = null;
                    Method mutantMethod = null;
                    Class mutantClazz = null;
                    try {
                        mutantClazz = Class.forName(mutant.getFullName());
                        Constructor mutantConstructor = mutantClazz.getConstructor();
                        mutantInstance = mutantConstructor.newInstance();

                        if (objectName.equals("ACMS")) {
                            double sourceResult = 0;
                            double expectedResult = 0;
                            mutantMethod = mutantClazz.getMethod(methodName, int.class, int.class,
                                    boolean.class, double.class, double.class);

                            TestCase4ACMS sourceTestCase = tc4acms;

                            //　执行测试用例
//                            long startExecuteTestCase = System.nanoTime();
                            sourceResult = (double) mutantMethod.invoke(mutantInstance,
                                    sourceTestCase.getAirClass(), sourceTestCase.getArea(),
                                    sourceTestCase.isStudent(), sourceTestCase.getLuggage(),
                                    sourceTestCase.getEconomicfee());

                            expectedResult = new AirlinesBaggageBillingService().feeCalculation(sourceTestCase.getAirClass(), sourceTestCase.getArea(),
                                    sourceTestCase.isStudent(), sourceTestCase.getLuggage(),
                                    sourceTestCase.getEconomicfee());
//                            long endExecuteTestCase = System.nanoTime();

                            //　记录测试用例的执行时间
//                            if (killedMutants.size() == 0) {
//                                onceTimeRecord.firstExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            } else if (killedMutants.size() == 1) {
//                                onceTimeRecord.secondExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            }

                            //判断结果是否违反ＭＲ
                            if (sourceResult != expectedResult) {
                                //检测出故障
                                isKilledMutants = true;
                                //检测出第一个故障，记录此时的数据
                                if (killedMutants.size() == 0) {
                                    onceMeasureRecord.FmeasurePlus(counter);
                                }

                                if (killedMutants.size() == 1) {
                                    onceMeasureRecord.F2measurePlus(counter -
                                            onceMeasureRecord.getFmeasure());
                                }
                                killedMutants.add(entry.getKey());
                            }
                        } else if (objectName.equals("CUBS")) {
                            double sourceResult = 0;
                            double expectedResult = 0;
                            mutantMethod = mutantClazz.getMethod(methodName, String.class, int.class,
                                    int.class, int.class);

                            TestCase4CUBS sourceTestCase = tc4cubs;

                            //执行测试用例
//                            long startExecuteTestCase = System.nanoTime();
                            sourceResult = (double) mutantMethod.invoke(mutantInstance,
                                    sourceTestCase.getPlanType(),
                                    sourceTestCase.getPlanFee(), sourceTestCase.getTalkTime(), sourceTestCase.getFlow());

                            expectedResult = new BillCalculation().phoneBillCalculation(sourceTestCase.getPlanType(),
                                    sourceTestCase.getPlanFee(), sourceTestCase.getTalkTime(), sourceTestCase.getFlow());
//                            long endExecuteTestCase = System.nanoTime();

                            //记录测试用例执行需要的时间
//                            if (killedMutants.size() == 0) {
//                                onceTimeRecord.firstExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            } else if (killedMutants.size() == 1) {
//                                onceTimeRecord.secondExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            }

                            if (sourceResult != expectedResult) {
                                //检测出故障
                                isKilledMutants = true;
                                if (killedMutants.size() == 0) {
                                    onceMeasureRecord.FmeasurePlus(counter);

                                }

                                if (killedMutants.size() == 1) {
                                    onceMeasureRecord.F2measurePlus(counter -
                                            onceMeasureRecord.getFmeasure());
                                }
                                killedMutants.add(entry.getKey());
                            }
                        } else if (objectName.equals("ERS")) {
                            double sourceResult = 0;
                            double expectedResult = 0;
                            mutantMethod = mutantClazz.getMethod(methodName, String.class, double.class,
                                    double.class, double.class, double.class);

                            TestCase4ERS sourceTestCase = tc4ers;

                            //执行测试用例
//                            long startExecuteTestCase = System.nanoTime();
                            sourceResult = (double) mutantMethod.invoke(mutantInstance, sourceTestCase.getStafflevel(),
                                    sourceTestCase.getActualmonthlymileage(), sourceTestCase.getMonthlysalesamount(),
                                    sourceTestCase.getAirfareamount(), sourceTestCase.getOtherexpensesamount());

                            expectedResult = new ExpenseReimbursementSystem().calculateReimbursementAmount(sourceTestCase.getStafflevel(),
                                    sourceTestCase.getActualmonthlymileage(), sourceTestCase.getMonthlysalesamount(),
                                    sourceTestCase.getAirfareamount(), sourceTestCase.getOtherexpensesamount());
//                            long endExecuteTestCase = System.nanoTime();

                            //记录执行测试用例需要的时间
//                            if (killedMutants.size() == 0) {
//                                onceTimeRecord.firstExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            } else if (killedMutants.size() == 1) {
//                                onceTimeRecord.secondExecutingTime(endExecuteTestCase - startExecuteTestCase);
//                            }

                            if (sourceResult != expectedResult) {
                                //检测出故障
                                isKilledMutants = true;
                                if (killedMutants.size() == 0) {
                                    onceMeasureRecord.FmeasurePlus(counter);
                                }
                                if (killedMutants.size() == 1) {
                                    onceMeasureRecord.F2measurePlus(counter -
                                            onceMeasureRecord.getFmeasure());
                                }
                                killedMutants.add(entry.getKey());
                            }
                        } else break;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (killedMutants.size() == Constant.getMutantsNumber(objectName))
                    break;

                //调整测试剖面
                long startExecuteTestCase = System.nanoTime();
                mapt.adjustMAPT(partitionIndex, isKilledMutants);
                long endExecuteTestCase = System.nanoTime();

                //记录调整测试剖面需要的时间
                if (killedMutants.size() == 0) {
                    onceTimeRecord.firstExecutingTime(endExecuteTestCase - startExecuteTestCase);
                } else if (killedMutants.size() == 1) {
                    onceTimeRecord.secondExecutingTime(endExecuteTestCase - startExecuteTestCase);
                }
            }
            if ((Constant.getMutantsNumber(objectName) == 1 && onceMeasureRecord.getFmeasure() != 0) || onceMeasureRecord.getF2measure() != 0) {
                measureRecorder.addFMeasure(onceMeasureRecord.getFmeasure());
                measureRecorder.addF2Measure(onceMeasureRecord.getF2measure());

                //记录相应的测试用例选择、生成和执行的时间
                timeRecorder.addFirstSelectTestCase(onceTimeRecord.getFirstSelectingTime());
                timeRecorder.addFirstGenerateTestCase(onceTimeRecord.getFirstGeneratingTime());
                timeRecorder.addFirstExecuteTestCase(onceTimeRecord.getFirstExecutingTime());
                timeRecorder.addSecondSelectTestCase(onceTimeRecord.getSecondSelectingTime());
                timeRecorder.addSecondGenerateTestCase(onceTimeRecord.getSecondGeneratingTime());
                timeRecorder.addSecondExecuteTestCase(onceTimeRecord.getSecondExecutingTime());
            }
            if (measureRecorder.getFmeasureArray().size() < 30 && i == Constant.repeatNumber - 1) {
                i--;
            }
        }
        //记录均值结果方便查看
        String txtLogName = "MAPT4" + objectName + "#" + tcnum + ".txt";
        double FT = timeRecorder.getAverageExecuteFirstTestCaseTime() + timeRecorder.getAverageGenerateFirstTestCaseTime() + timeRecorder.getAverageSelectFirstTestCaseTime();
        double F2T = timeRecorder.getAverageExecuteSecondTestCaseTime() + timeRecorder.getAverageGenerateSecondTestCaseTime() + timeRecorder.getAverageSelectSecondTestCaseTime();
        RecordResult.recordResult2(txtLogName, repeatTimes, measureRecorder.getAverageFmeasure(),
                measureRecorder.getAverageF2measure(), FT, F2T);
        txtLogName = "MAPT4" + objectName + "#" + tcnum + "_Contents.txt";
        RecordResult.SpecificResult(txtLogName, repeatTimes, measureRecorder.getFmeasureArray(), measureRecorder.getF2measureArray(), timeRecorder.getFirstTotalArray(), timeRecorder.getSecondTotalArray());
    }
}