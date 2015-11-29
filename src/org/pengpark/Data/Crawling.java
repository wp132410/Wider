package org.pengpark.Data;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Crawling {
    private String URL;

    private static final String InitialURL = "http://www.naver.com";
    private static final String GeoLocationURL = "https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyA_8lMn1QcHShOb8O6I-AS0IccuPRo4o7o";

    public Crawling() {
        this.URL = InitialURL;
    }

    public Crawling(String URL) {
        this.URL = URL;
    }

    public static String getGeoLocationURL() {
        return GeoLocationURL;
    }

    // Mac주소와 기본적인 AP정보를 가지고 GMap Geolocation API를 사용하기 위해 추가 정보 설정

    public List<Map<String, Object>> setGeoLocation() throws SocketException, UnknownHostException {
            String macAddress = getMacAddress();

            List<Map<String, Object>> wifipoints = new ArrayList<>();
            List<Map<String, Object>> wifiAccessPoints = new ArrayList<>();

            Map<String, Object> WifiAP = new HashMap<>();
            WifiAP.put("macAddress", macAddress);

            Map<String, Object> Wifi = new HashMap<>();
            Wifi.put("homeMobileCountryCode", 450);
            Wifi.put("homeMobileNetworkCode", 05);
            Wifi.put("radioType", "wcdma");
            Wifi.put("carrier", "SKTelecom");
            Wifi.put("wifiAccessPoints", wifiAccessPoints);

            wifiAccessPoints.add(WifiAP);

            wifipoints.add(Wifi);

            return wifipoints;
    }

    // 사용자 AP의 MAC주소를 가져온다

    public String getMacAddress() throws UnknownHostException, SocketException {
        InetAddress ia = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ia);

        byte[] mac = network.getHardwareAddress();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
        }

        return sb.toString();
    }

    // URL을 통해 웹사이트 문서를 긁어온다

    public Document getData(String url) throws IOException {
        if (url == null) {
            url = InitialURL;
        }
        Connection conn = Jsoup.connect(url);
        conn.header("Accept-Encoding", "gzip, deflate");
        conn.userAgent(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/601.2.7 (KHTML, like Gecko) Version/9.0.1 Safari/601.2.7");
        conn.referrer("http://www.naver.com");
        conn.timeout(600000);

        return conn.get();
    }

}
