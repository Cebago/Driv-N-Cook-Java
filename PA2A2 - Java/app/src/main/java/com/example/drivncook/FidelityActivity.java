package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class FidelityActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidelity);
        final User user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        AsyncTask<String, Void, String> advantage = new ApiAdvantage(user);
        try {
            JSONObject json = new JSONObject(advantage.execute().get());
            Log.d("allaoua", "onCreate: "+json);
        } catch (JSONException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        listView = findViewById(R.id.allFidelity);
        //FidelityAdapter adapter = new FidelityAdapter(FidelityActivity.this, getData());

    }



}