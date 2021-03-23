import Methods.HalfDivision;
import Methods.Newtons;
import Methods.SimpleIteration;
import Utils.ColorfulString;

import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] equations = {"2*x^3+3.41*x^2-23.74*x+2.95", "(23.9*x+6)^(1/2)-239", "cos(x)+1/2"};
    static int chosenEquation;
    static String[] methods = {"Метод половинного деления", "Метод Ньютона", "Метод простой итерации"};
    static int chosenMethod;
    static double left;
    static double right;
    static double accuracy;

    public static void main(String[] args) throws IOException {
        ColorfulString.println("Выберите уравнение.");
        for (int i = 0; i < equations.length; i++) {
            System.out.println(i + 1 + ") " + equations[i]);
        }
        ColorfulString.println("Введите номер выбранного уравнения.");
        chosenEquation = sc.nextInt();
        while ((chosenEquation != 1) && (chosenEquation != 2) && (chosenEquation != 3)) {
            ColorfulString.println("Попробуйте выбрать номер уравнения ещё раз.");
            chosenEquation = sc.nextInt();
        }
        ColorfulString.println("Выберите метод решения уравнения.");
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + 1 + ") " + methods[i]);
        }
        ColorfulString.println("Введите номер выбранного метода.");
        chosenMethod = sc.nextInt();
        while ((chosenMethod != 1) && (chosenMethod != 2) && (chosenMethod != 3)) {
            ColorfulString.println("Попробуйте выбрать метод решения уравнения ещё раз.");
            chosenMethod = sc.nextInt();
        }
        ColorfulString.println("Читать исходные данные из файла? [y]/[n]");
        String q = sc.next();
        while (!q.equals("y") && !q.equals("n")) {
            ColorfulString.println("Попробуйте ещё раз.");
            q = sc.next();
        }
        if (q.equals("y")) {
            readFromFile();
        } else {
            readFromConsole();
        }
        if (chosenMethod == 1)
            HalfDivision.solution(left, right, accuracy, chosenEquation);
        if (chosenMethod == 2)
            Newtons.solution(left, right, accuracy, chosenEquation);
        if (chosenMethod == 3)
            SimpleIteration.solution(left, right, accuracy, chosenEquation);


    }

    public static void readFromFile() throws IOException {
        ColorfulString.println("Введите название файла.");
        String fileName = sc.next();
        File file = new File(fileName);
        while (!file.exists()) {
            ColorfulString.println("Попробуйте ещё раз.");
            fileName = sc.next();
            file = new File(fileName);
        }
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String left_s = reader.readLine();
            left = Double.parseDouble(left_s);
            String right_s = reader.readLine();
            right = Double.parseDouble(right_s);
            String accuracy_s = reader.readLine();
            accuracy = Double.parseDouble(accuracy_s);
        } catch (FileNotFoundException e) {
            ColorfulString.println("Файл не найден!");
        }
    }

    public static void readFromConsole() {
        ColorfulString.println("Введите левую границу интервала.");
        left = sc.nextDouble();
        ColorfulString.println("Введите правую границу интервала.");
        right = sc.nextDouble();
        while (right - left <= 0) {
            ColorfulString.println("Введите границы ещё раз.");
            ColorfulString.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            ColorfulString.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        ColorfulString.println("Введите погрешность вычисления.");
        accuracy = sc.nextDouble();
    }
}
//TODO Если будет время, ловить ошибки ввода (не только в Main).