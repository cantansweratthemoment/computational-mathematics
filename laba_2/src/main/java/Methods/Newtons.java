package Methods;

import Utils.Calculator;
import Utils.ColorfulString;
import Utils.Graph;

import java.io.IOException;
import java.util.Scanner;

public class Newtons {
    static Scanner sc = new Scanner(System.in);

    public static boolean checkSecondDerivative(double left, double right, int EQUATION_IDENTIFIER) {
        boolean flag = true;
        double step = (right - left) / 100;
        double x = left;
        double sign = Math.signum(Calculator.calculateSecondDerivative(x, EQUATION_IDENTIFIER));
        while (x < right) {
            x += step;
            if (Math.signum(Calculator.calculateSecondDerivative(x, EQUATION_IDENTIFIER)) != sign) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean verifySingularity(double left, double right, int EQUATION_IDENTIFIER) {
        boolean flag = true;
        double step = (right - left) / 100;
        double x = left;
        double sign = Math.signum(Calculator.calculateDerivative(x, EQUATION_IDENTIFIER));
        while (x < right) {
            x += step;
            if (Math.signum(Calculator.calculateDerivative(x, EQUATION_IDENTIFIER)) != sign) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean verifyExisting(double left, double right, int EQUATION_IDENTIFIER) {
        if ((Calculator.calculateFunction(left, EQUATION_IDENTIFIER) * Calculator.calculateFunction(right, EQUATION_IDENTIFIER) < 0)) {
            return true;
        }
        ColorfulString.println("Не пройдена верификация существования и единственности корня на данном промежутке.");
        return false;
    }

    public static double chooseInitialX(double left, double right, int EQUATION_IDENTIFIER) {
        if (Calculator.calculateFunction(left, EQUATION_IDENTIFIER) * Calculator.calculateSecondDerivative(left, EQUATION_IDENTIFIER) > 0) {
            return left;
        } else {
            return right;
        }
    }

    public static void solution(double left, double right, double accuracy, int EQUATION_IDENTIFIER) throws IOException {
        Graph graph = new Graph(left, right, EQUATION_IDENTIFIER);
        while (!verifyExisting(left, right, EQUATION_IDENTIFIER)) {
            ColorfulString.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            ColorfulString.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        if (!verifySingularity(left, right, EQUATION_IDENTIFIER)) {
            ColorfulString.println("На данном промежутке корень может быть не единственный.");
        }
        while (!checkSecondDerivative(left, right, EQUATION_IDENTIFIER)) {
            ColorfulString.println("Не пройдена проверка на сохранение знака второй производной на заданном промежутке.");
            ColorfulString.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            ColorfulString.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        System.out.println("Процесс решения:");
        double prev = chooseInitialX(left, right, EQUATION_IDENTIFIER);
        int count = 1;
        double h0 = Calculator.calculateFunction(prev, EQUATION_IDENTIFIER) / Calculator.calculateDerivative(prev, EQUATION_IDENTIFIER);
        double x = prev - h0;
        print(count, accuracy, prev, Calculator.calculateFunction(prev, EQUATION_IDENTIFIER), Calculator.calculateDerivative(prev, EQUATION_IDENTIFIER), x, Math.abs(prev - x));
        count++;
        while (!(Math.abs(x - prev) <= accuracy || Math.abs(Calculator.calculateFunction(x, EQUATION_IDENTIFIER) / Calculator.calculateDerivative(x, EQUATION_IDENTIFIER)) <= accuracy || Math.abs(Calculator.calculateFunction(x, EQUATION_IDENTIFIER)) <= accuracy)) {
            prev = x;
            h0 = Calculator.calculateFunction(prev, EQUATION_IDENTIFIER) / Calculator.calculateDerivative(prev, EQUATION_IDENTIFIER);
            x = prev - h0;
            print(count, accuracy, prev, Calculator.calculateFunction(prev, EQUATION_IDENTIFIER), Calculator.calculateDerivative(prev, EQUATION_IDENTIFIER), x, Math.abs(prev - x));
            count++;
        }
        printResult(x, EQUATION_IDENTIFIER, accuracy, count - 1);
    }

    public static void print(int count, double accuracy, double prev, double f, double ff, double x, double r) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") xk=");
        System.out.printf("%." + decimal + "f", prev);
        System.out.print(", f(xk)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", f'(xk)=");
        System.out.printf("%." + decimal + "f", ff);
        System.out.print(", xk+1=");
        System.out.printf("%." + decimal + "f", x);
        System.out.print(", |xk-xk+1|=");
        System.out.printf("%." + decimal + "f\n", r);
    }

    public static void printResult(double x0, int EQUATION_IDENTIFIER, double accuracy, int count) {
        int decimal = decimalPlaces(accuracy);
        System.out.print("Результат:\nНайденный корень уравнения: ");
        System.out.printf("%." + decimal + "f\n", x0);
        System.out.print("Значение функции в корне: ");
        System.out.printf("%." + decimal + "f\n", Calculator.calculateFunction(x0, EQUATION_IDENTIFIER));
        System.out.println("Число итераций: " + count);
    }

    public static int decimalPlaces(double accuracy) {
        int count = 1;
        double q = 0.1;
        while (q >= accuracy) {
            q = q / 10;
            count++;
        }
        return count;
    }
}
//TODO Почистить дубликаты.