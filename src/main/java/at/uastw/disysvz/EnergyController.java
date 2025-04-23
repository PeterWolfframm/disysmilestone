package at.uastw.disysvz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnergyController {
    @FXML
    private Button btnCurrentHour;

    @FXML
    private Button btnHistoricData;

    @FXML
    private TextArea taResult;

    @FXML
    private Label lblStatus;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @FXML
    public void initialize() {
        btnCurrentHour.setOnAction(event -> fetchCurrentHourData());
        btnHistoricData.setOnAction(event -> fetchHistoricData());
    }

    private void fetchCurrentHourData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/energy/current"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                taResult.setText(response.body());
                lblStatus.setText("Current hour data retrieved successfully");
            } else {
                lblStatus.setText("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
            System.err.println("Error fetching current hour data: " + e.getMessage());
        }
    }

    private void fetchHistoricData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/energy/historic"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                taResult.setText(response.body());
                lblStatus.setText("Historic data retrieved successfully");
            } else {
                lblStatus.setText("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
            System.err.println("Error fetching historic data: " + e.getMessage());
        }
    }
} 