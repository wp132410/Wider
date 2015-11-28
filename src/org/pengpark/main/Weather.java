package org.pengpark.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pengpark on 2015. 11. 28..
 */
public class Weather {
    private String woeid;

    public static void main(String[] args) throws IOException, ParseException {
        Weather weather = new Weather();
        weather.GetWoeidNumber();
        System.out.println(weather.GetWoeidNumber());
        System.out.println(weather.getWeather());
    }

    public String GetWoeidNumber() throws IOException, ParseException {
        Crawling c = new Crawling();

        JSONObject jsonObject = (JSONObject) Geolocation.PointsToGeo().get(0);
        String keywords = jsonObject.get("formatted_address").toString().replace(",", "%20");
        String url = "http://woeid.rosselliot.co.nz/lookup/" + keywords;

        Document doc = c.getData(url);
        Elements e = doc.select("td.woeid");

        woeid = e.text();

        return woeid;
    }

    public String getWeather() throws IOException {
        String Searchurl = "http://query.yahooapis.com/v1/public/yql?" +
                "q=select%20item%20from%20weather.forecast%20" +
                "where%20woeid=" + woeid + "&format=json&u=c";

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
