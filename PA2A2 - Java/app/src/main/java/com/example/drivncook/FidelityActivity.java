package com.example.drivncook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class FidelityActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidelity);

        listView = findViewById(R.id.allFidelity);
        FidelityAdapter adapter = new FidelityAdapter(MainActivity.this, getData());

    }



}