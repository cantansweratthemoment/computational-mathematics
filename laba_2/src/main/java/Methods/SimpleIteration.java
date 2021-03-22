package Methods;

import Utils.Calculator;
import Utils.ColorfulString;
import Utils.Graph;

import java.io.IOException;
import java.util.Scanner;

public class SimpleIteration {
    static Scanner sc = new Scanner(System.in);
    static double lambda;

    public static boolean verifyConvergence(double left, double right, int EQUATION_IDENTIFIER) {
        boolean flag = true;
        double step = (right - left) / 50;
        double x = left;
        lambda = -1 / Math.max(Calculator.calculateDerivative(left, EQUATION_IDENTIFIER), Calculator.calculateDerivative(right, EQUATION_IDENTIFIER));
        while (x < right) {
            x += step;
            if (Math.abs(Calculator.calculatePhiFunctionDerivative(x, EQUATION_IDENTIFIER, lambda)) >= 1) {
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
        while (!verifyConvergence(left, right, EQUATION_IDENTIFIER)) {
            ColorfulString.println("Условие сходимости с данным интервалом не выполнено.");
            ColorfulString.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            ColorfulString.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        ColorfulString.println("Процесс решения:");
        double prev = left;
        int count = 1;
        double x = Calculator.calculatePhiFunction(prev, EQUATION_IDENTIFIER, lambda);
        print(count, accuracy, prev, Calculator.calculateFunction(prev, EQUATION_IDENTIFIER), x, Math.abs(x - prev));
        count++;
        double q = Math.abs(Calculator.calculatePhiFunctionDerivative(x, EQUATION_IDENTIFIER, lambda));
        while (!((q <= 0.5) && (Math.abs(x - prev) <= accuracy) || (q >= 0.5) && (Math.abs(x - prev) <= (1 - q) * accuracy / q))) {
            prev = x;
            x = Calculator.calculatePhiFunction(prev, EQUATION_IDENTIFIER, lambda);
            print(count, accuracy, prev, Calculator.calculateFunction(prev, EQUATION_IDENTIFIER), x, Math.abs(x - prev));
            count++;
            q = Math.abs(Calculator.calculatePhiFunctionDerivative(x, EQUATION_IDENTIFIER, lambda));
        }
        printResult(x, EQUATION_IDENTIFIER, accuracy, count - 1);
    }

    public static void print(int count, double accuracy, double prev, double f, double x, double r) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") xk=");
        System.out.printf("%." + decimal + "f", prev);
        System.out.print(", f(xk)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", xk+1 = \uD835\uDF11(\uD835\uDC65\uD835\uDC58)");
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
