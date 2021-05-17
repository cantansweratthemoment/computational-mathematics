import Methods.Lagrange;
import Methods.Method;
import Methods.Newton;
import Structures.Func;
import Structures.Table;
import Utils.Chart;
import Utils.ColorfulString;

import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import static Utils.ColorfulString.*;

public class Main {
    private static final String[] METHOD_NAMES = {"Многочлен Лагранжа", "Многочлен Ньютона"};
    private static final String[] INPUT_NAMES = {"В виде таблицы", "На основе функции"};
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        colorfulPrint("Выберите метод интерполяции.\n");
        for (int i = 0; i < METHOD_NAMES.length; i++) {
            System.out.println(i + 1 + ") " + METHOD_NAMES[i]);
        }
        boolean isLagrange = chooseMethodAndInput();
        colorfulPrint("Как задавать исходные данные?\n");
        for (int i = 0; i < INPUT_NAMES.length; i++) {
            System.out.println(i + 1 + ") " + INPUT_NAMES[i]);
        }
        boolean isTable;
        isTable = chooseMethodAndInput();
        Func func;
        Table table;
        if (isTable) {
            table = new Table(readTable());
        } else {
            colorfulPrint("Выберите функцию.\n");
            func = chooseFunction();
            SortedMap<Double, Double> map = new TreeMap<>();
            double inc = (func.getRight() - func.getLeft()) / (9);
            for (double i = func.getLeft(); i <= func.getRight(); i += inc) {
                map.put(i, func.getFunction().apply(i));
            }
            table = new Table(map);
        }
        Method method = isLagrange ? new Lagrange() : new Newton();
        double argumentValue = method.solve(table, 5);//todo лол
        System.out.println("Приближённое значение функции, при x=" + 5 + ": " + argumentValue);
        Chart.drawChart(table, argumentValue, 5, isTable);//todo убрать пятерку
    }

    private static boolean chooseMethodAndInput() {
        String w;
        boolean q = false;
        boolean is = false;
        while (!q) {
            w = sc.nextLine();
            switch (w) {
                case ("1") -> {
                    is = true;
                    q = true;
                }
                case ("2") -> q = true;
                default -> aggressivelyPrint("Попробуйте ещё раз!\n");
            }
        }
        return is;
    }

    private static Func chooseFunction() {
        for (int i = 0; i < Func.values().length; i++) {
            System.out.println((i + 1) + ") " + Func.values()[i].getText());
        }
        String t_s;
        int t = 0;
        boolean q = false;
        while (!q) {
            t_s = sc.nextLine();
            try {
                t = Integer.parseInt(t_s);
            } catch (NumberFormatException e) {
                aggressivelyPrint("Попробуйте ещё раз!\n");
            }
            if (t == 1 || t == 2) {
                q = true;
            } else {
                aggressivelyPrint("Попробуйте ещё раз!\n");
            }
        }
        ColorfulString.colorfulPrint("Введите границы через пробел.");
        q = false;
        String w_s, e_s;
        double w = 0, e = 0;
        Scanner sc = new Scanner(System.in);
        while (!q) {
            w_s = sc.next();
            e_s = sc.next();
            q = true;
            try {
                w = Double.parseDouble(w_s);
                e = Double.parseDouble(e_s);
            } catch (NumberFormatException er) {
                ColorfulString.aggressivelyPrint("Попробуйте ещё раз!");
                q = false;
            }
        }
        if (w > e) {
            Func.values()[t - 1].setRight(w);
            Func.values()[t - 1].setLeft(e);
        } else {
            Func.values()[t - 1].setRight(e);
            Func.values()[t - 1].setLeft(w);
        }
        return Func.values()[t - 1];
    }

    private static SortedMap<Double, Double> readTable() {
        colorfulPrint("Введите количество точек.\n");
        String t_s;
        int t = -1;
        boolean q = false;
        while (!q) {
            t_s = sc.nextLine();
            q = true;
            try {
                t = Integer.parseInt(t_s);
            } catch (NumberFormatException e) {
                aggressivelyPrint("Попробуйте ещё раз!\n");
                q = false;
            }
        }
        colorfulPrint("Вводите точки в формате \"x y\"\n");
        SortedMap<Double, Double> map = new TreeMap<>();
        double x = 0, y = 0;
        String x_s, y_s;
        for (int i = 0; i < t; i++) {
            System.out.print("Точка " + (i + 1) + ": ");
            q = false;
            while (!q) {
                q = true;
                x_s = sc.next();
                y_s = sc.next();
                try {
                    x = Double.parseDouble(x_s);
                    y = Double.parseDouble(y_s);
                } catch (NumberFormatException e) {
                    aggressivelyPrint("Введите координаты точки ещё раз!\n");
                    q = false;
                }
            }
            map.put(x, y);
        }
        return map;
    }
}
//todo ввод функции бурагозит