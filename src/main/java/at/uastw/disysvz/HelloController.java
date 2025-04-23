package at.uastw.disysvz;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField tfnum1;

    @FXML
    private TextField tfnum2;

    @FXML
    private Label labelResult;

    @FXML
    private Button btnPlus;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfGenre;

    @FXML
    private Button btnCreate;

    @FXML
    public void initialize() {
        btnPlus.setOnAction(event -> {
            String number1Text = tfnum1.getText();
            String number2Text = tfnum2.getText();

            double num1 = Double.parseDouble(number1Text);
            double num2 = Double.parseDouble(number2Text);

            double result = num1 + num2;

            labelResult.setText("Result: " + result);
        });

        btnCreate.setOnAction(event -> {
            String idText = tfId.getText();
            String titleText = tfTitle.getText();
            String genreText = tfGenre.getText();
            int id = Integer.parseInt(idText);

            Map<String, Object> requestParameters = Map.of(
                    "id", id,
                    "title", titleText,
                    "genre", genreText
            );

            StringBuilder body = new StringBuilder();
            body.append("{");
            requestParameters.entrySet().forEach(entry -> {
                body.append("\"");
                body.append(entry.getKey());
                body.append("\": ");
                if (entry.getValue() instanceof String) {
                    body.append("\"");
                }
                body.append(entry.getValue());
                if (entry.getValue() instanceof String) {
                    body.append("\"");
                }
                body.append(",");
                body.append("\n");
            });
            body.deleteCharAt(body.lastIndexOf(","));
            body.append("}");
            System.out.println(body.toString());

            // Code to perform an HTTP request (POST)
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest createBookRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/book"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(createBookRequest, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response code" + response.statusCode());
            } catch (Exception e) {
                System.err.println("Something went wrong" + e.getMessage());
            }
        });

    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}