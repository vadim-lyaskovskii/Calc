import java.util.ArrayList;

public class ArithmeticExpression {

    private static String calc;

    public ArithmeticExpression(String calc) {
        ArithmeticExpression.calc = calc;
    }

    public boolean isDivByZero() {
        ArrayList<String> numList = toListArithmeticExpression();
        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i).equals("/") && numList.get(i + 1).equals("0.0")) {
                return true;
            }
        }
        return false;
    }

    public double outputArithmeticExpression() {
        ArrayList<String> numList = toListArithmeticExpression();
        int indexI = 0;
        int indexJ = 0;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i).equals(")")) {
                indexI = i;
                for (int j = i; j >= 0; j--) {
                    if (numList.get(j).equals("(")) {
                        indexJ = j;
                        break;
                    }
                }
                for (int j = 1; j < indexI - indexJ; j++) {
                    temp.add(numList.get(j + indexJ));
                }

                if (indexI >= indexJ) {
                    numList.subList(indexJ, indexI + 1).clear();
                }
                if (temp.get(0).equals("-")) {
                    temp.remove(0);
                    if (Double.parseDouble(temp.get(0)) <= 0) {
                        temp.set(0, String.valueOf(Math.abs(Double.parseDouble(temp.get(0)))));
                    } else {
                        temp.set(0, String.valueOf(-Math.abs(Double.parseDouble(temp.get(0)))));
                    }
                }
                numList.add((indexJ), String.valueOf(calc(temp)));
                temp.clear();
                i = 0;
            }
        }
        if(numList.get(0).equals("-")) {
            numList.remove(0);
            if (Double.parseDouble(numList.get(0)) <= 0) {
                numList.set(0, String.valueOf(Math.abs(Double.parseDouble(numList.get(0)))));
            } else {
                numList.set(0, String.valueOf(-Math.abs(Double.parseDouble(numList.get(0)))));
            }
        }
        return calc(numList);
    }

    private static double calc(ArrayList<String> numList) {
        double num;
        for (int i = 0; i < numList.size(); i++) {
            switch (numList.get(i)) {
                case "*":
                    num = Double.parseDouble(numList.get(i - 1)) * Double.parseDouble(numList.get(i + 1));
                    numList.subList(i - 1, i + 2).clear();
                    numList.add((i - 1), String.valueOf(num));
                    i--;
                    break;
                case "/":
                    num = Double.parseDouble(numList.get(i - 1)) / Double.parseDouble(numList.get(i + 1));
                    numList.subList(i - 1, i + 2).clear();
                    numList.add((i - 1), String.valueOf(num));
                    i--;
                    break;
            }
        }
        for (int i = 0; i < numList.size(); i++) {
            switch (numList.get(i)) {
                case "-":
                    num = Double.parseDouble(numList.get(i - 1)) - Double.parseDouble(numList.get(i + 1));
                    numList.subList(i - 1, i + 2).clear();
                    numList.add((i - 1), String.valueOf(num));
                    i--;
                    break;
                case "+":
                    num = Double.parseDouble(numList.get(i - 1)) + Double.parseDouble(numList.get(i + 1));
                    numList.subList(i - 1, i + 2).clear();
                    numList.add((i - 1), String.valueOf(num));
                    i--;
                    break;
            }
        }
        return Double.parseDouble(numList.get(0));
    }

    private static ArrayList<String> toListArithmeticExpression() {
        ArrayList<String> alNum = new ArrayList<String>();
        StringBuilder sbNum = new StringBuilder();
        for (int i = 0; i < calc.length(); i++) {
            if (calc.charAt(i) >= '0' && calc.charAt(i) <= '9' || calc.charAt(i) == '.') {
                sbNum.append(calc.charAt(i));
            } else {
                if (!sbNum.toString().isEmpty()) {
                    alNum.add(String.valueOf(Double.parseDouble(sbNum.toString())));
                    sbNum.delete(0, sbNum.length());
                }
                alNum.add(String.valueOf(calc.charAt(i)));
            }
        }
        if (!sbNum.toString().isEmpty()) {
            alNum.add(String.valueOf(Double.parseDouble(sbNum.toString())));
            sbNum.delete(0, sbNum.length());
        }
        return alNum;
    }
}
