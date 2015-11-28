package org.pengpark.main;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.soap.Detail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengpark on 2015. 11. 26..
 */
public class Webtoon {
    private static Map<String, String> data;

    public static List<String> GetWebtoonList(Document doc) {
        data = new HashMap<>();
        List<String> datas;
        List<String> toonNameList = new ArrayList<>();


        doc.getElementById("pageList");

        Elements toonList = doc.select("li.lst");

        int i = 1;
        String DetailUrl;
        String toonName;
        for (Element e : toonList) {
            datas = new ArrayList<>();

            DetailUrl = "http://m.comic.naver.com" + e.select("a").attr("href");
            toonName = e.getElementsByClass("toon_name").text();

            toonNameList.add(toonName);

            data.put(toonName, DetailUrl);
            i += 1;
        }
        return toonNameList;
    }

    public static void GetWebtoon(String selectedItem) throws IOException {
        Crawling c = new Crawling();
        String FolderName = selectedItem.replace("[", "").replace("]", "");

        File dir = new File(FolderName);
        String DetailUrl = data.get(FolderName);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        Document doc = c.getData(DetailUrl);


        String epiNum = doc.select("div.lst a").attr("href").split("&")[1].replace("no", "").replace("=", "");
        int PageNum = (Integer.parseInt(epiNum) / 30) + 1;

        int i = Integer.parseInt(epiNum);
        while (i > 0) {
            String episodePath = FolderName + "/" + i + "화";

            dir = new File(episodePath);

            if(!dir.exists()) {
                dir.mkdir();
            }
            i-=1;
        }

        int page = 1;
        URL parseURL = new URL(DetailUrl);


        while (page <= PageNum) {
            String urlQuery = "/webtoon/list.nhn?" + parseURL.getQuery() + "&page=" + page;
            URL newURL = new URL(parseURL, urlQuery);

            doc = c.getData(newURL.toString());
            Elements d = doc.select("ul#pageList li");

            int number = Integer.parseInt(epiNum);

            ArrayList<Object> downloadLink;

            for (Element s : d) {
                DetailUrl = "http://m.comic.naver.com" + s.select("a").attr("href");
                downloadLink = new ArrayList<>();

                doc = c.getData(DetailUrl);

                Elements e = doc.select("div#toonLayer ul li");

                int length = e.size();

                String ImageSrc = e.select("img").attr("src").split("_")[0];


                downloadLink.add(number);
                downloadLink.add(ImageSrc);


                number -= 1;

                String downloadUrl;
                String name;


                i = 1;
                while (i < length) {
                    int first = Integer.parseInt(downloadLink.get(0).toString());
                    if (i < 10) {
                        downloadUrl = downloadLink.get(1).toString() + "_00" + i + ".jpg";
                        name = downloadLink.get(0) + "-00" + i + ".jpg";
                    } else {
                        downloadUrl = downloadLink.get(1).toString() + "_0" + i + ".jpg";
                        name = downloadLink.get(0) + "-0" + i + ".jpg";
                    }
                    String SavePath = FolderName + "/" + first + "화/";
                    System.out.println(SavePath + name);

                    imgFile.Download(downloadUrl, SavePath, name);

                    i += 1;
                }
            }
            page += 1;
        }
    }
}
