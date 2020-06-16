package com.example.drivncook;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;


class ApiAdvantage extends AsyncTask<String, Void, String> {
    private User user;

    public ApiAdvantage(User user) {
        this.user = user;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            String link = "https://drivncook.fr/assets/api/apiAuth/fidelity/allFidelity.php";
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
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return response.toString();
            }
            return "Invalide syntax";

        } catch (IOException e3) {
            return "Exception3: " + e3.getMessage();
        }
    }
}
