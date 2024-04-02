package algorithm;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.ImageTitle;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
public class Graphics {

    public void chart(BigDecimal a, BigDecimal b, int functionNumber) {
        JFrame frame = new JFrame("Уравнение");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.setLayout(layout);

        ChartPanel chart = generateChart(a, b, functionNumber);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(chart)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(chart)
        );

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void chartEquations(BigDecimal a, BigDecimal b, int functionNumber) {
        JFrame frame = new JFrame("Уравнения");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.setLayout(layout);

        ChartPanel chart = generateChartEquations(a, b, functionNumber);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(chart)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(chart)
        );

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public ChartPanel generateChart(BigDecimal a, BigDecimal b, int functionNumber) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Функция", generateData(a.doubleValue(), b.doubleValue(), functionNumber));

        JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

        //ImageIcon image = new ImageIcon(equation.getImage());
        //chart.addSubtitle(new ImageTitle(image.getImage()));

        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainZeroBaselineVisible(true);

        NumberAxis xAxis = new NumberAxis("x");
        NumberAxis yAxis = new NumberAxis("f(x)");

        xAxis.setRange(a.doubleValue(), b.doubleValue());
        yAxis.setRange(-10, 10);

        chart.getXYPlot().setDomainAxis(xAxis);
        chart.getXYPlot().setRangeAxis(yAxis);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 700));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        return chartPanel;
    }

    public ChartPanel generateChartEquations(BigDecimal a, BigDecimal b, int functionNumber) {
        if (functionNumber == 1) {
            DefaultXYDataset dataset = new DefaultXYDataset();
            dataset.addSeries("Функция", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber));
            dataset.addSeries("Функция 2", generateDataEquations(2 * a.doubleValue(), 2 * b.doubleValue(), functionNumber + 1));
            JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

            //ImageIcon image = new ImageIcon(equation.getImage());
            //chart.addSubtitle(new ImageTitle(image.getImage()));

            chart.getXYPlot().setRangeZeroBaselineVisible(true);
            chart.getXYPlot().setDomainZeroBaselineVisible(true);

            NumberAxis xAxis = new NumberAxis("x");
            NumberAxis yAxis = new NumberAxis("f(x)");

            xAxis.setRange(a.doubleValue(), b.doubleValue());
            yAxis.setRange(a.doubleValue(), b.doubleValue());

            chart.getXYPlot().setDomainAxis(xAxis);
            chart.getXYPlot().setRangeAxis(yAxis);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(1000, 700));
            chartPanel.setMouseZoomable(true);
            chartPanel.setMouseWheelEnabled(true);

            return chartPanel;
        }
        else{
            DefaultXYDataset dataset = new DefaultXYDataset();
            dataset.addSeries("Функция", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber));
            dataset.addSeries("Функция 2 (первый полукруг)", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber + 1));
            dataset.addSeries("Функция 2 (второй полукруг)", generateDataEquations(a.doubleValue(), b.doubleValue(), functionNumber + 2));
            JFreeChart chart = ChartFactory.createXYLineChart("", "x", "y", dataset, PlotOrientation.VERTICAL, true, false, false);

            //ImageIcon image = new ImageIcon(equation.getImage());
            //chart.addSubtitle(new ImageTitle(image.getImage()));

            chart.getXYPlot().setRangeZeroBaselineVisible(true);
            chart.getXYPlot().setDomainZeroBaselineVisible(true);

            NumberAxis xAxis = new NumberAxis("x");
            NumberAxis yAxis = new NumberAxis("f(x)");

            xAxis.setRange(a.doubleValue(), b.doubleValue());
            yAxis.setRange(a.doubleValue(), b.doubleValue());

            chart.getXYPlot().setDomainAxis(xAxis);
            chart.getXYPlot().setRangeAxis(yAxis);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(1000, 700));
            chartPanel.setMouseZoomable(true);
            chartPanel.setMouseWheelEnabled(true);

            return chartPanel;
        }
    }

    private double[][] generateData(double a, double b, int functionNumber) {
        java.util.List<Double> x = new ArrayList<>();
        java.util.List<Double> y = new ArrayList<>();

        for (int i = 0; a < b; i++) {
            x.add(a);
            BigDecimal d = Functions.function(new BigDecimal(a), functionNumber);
            y.add(d.doubleValue());

            double f1 = Math.abs(Functions.function(new BigDecimal(a), functionNumber).doubleValue());
            double f2 = Math.abs(Functions.function(new BigDecimal(a + 0.01), functionNumber).doubleValue());
            if (f1 < 1 && f1 > 0.0001) {
                a += 0.01 * f1;
            }
            else if (f2 < 1) {
                a += Math.max(0.000001, 0.0001 * f2);
            }
            else {
                a += 0.005;
            }
        }
        x.add(b);
        BigDecimal d = Functions.function(new BigDecimal(b), functionNumber);
        y.add(d.doubleValue());

        double[][] data = new double[2][x.size()];
        for (int i = 0; i < x.size(); i++) {
            data[0][i] = x.get(i);
            data[1][i] = y.get(i);
        }
        return data;
    }

    private double[][] generateDataEquations(double a, double b, int functionNumber) {
        if (functionNumber == 1) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                x.add(a);
                BigDecimal d = Functions.e1_y(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e1_y(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e1_y(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e1_y(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 2) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                y.add(a);
                BigDecimal d = Functions.e2_x(new BigDecimal(a));
                x.add(d.doubleValue());

                double f1 = Math.abs(Functions.e2_x(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e2_x(new BigDecimal(a)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            y.add(b);
            BigDecimal d = Functions.e2_x(new BigDecimal(b));
            x.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 3) {
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();

            for (int i = 0; a < b; i++) {
                x.add(a);
                BigDecimal d = Functions.e3_y(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e3_y(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e3_y(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e3_y(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        if (functionNumber == 4){
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();
            a = -1.414;
            b = 1.403;
            for (int i = 0; a < b; i++) {
                //System.out.println(a);
                x.add(a);
                BigDecimal d = Functions.e4_y1(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e4_y1(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e4_y1(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e4_y1(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }
        else{
            java.util.List<Double> x = new ArrayList<>();
            java.util.List<Double> y = new ArrayList<>();
            a = -1.414;
            b = 1.403;
            for (int i = 0; a < b; i++) {
                //System.out.println(a);
                x.add(a);
                BigDecimal d = Functions.e4_y2(new BigDecimal(a));
                y.add(d.doubleValue());

                double f1 = Math.abs(Functions.e4_y2(new BigDecimal(a)).doubleValue());
                double f2 = Math.abs(Functions.e4_y2(new BigDecimal(a + 0.01)).doubleValue());
                if (f1 < 1 && f1 > 0.0001) {
                    a += 0.01 * f1;
                } else if (f2 < 1) {
                    a += Math.max(0.000001, 0.0001 * f2);
                } else {
                    a += 0.005;
                }
            }
            x.add(b);
            BigDecimal d = Functions.e4_y2(new BigDecimal(a));
            y.add(d.doubleValue());

            double[][] data = new double[2][x.size()];
            for (int i = 0; i < x.size(); i++) {
                data[0][i] = x.get(i);
                data[1][i] = y.get(i);
            }
            return data;
        }

    }



}
