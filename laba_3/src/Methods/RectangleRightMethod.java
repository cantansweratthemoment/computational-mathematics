package Methods;

import Utils.Functions;

public class RectangleRightMethod extends Method {
    public RectangleRightMethod(double left, double right, double accuracy) {
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
        double integral = 0;
        for (int i = 1; i <= n; i++) {
            if (!Double.isFinite(function.getFunction().apply(left + i * h))) {
                double leftLimit = function.getFunction().apply(left + i * h + DX);
                double rightLimit = function.getFunction().apply(left + i * h - DX);
                if (!(Double.isFinite(leftLimit) && Double.isFinite(rightLimit))) {
                    throw new RuntimeException("Разрыв неустраним.");
                } else {
                    integral += h * (leftLimit + rightLimit) / 2;
                    continue;
                }
            }
            integral += h * function.getFunction().apply(left + i * h);
        }
        return integral;
    }
}
