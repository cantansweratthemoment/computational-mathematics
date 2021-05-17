package Methods;

import Structures.Table;

import java.util.ArrayList;
import java.util.List;

public class Lagrange implements Method {
    @Override
    public double solve(Table table, double argument) {
        int size = table.getTable().size();
        List<Double> l = new ArrayList<>();
        List<Double> xData = table.getXs();
        for (int i = 0; i < size; i++) {
            double currentX = xData.get(i);
            double numerator = 1;
            for (Double xDatum : xData) {
                if (xDatum != currentX) {
                    double v = (argument - xDatum);
                    numerator = numerator * v;
                }
            }
            double denominator = 1;
            for (Double x : xData) {
                if (x != currentX) {
                    double v = currentX - x;
                    denominator = denominator * v;
                }
            }
            double yi = table.getTable().get(currentX);
            l.add(yi * numerator / denominator);
        }
        double sum = 0.0;
        for (Double aDouble : l) {
            double doubleValue = aDouble;
            sum += doubleValue;
        }
        return sum;
    }
}
