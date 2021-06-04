package ehb.group5.app.backend.utils;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Een kleine ChartBuilder om de statistieken pagina lighter te maken
 * Gebruik gemaakt van Vaadin Chart documentatie
 * https://vaadin.com/components/vaadin-charts/java-examples
 * @author Arnaud Faille
 */
public abstract class ChartUtils {


    @Accessors(chain = true)
    public static class ChartBuilder {
        @Getter
        private Chart chart;
        @Getter
        @Setter
        private String title = "Chart Title";
        @Getter
        @Setter
        private String[] categories = new String[]{"category1", "category2"};
        @Getter
        @Setter
        private String yAsTitle = "";
        @Getter
        @Setter
        private String tooltipUnit = null;

        public ChartBuilder(ChartType type) {
            chart = new Chart(type);
        }

        public Chart build() {
            // Get configuration of chart
            Configuration conf = chart.getConfiguration();
            conf.setTitle(new Title(title));

            // Creates a legend for the chart
            Legend legend = new Legend();
            legend.setLayout(LayoutDirection.VERTICAL);
            legend.setAlign(HorizontalAlign.LEFT);
            legend.setFloating(true);
            legend.setVerticalAlign(VerticalAlign.TOP);
            legend.setX(150);
            legend.setY(100);
            conf.setLegend(legend);

            // Defines xAxis categories
            XAxis xAxis = new XAxis();
            xAxis.setCategories(categories);
            conf.addxAxis(xAxis);

            // Defines yAxis title
            if (yAsTitle != null) {
                YAxis yAxis = new YAxis();
                yAxis.setTitle(new AxisTitle(yAsTitle));
                conf.addyAxis(yAxis);
            }

            // Defines tooltip information
            if (tooltipUnit != null) {
                Tooltip tooltip = new Tooltip();
                tooltip.setShared(true);
                tooltip.setValueSuffix(tooltipUnit);
                conf.setTooltip(tooltip);
            }

            PlotOptionsArea plotOptions = new PlotOptionsArea();
            conf.setPlotOptions(plotOptions);

            return chart;
        }
    }
}
