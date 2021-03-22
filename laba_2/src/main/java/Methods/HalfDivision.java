package Methods;

import Utils.Calculator;
import Utils.ColorfulString;
import Utils.Graph;

import java.io.*;
import java.util.Scanner;

public class HalfDivision {
    static Scanner sc = new Scanner(System.in);

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

    public static void solveInFile(double left, double right, int EQUATION_IDENTIFIER, double accuracy) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write("Процесс решения:\n");
        int count = 1;
        double x0 = (left + right) / 2;
        double f = Calculator.calculateFunction(x0, EQUATION_IDENTIFIER);
        printToFile(left, right, x0, f, EQUATION_IDENTIFIER, accuracy, count, writer);
        count++;
        while (!(Math.abs(right - left) <= accuracy || Math.abs(f) <= accuracy)) {
            if (Calculator.calculateFunction(left, EQUATION_IDENTIFIER) * Calculator.calculateFunction(x0, EQUATION_IDENTIFIER) < 0) {
                right = x0;
            } else {
                left = x0;
            }
            x0 = (left + right) / 2;
            f = Calculator.calculateFunction(x0, EQUATION_IDENTIFIER);
            printToFile(left, right, x0, f, EQUATION_IDENTIFIER, accuracy, count, writer);
            count++;
        }
        printResult(x0, EQUATION_IDENTIFIER, accuracy, count - 1);
        return;
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
        ColorfulString.println("Выводить процесс решения в файл? [y]/[n]");
        String q = sc.next();
        while (!q.equals("y") && !q.equals("n")) {
            ColorfulString.println("Попробуйте ещё раз.");
            q = sc.next();
        }
        if (q.equals("y")) {
            solveInFile(left, right, EQUATION_IDENTIFIER, accuracy);
            return;
        }
        System.out.println("Процесс решения:");
        int count = 1;
        double x0 = (left + right) / 2;
        double f = Calculator.calculateFunction(x0, EQUATION_IDENTIFIER);
        print(left, right, x0, f, EQUATION_IDENTIFIER, accuracy, count);
        count++;
        while (!(Math.abs(right - left) <= accuracy || Math.abs(f) <= accuracy)) {
            if (Calculator.calculateFunction(left, EQUATION_IDENTIFIER) * Calculator.calculateFunction(x0, EQUATION_IDENTIFIER) < 0) {
                right = x0;
            } else {
                left = x0;
            }
            x0 = (left + right) / 2;
            f = Calculator.calculateFunction(x0, EQUATION_IDENTIFIER);
            print(left, right, x0, f, EQUATION_IDENTIFIER, accuracy, count);
            count++;
        }
        printResult(x0, EQUATION_IDENTIFIER, accuracy, count - 1);
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

    public static void print(double left, double right, double x0, double f, int EQUATION_IDENTIFIER, double accuracy, int count) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") a=");
        System.out.printf("%." + decimal + "f", left);
        System.out.print(", b=");
        System.out.printf("%." + decimal + "f", right);
        System.out.print(", x=");
        System.out.printf("%." + decimal + "f", x0);
        System.out.print(", f(a)=");
        System.out.printf("%." + decimal + "f", Calculator.calculateFunction(left, EQUATION_IDENTIFIER));
        System.out.print(", f(b)=");
        System.out.printf("%." + decimal + "f", Calculator.calculateFunction(right, EQUATION_IDENTIFIER));
        System.out.print(", f(x)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", |a-b|=");
        System.out.printf("%." + decimal + "f\n", Math.abs(right - left));
    }

    public static void printResult(double x0, int EQUATION_IDENTIFIER, double accuracy, int count) {
        int decimal = decimalPlaces(accuracy);
        System.out.print("Результат:\nНайденный корень уравнения: ");
        System.out.printf("%." + decimal + "f\n", x0);
        System.out.print("Значение функции в корне: ");
        System.out.printf("%." + decimal + "f\n", Calculator.calculateFunction(x0, EQUATION_IDENTIFIER));
        System.out.println("Число итераций: " + count);
    }

    public static void printToFile(double left, double right, double x0, double f, int EQUATION_IDENTIFIER, double accuracy, int count, BufferedWriter writer) throws IOException {
        writer.write(count + ") a=");
        writer.write(String.valueOf(left));
        writer.write(", b=");
        writer.write(String.valueOf(right));
        writer.write(", x=");
        writer.write(String.valueOf(x0));
        writer.write(", f(a)=");
        writer.write(String.valueOf(Calculator.calculateFunction(left, EQUATION_IDENTIFIER)));
        writer.write(", f(b)=");
        writer.write(String.valueOf(Calculator.calculateFunction(right, EQUATION_IDENTIFIER)));
        writer.write(", f(x)=");
        writer.write(String.valueOf(f));
        writer.write(", |a-b|=");
        writer.write(String.valueOf(Math.abs(right - left)) + "\n");
        writer.flush();
    }
}
//todo структура =(