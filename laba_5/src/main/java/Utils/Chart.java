package Utils;

import Structures.Table;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Diamond;
import org.knowm.xchart.style.markers.Marker;

import java.awt.*;
import java.util.ArrayList;

public class Chart {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final Color PINK_LACE = new Color(255, 211, 232);
    private static final Color WINTER_SKY = new Color(245, 0, 118);
    private static final Color NYANZA_LIGHT = new Color(243, 255, 225);//todo мб цвета поменять?

    public static void drawChart(Table table, double argumentValue, double argument, boolean isTable) {
        XYChart chart = new XYChartBuilder().width(WIDTH).height(HEIGHT).title("Интерполяция функции")
                .xAxisTitle("x").yAxisTitle("F(x)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setChartBackgroundColor(PINK_LACE);
        chart.getStyler().setPlotGridLinesColor(PINK_LACE);
        ArrayList<Double> xs = table.getXs();
        ArrayList<Double> ys = table.getYs();
        XYSeries series = chart.addSeries("Введённые значения", xs, ys);
        series.setMarkerColor(WINTER_SKY);
        if (isTable) {
            series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        } else {
            series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        }
        ArrayList<Double> argumentListX = new ArrayList<>();
        ArrayList<Double> argumentListU = new ArrayList<>();
        argumentListX.add(argument);
        argumentListU.add(argumentValue);
        XYSeries argumentSeries = chart.addSeries("Результат", argumentListX, argumentListU);
        argumentSeries.setMarkerColor(Color.BLACK);
        //todo marker
        new SwingWrapper(chart).displayChart();
    }
}
