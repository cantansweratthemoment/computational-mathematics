package base.Utils;

import base.Methods.Method;
import base.Structures.Equation;
import base.Structures.Table;
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

    public static void drawChart(Table table, Equation equation) {
        XYChart chart = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Численное дифференцирование")
                .xAxisTitle("x").yAxisTitle("F(x)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setChartBackgroundColor(PINK_LACE);
        chart.getStyler().setPlotGridLinesColor(PINK_LACE);
        ArrayList<Double> xs = table.getXs();
        ArrayList<Double> ys = table.getYs();
        XYSeries series = chart.addSeries("Численное решение", xs, ys);
        series.setMarkerColor(WINTER_SKY);
        series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);

        List<Double> xExact = new ArrayList<>();
        List<Double> yExact = new ArrayList<>();
        Function<Double, Double> exactValueFunction = equation::getExactValue;
        double right = table.getXs().get(table.getXs().size() - 1);
        double left = table.getXs().get(0);
        double step = (right - left) / 100;
        for (double i = left - step * 5; i <= right + step * 5; i += step) {
            xExact.add(i);
            yExact.add(exactValueFunction.apply(i));
        }
        XYSeries exactSeries = chart.addSeries("Точное решение", xExact, yExact);
        exactSeries.setMarker(SeriesMarkers.NONE);
        exactSeries.setMarkerColor(Color.BLUE);
        exactSeries.setLineColor(Color.DARK_GRAY);
        new SwingWrapper(chart).displayChart();
    }
}
