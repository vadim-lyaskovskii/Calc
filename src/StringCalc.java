import java.util.ArrayList;

public class StringCalc {

    private static final String str = new String(new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '.', '(', ')', '/', '*', '-', '+'});

    private static String calc;

    public StringCalc(String calc) {
        StringCalc.calc = calc;
    }

    private static boolean compareCalcToStr() {
        int sumChar = 0;
        for (int i = 0; i < calc.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (calc.charAt(i) == str.charAt(j)) {
                    sumChar++;
                    break;
                }
            }
        }
        return sumChar == calc.length();
    }

    private static boolean isNum() {
        ArrayList<String> alNum = new ArrayList<>();
        StringBuilder sbNum = new StringBuilder();
        for (int i = 0; i < calc.length(); i++) {
            if ((calc.charAt(i) >= '0' && calc.charAt(i) <= '9') || calc.charAt(i) == '.') {
                sbNum.append(calc.charAt(i));
            } else if (!sbNum.toString().isEmpty()){
                alNum.add(sbNum.toString());
                sbNum.delete(0, sbNum.length());
            }
        }
        if (!sbNum.toString().isEmpty()){
            alNum.add(sbNum.toString());
            sbNum.delete(0, sbNum.length());
        }
        int tempDot = 0;
        for (String s : alNum) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '.') {
                    tempDot++;
                }
            }
            if (tempDot > 1) {
                return false;
            }
            tempDot = 0;
        }
        return true;
    }

    private static boolean isNumBrackets() {
        int tempOpenParenthesis = 0;
        int tempCloseParenthesis = 0;
        for (int i = 0; i < calc.length(); i++) {
            if (calc.charAt(i) == '(') {
                tempOpenParenthesis++;
            } else if ( calc.charAt(i) == ')') {
                tempCloseParenthesis++;
            }
        }
        return tempOpenParenthesis == tempCloseParenthesis;
    }

    private static boolean isExtraBrackets() {
        int indexI = 0;
        int indexJ = 0;
        char[] temp = calc.toCharArray();
        for (int i = indexI; i < (temp.length - 1); i++) {
            if (temp[i] == ')') {
                indexI = i;
                for (int j = indexI; j > 0 ; j--) {
                    if (temp[j] == '(') {
                        indexJ = j;
                        break;
                    }
                }
                if (temp[indexJ - 1] == '(' && temp[indexI + 1] == ')') {
                    return false;
                }
                temp[indexJ] = '|';
                temp[indexI] = '|';
            }
        }
        return true;
    }

    public static boolean isStringCalc() {

        int error = 0;

        if (compareCalcToStr()) {

            if (calc.charAt(0) == '.' || calc.charAt(0) == ')' || calc.charAt(0) == '/' || calc.charAt(0) == '*'
                    || calc.charAt(0) == '+') {
                System.out.println("Первый символ примера введен не корректно: " + calc.charAt(0));
                error++;
            }

            if (calc.charAt(calc.length() - 1) == '.' || calc.charAt(calc.length() - 1) == '('
                    || calc.charAt(calc.length() - 1) == '/' || calc.charAt(calc.length() - 1) == '*'
                    || calc.charAt(calc.length() - 1) == '-' || calc.charAt(calc.length() - 1) == '+') {
                System.out.println("Последний символ примера введен не корректно: " + calc.charAt(calc.length() - 1));
                error++;
            }

            if (!isNum()) {
                System.out.println("Число введено не корректно.");
                error++;
            }

            if (!isNumBrackets()) {
                System.out.println("Введено не корректное число круглых скобок.");
                error++;
            }

            if (!isExtraBrackets()) {
                System.out.println("Необходимо убрать лишние круглые скобки.");
                error++;
            }

            for (int i = 0; i < (calc.length() - 1); i++) {
                switch (calc.charAt(i)) {
                    case '/':
                    case '*':
                    case '-':
                    case '+':
                        if (calc.charAt(i + 1) == '/' || calc.charAt(i + 1) == '*'
                                || calc.charAt(i + 1) == '-' || calc.charAt(i + 1) == '+') {
                            System.out.println("Арифмитические знаки не должны стоять друг за другом, " +
                                    "необходмо использовать круглые скобки");
                            error++;
                        }
                        if (calc.charAt(i + 1) == '.') {
                            System.out.println("После арифмитического знака не должен стоять символ ' . '");
                            error++;
                        }
                        if (calc.charAt(i + 1) == ')') {
                            System.out.println("После арифмитического знака не должен стоять символ ' ) '");
                            error++;
                        }
                        continue;
                    case '.':
                        if (calc.charAt(i + 1) == '/' || calc.charAt(i + 1) == '*'
                                || calc.charAt(i + 1) == '-' || calc.charAt(i + 1) == '+') {
                            System.out.println("После символа ' . ' не должен стоять арифмитический знак:" +
                                    " ' / '; ' * '; ' - '; + '");
                            error++;
                        }
                        if (calc.charAt(i + 1) == '(') {
                            System.out.println("После символа ' . ' не должен стоять символ ' ( '");
                            error++;
                        }
                        if (calc.charAt(i + 1) == ')') {
                            System.out.println("После символа ' . ' не должен стоять символ ' ) '");
                            error++;
                        }
                        continue;
                    case '(':
                        if (calc.charAt(i + 1) == '.') {
                            System.out.println("После символа ' ( ' не должен стоять символ ' . '");
                            error++;
                        }
                        if (calc.charAt(i + 1) == '/' || calc.charAt(i + 1) == '*' || calc.charAt(i + 1) == '+') {
                            System.out.println("После символа ' ( ' не должен стоять арифмитический знак:" +
                                    " ' / '; ' * '; ' + '");
                            error++;
                        }
                        if (calc.charAt(i + 1) == ')') {
                            System.out.println("После символа ' ( ' не должен стоять символ ' ) '");
                            error++;
                        }
                        continue;
                    case ')':
                        if (calc.charAt(i + 1) >= '0' && calc.charAt(i + 1) <= '9') {
                            System.out.println("После символа ' ) ' не должено стоять число");
                            error++;
                        }

                        if (calc.charAt(i + 1) == '.') {
                            System.out.println("После символа ' ) ' не должен стоять символ ' . '");
                            error++;
                        }

                        if (calc.charAt(i + 1) == '(') {
                            System.out.println("После символа ' ) ' не должен стоять символ ' ( '");
                            error++;
                        }
                        continue;
                    default:
                        if (calc.charAt(i) >= '0' && calc.charAt(i) <= '9' && calc.charAt(i + 1) == '(') {
                            System.out.println("После числа не должен стоять символ ' ( '");
                            error++;
                        }
                }
            }

            if (error != 0) {
                System.out.println("Количество ошибок: " + error);
                return false;
            }
            return true;

        } else {
            System.out.println("Пример введен некорректно");
            return false;
        }
    }
}
