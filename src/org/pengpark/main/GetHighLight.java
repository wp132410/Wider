package org.pengpark.main;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.*;

public class GetHighLight {

    public static Map<Integer, List<String>> getWords(Elements contents) {

        Map<Integer, List<String>> highlight = null;

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

                detail.add(Keyword);
                detail.add(Urls);
                detail.add(status);

                highlight.put(i, detail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return highlight;
    }
}

