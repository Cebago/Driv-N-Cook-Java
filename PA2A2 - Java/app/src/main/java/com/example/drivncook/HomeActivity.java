package com.example.drivncook;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {
    private TextView idFidelity;
    private TextView points;
    private Button subscribe;
    private Button details;
    private TextView fidelityID;
    private TextView number;
    private TextView message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final User user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        idFidelity = findViewById(R.id.idFidelity);
        points = findViewById(R.id.fidelityPoints);
        subscribe = findViewById(R.id.subscribe);
        details = findViewById(R.id.fidelityDetails);
        fidelityID = findViewById(R.id.fidelityID);
        number = findViewById(R.id.numberPoints);
        message = findViewById(R.id.message);

        message.setVisibility(View.GONE);
        details.setVisibility(View.GONE);
        subscribe.setVisibility(View.GONE);
        assert user != null;
        AsyncTask<String, Void, String> getFidelity = new ApiFidelity(user);
        try {
            JSONObject myJson = new JSONObject(getFidelity.execute().get());
            Log.d("jsonToMe", "onCreate: " + myJson);
            JSONObject card = myJson.getJSONObject("card");
            Fidelity fidelity = new Fidelity(card.getInt("idFidelity"), card.getInt("points"));
            user.setFidelity(fidelity);
            idFidelity.setText( "" + fidelity.getIdFidelity());
            points.setText("" + fidelity.getPoints());
            details.setVisibility(View.VISIBLE);
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
            message.setVisibility(View.VISIBLE);
            message.setText(getString(R.string.noFidelity));
            fidelityID.setVisibility(View.GONE);
            idFidelity.setVisibility(View.GONE);
            number.setVisibility(View.GONE);
            points.setVisibility(View.GONE);
            subscribe.setVisibility(View.VISIBLE);
        }

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<String, Void, String> subscribeFidelity = new ApiSubscribe(user);
                try {
                    String response = subscribeFidelity.execute().get();
                    if (response.equals("OK")) {
                        message.setVisibility(View.GONE);
                        fidelityID.setVisibility(View.VISIBLE);
                        number.setVisibility(View.VISIBLE);
                        subscribe.setVisibility(View.GONE);
                        message.setText(getString(R.string.congrat));
                        AsyncTask<String, Void, String> ApiFidelity = new ApiFidelity(user);
                        JSONObject json = new JSONObject(ApiFidelity.execute().get());
                        JSONObject card = json.getJSONObject("card");
                        Fidelity fidelity = new Fidelity(card.getInt("idFidelity"), card.getInt("points"));
                        idFidelity.setVisibility(View.VISIBLE);
                        idFidelity.setText( "" + fidelity.getIdFidelity());
                        points.setVisibility(View.VISIBLE);
                        points.setText("" + fidelity.getPoints());
                        details.setVisibility(View.VISIBLE);
                    } else {
                        fidelityID.setVisibility(View.GONE);
                        number.setVisibility(View.GONE);
                        idFidelity.setText(getString(R.string.incorrect));
                    }
                } catch (ExecutionException | InterruptedException | JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, FidelityActivity.class);
                it.putExtra("user", user);
                startActivity(it);
            }
        });
    }
}
