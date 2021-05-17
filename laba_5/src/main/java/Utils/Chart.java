package Utils;

import Methods.Method;
import Structures.Table;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Diamond;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Chart {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final Color PINK_LACE = new Color(255, 211, 232);
    private static final Color WINTER_SKY = new Color(245, 0, 118);
    private static final Color NYANZA_LIGHT = new Color(243, 255, 225);

    public static void drawChart(Table table, double argumentValue, double argument, Method method) {
        XYChart chart = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Интерполяция функции")
                .xAxisTitle("x").yAxisTitle("F(x)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setChartBackgroundColor(PINK_LACE);
        chart.getStyler().setPlotGridLinesColor(PINK_LACE);
        ArrayList<Double> xs = table.getXs();
        ArrayList<Double> ys = table.getYs();
        XYSeries series = chart.addSeries("Введённые значения", xs, ys);
        series.setMarkerColor(WINTER_SKY);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        ArrayList<Double> argumentListX = new ArrayList<>();
        ArrayList<Double> argumentListU = new ArrayList<>();
        argumentListX.add(argument);
        argumentListU.add(argumentValue);
        XYSeries argumentSeries = chart.addSeries("Результат", argumentListX, argumentListU);
        argumentSeries.setMarkerColor(Color.BLACK);
        argumentSeries.setMarker(SeriesMarkers.DIAMOND);
        List<Double> xInterpolation = new ArrayList<>();
        List<Double> yInterpolation = new ArrayList<>();
        Function<Double, Double> interpolationFunction = x -> method.solve(table, x);
        double right = table.getXs().get(table.getXs().size() - 1);
        double left = table.getXs().get(0);
        double step = (right - left) / 100;
        for (double i = left - step * 5; i <= right + step * 5; i += step) {
            xInterpolation.add(i);
            yInterpolation.add(interpolationFunction.apply(i));
        }
        XYSeries interpolationSeries = chart.addSeries("Интерполяционная функция", xInterpolation, yInterpolation);
        interpolationSeries.setMarker(SeriesMarkers.CROSS);
        interpolationSeries.setMarkerColor(Color.BLUE);
        interpolationSeries.setLineColor(Color.DARK_GRAY);
        new SwingWrapper(chart).displayChart();
    }
}
