package org.pengpark.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import org.pengpark.Data.Crawling;
import org.pengpark.Data.Webtoon;


import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pengpark on 2015. 11. 28..
 */
public class controller {

    @FXML private Button btnNews;
    @FXML private Button btnWeather;
    @FXML private Button btnWebtoon;
    @FXML private TextField txtId;
    @FXML private PasswordField txtPw;
    @FXML private Label response;
    @FXML private Button goBack;
    @FXML private Button btnLogin;


    @FXML private Button btnMonday;
    @FXML private Button btnTuesday;
    @FXML private Button btnWednesday;
    @FXML private Button btnThursday;
    @FXML private Button btnFriday;
    @FXML private Button btnSaturday;
    @FXML private Button btnSunday;
    @FXML private Button btnFinday;

    @FXML private ListView<String> WebtoonList;

    private Map<Integer, String> WeekList;


    private static final String ID = "admin";
    private static final String PW = "test";

    private boolean loginFlag;

    public void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;
        if(event.getSource() == btnNews) {
            stage = (Stage)btnNews.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("News.fxml"));
        }

        else if(event.getSource() == btnWeather) {
            stage = (Stage)btnNews.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Weather.fxml"));
        }

        else if(event.getSource() == btnWebtoon) {
            stage = (Stage)btnNews.getScene().getWindow();

            System.out.println(loginFlag);
            if(!loginFlag) {
                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            }
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

//    public void setItems();

    public void handleButtonWeek(ActionEvent event) {
        Crawling c = new Crawling();

            btnMonday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=mon";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);


                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnTuesday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=tue";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnWednesday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=wed";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnThursday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=thu";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnFriday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=fri";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnSaturday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=sat";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnSunday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=sun";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            btnFinday.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String WebtoonURL = "http://m.comic.naver.com/webtoon/weekday.nhn?week=fin";

                        Document doc = c.getData(WebtoonURL);
                        List<String> webtoonList = Webtoon.GetWebtoonList(doc);

                        ObservableList<String> items = FXCollections.observableArrayList(webtoonList);
                        WebtoonList.setItems(items);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        WebtoonList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    System.out.println("Clicked");
                    String selectedItem = WebtoonList.getSelectionModel().getSelectedItems().toString();
                    try {
                        Webtoon.GetWebtoon(selectedItem);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void handleButtonGoBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)goBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleLoginAction(ActionEvent event) throws IOException {
        String id = this.txtId.getText();
        String pw = this.txtPw.getText();
        if(!id.isEmpty() && !pw.isEmpty()) {
            if(id.equals(ID) && pw.equals(PW)) {
                this.response.setText("로그인");

                Stage stage = (Stage)btnLogin.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Webtoon.fxml"));

                Scene sc = new Scene(root);
                stage.setScene(sc);
                stage.show();

            } else {
                this.response.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
            }

        } else {
            this.response.setText("로그인 정보가 입력되지 않았습니다.");
        }
    }

}
