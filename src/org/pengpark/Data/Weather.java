package org.pengpark.Data;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pengpark on 2015. 11. 28..
 */
public class Weather {
    private String woeid;


    // PointsToGeo를 통해 받은 위치 정보를
    // woeid를 리턴받는다
    // 이 것을 이용하여 Yahoo Weather API에서 해당 위치의 날씨 가져온다
    public String GetWoeidNumber() throws IOException, ParseException {
        Crawling c = new Crawling();

        JSONObject jsonObject = (JSONObject) Geolocation.PointsToGeo().get(0);
        String keywords = jsonObject.get("formatted_address").toString().replace(",", "%20");

        String url = "http://woeid.rosselliot.co.nz/lookup/";
        String removeKeyword;
        String changed;

        if (keywords.contains("(")) {
            int start = keywords.indexOf("(");
            int end = keywords.indexOf(")") + 1;

            removeKeyword = keywords.substring(start, end);
            changed = keywords.replace(removeKeyword, "");
            url += changed;
        }
        else {
            url += keywords;
        }



        Document doc = c.getData(url);
        Elements e = doc.select("td.woeid");

        woeid = e.text();

        return woeid;
    }

    // 날씨를 가져온다
    public String getWeather(String woeid) throws IOException {
        String Searchurl = "https://query.yahooapis.com/v1/public/yql?" +
                "q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%20" +
                woeid +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        URL url = new URL(Searchurl);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }
}
