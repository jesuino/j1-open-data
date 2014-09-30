/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.campaigndonation;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class AppFX extends Application {

    final static int CHART_WIDTH = 400;
    final static int CHART_HEIGHT = 500;

    public static void main(String args[]) {
        launch();
    }

    @Override
    public void start(Stage s) throws Exception {
        Map<String, Set<Donation>> donations = CampaignDonation.load();
        HBox charts = new HBox(30);
        donations.entrySet().forEach(e -> {
            PieChart chart = new PieChart();
            chart.setPrefSize(300, 400);
            chart.setTitle(e.getKey());
            TableView<Donation> tblDonations = new TableView<>();
            TableColumn<Donation, String> clDonatorName = new TableColumn<>("Donor Name");
            TableColumn<Donation, String> clAmount = new TableColumn<>("Amount");
            clDonatorName.setCellValueFactory(new PropertyValueFactory("donorName"));
            clAmount.setCellValueFactory(new PropertyValueFactory("amount"));
            tblDonations.getColumns().addAll(clDonatorName, clAmount);
            tblDonations.setPrefSize(CHART_WIDTH, 150);
            e.getValue().stream()
                    .sorted(Comparator.comparing(Donation::getAmount).reversed())
                    .limit(7)
                    .peek(tblDonations.getItems()::add)
                    .map(d -> new PieChart.Data(d.getDonorName(), d.getAmount()))
                    .forEach(chart.getData()::add);
            charts.getChildren().add(new VBox(20, chart, tblDonations));
        });
        s.setScene(new Scene(charts));
        s.show();
    }
}
