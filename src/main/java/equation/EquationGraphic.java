package equation;

import equation.linearSquareMethod.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import java.util.Arrays;

import static java.lang.Math.*;

public class EquationGraphic {

    void drawPoints(XYSeriesCollection xyDataset, double[] x, double[] y) {
        XYSeries series = new XYSeries("y");
        for (int i = 0; i < x.length; i += 1) series.add(x[i], y[i]);
        xyDataset.addSeries(series);
    }

    public void drawGraphic(int chosenFunc, double[] x, double[] y) {
        String name = "phi(x)";
        XYSeries series = new XYSeries(name);
        XYSeries abscissa = new XYSeries("x = 0");
        double h = abs((x[x.length - 1] - x[0]) * 0.2);
        IFunc func = switch (chosenFunc) {
            case 1 -> new LinearFunc();
            case 2 -> new SquareFunc();
            case 3 -> new CubeFunc();
            case 4 -> new PowFunc();
            case 5 -> new ExpFunc();
            case 6 -> new LnFunc();
            default -> null;
        };

        for (double xx = Arrays.stream(x).min().getAsDouble() - h; xx < Arrays.stream(x).max().getAsDouble() + h; xx += 0.001)
            series.add(xx, func != null ? func.f(xx, x, y) : 0);
        for (double xx = Arrays.stream(x).min().getAsDouble() - h; xx < Arrays.stream(x).max().getAsDouble() + h; xx += 0.001)
            abscissa.add(xx, 0);


        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(series);
        xyDataset.addSeries(abscissa);
        drawPoints(xyDataset, x, y);
        JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);
        JFrame frame = new JFrame("График функции");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(500, 500);
        frame.show();
    }


}

