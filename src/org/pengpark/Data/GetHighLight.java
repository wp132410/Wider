package org.pengpark.Data;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.*;

public class GetHighLight {

    // 네이버 실시간 검색어 가져오기
    // 1 ~ 10위의 검색어르 가져와 Map형식으로 Key값으로 검색어 Value값으로 검색어 세부 정보를 리턴
    public static Map<String, List<String>> getWords(Elements contents) {

        Map<String, List<String>> highlight = null;

        try {
            String Keyword;
            String Urls;
            String status;

            highlight = new HashMap<>();
            List<String> detail;

            int i = 0;
            for (Element e : contents) {
                detail = new ArrayList<>();

                i += 1;

                if (i == 11) break;

                Keyword = e.attr("title");
                Urls = e.attr("href");
                status = e.select("span").text();

                detail.add(Urls);
                detail.add(status);

                highlight.put(Keyword, detail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return highlight;
    }
}

