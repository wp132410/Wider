package org.pengpark.Data;


import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pengpark on 2015. 11. 26..
 */
public class Geolocation {

    // GEO정보를 가져오기 위해 Http 통신을 통해 데이터를 response 받음

    public static String Geo () throws IOException {
            Crawling crawling = new Crawling();
            List<Map<String, Object>> wifipoints = crawling.setGeoLocation();

            JSONArray jsonArray = new JSONArray();
            jsonArray.addAll(wifipoints);

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(Crawling.getGeoLocationURL());

            StringEntity stringEntity = null;

            stringEntity = new StringEntity(jsonArray.toString(), "utf-8");
            stringEntity.setContentType("application/json");

            httpPost.setEntity(stringEntity);

            HttpResponse response;

            response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            String responseData = EntityUtils.toString(resEntity);

            return responseData;
    }

        // 야후 woeid를 이용하기 위해 좌표값을 장소로 리턴받음
        public static JSONArray PointsToGeo() throws IOException, ParseException {
                // 위도 경도 좌표
                String Points = Geo().replace("{", "").replace("}", "")
                        .replace(":", "")
                        .replace("\n", "")
                        .replace(" ", "")
                        .replace("\"", "")
                        .replace("location", "")
                        .replace("lat", "")
                        .replace("lng", "")
                        .replace("accuracy", "");

                String lat = Points.split(",")[0];
                String lng = Points.split(",")[1];

                String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
                        lat + "," + lng +
                        "&key=AIzaSyA_8lMn1QcHShOb8O6I-AS0IccuPRo4o7o";

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);

                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity resEntity = response.getEntity();

                String Data = EntityUtils.toString(resEntity);

                String newData = Data.replace("\n", "").replace(" ", "");

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(newData);

                return (JSONArray) jsonObject.get("results");
        }
}
