package com.example.pizza;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
public class AzureRestKliens {

    static HttpURLConnection connection;
    static void seged1() {
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }


    static void seged2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        System.out.println(connection.getResponseMessage());
        connection.connect();
    }
    static String seged3(int code) throws IOException {
        int statusCode = connection.getResponseCode();
        System.out.println("statusCode: " + statusCode);
        StringBuffer jsonResponseData = null;
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonResponseData = new StringBuffer();
            String readLine = null;

            while((readLine = in.readLine()) != null) {
                jsonResponseData.append(readLine);
            }

            in.close();
        } else {
            System.out.println("Hiba!!!");
            return "Hiba!";
        }

        connection.disconnect();
        System.out.println(jsonResponseData.toString());
        return jsonResponseData.toString();
    }
    static pizza[] GET(String url) throws IOException {
        System.out.println("\nGET...");
        URL usersUrl = new URL(url);
        connection = (HttpURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("GET");
        int statusCode = connection.getResponseCode();
        System.out.println("statusCode: " + statusCode);
        String data = null;
        pizza[] pizzaArray = null;
        if (statusCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = in.readLine();
            Gson gson = new Gson();
            pizzaArray = gson.fromJson(readLine, pizza[].class);
            in.close();
        } else {
            System.out.println("Hiba!!!");
        }
        connection.disconnect();
        return pizzaArray;
    }
    static String POST(pizza tagok,String url) throws IOException {
        System.out.println("\nPOST...");

        URL usersUrl = new URL(url);
        connection = (HttpURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("POST");

        seged1();
        String params = tagok.toString();
        seged2(params);

        return seged3(200);
    }
    static String DELETE(String url) throws IOException {
        System.out.println("\nDELETE...");
        URL postUrl = new URL(url);
        connection = (HttpURLConnection) postUrl.openConnection();
        connection.setRequestMethod("DELETE");
        seged1();
        return seged3(204);
    }

}
