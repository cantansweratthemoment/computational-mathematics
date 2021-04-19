package Methods;

import Utils.Functions;

public class SimpsonMethod extends Method {
    public SimpsonMethod(double left, double right, double accuracy) {
        super(left, right, accuracy);
    }

    @Override
    public double solve(Functions function) {
        int n = N;
        boolean borders = false;
        if (left > right) {
            double tmp = left;
            left = right;
            right = tmp;
            borders = true;
        }
        double integral1 = calculateIntegral(function, left, right, n);
        double integral2 = calculateIntegral(function, left, right, n * 2);
        while (Math.abs(integral1 - integral2) > accuracy) {
            intermediatePrint(integral2, n, Math.abs(integral1 - integral2), false);
            n *= 2;
            integral1 = calculateIntegral(function, left, right, n);
            integral2 = calculateIntegral(function, left, right, n * 2);
        }
        intermediatePrint(integral2, n, Math.abs(integral1 - integral2), true);
        if (borders) {
            return (-integral2);
        } else {
            return (integral2);
        }
    }

    public double calculateIntegral(Functions function, double left, double right, int n) {
        double h = (right - left) / n;
        double[] values = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            values[i] = function.getFunction().apply(left + i * h);
            if (!Double.isFinite(values[i])) {
                double leftLimit = function.getFunction().apply(left + i * h + DX);
                double rightLimit = function.getFunction().apply(left + i * h - DX);
                if (!(Double.isFinite(leftLimit) && Double.isFinite(rightLimit))) {
                    throw new RuntimeException("Разрыв неустраним.");
                } else {
                    values[i] = (leftLimit + rightLimit) / 2;
                }
            }
        }
        double integral = h / 3 * (values[0] + values[n]);
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                integral += 4 * h / 3 * values[i];
            } else {
                integral += 2 * h / 3 * values[i];
            }
        }
        return integral;
    }
}