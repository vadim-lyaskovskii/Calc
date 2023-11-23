import java.util.*;

public class Calc {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str;

        while (true) {
            System.out.println("1. Для ввода примера введите (Yes или Y)");
            System.out.println("2. Для выхода из программы введите (Exit или E)");
            str = scanner.nextLine();
            if (str.equals("Exit") || str.equals("E") || str.equals("exit") || str.equals("e")) {
                break;
            } else if (str.equals("Yes") || str.equals("Y") || str.equals("yes") || str.equals("y")) {
                inputStringExpression();
            } else {
                System.out.println("Вы ввели не правильную команду");
            }
        }
    }

    private static void inputStringExpression() {

        Scanner scanner = new Scanner(System.in);
        String calc;
        boolean run = true;

        do {
            System.out.println("Введите пример:");
            calc = scanner.nextLine();
            calc = calc.replaceAll("\\s", "");
            if (calc.length() == 0) {
                System.out.println("Вы ввели пустую строку");
            } else {
                StringCalc stringCalc = new StringCalc(calc);
                ArithmeticExpression arithmeticExpression = new ArithmeticExpression(calc);
                if (stringCalc.isStringCalc()) {
                    if (!arithmeticExpression.isDivByZero()) {
                        System.out.println("Ответ: " + arithmeticExpression.outputArithmeticExpression());
                        run = false;
                    } else {
                        System.out.println("Ошибка: деление на 0");
                        break;
                    }
                }
            }
        } while (run);
    }
}
