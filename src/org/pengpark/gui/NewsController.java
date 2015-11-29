package org.pengpark.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jsoup.nodes.Document;
import org.pengpark.Data.Crawling;
import org.pengpark.Data.GetHighLight;
import org.pengpark.Data.News;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by pengpark on 2015. 11. 29..
 */
public class NewsController implements Initializable {

    private Map<String, List<String>> result;
    private List<String> newsResult;
    private News news;
    @FXML private ListView<String> NewsList;
    @FXML private Button goBack;
    @FXML private ListView<String> NewsTable;
    @FXML private Button goNews;

    private int length;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Crawling c = new Crawling();
        Document doc = null;
        try {
            doc = c.getData(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = GetHighLight.getWords(doc.select("ol#realrank li a"));
        length = result.size() + 1;

        ObservableList<String> items= FXCollections.observableArrayList();
        for(String keyword : result.keySet()) {
            items.add(keyword);
        }
        NewsList.setItems(items);

        setNewsList();
    }


    public void handleButtonGoBack(ActionEvent event) throws IOException {
        Stage stage = (Stage)goBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        setScenery(stage, root);

    }

    public void handleButtonGoNews(ActionEvent event) throws IOException {
        Stage stage = (Stage)goNews.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("News"));

        setScenery(stage, root);
    }

    public void setScenery(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setNewsList() {
        NewsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    String selectedItem = NewsList.getSelectionModel().getSelectedItem();

                    newsResult = result.get(selectedItem);
//                    System.out.println(newsResult);
                    news = new News(newsResult.get(0), selectedItem);
                    try {
                        setNewsTable();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void setNewsTable() throws IOException {
        // news정보 가지고 오기
        Map<Integer, List<String>> setNews = news.getNews();
        ObservableList<String> title = FXCollections.observableArrayList();

        // 데이터가 없는 뉴스 삭제
        for(int i=0; i<setNews.size(); i++) {
            if(setNews.get(i).indexOf("") == -1) {
                title.add(setNews.get(i).get(0));
            }
        }

        NewsTable.setItems(title);

        NewsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("NewsWebView.fxml"));
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
