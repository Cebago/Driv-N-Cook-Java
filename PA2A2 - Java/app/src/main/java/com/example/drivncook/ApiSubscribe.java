package com.example.drivncook;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;


class ApiSubscribe extends AsyncTask<String, Void, String> {
    private User user;

    public ApiSubscribe(User user) {
        this.user = user;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String link = "https://drivncook.fr/assets/api/apiAuth/user/subscribeFidelity.php";
            String data = "{\"token\":" + "\"" + user.getToken() + "\"" + "}";
            HttpsURLConnection connection;

            URL url = new URL(link);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (IOException e) {
                return "Exception1: " + e.getMessage();
            }
            final int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return "OK";
            }
            return "Invalide syntax";

        } catch (IOException e3) {
            return "Exception3: " + e3.getMessage();
        }
    }
}
