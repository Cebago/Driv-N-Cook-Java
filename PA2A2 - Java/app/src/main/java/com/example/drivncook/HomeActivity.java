package com.example.drivncook;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    private TextView login;
    private TextView pwd;
    private TextView token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = (User) Objects.requireNonNull(getIntent().getExtras()).getSerializable("user");

        login = findViewById(R.id.loginDisplay);
        pwd = findViewById(R.id.pwdDisplay);
        token = findViewById(R.id.tokenDisplay);
        assert user != null;
        login.setText(user.getEmail());
        pwd.setText(user.getPwd());
        token.setText(user.getToken());

    }
}
