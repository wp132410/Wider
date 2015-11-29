package org.pengpark.Data;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengpark on 2015. 11. 25..
 */
public class News {
    private String title;
    private String url;
    private String[] news_id = new String[]{
      "div ul.type01 li#sp_nws_all1", "div ul.type01 li#sp_nws_all2", "div ul.type01 li#sp_nws_all3",
            "div ul.type01 li#sp_nws_all4", "div ul.type01 li#sp_nws_all5", "div ul.type01 li#sp_nws_all6",
            "div ul.type01 li#sp_nws_all7", "div ul.type01 li#sp_nws_all8", "div ul.type01 li#sp_nws_all9",
            "div ul.type01 li#sp_nws_all10"
    };

    private Map<Integer, String> NewsResult;


    // Highlight에서 키워드를 가져오면
    // News에서 url과 title을 받아와
    // 해당 url의 핫한 뉴스를 가져온다
    public News(String url, String title) {
        this.title = title;
        this.url = url;
    }

    public Map<Integer, List<String>> getNews() throws IOException {
        Map<Integer, List<String>> news_contents = new HashMap<>();
        List<String> news_content;

        Crawling c = new Crawling();
        Document doc = c.getData(this.url);
        doc.getElementsByClass("txt_inline").remove();
        doc.getElementsByClass("newr_more").remove();
        doc.getElementsByClass("relation_lst").remove();

        Elements[] content;
        Elements[] title;
        String[] url;

        content = new Elements[news_id.length];
        title = new Elements[news_id.length];
        url = new String[news_id.length];

        for (int i = 0; i < news_id.length; i++) {
            news_content = new ArrayList<>();

            content[i] = doc.select(news_id[i]).select("dd");
            title[i] = doc.select(news_id[i]).select("a");
            url[i] = doc.select(news_id[i]).select("a").attr("href");

                news_content.add(title[i].text());
                news_content.add(content[i].text());
                news_content.add(url[i]);


                news_contents.put(i, news_content);

        }
        return news_contents;
    }

}
