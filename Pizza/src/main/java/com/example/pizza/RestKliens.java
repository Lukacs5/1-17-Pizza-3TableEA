package com.example.pizza;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;

public class    RestKliens {
    static HttpsURLConnection connection;
    static String token = "ac8e2a6c12dfa95683427f566063a886128d163d40b1421b5b76682b7e0b338b";
    static String url = "https://gorest.co.in/public/v2/users";
    static void seged1() {
        connection.setRequestProperty("Content-Type", "application/json");
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
        }

        connection.disconnect();
        return jsonResponseData.toString();
    }

    static RestUser[] GET() throws IOException {
        System.out.println("\nGET...");
        URL usersUrl = new URL(url);
        connection = (HttpsURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        int statusCode = connection.getResponseCode();
        System.out.println("statusCode: " + statusCode);
        String data = null;
        RestUser[] userArray = null;
        if (statusCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = in.readLine();

            Gson gson = new Gson();
            userArray = gson.fromJson(readLine, RestUser[].class);
            in.close();
        } else {
            System.out.println("Hiba!!!");
        }
        connection.disconnect();
        return userArray;

    }

    static String POST(RestUser tagok) throws IOException {
        System.out.println("\nPOST...");

        URL usersUrl = new URL(url + "?access-token=336ac269fd5abc610b4de1d779127b223cb9dbf7078eed498a82a7a48abdf140");
        connection = (HttpsURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("POST");

        seged1();
        String params = tagok.toString();
        seged2(params);

        return seged3(201);
    }

    static void PUT(RestUser tagok) throws IOException {
        System.out.println("\nPUT...");
        URL usersUrl = new URL(url);
        String url = "https://gorest.co.in/public/v2/users/" + tagok.getId();
        URL postUrl = new URL(url);
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        seged1();
        String params = tagok.toString();
        seged2(params);
        //seged3(200);
    }

    static void DELETE(String ID) throws IOException {
        System.out.println("\nDELETE...");
        String url = "https://gorest.co.in/public/v2/users/" + ID;
        URL postUrl = new URL(url);
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("DELETE");
        seged1();
        //seged3(204);
    }/**/

}
