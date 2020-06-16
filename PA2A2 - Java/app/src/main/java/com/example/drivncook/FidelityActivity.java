package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class FidelityActivity extends AppCompatActivity {

    private ListView listView;
    private List<Advantage> advantages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidelity);
        final User user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");
        AsyncTask<String, Void, String> advantage = new ApiAdvantage(user);
        try {
            JSONObject json = new JSONObject(advantage.execute().get());
            for (int i = 0; i < json.length(); i++) {
                JSONObject newjson = json.getJSONObject("Advantage" + i);
                advantages.add(new Advantage(newjson.getInt("idAdvantage"), newjson.getString("name"),
                        newjson.getString("category"), newjson.getInt("points")));
            }
        } catch (JSONException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Log.d("allaoua", "onCreate: " + e);
        }

        listView = findViewById(R.id.allFidelity);
        Log.d("allaoua", "onCreate: " + advantages);
        FidelityAdapter adapter = new FidelityAdapter(FidelityActivity.this, advantages);
        listView.setAdapter(adapter);

    }



}