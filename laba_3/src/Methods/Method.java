package Methods;

import Utils.ColorfulString;
import Utils.Functions;

public abstract class Method {
    protected double left;
    protected double right;
    protected double accuracy;
    final int N = 4;
    final double DX = 1e-10;
    final double SYMMETRICAL_POINT = 0;

    public Method(double left, double right, double accuracy) {
        this.left = left;
        this.right = right;
        this.accuracy = accuracy;
    }

    public abstract double solve(Functions function);

    public void start(Functions functions) {
        if (functions.isSymmetrical()) {
            if (Math.abs(left - SYMMETRICAL_POINT) != Math.abs(right - SYMMETRICAL_POINT)) {
                throw new RuntimeException("Предела не существует!");
            } else {
                double tmpLeft = left;
                double tmpRight = right;
                this.right = SYMMETRICAL_POINT - DX*1000000;
                double leftResult = solve(functions);
                this.right = tmpRight;
                this.left = SYMMETRICAL_POINT + DX*1000000;
                double rightResult = solve(functions);
                this.left = tmpLeft;
                print(leftResult + rightResult);
            }
        } else {
            print(solve(functions));
        }
    }

    public void print(double answer) {
        ColorfulString.println("Ответ:");
        System.out.println("Значение интеграла:" + answer);
    }

    public void intermediatePrint(double answer, int n, double abs, boolean accuracyAchieved) {
        System.out.println("Количество разбиений: " + n + ".");
        System.out.println("Значение интеграла: " + answer + ".");
        System.out.println("Модуль разности: " + abs + ".");
        if (accuracyAchieved) {
            System.out.println("Точность достигнута.\n");
        } else {
            System.out.println("Точность не достигнута.\n");
        }
    }
}
