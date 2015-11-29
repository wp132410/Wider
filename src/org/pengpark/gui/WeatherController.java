package org.pengpark.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.pengpark.Data.Weather;
import org.pengpark.Data.WeatherData.WeatherResources;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pengpark on 2015. 11. 29..
 */
public class WeatherController implements Initializable {

    @FXML private Button go_Back;
    @FXML private Label status;
    @FXML private Label temp;
    @FXML private Label pubData;
    @FXML private Label city;
    @FXML private Label country;
    @FXML private ImageView WeatherImage;

    private JSONArray Data;
    WeatherResources weatherResources;

    public void handleButtonGoBack() throws IOException {
        Stage stage = (Stage)go_Back.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Weather w = new Weather();
            String woeid = w.GetWoeidNumber();
            String weather = w.getWeather(woeid);

            System.out.println(weather);

//            String fa = weatherResources.getTemp();
//            System.out.println(fa);
//            int fa = Integer.parseInt(weatherResources.getTemp());
//            System.out.println(fa);
//            int ce = (int) ((fa - 32) / 1.8);


            weatherResources = new WeatherResources(weather);
            weatherResources.setQueries();

            status.setTextAlignment(TextAlignment.CENTER);
            temp.setTextAlignment(TextAlignment.CENTER);
            city.setTextAlignment(TextAlignment.CENTER);
            country.setTextAlignment(TextAlignment.CENTER);
            pubData.setTextAlignment(TextAlignment.CENTER);

            if(weatherResources.getCode().equals("11")) {
                File file = new File("resources/11.jpg");
                WeatherImage.setImage(new Image(file.toURI().toString()));
            }

            status.setText(weatherResources.getStatus());
            temp.setText(weatherResources.getTemp() + "℃");
            city.setText(weatherResources.getCity());
            country.setText(weatherResources.getCountry());
            pubData.setText(weatherResources.getPubData());

        } catch (NullPointerException e) {
            temp.setText("Retry");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
