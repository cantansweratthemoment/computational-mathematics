import IO.AnswerWriter;
import Structures.Dependencies.*;
import Structures.Point;
import Utils.ColorfulString;
import Utils.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solver {
    private ArrayList<Point> points;
    private boolean writeToConsole;

    public Solver(ArrayList<Point> points) {
        this.points = points;
    }

    void solve() throws IOException {
        Graph graph = new Graph(points);
        graph.drawMainFrame();
        boolean q = false;
        String w;
        Scanner sc = new Scanner(System.in);
        while (!q) {
            ColorfulString.println("Записывать выходные данные в консоль? [y]/[n]");
            w = sc.nextLine();
            switch (w) {
                case "y" -> {
                    writeToConsole = true;
                    q = true;
                }
                case "n" -> q = true;
                default -> ColorfulString.aggressivelyPrintln("Попробуйте ещё раз!");
            }
        }
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
        dependencies.add(powerDependence);
        for (Dependence dependence : dependencies) {
            dependence.findDependence();
            if (dependence.isCanBeSolved()) {
                try {
                    graph.drawDependency(dependence);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
        AnswerWriter answerWriter = new AnswerWriter(dependencies, writeToConsole);
        answerWriter.printAnswer();
        graph.showGraph();
    }
}
