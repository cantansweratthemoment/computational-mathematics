package Utils;

import Structures.Dependencies.Dependence;
import Structures.Point;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Graph {
    private ArrayList<Point> points;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public XYChart getChart() {
        return chart;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getRightX() {
        return rightX;
    }

    private final XYChart chart;
    private double leftX = 0;
    private double rightX = 0;

    public Graph(ArrayList<Point> points) {
        this.chart = new XYChartBuilder().width(750).height(550).title("Выбор аппроксимирующей функции")
                .xAxisTitle("X").yAxisTitle("Y").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        this.points = points;
        leftX = points.get(0).getX();
        rightX = points.get(0).getX();

        for (Point point : points) {
            if (point.getX() < leftX) {
                leftX = point.getX();
            }
            if (point.getX() > rightX) {
                rightX = point.getX();
            }
        }
        double addingArea = abs(rightX - leftX) * 0.05;
        leftX -= addingArea;
        rightX += addingArea;
    }

    public void drawMainFrame() {
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        ArrayList<Double> dataX = new ArrayList<>(), dataY = new ArrayList<>();
        points.forEach(p -> {
            dataX.add(p.getX());
            dataY.add(p.getY());
        });
        XYSeries series = chart.addSeries("Исходные данные", dataX, dataY);
        series.setMarker(SeriesMarkers.DIAMOND);
    }

    public void drawDependency(Dependence dependency) {
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        ArrayList<Double> dataX = new ArrayList<>(), dataY = new ArrayList<>();
        for (double i = leftX; i < rightX; i += 0.01) {
            if (!Double.isNaN(dependency.getFunction().apply(i))) {
                dataX.add(i);
                dataY.add(dependency.getFunction().apply(i));
            }
        }
        XYSeries series = chart.addSeries(dependency.getFunctionName(), dataX, dataY);
        series.setMarker(SeriesMarkers.NONE);
    }

    public void showGraph() {
        new SwingWrapper(chart).displayChart();
    }
}
