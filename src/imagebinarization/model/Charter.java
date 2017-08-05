package imagebinarization.model;

import imagebinarization.math.Binarization;
import java.awt.Color;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class Charter {

    public static ChartPanel getHistogramChart(BufferedImage image){
        int[] histVals = Binarization.histogram(image);
        double[] values = new double[histVals.length];
        for(int i=0; i<histVals.length; i++) {
            values[i] = histVals[i];
        }
        
	HistogramDataset hd = new HistogramDataset();
	hd.setType(HistogramType.FREQUENCY);
	hd.addSeries("Histogram", values, values.length/2);
	JFreeChart chart = ChartFactory.createHistogram("", "", "", hd,
			PlotOrientation.VERTICAL, false, false, false);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(296, 246));
        chart.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF, 0)); // transparent
        return chartPanel;
    }
    
   /* private static XYDataset createDataset() {
        XYSeries series = new XYSeries("Histogram");
        final AtomicInteger count = new AtomicInteger(0);
        series.add(count.getAndAdd(1), 32);
        XYSeriesCollection data = new XYSeriesCollection(series);
        return data;
    }*/
    
}
