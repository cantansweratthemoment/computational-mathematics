import Utils.ColorfulString;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] equations = {"2*x^3+3.41*x^2-23.74*x+2.95", "x+(23.9*x+6)^(1/2)", "cos(x)+1/2"};
    static int chosenEquation;

    public static void main(String[] args) {
        ColorfulString.println("Выберите уравнение.");
        for (int i = 0; i < equations.length; i++) {
            System.out.println(i + 1 + ") " + equations[i]);
        }
        ColorfulString.println("Введите номер выбранного уравнения.");
        chosenEquation = sc.nextInt();
        while((chosenEquation!=1)&&(chosenEquation!=2)&&(chosenEquation!=3)){
            ColorfulString.println("Попробуйте выбрать номер уравнения ещё раз.");
            chosenEquation = sc.nextInt();
        }
    }
}
