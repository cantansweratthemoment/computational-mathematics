package Utils;

import java.lang.Math;

public class Calculator {
    public static double calculateFunction(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> 2 * Math.pow(x, 3) + 3.41 * Math.pow(x, 2) - 23.74 * x + 2.95;
            case (2) -> Math.pow(23.9 * x + 6, 0.5)-239;
            case (3) -> Math.cos(x) + 0.5;
            default -> 0;
        };
    }

    public static double calculateDerivative(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> 6 * Math.pow(x, 2) + 6.82 * x - 23.74;
            case (2) -> 11.95 / (Math.pow(23.9 * x + 6, 0.5));
            case (3) -> -1 * Math.sin(x);
            default -> 0;
        };
    }

    public static double calculateSecondDerivative(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> 12 * x + 6.82;
            case (2) -> -1 * (142.802) / Math.pow(23.9 * x + 6, 1.5);
            case (3) -> -1 * Math.cos(x);
            default -> 0;
        };
    }

    public static double calculatePhiFunction(double x, int chosenEquation, double lambda) {
        return switch (chosenEquation) {
            case (1) -> (2 * Math.pow(x, 3) + 3.41 * Math.pow(x, 2) - 23.74 * x + 2.95) * lambda + x;
            case (2) -> (Math.pow(23.9 * x + 6, 0.5)-239) * lambda + x;
            case (3) -> (Math.cos(x) + 0.5) * lambda + x;
            default -> 0;
        };
    }

    public static double calculatePhiFunctionDerivative(double x, int chosenEquation, double lambda) {
        return switch (chosenEquation) {
            case (1) -> 6 *lambda*(x*x+1.13667*x-3.95667)+1;
            case (2) -> 11.95*lambda / (Math.pow(23.9 * x + 6, 0.5)) + 1;
            case (3) -> 1 - lambda*Math.sin(x);
            default -> 0;
        };
    }
}