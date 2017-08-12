package imagebinarization.model;

import imagebinarization.math.Binarization;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Charter {

    public static ChartPanel getHistogramChart(BufferedImage image) {
        int[] histVals = Binarization.histogram(image);
        final XYSeries series = new XYSeries("Histogram");
        int v = 0;
        while(v < histVals.length){
            series.add(v, histVals[v]);
            v++;
        }
        
        final XYSeriesCollection data = new XYSeriesCollection(series);
        final JFreeChart chart = ChartFactory.createXYBarChart(
                "Histogram",
                "",
                false,
                "",
                data,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(296, 246));
        chart.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF, 0)); // transparent
        return chartPanel;
    }

}
