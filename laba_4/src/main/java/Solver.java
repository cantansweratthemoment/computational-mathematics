import Structures.Dependencies.*;
import Structures.Point;

import java.util.ArrayList;

public class Solver {
    private ArrayList<Point> points;
    private boolean writeToConsole;

    public Solver(ArrayList<Point> points, boolean writeToConsole) {
        this.points = points;
        this.writeToConsole = writeToConsole;
    }

    void solve() {
        //todo График и вывод наверное сюда надо будет пихнуть.
        LinearDependence linearDependence = new LinearDependence(points);
        PolynomialDependence polynomialDependence = new PolynomialDependence(points);
        ExponentialDependence exponentialDependence = new ExponentialDependence(points);
        LogarithmicDependence logarithmicDependence = new LogarithmicDependence(points);
        PowerDependence powerDependence = new PowerDependence(points);
        ArrayList<Dependence> dependencies = new ArrayList<>();
        dependencies.add(linearDependence);
        dependencies.add(polynomialDependence);
        dependencies.add(exponentialDependence);
        dependencies.add(logarithmicDependence);
        dependencies.add(polynomialDependence);
        for (Dependence dependence : dependencies) {
            dependence.findDependence();
            //todo рисовать решение если слау решилась
        }
    }
}
