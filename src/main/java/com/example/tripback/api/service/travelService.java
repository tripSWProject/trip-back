package com.example.tripback.api.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@Service
public class travelService {
    public String getPath(double sx, double sy, double ex, double ey) throws IOException {
        String apiKey = "PguOVWaTC/yfQ2fsBbcUpS9Jw5x5I0T3lP+Sy8wU82I";

        String urlInfo = "https://api.odsay.com/v1/api/searchPubTransPathT?SX=" +
                sx + "&SY=" + sy + "&EX=" +
                ex + "&EY=" + ey + "&apiKey=" + URLEncoder.encode(apiKey, "UTF-8");

        System.out.println("urlInfo = " + urlInfo);
        // http 연결
        URL url = new URL(urlInfo);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("line = " + line);
            sb.append(line);
        }
        bufferedReader.close();
        conn.disconnect();

        // 결과 출력
        return sb.toString();
    }
}
