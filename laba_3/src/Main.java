import Methods.*;
import Utils.ColorfulString;
import Utils.Functions;
import Utils.MethodType;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static Functions function;
    private static double left;
    private static double right;
    private static double accuracy;
    private static Method method;

    public static void main(String[] args) {
        input();
        method.solve();
    }

    public static void input() {
        ColorfulString.println("Выберите функцию, интеграл которой требуется вычислить.");
        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + ") " + Functions.values()[i].getText());
        }
        int functionNumber = sc.nextInt();
        while ((functionNumber != 1) && (functionNumber != 2) && (functionNumber != 3)) {
            ColorfulString.aggressivelyPrintln("Неправильно введён номер функции!");
            functionNumber = sc.nextInt();
        }
        function = Functions.values()[functionNumber - 1];
        ColorfulString.println("Выберите метод решения.");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + ") " + MethodType.values()[i].getName());
        }
        int methodNumber = sc.nextInt();
        while ((methodNumber != 1) && (methodNumber != 2) && (methodNumber != 3) && (methodNumber != 4) && (methodNumber != 5)) {
            ColorfulString.aggressivelyPrintln("Неправильно введён номер метода!");
            methodNumber = sc.nextInt();
        }
        setMethod(methodNumber);
        ColorfulString.println("Введите левую границу интегрирования.");
        left = sc.nextDouble();
        ColorfulString.println("Введите правую границу интегрирования.");
        right = sc.nextDouble();
        ColorfulString.println("Введите точность вычисления.");
        accuracy = sc.nextDouble();
    }

    public static void setMethod(int number) {
        switch (number) {
            case 1 -> method = new RectangleLeftMethod(function, left, right, accuracy);
            case 2 -> method = new RectangleMiddleMethod(function, left, right, accuracy);
            case 3 -> method = new RectangleRightMethod(function, left, right, accuracy);
            case 4 -> method = new SimpsonMethod(function, left, right, accuracy);
            case 5 -> method = new TrapeziumMethod(function, left, right, accuracy);
        }
    }
}