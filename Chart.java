import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * A chart in which lines connect a series of data points. Useful for viewing
 * data trends over time.
 *
 * @see javafx.scene.chart.LineChart
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.NumberAxis
 * @related charts/area/AreaChart
 * @related charts/scatter/ScatterChart
 */
public class Chart extends Application {
    ArrayList<Double> sup_min;
    ArrayList<Double> time_a;
    ArrayList<Double> time_f;
    private void init(Stage primaryStage) throws Exception {

        Apriorii q3 = new Apriorii();
        q3.weka_Apriori();
        sup_min= new ArrayList<Double>();
        time_a = new ArrayList<Double>();
        sup_min =q3.sup_min_apriori;
        time_a= q3.time_apriori;
        FpGrowth fpGrowth = new FpGrowth();
        fpGrowth.weka_fp();
        time_f = fpGrowth.time_fp;

        Group root = new Group();
        primaryStage.setScene(new Scene(root));

        NumberAxis xAxis = new NumberAxis("sup_min", 0.05, 0.15, 0.01);
        NumberAxis yAxis = new NumberAxis("time", 0, 3, 0.1);
        ObservableList<XYChart.Series<Double,Double>> lineChartData = FXCollections.observableArrayList(
                new LineChart.Series<Double,Double>("Apriori", FXCollections.observableArrayList(
                        new XYChart.Data<Double,Double>(sup_min.get(0), time_a.get(0)),
                        new XYChart.Data<Double,Double>(sup_min.get(1), time_a.get(1)),
                        new XYChart.Data<Double,Double>(sup_min.get(2), time_a.get(2)),
                        new XYChart.Data<Double,Double>(sup_min.get(3), time_a.get(3)),
                        new XYChart.Data<Double,Double>(sup_min.get(4), time_a.get(4)),
                        new XYChart.Data<Double,Double>(sup_min.get(5), time_a.get(5)),
                        new XYChart.Data<Double,Double>(sup_min.get(6), time_a.get(6)),
                        new XYChart.Data<Double,Double>(sup_min.get(7), time_a.get(7)),
                        new XYChart.Data<Double,Double>(sup_min.get(8), time_a.get(8)),
                        new XYChart.Data<Double,Double>(sup_min.get(9), time_a.get(9)),
                        new XYChart.Data<Double,Double>(sup_min.get(10), time_a.get(10))

                )),
                new LineChart.Series<Double,Double>("FPGrowth", FXCollections.observableArrayList(
                        new XYChart.Data<Double,Double>(sup_min.get(0), time_f.get(0)),
                        new XYChart.Data<Double,Double>(sup_min.get(1), time_f.get(1)),
                        new XYChart.Data<Double,Double>(sup_min.get(2), time_f.get(2)),
                        new XYChart.Data<Double,Double>(sup_min.get(3), time_f.get(3)),
                        new XYChart.Data<Double,Double>(sup_min.get(4), time_f.get(4)),
                        new XYChart.Data<Double,Double>(sup_min.get(5), time_f.get(5)),
                        new XYChart.Data<Double,Double>(sup_min.get(6), time_f.get(6)),
                        new XYChart.Data<Double,Double>(sup_min.get(7), time_f.get(7)),
                        new XYChart.Data<Double,Double>(sup_min.get(8), time_f.get(8)),
                        new XYChart.Data<Double,Double>(sup_min.get(9), time_f.get(9)),
                        new XYChart.Data<Double,Double>(sup_min.get(10), time_f.get(10))
                ))
        );
        LineChart chart = new LineChart(xAxis, yAxis, lineChartData);
        root.getChildren().add(chart);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX 
     * application. main() serves only as fallback in case the 
     * application can not be launched through deployment artifacts,
     * e.g., in IDEs with limited FX support. NetBeans ignores main().
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}