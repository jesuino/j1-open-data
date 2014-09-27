/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.surveyexplorer;

import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class AppFX extends Application {

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane chartPane = new Pane();
        Map<String, Map<String, Integer>> results = SurveyData.load();
        ComboBox<String> cmbQuestions = new ComboBox<>(FXCollections.observableArrayList(results.keySet()));
        cmbQuestions.setPrefWidth(900);
        cmbQuestions.getSelectionModel().selectedItemProperty().addListener((t, o, n) -> {
            final BarChart<String, Number> chart = new BarChart<>(new CategoryAxis(), new NumberAxis());
            chart.setTitle(n);
            results.get(n).entrySet().forEach(r -> {
                XYChart.Series resultSeries = new XYChart.Series();
                resultSeries.getData().add(new XYChart.Data(r.getKey(), r.getValue()));
                resultSeries.setName(r.getKey());
                chart.getData().add(resultSeries);
            });
            chart.setPrefWidth(850);
            chartPane.getChildren().setAll(chart);
        });        
        stage.setScene(new Scene(new VBox(10, cmbQuestions, chartPane)));
        stage.setWidth(900);
        stage.setHeight(600);
        stage.show();
        cmbQuestions.getSelectionModel().select(0);
    }
}