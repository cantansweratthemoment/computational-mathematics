package base;

import base.Methods.Euler;
import base.Methods.Method;
import base.Methods.Milne;
import base.Structures.Equation;
import base.Structures.Result;
import base.Utils.Chart;

import java.util.ArrayList;
import java.util.Scanner;

import static base.Utils.ColorfulString.aggressivelyPrint;
import static base.Utils.ColorfulString.colorfulPrint;
import static java.lang.Math.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static final Equation[] equations = {
            new Equation(
                    "y' = sin(x) + y",
                    (x, y) -> Math.sin(x) + y,
                    (x0, y0) -> (y0 + sin(x0) / 2 + cos(x0) / 2) / exp(x0),
                    (c, x) -> c * exp(x) - sin(x) / 2 - cos(x) / 2
            ),
            new Equation(
                    "y' = x * y",
                    (x, y) -> x * y,
                    (x0, y0) -> y0 / exp(pow(x0, 2) / 2),
                    (c, x) -> c * exp(pow(x, 2) / 2)
            )
    };

    private static final Method[] methods = {
            new Euler(),
            new Milne()
    };

    public static void main(String[] args) {
        colorfulPrint("Выберите уравнение.\n");
        for (int i = 0; i < equations.length; i++) {
            System.out.printf("%d. %s%n", i, equations[i].getText());
        }
        Equation equation = equations[readInt(0, equations.length)];

        colorfulPrint("Выберите метод.\n");
        for (int i = 0; i < equations.length; i++) {
            System.out.printf("%d. %s%n", i, methods[i].getText());
        }
        Method method = methods[readInt(0, methods.length)];


        colorfulPrint("Введите левую границу интервала.\n");
        double a = readDouble(null, null);

        colorfulPrint("Введите правую границу интервала.\n");
        double b = readDouble(a, null);

        colorfulPrint(String.format("Введите начальное условие y(%s).\n", a));
        double y0 = readDouble(null, null);
        equation.calculateC(a, y0);

        colorfulPrint("Введите шаг.\n");
        double h = readDouble(null, b - a);

        Double precision = null;
        if (method instanceof Milne) {
            colorfulPrint("Введите точность.\n");
            precision = readDouble(0d, null);
        }

        Result result = method.solve(equation, a, b, y0, h, precision);
        result.getSteps().forEach(step -> System.out.println("\n" + step));

        Result doubleStepResult = method.solve(equation, a, b, y0, 2 * h, precision);
        ArrayList<Double> y2h = doubleStepResult.getTable().getYs();
        int p = method.getOrder();
        double R = (y2h.get(2) - result.getTable().getYs().get(2)) / (Math.pow(2, p) - 1);
        colorfulPrint("\nОценка точности по правилу Рунге: R = " + R);

        Chart.drawChart(result.getTable(), equation);
    }

    private static void tryAgain() {
        aggressivelyPrint("Попробуйте еще раз!\n");
    }

    private static double readDouble(Double min, Double max) {
        while (true) {
            try {
                var input = Double.parseDouble(sc.nextLine().trim());
                if (min != null && input < min || max != null && input > max) {
                    tryAgain();
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                tryAgain();
            }
        }
    }

    private static int readInt(Integer min, Integer max) {
        while (true) {
            try {
                var input = Integer.parseInt(sc.nextLine().trim());
                if (min != null && input < min || max != null && input > max) {
                    tryAgain();
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                tryAgain();
            }
        }
    }
}
