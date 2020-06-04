package com.example.drivncook;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText enterLogin;
    private EditText enterPwd;
    private Button login;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isOnline()) {
            enterLogin = findViewById(R.id.enterLogin);
            enterPwd = findViewById(R.id.enterPasswd);
            login = findViewById(R.id.loginButton);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String login = enterLogin.getText().toString();
                    String pwd = enterPwd.getText().toString();
                    if (login.length() > 0) {
                        if (pwd.length() > 0) {
                            user = new User(login, pwd);
                            AsyncTask<String, Void, String> asyncTask = new ApiLogin(user);
                            try {
                                JSONObject myJson = new JSONObject(asyncTask.execute().get());
                                String token = myJson.getString("token");
                                user.setToken(token);
                                if (user.getToken() != null) {
                                    Intent it = new Intent(MainActivity.this, HomeActivity.class);
                                    it.putExtra("user", user);
                                    startActivity(it);
                                }
                            } catch (ExecutionException | InterruptedException | JSONException e) {
                                e.printStackTrace();
                                enterPwd.setError("Incorrect");
                                enterLogin.setError("Incorrect");
                            }
                        } else {
                            enterPwd.setError("Required");
                        }
                    } else {
                        enterLogin.setError("Required");
                    }
                }
            });
        } else {
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                alertDialog.show();
            } catch (Exception e) {
                Log.d("connectionBDD", "Show Dialog: " + e.getMessage());
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert conMgr != null;
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}