package Utils;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Double> xs;
    private List<Double> ys;
    public static final int STEPS = 30;
    private double left;
    private double right;
    private int EQUATION_IDENTIFIER;
    private XYChart chart;

    public Graph(double left, double right, int EQUATION_IDENTIFIER) throws IOException {
        this.left = left;
        this.right = right;
        this.EQUATION_IDENTIFIER = EQUATION_IDENTIFIER;
        this.xs = createXSeries(left, right);
        this.ys = createYSeries(EQUATION_IDENTIFIER, left, right);
        chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
                .title("Graph").xAxisTitle("X").yAxisTitle("Y")
                .build();
        chart.setCustomXAxisTickLabelsFormatter((x) -> String.format("%.2f", x));
        chart.addSeries("Graph", this.xs, this.ys);
        BitmapEncoder.saveBitmap(chart, "Graph", BitmapEncoder.BitmapFormat.PNG);
    }

    public static List<Double> createXSeries(double left, double right) {
        double step = (right - left) / STEPS;
        double x = left;
        List<Double> xs = new ArrayList<>();
        while (x < right) {
            xs.add(x);
            x += step;
        }
        return xs;
    }

    public static List<Double> createYSeries(int EQUATION_IDENTIFIER, double left, double right) {
        double step = (right - left) / STEPS;
        double x = left;
        List<Double> ys = new ArrayList<>();
        while (x < right) {
            ys.add(Calculator.calculateFunction(x, EQUATION_IDENTIFIER));
            x += step;
        }
        return ys;
    }
}