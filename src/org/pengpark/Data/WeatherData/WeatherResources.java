package org.pengpark.Data.WeatherData;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.security.krb5.internal.crypto.Des;

import java.util.HashMap;
import java.util.Map;
//import org.pengpark.Data.Weather;

/**
 * Created by pengpark on 2015. 11. 29..
 */
public class WeatherResources {
    private String City;
    private String Country;
    private String query;
    private String temp;
    private String status;
    private String pubData;
    private Map<String, String> astronomy;

    public WeatherResources(String query) {
        this.query = query;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getTemp() {
        return temp;
    }

    public String getStatus() {
        return status;
    }

    public String getPubData() {
        return pubData;
    }

    public Map<String, String> getAstronomy() {
        return astronomy;
    }

    public void setQueries() throws ParseException {
        astronomy = new HashMap<>();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(query);

        JSONObject Query = (JSONObject)jsonObject.get("query");
        JSONObject Results = (JSONObject)Query.get("results");
        JSONObject channel = (JSONObject)Results.get("channel");
        JSONObject location = (JSONObject)channel.get("location");
        JSONObject items = (JSONObject)channel.get("item");
        JSONObject condition = (JSONObject)items.get("condition");
        JSONObject astronomy = (JSONObject)channel.get("astronomy");


        this.City = location.get("city").toString();
        this.Country = location.get("country").toString();

        this.temp = condition.get("temp").toString();
        this.status = condition.get("text").toString();

        this.pubData = condition.get("date").toString();

        this.astronomy.put("sunrise", astronomy.get("sunrise").toString());
        this.astronomy.put("sunset", astronomy.get("sunset").toString());
    }

}
