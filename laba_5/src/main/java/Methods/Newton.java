package Methods;

import Structures.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class Newton implements Method {
    @Override
    public double solve(Table table, double argument) {
        SortedMap<Double, Double> data = table.getTable();
        List<Double> xData = table.getXs();
        int size = data.size();
        List<List<Double>> separatedDifferences = new ArrayList<>();
        List<Double> sd = new ArrayList<>();
        for (int j = 1; j < data.size(); j++) {
            sd.add((data.get(xData.get(j)) - data.get(xData.get(j - 1))) / (xData.get(j) - xData.get(j - 1)));
        }
        separatedDifferences.add(sd);
        for (int i = 0; i < size - 2; i++) {
            List<Double> sd1 = new ArrayList<>();
            for (int j = 1; j < separatedDifferences.get(i).size(); j++) {
                sd1.add((separatedDifferences.get(i).get(j) - separatedDifferences.get(i).get(j - 1)) / (xData.get(j + i + 1) - xData.get(j - 1)));
            }
            separatedDifferences.add(sd1);
        }
        double Nn = table.getYs().get(0);
        int counter = 1;
        for (List<Double> difference : separatedDifferences) {
            double mulX = 1;
            for (int j = 0; j < counter; j++) {
                mulX *= (argument - xData.get(j));
            }
            counter++;
            Nn += difference.get(0) * mulX;
        }
        return Nn;
    }
}
